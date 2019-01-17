package com.vz;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverManager{
    
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

