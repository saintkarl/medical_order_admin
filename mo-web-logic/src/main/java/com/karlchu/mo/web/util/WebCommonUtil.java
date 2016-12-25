package com.karlchu.mo.web.util;

import com.karlchu.mo.common.Constants;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: KhanhTran
 * Date: 8/26/15
 * Time: 1:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebCommonUtil {
    private static transient final Logger log = Logger.getLogger(WebCommonUtil.class);

    private static final Pattern EMAIL_P = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

    private static final Pattern ZIP_P = Pattern.compile("\\d{5}(-\\d{4})?");

    private static final Pattern USERNAME_P = Pattern.compile("^[A-Za-z0-9_-]{3,25}");


    public static boolean isValidEmail(String email) {
        Matcher m = EMAIL_P.matcher(email);
        return m.matches();
    }

    public static boolean isValidUsername(String username) {
        Matcher m = USERNAME_P.matcher(username);
        return m.matches();
    }

    public static boolean isValidZip(String zipFile) {
        Matcher m = ZIP_P.matcher(zipFile);
        return m.matches();
    }

    public static boolean isValidImage(String path){
        if(path.toLowerCase().endsWith(".jpg") || path.toLowerCase().endsWith(".png") || path.toLowerCase().endsWith(".gif") || path.toLowerCase().endsWith(".jpeg")){
            return true;
        }
        return false;
    }

    /*
      * get extension of file
      */
    public static String getExtension(String fileName) {
        return (fileName.indexOf(".") < fileName.length()) ? fileName
                .substring(fileName.lastIndexOf(".") + 1) : "";
    }

    /*
      * get fileName without extension
      */
    public static String getNameWithoutExtension(String fileName) {
        return (fileName.indexOf(".") > 0) ? fileName.substring(0, fileName
                .lastIndexOf(".")) : fileName;
    }

    public static  String countDateToCurrentDate(Timestamp time){
        Date current = new Date();
        long diff = current.getTime() - time.getTime();

        String hrDateText = DurationFormatUtils.formatDuration(diff, "d 'day' H 'hour' ago");
        return hrDateText;
    }

    public static void addRedirectMsg(ModelAndView mav, String crudaction, Map<String, String> messageMap){
        if(Constants.REDIRECT_UPDATE.equals(crudaction)){
            mav.addObject(Constants.ALTER, Constants.TYPE_SUCCESS);
            mav.addObject(Constants.MESSAGE_RESPONSE, messageMap.get(Constants.REDIRECT_UPDATE));
        }else if(Constants.REDIRECT_INSERT.equals(crudaction)){
            mav.addObject(Constants.ALTER, Constants.TYPE_SUCCESS);
            mav.addObject(Constants.MESSAGE_RESPONSE, messageMap.get(Constants.REDIRECT_INSERT));
        }else if(Constants.REDIRECT_ERROR.equals(crudaction)){
            mav.addObject(Constants.ALTER, Constants.TYPE_DANGER);
            mav.addObject(Constants.MESSAGE_RESPONSE, messageMap.get(Constants.REDIRECT_ERROR));
        }
    }

}
