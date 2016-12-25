package com.karlchu.mo.jcr.util;


public class Utils {
    // jdk1.6
    public static String normalize(String str){
        String result = java.text.Normalizer.normalize(str, java.text.Normalizer.Form.NFD );
        result = result.replaceAll("\\p{InCombiningDiacriticalMarks}+", "").replace('\u0111', 'd').replace('\u0110', 'D');
        return result;
    }
}
