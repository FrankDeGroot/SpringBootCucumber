package shop;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignUpWebTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        ChromeDriverManager.getInstance().setup();
    }

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

    @Test
    public void signup() {
        driver.get("http://localhost:" + port + "/signup");

        WebElement name = driver.findElement(By.id("name"));
        name.sendKeys("test");

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("test");

        WebElement passwordVerification = driver.findElement(By.id("passwordVerification"));
        passwordVerification.sendKeys("test");

        WebElement submit = driver.findElement(By.tagName("form"));
        submit.submit();

        assertThat(driver.getTitle(), equalTo("Registered - Shop"));
    }
}
