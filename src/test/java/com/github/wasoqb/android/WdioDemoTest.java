package com.github.wasoqb.android;

import static java.lang.System.getProperty;
import static java.time.Duration.ofSeconds;
import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

import com.github.wasoqb.page.android.SignUpPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WdioDemoTest {
    private AndroidDriver driver;

    @BeforeClass
    public void setupClass () throws MalformedURLException {
        final var options = new UiAutomator2Options ();
        options.setApp (Path.of (getProperty ("user.dir"), "src/test/resources/wdio-demo.apk")
                .toString ())
            .setDeviceName ("Pixel 7 Pro")
            .setPlatformVersion ("11.0")
            .setAvd ("Pixel_7_Pro")
            .setAppWaitActivity ("com.wdiodemoapp.MainActivity")
            .setCapability ("appium:settings[ignoreUnimportantViews]", true);

        this.driver = new AndroidDriver (new URL ("http://localhost:4723/"), options);
        this.driver.manage ()
            .timeouts ()
            .implicitlyWait (ofSeconds (1));
    }

    @AfterClass
    public void teardownClass () {
        this.driver.quit ();
    }

    @Test
    public void testSignUp () {
        final var signUpPage = new SignUpPage (this.driver);
        final var actualMessage = signUpPage.signUp ("admin@gmail.com", "Admin@1234");

        assertEquals (actualMessage, "You successfully signed up!");
    }
}
