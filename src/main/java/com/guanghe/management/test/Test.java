package com.guanghe.management.test;

import com.guanghe.management.util.env.Env;

/**
 * Created by Shibo on 17/1/5.
 */
public class Test {


    public static void main(String[] args) {
        System.out.println(new Env().getProperty("jdbc.password"));
    }
}
