package com.nopcommerce.common;

import org.testng.annotations.*;

public class Annotation {

    @Test
    public void TC_01() {
        System.out.println("Run TC01");
    }
    @BeforeClass
    public void beforeClass() {
        System.out.println("Run beforeClass");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Run afterClass");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Run beforeTest");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("Run afterTest");
    }

    @BeforeSuite
    public void beforeSuite() {
        System.out.println("Run beforeSuite");
    }

    @AfterSuite
    public void afterSuite() {
        System.out.println("Run afterSuite");
    }

}
