package com.karlchu.mo.web.taglibs;

import org.apache.commons.lang.StringUtils;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) by Ban Vien Co., Ltd.
 * User: MBP
 * Date: 4/11/13
 * Time: 10:08 AM
 * Author: vien.nguyen@banvien.com
 */
public class PortalTaglibs {
    /**
     * Encode a URL parameter
     * @param value
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static String urlEncode(String value) throws UnsupportedEncodingException {
        return URLEncoder.encode(value, "UTF-8");
    }

    /**
     * Encodes the passed String as UTF-8 using an algorithm that's compatible
     * with JavaScript's <code>encodeURIComponent</code> function. Returns
     * <code>null</code> if the String is <code>null</code>.
     *
     * @param s
     *            The String to be encoded
     * @return the encoded String
     */
    public static String encodeURIComponent(String s) {
        String result = null;

        try {
            if(s != null && !s.trim().equals("")){
                result = URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20")
                        .replaceAll("\\%21", "!").replaceAll("\\%27", "'")
                        .replaceAll("\\%28", "(").replaceAll("\\%29", ")")
                        .replaceAll("\\%7E", "~");
            }
        }

        // This exception should never occur.
        catch (UnsupportedEncodingException e) {
            result = s;
        }

        return result;
    }

    /**
     * Decodes the passed UTF-8 String using an algorithm that's compatible with
     * JavaScript's <code>decodeURIComponent</code> function. Returns
     * <code>null</code> if the String is <code>null</code>.
     *
     * @param s
     *            The UTF-8 encoded String to be decoded
     * @return the decoded String
     */
    public static String decodeURIComponent(String s) {
        if (s == null) {
            return null;
        }

        String result = null;

        try {
            result = URLDecoder.decode(s, "UTF-8");
        }

        // This exception should never occur.
        catch (UnsupportedEncodingException e) {
            result = s;
        }

        return result;
    }

    public static String cleanHtmlTags(String input) {
        try{
            //Remove math expression
            List<String> res = extractText(new StringReader(input));
            input = StringUtils.join(res, ' ');
        }catch (IOException ex) {

        }

        return input;
    }

    private static List<String> extractText(Reader reader) throws IOException {
        final ArrayList<String> list = new ArrayList<String>();

        ParserDelegator parserDelegator = new ParserDelegator();
        HTMLEditorKit.ParserCallback parserCallback = new HTMLEditorKit.ParserCallback() {
            public void handleText(final char[] data, final int pos) {
                list.add(new String(data));
            }
            public void handleStartTag(HTML.Tag tag, MutableAttributeSet attribute, int pos) {}
            public void handleEndTag(HTML.Tag t, final int pos) {  }
            public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, final int pos) {
                if (t.toString().equalsIgnoreCase("img")) {
                    list.add("###");
                }
            }
            public void handleComment(final char[] data, final int pos) { }
            public void handleError(final String errMsg, final int pos) { }
        };
        parserDelegator.parse(reader, parserCallback, true);
        return list;
    }
    public static boolean contains(List list, Object o) {
        return list.contains(o);
    }

}
