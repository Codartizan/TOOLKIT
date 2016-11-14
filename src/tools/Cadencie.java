package tools;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by tshi on 13/10/2016.
 * Class Description:
 * Method Include:
 * Possible relevant Scenario:
 * PS:
 */
public class Cadencie {

    static WebDriver driver;

    public void LoginCad(String vm, String usr) throws SQLException {

        //System.setProperty("webdriver.firefox.bin", "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
        //System.setProperty("webdriver.gecko.driver", "C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");
        //FirefoxProfile profile = new FirefoxProfile();
        //FirefoxOptions ffOptions = new FirefoxOptions();
        //profile.setPreference("dom.disable_open_during_load", false);
        //profile.setPreference("dom.disable_open_during_load", false);
        //profile.setPreference("privacy.popups.disable_from_plugins", 0);
        //profile.setPreference("privacy.popups.policy", 1);
        //profile.setPreference("dom.max_script_run_time", "999");
        //ffOptions.setProfile(profile);
        //driver = new FirefoxDriver(profile);
        //driver.get("http://172.16.1.133:8090/CADENCIE/servlet/app");

        System.setProperty("webdriver.chrome.bin", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");

        //Properties prop = new Properties();
        //String propFile  = "P:\\Temp\\Tim\\ENV\\" + vm + ".properties";
        //prop.load(new FileInputStream(propFile));
        //String baseUrl = prop.getProperty("URL");
        //String bankNo = prop.getProperty(usr + "BANK");
        //String emp = prop.getProperty(usr + "USER");
        //String pwd = prop.getProperty(usr + "PWD");
        Utilities util = new Utilities();
        String baseUrl = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "VM_URL");
        String bankNo = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "BANK_NBR");
        String emp = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "BANK_EMP");
        String pwd = util.connDB(("SELECT * FROM " + vm + " WHERE EMP_CODE = '" + usr + "';"), "BANK_PWD");


        driver = new ChromeDriver();
        driver.get(baseUrl);


        try {

            Thread.sleep(1000);

        } catch(InterruptedException e){

            e.printStackTrace();

        }

        Utilities.switchToWindow("Cadencie - User Logon [LOGON]", driver);

        try{

            Thread.sleep(2000);

        } catch(InterruptedException e){

            e.printStackTrace();

        }

        driver.findElement(By.id("idBANK")).clear();
        driver.findElement(By.id("idBANK")).sendKeys(bankNo);

        driver.findElement(By.id("idEMPLOYEE")).clear();
        driver.findElement(By.id("idEMPLOYEE")).sendKeys(emp);

        driver.findElement(By.id("idPASSWORD")).clear();
        driver.findElement(By.id("idPASSWORD")).sendKeys(pwd);

        driver.findElement(By.id("maintLOGON")).click();

        driver.findElement(By.id("idPASSWORD")).clear();
        driver.findElement(By.id("idPASSWORD")).sendKeys(pwd);

        driver.findElement(By.id("maint")).click();

    }

}
