package com.anderson.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {

    public static String decodeParam(String string){

        try {
            return URLDecoder.decode(string, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static List<Integer> decodeIntList(String string){

        /*
        Before Java 8

        String [] vetor = string.split(",");
        List<Integer> list = new ArrayList<>();

        for(int i = 0; i < vetor.length; i++){
            list.add(Integer.parseInt(vetor[i]));
        }

        return list;

        return Arrays.asList(string.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
        return Arrays.stream(string.split(",")).map(x -> Integer.parseInt(x)).collect(Collectors.toList());
        */

        return Arrays.stream(string.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }
}
