package uitest.generated;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        strict = true,
        features = {"/Users/gregory/Documents/2019/git/selenium/target/classes/smoke/smokea.feature"},
        plugin = {"json:/Users/gregory/Documents/2019/git/selenium/target/cucumber-parallel/4.json"},
        monochrome = false,
        tags = {},
        glue = {"com.vz"})
public class Parallel04IT extends AbstractTestNGCucumberTests {
}
