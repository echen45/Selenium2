package poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPOM {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "submit-button")
    WebElement loginBtn;

    @FindBy(id = "username-input")
    WebElement usernameInput;

    @FindBy(id = "password-input")
    WebElement passwordInput;

    @FindBy(className = "err-message")
    WebElement errMessage;

    @FindBy(id = "register-button")
    WebElement registerBtn;

    public LoginPOM(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5000));

        PageFactory.initElements(this.driver, this);
    }

    public void enterUsername(String username){
        this.usernameInput.sendKeys(username);
    }

    public void enterPassword(String password){
        this.passwordInput.sendKeys(password);
    }

    public String getCurrentUrl(){
        return this.driver.getCurrentUrl();
    }

    public String getErrorMessage(){
        this.wait.until(ExpectedConditions.visibilityOf(this.errMessage));
        return this.errMessage.getText();
    }

    public void clickLoginBtn(){
        this.loginBtn.click();
    }

    public void waitForMainFeed(){
        this.wait.until(ExpectedConditions.urlToBe("http://localhost:4200/main"));
    }

    public void clickRegisterBtn(){
        this.registerBtn.click();
        this.wait.until(ExpectedConditions.urlToBe("http://localhost:4200/create"));
    }

}
