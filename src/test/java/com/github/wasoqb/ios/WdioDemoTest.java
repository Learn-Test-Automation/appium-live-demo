package com.github.wasoqb.ios;

import static java.lang.System.getProperty;
import static java.time.Duration.ofSeconds;
import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

import com.github.wasoqb.page.ios.SignUpPage;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WdioDemoTest {
    private IOSDriver driver;

    @BeforeClass
    public void setupClass () throws MalformedURLException {
        final var option = new XCUITestOptions ();
        option.setApp (Path.of (getProperty ("user.dir"), "src/test/resources/wdio-demo.app.zip")
                .toString ())
            .setPlatformVersion ("17.0")
            .setDeviceName ("iPhone 15");
        this.driver = new IOSDriver (new URL ("http://localhost:4723/"), option);
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
        final var actualMessage = signUpPage.doSignUp ("admin@gmail.com", "Admin@1234");

        assertEquals (actualMessage, "You successfully signed up!");
    }
}
