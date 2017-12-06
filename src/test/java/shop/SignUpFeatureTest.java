package shop;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions
public class SignUpFeatureTest {

    @BeforeClass
    public static void beforeClass() {
        ChromeDriverManager.getInstance().setup();
    }
}
