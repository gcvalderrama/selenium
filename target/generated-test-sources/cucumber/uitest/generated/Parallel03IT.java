package uitest.generated;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        strict = true,
        features = {"/Users/gregory/Documents/2019/git/selenium/target/classes/customers/simple3.feature"},
        plugin = {"json:/Users/gregory/Documents/2019/git/selenium/target/cucumber-parallel/3.json"},
        monochrome = false,
        tags = {},
        glue = {"com.vz"})
public class Parallel03IT extends AbstractTestNGCucumberTests {
}
