package org.saucedemo.java.core;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

public class PlaywrightFactory {

    Playwright playwright;
    Browser browser;
    Page page;
    BrowserContext browserContext;
    Properties prop;


    private static ThreadLocal<Playwright> tlPlaywright= new ThreadLocal<>();
    private static ThreadLocal<Browser> tlBrowser= new ThreadLocal<>();
    private static ThreadLocal<Page> tlPage= new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> tlBrowserContext= new ThreadLocal<>();


    public static Playwright getPlaywright() {
        return tlPlaywright.get();
    }

    public static Browser getBrowser() {
        return tlBrowser.get();
    }

    public static BrowserContext getBrowserContext() {
        return tlBrowserContext.get();
    }

    public static Page getPage() {
        return tlPage.get();
    }

    /**
     * this method is used to initialize the browser
     */
    public Page initBrowser(Properties prop){

        String browserName = prop.getProperty("browser").trim();
        System.out.println("Browser name is : " + browserName);

        tlPlaywright.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
                tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
                tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
                tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
                tlBrowser.set(
                        getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            case "edge":
                tlBrowser.set(
                        getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("msedge").setHeadless(false)));
                break;

            default:
                System.out.println("please pass the right browser name...... Currently, it's running in headless mode by default");
                tlBrowser.set(getPlaywright().chromium().launch());
                break;
        }

        tlBrowserContext.set(getBrowser().newContext());
        tlPage.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url").trim());
        return getPage();
    }

    /**
     * this method is used to initialize the properties from config file
     */
    public Properties init_prop() {

        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;

    }

    /**
     * take screenshot
     *
     */

    public static String takeScreenshot() {
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        //getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));

        byte[] buffer = getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
        String base64Path = Base64.getEncoder().encodeToString(buffer);

        return base64Path;
    }


}
