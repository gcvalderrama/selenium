package com.vz;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager{

    // mvn archetype:generate -DgroupId=com.uitest -DartifactId=uitest -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
    //mvn clean test  -Dsurefire.suiteXmlFiles=testngP.xml
    //java -jar selenium-server-standalone-3.11.0.jar -role hub
    //java -jar selenium-server-standalone-3.11.0.jar -role hub -port 80
    //java -jar selenium-server-standalone-3.11.0.jar -role node  -hub http://localhost:4444/grid/register
    //java -jar selenium-server.jar -role node  -hub http://10.118.128.250:80/grid/register


    private static final ThreadLocal<WebDriver> INSTANCE = new ThreadLocal<WebDriver>();
    private static final ThreadLocal<Long> ID = new ThreadLocal<Long>();

    public static Long GetID(){
        Long id = ID.get(); 
        if ( id == null){
            id = Thread.currentThread().getId();
            ID.set(id);
        }
        return id;
    }
    public static WebDriver GetDriver() throws MalformedURLException{        
        WebDriver driver = DriverManager.INSTANCE.get();
        if ( driver == null ){
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setBrowserName("chrome");
            WebDriver d = new RemoteWebDriver(new URL("http://10.118.128.250:80/wd/hub"), cap);        
            d.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); 
            DriverManager.INSTANCE.set(d);
            driver = d;
        }
        return driver;
    }
}

