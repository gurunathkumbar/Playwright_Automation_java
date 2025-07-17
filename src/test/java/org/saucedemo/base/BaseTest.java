package org.saucedemo.base;

import java.util.Properties;

import org.saucedemo.java.core.PlaywrightFactory;
import org.saucedemo.java.pages.LoginPage;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;


public class BaseTest {

    PlaywrightFactory pf;
    Page page;
    protected Properties prop;

   // protected HomePage homePage;
    protected LoginPage loginPage;

    @Parameters({ "browser" })
    @BeforeTest
    public void setup(String browserName) {
        pf = new PlaywrightFactory();

        prop = pf.init_prop();

        if (browserName != null) {
            prop.setProperty("browser", browserName);
        }

        page = pf.initBrowser(prop);
        loginPage = new LoginPage(page);
    }

    @AfterTest
    public void tearDown() {
        page.context().browser().close();
    }

}
