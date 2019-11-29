package com.roll.casserole.todel;

import java.io.File;

/**
 * @author roll
 * created on 2019-11-29 11:24
 */
public class ErrorTest {
    public static void main(String[] args) {
        ErrorTest errorTest = new ErrorTest();
        errorTest.exception();
    }

    public void exception(){
        try {
            String a = null;
            File file = new File(a);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
            System.out.println(e.getClass());
            System.out.println(e.toString());
            System.out.println(e.getCause().toString());
        }
    }

}
