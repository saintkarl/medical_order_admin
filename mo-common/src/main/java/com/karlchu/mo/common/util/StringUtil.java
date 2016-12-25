package com.karlchu.mo.common.util;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DurationFormatUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Normalizer;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Thi
 * Date: 4/12/13
 * Time: 4:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtil {
    /**
     * Remove all diacritical characters
     * @param input the input string to remove all diacritical character
     * @return
     */
    public static String removeDiacritic(String input) {
        String result = Normalizer.normalize(input, Normalizer.Form.NFD);
        result = result.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        result = result.replaceAll("[^a-z A-Z0-9-_]", "");
        return result;
    }

    public static String formatDuration(Double second) {
        if (second == null)
            second = 0D;
        return DurationFormatUtils.formatDuration(second.longValue() * 1000, "HH:mm:ss");
    }

    public static String replaceLineBreakChar(String input) {
        if (input == null) {
            return "";
        }
        return input.replaceAll("\\n", "<br/>");
    }

    public static String unEscapeHTML(String value) {
        if (StringUtils.isNotBlank(value)) {
            value = StringEscapeUtils.unescapeHtml(value);
        }
        return value;
    }

    public static String escapeJS(String value) {
        return StringEscapeUtils.escapeJavaScript(value);
    }

    public static String joinList(Object value, String separator) {
        StringBuilder result = new StringBuilder();
        if (value != null && value instanceof Collection) {
            Object[] valueArray = ((Collection) value).toArray();
            for (int i=0; i<valueArray.length; i++) {
                result.append(valueArray[i]);
                if (i < valueArray.length - 1) {
                    result.append(separator);
                }
            }
        }
        return result.toString();
    }
    /**
     * rounding double to decimalDigit, HALF_UP
     * @param val
     * @param decimalDigit
     * @return
     */
    public static String roundUp(Double val, int decimalDigit) {
        if (val == null) {
            return "";
        }
        BigDecimal decimal = new BigDecimal(val);
        decimal = decimal.setScale(decimalDigit, RoundingMode.HALF_UP);
        return decimal.toString();
    }


    public static String normalizeUsername(String input) {
        input = removeDiacritic(input);
        input = input.replaceAll("[^a-zA-Z0-9_]", "");
        return input;
    }

    public static boolean isUsernameValid(String input) {
        if (input == null) return false;
        Pattern pattern = Pattern.compile("^[a-z0-9_\\-\\.]$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static boolean isNameValid(String input) {
        if (input == null) return false;
        Pattern pattern = Pattern.compile("^[a-z -']+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);
        return matcher.find();
    }

    public static String convertSoPhieuTC(Long number){
        String result = number.toString();
        if(result.length() < 7)  {
            for(int i = 0; i<= 7 - result.length(); i++)
            {
                result = "0" + result;
            }
        }
        return result;
    }

    public static String decimalFormat(Double val, String format){
        if(val == null){
            return "";
        }
        DecimalFormat df = new DecimalFormat(format);
        BigDecimal bd = BigDecimal.valueOf(val);
        try{
            return df.format(bd).toString();
        }catch (Exception e){
            DecimalFormatSymbols dfs = new DecimalFormatSymbols();
            dfs.setGroupingSeparator(',');
            df.setGroupingSize(3);
            df.setDecimalFormatSymbols(dfs);
            bd.setScale(2);
            return df.format(bd).toString();
        }
    }
    public static Boolean  isBigDecimal(String str){
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if ((Character.isDigit(str.charAt(i)) == false) && (str.charAt(i) != '.')) {
                return false;
            }
        }
        return true;

    }
}
