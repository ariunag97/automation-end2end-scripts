package com.academy.techcenture.end2end;

import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.swing.interop.SwingInterOpUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Locale;

public class UserRegistrationEnd2End {

    private static final String APP_URL = "http://automationpractice.com";

    public static void main(String[] args) throws InterruptedException {

        // configure the webdriver

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get(APP_URL);

        String title = driver.getTitle();

        if (title.equals("My Store"))
        {
            System.out.println("Title is correct");
        } else {
            System.out.println("Title is incorrect");
        }

        WebElement signInLink = driver.findElement(By.linkText("Sign in"));

        if (signInLink.isDisplayed()){
            System.out.println("Clicking sign in link, because it exists");
            signInLink.click();
        }else
        {
            System.out.println("Sign in link does not exist on the page");
        }

        String emailAddress = CommonUtils.randomEmail();

        WebElement newUserEmailInput = driver.findElement(By.id("email_create"));
        newUserEmailInput.clear();

        newUserEmailInput.sendKeys(emailAddress);

        WebElement createAccountBtn = driver.findElement(By.id("SubmitCreate"));
        createAccountBtn.click();

        Thread.sleep(1000);
        title = driver.getTitle();

        if (title.equals("Login - My Store"))
        {
            System.out.println("Second page title is correct");
        } else {
            System.out.println("Second page title is NOT correct");
        }

        WebElement mainHeader = driver.findElement(By.className("page-heading"));

        if (mainHeader.getText().equals("CREATE AN ACCOUNT")){

            System.out.println("Header is correct");
        }
        else System.out.println("nope");

        int randomGender = (int) (Math.random()* (3 - 1) + 1);

        WebElement genderRadioBtn = driver.findElement(By.xpath("//input[@id='id_gender"+randomGender+"']"));

        genderRadioBtn.click();

        int atSign = emailAddress.indexOf("@");
        String[] fullName = emailAddress.substring(0, atSign).split("\\.");
        String firstName = fullName[1].substring(0,1).toUpperCase() + fullName[1].substring(1);
        String lastName = fullName[0].substring(0,1).toUpperCase() + fullName[0].substring(1);
        String password = lastName+ "123" + firstName.charAt(0) + "!";


        WebElement firstNameInput = driver.findElement(By.id("customer_firstname"));
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.id("customer_lastname"));
        lastNameInput.sendKeys(lastName);

        WebElement emailInputRegistration = driver.findElement(By.id("email"));
        String emailValueRegistration = emailInputRegistration.getAttribute("value");

        //compare the randomly generated email with populated email on the registration page

        if (emailValueRegistration.equals(emailAddress)){
            System.out.println("Email ids are correct");
        }
        else {
            System.out.println("Email ids do not match");
        }

        WebElement passwdInput = driver.findElement(By.id("passwd"));
        passwdInput.sendKeys(password);



    }
}
