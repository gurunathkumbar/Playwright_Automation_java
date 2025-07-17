package org.saucedemo.tests;

import org.saucedemo.base.BaseTest;
import org.saucedemo.java.constants.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginPageTests extends BaseTest {

    @Test(priority = 1)
    public void loginPageNavigationTest() {
        loginPage.doLogin(prop.getProperty("username").trim(),prop.getProperty("password").trim());
        String actLoginPageTitle = loginPage.getLoginPageTitle();
        System.out.println("page act title: " + actLoginPageTitle);
        Assert.assertEquals(actLoginPageTitle, Constants.LOGIN_PAGE_TITLE);
    }
/*
    @Test(priority = 2)
    public void forgotPwdLinkExistTest() {
        Assert.assertTrue(loginPage.isForgotPwdLinkExist());
    }*/

    @Test(priority = 2)
    public void appLoginTest() {
        Assert.assertTrue(loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim()));
    }
}
