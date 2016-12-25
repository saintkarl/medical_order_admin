package com.karlchu.mo.web.servlet.captcha;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.SimpleTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.ImageCaptcha;
import com.octo.captcha.image.ImageCaptchaFactory;
import com.octo.captcha.image.gimpy.GimpyFactory;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class CMSCaptchaService extends ListImageCaptchaEngine {

    private static final Integer MIN_WORD_LENGTH = new Integer(5);

    private static final Integer MAX_WORD_LENGTH = new Integer(5);

    private static final Integer IMAGE_WIDTH     = new Integer(90);

    private static final Integer IMAGE_HEIGHT    = new Integer(32);

    private static final Integer MIN_FONT_SIZE   = new Integer(20);

    private static final Integer MAX_FONT_SIZE   = new Integer(20);

    private static final String NUMERIC_CHARS    = "123456789";// No numeric 0

    private static final String UPPER_ASCII_CHARS= "ABCDEFGHJKLMNPQRSTUVWXYZ";// No upper O I

    // we don't use the lower characters because it cause difficult in some case, so that
    // we must always UPPERCASE the input from user (currently in OnlineUserImpl)
    //private static final String LOWER_ASCII_CHARS= "abcdefghjklmnpqrstuvwxyz";// No lower o i

    /**
     * Singleton instance of this class
     */
    private static CMSCaptchaService instance = new CMSCaptchaService();

    private List textPasterList;

    private List backgroundGeneratorList;

    private List fontGeneratorList;

    private ImageCaptcha imageCaptcha = null;
    /**
     * Private constructor to prevent instantiation
     */
    private CMSCaptchaService() {
    }

    public static CMSCaptchaService getInstance() {
        return instance;
    }

    protected void buildInitialFactories() {
        textPasterList = new ArrayList();
        backgroundGeneratorList = new ArrayList();
        fontGeneratorList = new ArrayList();

        textPasterList.add(new SimpleTextPaster(MIN_WORD_LENGTH, MAX_WORD_LENGTH, Color.black));
        textPasterList.add(new RandomTextPaster(MIN_WORD_LENGTH, MAX_WORD_LENGTH, Color.black));
        textPasterList.add(new SimpleTextPaster(MIN_WORD_LENGTH, MAX_WORD_LENGTH, Color.red));
        textPasterList.add(new RandomTextPaster(MIN_WORD_LENGTH, MAX_WORD_LENGTH, Color.red));
        textPasterList.add(new SimpleTextPaster(MIN_WORD_LENGTH, MAX_WORD_LENGTH, Color.blue));
        textPasterList.add(new RandomTextPaster(MIN_WORD_LENGTH, MAX_WORD_LENGTH, Color.blue));
        
        backgroundGeneratorList.add(new UniColorBackgroundGeneratorExtend(IMAGE_WIDTH, IMAGE_HEIGHT));
      
        Font[] fontsList = new Font[] {
                new Font("Verdana", Font.PLAIN, 10)
             };

        fontGeneratorList.add(new RandomFontGenerator(MIN_FONT_SIZE, MAX_FONT_SIZE, fontsList));

        // no char upper O, char lower o and numeric 0 because user cannot answer
        WordGenerator words = new RandomWordGenerator(NUMERIC_CHARS + UPPER_ASCII_CHARS);

        for (Iterator fontIter = fontGeneratorList.iterator(); fontIter.hasNext(); ) {
            FontGenerator font = (FontGenerator)fontIter.next();
            for (Iterator backIter = backgroundGeneratorList.iterator(); backIter.hasNext(); ) {
                BackgroundGenerator back = (BackgroundGenerator)backIter.next();
                for (Iterator textIter = textPasterList.iterator(); textIter.hasNext(); ) {
                    TextPaster parser = (TextPaster)textIter.next();

                    WordToImage word2image = new ComposedWordToImage(font, back, parser);
                    ImageCaptchaFactory factory = new GimpyFactory(words, word2image);
                    addFactory(factory);
                }
            }
        }
    }

    /**
     * Write the captcha image of current user to the servlet response
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     */
    public void writeCaptchaImage(HttpServletRequest request, HttpServletResponse response)
        throws IOException{
    	imageCaptcha = getNextImageCaptcha();
    	BufferedImage image = (BufferedImage)imageCaptcha.getChallenge();
        if (image != null) {
	        OutputStream outputStream = null;
	        try {
	            response.setHeader("Cache-Control", "no-store, no-cache"); // HTTP 1.1
	            response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	            response.setDateHeader("Expires", 0);
	            response.setContentType("image/jpeg");
	
	            outputStream = response.getOutputStream();
	
	            ImageIO.write(image, "jpeg", outputStream);
	
	            outputStream.flush();
	        } finally {
	            outputStream.close();
	        }
        }
    }
    
    /**
     * Validate the anwser of the captcha from user
     *
     * @param anwser String the captcha anwser from user
     * @return boolean true if the answer is valid, otherwise return false
     */
    public boolean validateCaptchaResponse(String anwser) {
        if (imageCaptcha == null) {
            return false;
        }
        anwser = anwser.toUpperCase();//use upper case for easier usage
        boolean result = (imageCaptcha.validateResponse(anwser)).booleanValue();
        return result;
    }
    
    class UniColorBackgroundGeneratorExtend extends UniColorBackgroundGenerator {
    	
    	public UniColorBackgroundGeneratorExtend(int width, int height) {
    		super(width, height);
    	}
    	@Override
    	public BufferedImage getBackground() {
    		BufferedImage image = new BufferedImage(this.getImageWidth(), this.getImageHeight(), BufferedImage.TYPE_INT_RGB); 
		    Graphics2D g2 = image.createGraphics();
		    
		    g2.setBackground(Color.white);
		    g2.fillRect(0, 0, getImageWidth(), getImageHeight());
		    
		    g2.setColor(Color.gray);
		    int rows = this.getImageHeight() / 7 ;
		    int columns = this.getImageWidth() / 7;
		    int htOfRow = this.getImageHeight() / rows;
		    for (int k = 0; k < rows; k++) {
		      g2.drawLine(0, k * htOfRow , this.getImageWidth(), k * htOfRow );
		    }

		    int wdOfRow = this.getImageWidth() / (columns);
		    for (int k = 0; k < columns; k++) {
		      g2.drawLine(k*wdOfRow , 0, k*wdOfRow , this.getImageHeight());
		    }
		    
		    g2.setColor(Color.red);
		    g2.drawRect(0, 0, this.getImageWidth() - 1, this.getImageHeight() - 1);
		    g2.dispose();

    		return image;
    	}
    }

}
