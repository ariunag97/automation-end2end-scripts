package com.academy.techcenture.end2end;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static com.academy.techcenture.end2end.CommonUtils.*;

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

        if (title.equals("My Store")) {
            System.out.println("Title is correct");
        } else {
            System.out.println("Title is incorrect");
        }

        WebElement signInLink = driver.findElement(By.linkText("Sign in"));

        if (signInLink.isDisplayed()) {
            System.out.println("Clicking sign in link, because it exists");
            signInLink.click();
        } else {
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

        if (title.equals("Login - My Store")) {
            System.out.println("Second page title is correct");
        } else {
            System.out.println("Second page title is NOT correct");
        }

        WebElement mainHeader = driver.findElement(By.className("page-heading"));

        if (mainHeader.getText().equals("CREATE AN ACCOUNT")) {

            System.out.println("Header is correct");
        } else System.out.println("nope");

        int randomGender = (int) (Math.random() * (3 - 1) + 1);

        WebElement genderRadioBtn = driver.findElement(By.xpath("//input[@id='id_gender" + randomGender + "']"));

        genderRadioBtn.click();

        int atSign = emailAddress.indexOf("@");
        String[] fullName = emailAddress.substring(0, atSign).split("\\.");
        String firstName = fullName[1].substring(0, 1).toUpperCase() + fullName[1].substring(1);
        String lastName = fullName[0].substring(0, 1).toUpperCase() + fullName[0].substring(1);
        String password = lastName + "123" + firstName.charAt(0) + "!!";


        WebElement firstNameInput = driver.findElement(By.id("customer_firstname"));
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.id("customer_lastname"));
        lastNameInput.sendKeys(lastName);

        WebElement emailInputRegistration = driver.findElement(By.id("email"));
        String emailValueRegistration = emailInputRegistration.getAttribute("value");

        //compare the randomly generated email with populated email on the registration page

        if (emailValueRegistration.equals(emailAddress)) {
            System.out.println("Email ids are correct");
        } else {
            System.out.println("Email ids do not match");
        }

        WebElement passwdInput = driver.findElement(By.id("passwd"));
        passwdInput.sendKeys(password);

        String dob = randomDOB18orAbove();

        String[] split = dob.split("-");
        int year = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int day = Integer.parseInt(split[2]);


        WebElement days = driver.findElement(By.id("days"));
        WebElement months = driver.findElement(By.id("months"));
        WebElement years = driver.findElement(By.id("years"));

        Select select = new Select(days);
        select.selectByValue(String.valueOf(day));

        select = new Select(months);
        select.selectByValue(String.valueOf(month));

        select = new Select(years);
        select.selectByValue(String.valueOf(year));

        WebElement newsletter = driver.findElement(By.id("newsletter"));
        WebElement specialOffers = driver.findElement(By.id("optin"));

        if (!newsletter.isSelected()) {
            System.out.println("Checked newsletter checkbox");
            newsletter.click();
        } else {
            System.out.println("Newsletter is already checked");

        }
        if (!specialOffers.isSelected()) {
            System.out.println("Checked special offers checkbox");
            newsletter.click();
        } else {
            System.out.println("Special offers is already checked");
        }

        String companyName = randomCompanyName();
        WebElement companyN = driver.findElement(By.xpath("//input[@name='company']"));
        companyN.sendKeys(companyName);

        WebElement postcode = driver.findElement(By.id("postcode"));
        postcode.sendKeys(randomZip());

        WebElement address1 = driver.findElement(By.id("address1"));
        address1.sendKeys(randomAddress());

        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys(randomCity());

        WebElement stateDropDown = driver.findElement(By.id("id_state"));
        select = new Select(stateDropDown);
        String randomState = randomState();
        select.selectByVisibleText(randomState);

        WebElement addInfo = driver.findElement(By.id("other"));
        addInfo.sendKeys(generateRandomString(20));

        WebElement phone = driver.findElement(By.id("phone_mobile"));
        phone.sendKeys(randomPhoneNumber());

        WebElement alias = driver.findElement(By.id("alias"));
        alias.sendKeys(randomAddress());

        Thread.sleep(3000);

        WebElement registerBtn = driver.findElement(By.id("submitAccount"));
        registerBtn.click();

    }
}