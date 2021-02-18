import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;
import helper.ElementHelper;
import helper.StoreHelper;
import model.ElementInfo;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;

public class BaseTest {


    String url = "https://www.trendyol.com/";

    @BeforeScenario
    public void senaryoOncesi() throws InterruptedException {
        System.out.println("-----Senaryo başlangıcı----");
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        action = new Actions(driver);

        driver.manage().window().maximize();
        System.out.println("driver calisti");

    }

    static WebDriver driver;
    static Actions action;

    public void hoverElement(String by) {
        action.moveToElement(findElement(by)).build().perform();
    }

   @AfterScenario
    public void senaryoSonrasi() {
        driver.quit();
        System.out.println("-----Senaryo sonu----");
    }

    public WebElement findElement(String key) {
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 30);
        WebElement webElement = webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(infoParam));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;
    }
    public List<WebElement> findElements(String key){
        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
        return driver.findElements(infoParam);
    }

    @Step("<saniye> saniye bekle")
    public void waitElement(int key) throws InterruptedException {
        Thread.sleep(key*1000);
        System.out.println(key+" saniye beklendi");
    }
    public void clickElement(String by){
        findElement(by).click();
    }
    public void clickListElement(String by,int index){

        findElements(by).get(index).click();
    }
    public  void sendkeysElement(String by,String text){
        findElement(by).sendKeys(text);
    }
    public void assertControl(String assertName, String expectedName){
        String assertName1 = findElement(assertName).getText();
        System.out.println(assertName1);
        Assert.assertEquals(assertName1,expectedName);
    }

    public static String convertTurkishChar(String string) {
        string = string.replace("ç", "c");
        string = string.replace("ö", "o");
        string = string.replace("ş", "s");
        string = string.replace("ğ", "g");
        string = string.replace("ü", "u");
        string = string.replace("ı", "i");
        string = string.replace("Ç", "C");
        string = string.replace("Ö", "O");
        string = string.replace("Ş", "S");
        string = string.replace("Ğ", "G");
        string = string.replace("Ü", "U");
        string = string.replace("İ", "I");
        return string;
    }
}

