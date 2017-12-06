package shop;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpSteps {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @Before
    public void before() {
        driver = new ChromeDriver();
    }

    @After
    public void after() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("^I am at the sign up page$")
    public void iAmAtTheSignUpPage() throws Throwable {
        driver.get("http://localhost:" + port + "/signup");
    }

    @When("^I enter a name$")
    public void iEnterAName() throws Throwable {
        WebElement name = driver.findElement(By.id("name"));
        name.sendKeys("test");
    }

    @And("^I enter a password$")
    public void iEnterAPassword() throws Throwable {
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("test");
    }

    @And("^I enter a password verification$")
    public void iEnterAPasswordVerification() throws Throwable {
        WebElement passwordVerification = driver.findElement(By.id("passwordVerification"));
        passwordVerification.sendKeys("test");
    }

    @And("^I submit$")
    public void iSubmit() throws Throwable {
        WebElement submit = driver.findElement(By.tagName("form"));
        submit.submit();
    }

    @Then("^I should be at the registered page$")
    public void iShouldBeAtTheRegisteredPage() throws Throwable {
        assertThat(driver.getTitle(), equalTo("Registered - Shop"));
    }
}
