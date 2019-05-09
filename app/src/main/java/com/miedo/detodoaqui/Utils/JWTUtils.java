package com.miedo.detodoaqui.Utils;

import java.io.UnsupportedEncodingException;

public class JWTUtils {

    private static JWT decode(String token) throws UnsupportedEncodingException {
        String[] split = token.split("\\.");
        String header = getJson(split[0]);
        String body = getJson(split[1]);
        return new JWT(header, body);
    }
    public static String getJson(String str) throws UnsupportedEncodingException{
        byte[] decodedBytes;
        decodedBytes = android.util.Base64.decode(str,android.util.Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
