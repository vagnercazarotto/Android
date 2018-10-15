package com.example.jamiltondamasceno.organizze.helper;

import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;

public class DataCustom {

    public static String dataAtual(){

        long data = System.currentTimeMillis();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataString = simpleDateFormat.format(data);
        return dataString;
    }

}
