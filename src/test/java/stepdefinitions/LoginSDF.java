package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import poms.LoginPOM;

public class LoginSDF {
    private LoginPOM loginPOM;

    @After
    public void tearDown(){
        DriverSingleton.quitInstance();
    }

    @Given("A user is on the login page")
    public void a_user_is_on_the_login_page() throws InterruptedException {
        DriverSingleton.getInstance().get("http://localhost:4200");
        Thread.sleep(5000);
        this.loginPOM = new LoginPOM(DriverSingleton.getInstance());
        Assertions.assertEquals("http://localhost:4200", this.loginPOM.getCurrentUrl());
    }
    @When("A user inputs incorrect credentials on the login form")
    public void a_user_inputs_incorrect_credentials_on_the_login_form() {
        this.loginPOM.enterUsername("literallyanything");
        this.loginPOM.enterPassword("pass123");
        this.loginPOM.clickLoginBtn();
    }
    @Then("invalid username or password will be displayed to the user")
    public void invalid_username_or_password_will_be_displayed_to_the_user() {
        Assertions.assertEquals("Invalid Username Or Password", this.loginPOM.getErrorMessage());
    }

    @When("A user inputs correct credentials on the login form")
    public void a_user_inputs_correct_credentials_on_the_login_form() {
        this.loginPOM.enterUsername("john45");
        this.loginPOM.enterPassword("asd");
        this.loginPOM.clickLoginBtn();
    }
    @Then("the user will be redirect to the dashboard")
    public void the_user_will_be_redirect_to_the_dashboard() {
        this.loginPOM.waitForMainFeed();
        Assertions.assertEquals("http://localhost:4200/main", this.loginPOM.getCurrentUrl());
    }

    @When("A user clicks the register button")
    public void a_user_clicks_the_register_button() {
        this.loginPOM.clickRegisterBtn();
    }
    @Then("user will be redirected to the register page")
    public void user_will_be_redirected_to_the_register_page() {
        Assertions.assertEquals("http://localhost:4200/create", this.loginPOM.getCurrentUrl());
    }
}
