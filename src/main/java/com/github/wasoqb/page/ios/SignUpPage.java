package com.github.wasoqb.page.ios;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    private final By            confirmPasswordField = accessibilityId ("input-repeat-password");
    private final By            emailId              = accessibilityId ("input-email");
    private final By            loginTab             = iOSNsPredicateString (
        "label == \"Login\" AND name == \"Login\" AND value == \"1\"");
    private final By            passwordField        = accessibilityId ("input-password");
    private final By            signUpButton         = accessibilityId ("button-SIGN UP");
    private final By            signUpTab            = iOSNsPredicateString (
        "label == \"Sign up\" AND name == \"Sign up\" AND value == \"Sign up\"");
    private final WebDriverWait wait;

    public SignUpPage (final IOSDriver driver) {
        this.wait = new WebDriverWait (driver, ofSeconds (10));
    }

    public String doSignUp (final String userName, final String password) {
        this.wait.until (visibilityOfElementLocated (this.loginTab))
            .click ();
        this.wait.until (elementToBeClickable (this.signUpTab))
            .click ();

        this.wait.until (elementToBeClickable (this.emailId))
            .sendKeys (userName);
        this.wait.until (elementToBeClickable (this.passwordField))
            .sendKeys (password);
        this.wait.until (elementToBeClickable (this.confirmPasswordField))
            .sendKeys (password);
        this.wait.until (elementToBeClickable (this.signUpButton))
            .click ();

        final var alert = this.wait.until (alertIsPresent ());
        final var message = alert.getText ();
        alert.accept ();
        return message;
    }
}
