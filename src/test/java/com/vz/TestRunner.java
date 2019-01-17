package com.vz;

/*

@CucumberOptions(	
    plugin = { "pretty", "html:target/html-report", "json:target/cucumber-reports/CucumberTestReport.json" }, 
    features = { "src/test/java/com/vz/features/demos" },
    glue = "com.vz.steps" )
public class TestRunner 
{
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

    @Test(dataProvider = "features", threadPoolSize = 4)
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

*/