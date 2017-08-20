package com.appium.testcase;

import  org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


/**
 * Created by wangyuying on 2017/8/20.
 */
public class Test1 {

    @BeforeSuite

    @Test
    public void searchTest(){
        int a=100;
        int b=88;
        int c=12;
        int d=b+c;
        Assert.assertEquals(b+c,a);
    }
}
