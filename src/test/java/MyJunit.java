import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunit {
    WebDriver driver;
    @BeforeAll
    public void setup(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @DisplayName("Get website title")
    @Test
    public void getTitle(){
        driver.get("https://demoqa.com/automation-practice-form");
        String titleActual= driver.getTitle();
        System.out.println(titleActual);
        String titleExpected="DEMOQA";
        Assertions.assertTrue(titleActual.contains(titleExpected));
    }
    @DisplayName("Submit user form")
    @Test
    public void submitForm() throws InterruptedException {
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("firstName")).sendKeys("Sumi");
        driver.findElement(By.id("lastName")).sendKeys("Mim");
        driver.findElement(By.id("userEmail")).sendKeys("mim@test.com");
        WebElement radioBtn= driver.findElement(By.id("gender-radio-2"));
        Actions actions=new Actions(driver);
        actions.click(radioBtn).perform();
        driver.findElement(By.id("userNumber")).sendKeys("0170000001");
        //Date
        WebElement dateElement=driver.findElement(By.id("dateOfBirthInput"));
        dateElement.click();
        dateElement.sendKeys(Keys.CONTROL+"a");
        dateElement.sendKeys("05 Dec 2000");
        dateElement.sendKeys(Keys.ENTER);
        Utils.scroll(driver,600);
        WebElement subject=driver.findElement(By.id("subjectsInput"));
        subject.sendKeys("Maths");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[text()='Maths']")); // Simpler XPath
        actions.sendKeys(Keys.ENTER).perform();

        WebElement hobbyBtn=driver.findElement(By.id("hobbies-checkbox-2"));
        actions.click(hobbyBtn).perform();
        //upload Image
        driver.findElement(By.id("uploadPicture")).sendKeys(System.getProperty("user.dir")+"/src/test/resources/guilin.jpg");
        driver.findElement(By.id("currentAddress")).sendKeys("Mirpur");
        //drop-down-1
        WebElement dropdownElem=driver.findElement(By.id("state"));
        dropdownElem.click();
        actions.keyDown(Keys.ARROW_DOWN).perform();
        actions.sendKeys(Keys.ENTER).perform();
        //drop-down-2
        WebElement dropdownElem2=driver.findElement(By.id("city"));
        dropdownElem2.click();
        actions.keyDown(Keys.ENTER).perform();
        //submit btn
        driver.findElement(By.id("submit")).click();
        Thread.sleep(5000);
        driver.findElement(By.id("closeLargeModal")).click();
        //success message
        WebElement resultElem = driver.findElement(By.id("example-modal-sizes-title-lg"));
        String actualMessage = resultElem.getText();
        System.out.println(actualMessage);
        String messageExpected ="Thanks for submitting the form";
        Assertions.assertTrue(actualMessage.contains(messageExpected));

    }
    @AfterAll
    public void closeBrowser(){
         driver.quit();
    }
}
