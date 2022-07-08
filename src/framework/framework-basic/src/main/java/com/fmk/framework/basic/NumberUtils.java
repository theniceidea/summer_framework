package com.fmk.framework.basic;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberUtils {
    public static BigInteger toBigInteger(String s){
        return new BigInteger(s);
    }
    public static BigDecimal toBigDecimal(String s){
        return new BigDecimal(s);
    }
    public static Integer toInteger(String s){
        return Integer.valueOf(s);
    }


    public static boolean compareToNum(BigInteger bi1,BigInteger bi2){
        boolean b=true;
        int res;
        res = bi1.compareTo(bi2);
        if( res == 0 )
          b=true;
        else if( res == 1 )
            b=false;
        else if( res == -1 )
            b=true;
        return b;
    }
}
