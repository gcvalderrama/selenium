package com.vz;

import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.CucumberFeatureWrapper;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;



@CucumberOptions(	
    plugin = { "pretty", "html:target/html-report", "json:target/cucumber-reports/CucumberTestReport.json" }, 
    features = { "src/test/java/com/vz/features/smoke" },
    glue = "com.vz.steps",
    tags = { "DEV" } )
public class SmokeRunner 
{

    //mvn -Dgroups=smoke -Dcucumber.options="--tags @DEV" test
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }    

    @BeforeTest
    public void setUpTest(){
        Long id = DriverManager.GetID();
        System.out.println("set up test " + id);
    }

    @Test(dataProvider = "features", threadPoolSize = 4, groups = {"smoke"})
    public void feature(CucumberFeatureWrapper cucumberFeature) {                        
        Long id = DriverManager.GetID();
        System.out.println("test " + id);
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @AfterTest
    public void tearDownTest(){
        Long id = DriverManager.GetID();
        System.out.println("tear dow test" +  id);
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}
