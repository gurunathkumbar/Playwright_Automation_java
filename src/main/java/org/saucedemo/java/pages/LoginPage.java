package org.saucedemo.java.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    private Page page;

    private String userNameTxt = "//input[@id='user-name']";
    private String pwdTxt = "//input[@id='password']";
    private String loginBtn = "//input[@id='login-button']";
    private String menuBtn = "//div[@id='menu_button_container']";
    private String logoutLink = "//a[@id='logout_sidebar_link']";



    //page constructor:
    public LoginPage(Page page) {
        this.page = page;
    }

    // page actions/methods:
    public String getLoginPageTitle() {
        return page.title();
    }

    public boolean doLogin(String appUserName, String appPassword) {
        System.out.println("App creds: " + appUserName + ":" + appPassword);
        page.fill(userNameTxt, appUserName);
        page.fill(pwdTxt, appPassword);
        page.click(loginBtn);
       // page.locator(menuBtn).waitFor();
        page.click(menuBtn);
       if(page.locator(logoutLink).isVisible()) {
            System.out.println("user is logged in successfully....");
            return true;
        }else {
            System.out.println("user is not logged in successfully....");
            return false;
        }
    }


    }
