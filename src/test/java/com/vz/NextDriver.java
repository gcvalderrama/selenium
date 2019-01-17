//package com.vz;
//
//public class NextDriver {
//
//    //mvn clean test  -Dsurefire.suiteXmlFiles=testngP.xml
//    //java -jar selenium-server-standalone-3.11.0.jar -role hub
//    //java -jar selenium-server-standalone-3.11.0.jar -role hub -port 80
//    //java -jar selenium-server-standalone-3.11.0.jar -role node  -hub http://localhost:4444/grid/register
//    //java -jar selenium-server.jar -role node  -hub http://10.118.128.250:80/grid/register
//
//    private static String OS = System.getProperty("os.name").toLowerCase();
//
//    private static final ThreadLocal<Long> ID = new ThreadLocal<Long>();
//    private static final ThreadLocal<WebDriver> DriverInstance = new ThreadLocal<WebDriver>();
//    private static final ThreadLocal<WebDriverWait> WaitInstance = new ThreadLocal<WebDriverWait>();
//    private static final ThreadLocal<Scenario> ScenarioInstance = new ThreadLocal<Scenario>();
//
//    public static void SetScenario(Scenario scenario) {
//        DriverManager.ScenarioInstance.set(scenario);
//    }
//
//    public static Scenario GetScenario() throws Exception {
//        Scenario scenario_instance = DriverManager.ScenarioInstance.get();
//
//        if (scenario_instance == null) {
//            throw new Exception("please put the scenario variable first");
//        }
//        return scenario_instance;
//    }
//
//
//    public static Long GetID() {
//        Long id = ID.get();
//        if (id == null) {
//            id = Thread.currentThread().getId();
//            ID.set(id);
//        }
//        return id;
//    }
//
//    public static void ReleaseDriver(Scenario scenario) throws Exception {
//        // release scenario variable
//        DriverManager.ScenarioInstance.remove();
//        WebDriver d = DriverManager.DriverInstance.get();
//        if (d != null) {
//            boolean jserror = false;
//            try {
//                CaptureScreen(d, scenario);
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
//            try {
//                CaptureExecutionInformation(d, scenario);
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
//            /*try{
//                jserror = CaptureJsErrors(d, scenario);
//            }
//            catch(Exception ex) {
//                System.out.println(ex.getMessage());
//            }*/
//            try {
//                d.close();
//            } catch (Exception ex) {
//                System.out.println(ex.getMessage());
//            }
//            try {
//                d.quit();
//            } catch (Exception ex) {
//                //this is just in case close failed
//            } finally {
//                DriverManager.DriverInstance.remove();
//            }
//
//            if (jserror) {
//                throw (new Exception("JS ERRORS FOUND "));
//            }
//        }
//    }
//
//    private static void CaptureScreen(WebDriver driver, Scenario scenario) {
//        try {
//            byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(
//                    org.openqa.selenium.OutputType.BYTES);
//            scenario.embed(screenshot, "image/png");
//        } catch (Exception ex) {
//            scenario.write(ex.getMessage());
//        }
//    }
//
//    private static void CaptureExecutionInformation(WebDriver driver, Scenario scenario) {
//        try {
//            String current_url = driver.getCurrentUrl();
//            String current_title = driver.getTitle();
//            scenario.write(String.format("title: %s , url: %s ", current_title, current_url));
//        } catch (Exception ex) {
//            scenario.write(ex.getMessage());
//        }
//    }
//
//    public static boolean CaptureJsErrors(WebDriver driver, Scenario scenario) {
//        boolean sb = false;
//        try {
//            List<String> errorStrings = new ArrayList<String>();
//            errorStrings.add("SyntaxError");
//            errorStrings.add("EvalError");
//            errorStrings.add("ReferenceError");
//            errorStrings.add("RangeError");
//            errorStrings.add("TypeError");
//            errorStrings.add("URIError");
//            LogEntries jsErrors = driver.manage().logs().get(LogType.BROWSER);
//            for (LogEntry log : jsErrors.getAll()) {
//                for (String key : errorStrings) {
//                    if (log.getMessage().contains(key)) {
//                        String jsErrorFormat = "<details style='background:#EDBBBB;border:1px solid #900;' open>";
//                        jsErrorFormat = jsErrorFormat + "<summary style='font-weight:bold;font-size:14px;'>JS ERRORS</summary><div>";
//                        jsErrorFormat = jsErrorFormat + "------------------------------------";
//                        jsErrorFormat = jsErrorFormat + "\n" + log.getMessage();
//                        jsErrorFormat = jsErrorFormat + "\n------------------------------------</div></details>";
//                        scenario.write(jsErrorFormat);
//                        sb = true;
//                    }
//                }
//            }
//        } catch (Exception ex) {
//            scenario.write(ex.getMessage());
//        }
//        return sb;
//    }
//
//    //mvn test -Dui_runtime=hub -Dus_browser=chrome -Dui_server=http://10.118.129.197:80/wd/hub -Dcucumber.options="--tags @content_test"
//
//
//    //Local Execution
//
//    // mvn clean test -Dcucumber.options="--tags @smoke --tags @otro" -Dus_browser=firefox
//    // mvn clean verify -Dcucumber.options="--tags @smoke" -Dus_browser=firefox
//
//    //Grid Execution
//    //mvn clean test -Dcucumber.options="--tags @smoke" -Dui_runtime=hub -Dus_browser=chrome -Dui_server=http://10.118.129.197:80/wd/hub
//    //mvn clean verify -Dsurefire.suiteXmlFiles=testngp.xml -Dcucumber.options="--tags @owner_pjara(at)one.verizon.com" -Dui_runtime=hub -Dus_browser=firefox -Dui_server=http://10.118.129.197:80/wd/hub
//
//
//    // if you want reports change mvn test by mvn verify
//
//    //Grid Parallel
//    //mvn clean test -Dsurefire.suiteXmlFiles=testngP.xml -Dui_runtime=hub -Dus_browser=chrome -Dui_server=http://10.118.129.197:80/wd/hub
//
//    //mvn clean test -Dsurefire.suiteXmlFiles=testngP.xml -Dui_runtime=hub  -Dcucumber.options="--tags @functional" -Dus_browser=chrome -Dui_server=http://10.118.129.197:80/wd/hub
//
//
//    //mvn clean test
//    //-Denv_var=%MY_ENV_VAR% (Windows)
//    //-Denv_var=$MY_ENV_VAR (Linux)
//
//    // new webdirever(231, 1234)
//
//    public static WebDriver GetDriver() throws Exception {
//        WebDriver driver = DriverManager.DriverInstance.get();
//        if (driver != null)
//            return driver;
//        else {
//            throw new Exception("please call before and scenario to create web driver");
//        }
//    }
//
//    public static WebDriver GetDriver(Scenario scenario) throws Exception {
//
//        DriverManager.SetScenario(scenario);
//
//        WebDriver driver = DriverManager.DriverInstance.get();
//        if (driver != null)
//            return driver;
//
//        String ui_server = System.getProperty("ui_server");//"http://127.0.0.1:4444/wd/hub"
//        String driver_kind = System.getProperty("ui_runtime"); // default local, server, hub
//        String browser_kind = System.getProperty("us_browser"); // default firefox, chrome, ie
//
//
//        if (driver_kind == null || driver_kind.isEmpty()) {
//            driver_kind = DriverKind.LOCAL;
//        }
//        driver_kind = driver_kind.toLowerCase();
//        if (browser_kind == null || browser_kind.isEmpty()) {
//            browser_kind = Browser.CHROME; // default browser
//        }
//        browser_kind = browser_kind.toLowerCase();
//
//        ConfigurationService configuration = new ConfigurationService();
//
//        System.out.println("=========================================");
//        System.out.println("ui runtime " + driver_kind);
//        System.out.println("ui browser " + browser_kind);
//        System.out.println("ui env " + configuration.getEnvironmentUrl());
//        System.out.println("=========================================");
//
//        ReactiveUIManager ui_manager = new ReactiveUIManager();
//
//        Collection<String> tags = scenario.getSourceTagNames();
//
//        switch (driver_kind) {
//            case DriverKind.LOCAL:
//                switch (browser_kind) {
//                    case Browser.FIREFOX:
//                        String geckoDriverPath = GetGeckoDriverPath();
//                        System.setProperty("webdriver.gecko.driver", geckoDriverPath);
//                        DesiredCapabilities capForFirefox = DesiredCapabilities.firefox();
//                        ui_manager.SetTagBrowserFirefoxCapabilities(capForFirefox, tags);
//                        driver = new FirefoxDriver(capForFirefox);
//
//                        break;
//                    case Browser.CHROME:
//                        //System.setProperty("webdriver.chrome.driver", "/Users/gvalderrama/bin/chromedriver");
//                        //ChromeDriver browser = new ChromeDriver();
//                        //export PATH=$PATH:/Users/gvalderrama/Documents/tools/geckoDriverPath
//                        // echo "export PATH=\"/Users/gvalderrama/Documents/tools:$PATH\"" > .bash_profile
//
//                        String chromeDriverPath = GetChromeDriverPath();
//                        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
//                        DesiredCapabilities capForChrome = DesiredCapabilities.chrome();
//                        ui_manager.SetTagBrowserChromeCapabilities(capForChrome, tags);
//                        driver = new ChromeDriver(capForChrome);
//
//
//
//                        //driver.manage().window().maximize();
//                        break;
//                    default:
//                        throw new IllegalArgumentException("Invalid : " + browser_kind);
//                }
//
//                break;
//            case DriverKind.HUB:
//
//                Capabilities cap;
//
//                switch (browser_kind) {
//                    case Browser.FIREFOX:
//                        cap = DesiredCapabilities.firefox();
//                        ui_manager.SetTagBrowserFirefoxCapabilities(cap, tags);
//                        FirefoxProfile profile = new FirefoxProfile();
//                        profile.setPreference("security.sandbox.content.level", 5);
//                        ((DesiredCapabilities) cap).setCapability(FirefoxDriver.PROFILE, profile);
//                        driver = new RemoteWebDriver(new URL(ui_server), cap);
//                        break;
//                    case Browser.CHROME:
//                        cap = DesiredCapabilities.chrome();
//                        ui_manager.SetTagBrowserChromeCapabilities(cap, tags);
//
//                        String PROXY = configuration.getProxy();
//                        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
//                        proxy.setHttpProxy(PROXY)
//                                .setFtpProxy(PROXY)
//                                .setSslProxy(PROXY)
//                                .setNoProxy(configuration.getNoProxy());
//
//                        ((DesiredCapabilities) cap).setCapability(CapabilityType.PROXY, proxy);
//
//                        driver = new RemoteWebDriver(new URL(ui_server), cap);
//                        //driver.manage().window().maximize();
//                        break;
//                    default:
//                        throw new IllegalArgumentException("Invalid : " + browser_kind);
//                }
//
//                break;
//
//            default:
//                throw new IllegalArgumentException("Invalid : " + driver_kind);
//        }
//
//        driver.manage().timeouts().implicitlyWait(TimeOut.FOR_DRIVER, TimeUnit.SECONDS);
//        DriverManager.DriverInstance.set(driver);
//        WebDriverWait wait = new WebDriverWait(driver, 30);
//        DriverManager.WaitInstance.set(wait);
//        return driver;
//    }
//
//    private static String GetGeckoDriverPath() {
//        String driver = System.getenv("selenium_firefox_driver");
//        if (driver == null || driver.isEmpty()) {
//            throw new IllegalArgumentException("must create and fill selenium_firefox_driver");
//        }
//        return driver;
//    }
//
//    private static String GetChromeDriverPath() {
//        String driver = System.getenv("selenium_chrome_driver");
//        if (driver == null || driver.isEmpty()) {
//            throw new IllegalArgumentException("must create and fill selenium_chrome_driver");
//        }
//        return driver;
//    }
//
//    public static WebDriverWait GetDriverWait() {
//        return DriverManager.WaitInstance.get();
//    }
//}