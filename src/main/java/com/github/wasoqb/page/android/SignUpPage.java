package com.github.wasoqb.page.android;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    private final By            confirmPassword = accessibilityId ("input-repeat-password");
    private final By            email           = accessibilityId ("input-email");
    private final By            loginTab        = accessibilityId ("Login");
    private final By            message         = androidUIAutomator (
        "new UiSelector().resourceId(\"android:id/message\")");
    private final By            okButton        = androidUIAutomator (
        "new UiSelector().resourceId(\"android:id/button1\")");
    private final By            password        = accessibilityId ("input-password");
    private final By            signUpButton    = accessibilityId ("button-SIGN UP");
    private final By            signUpTab       = accessibilityId ("button-sign-up-container");
    private final WebDriverWait wait;

    public SignUpPage (final AndroidDriver driver) {
        this.wait = new WebDriverWait (driver, ofSeconds (10));
    }

    public String signUp (final String userName, final String pass) {
        this.wait.until (elementToBeClickable (this.loginTab))
            .click ();
        this.wait.until (elementToBeClickable (this.signUpTab))
            .click ();
        this.wait.until (elementToBeClickable (this.email))
            .sendKeys (userName);
        this.wait.until (elementToBeClickable (this.password))
            .sendKeys (pass);
        this.wait.until (elementToBeClickable (this.confirmPassword))
            .sendKeys (pass);
        this.wait.until (elementToBeClickable (this.signUpButton))
            .click ();

        final var messageText = this.wait.until (visibilityOfElementLocated (this.message))
            .getText ();
        this.wait.until (elementToBeClickable (this.okButton))
            .click ();
        return messageText;
    }
}
