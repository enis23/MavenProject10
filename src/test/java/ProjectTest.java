import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class ProjectTest {


    ChromeDriver driver;
    WebDriverWait wait;


    @BeforeMethod
    @Parameters({"username", "password", "path"})
    public void setup (String username, String password,String path) {
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get("https://test-basqar.mersys.io");
        driver.manage().window().maximize();

        WebElement usernameInput = driver.findElement(By.cssSelector("[formcontrolname=\"username\"]"));
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.cssSelector("[formcontrolname=\"password\"]"));
        passwordInput.sendKeys(password);

        WebElement submitButton = driver.findElement(By.cssSelector("button[aria-label=\"LOGIN\"]"));
        submitButton.click();

        wait = new WebDriverWait(driver, 5);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[@class='cc-btn cc-dismiss']")).click();
    }

    @Test
    @Parameters({"gradeName", "gradeShortName", "gradeOrder"})
    public void createGradeLevel (String gradeName, String gradeShortName, String gradeOrder) {

        // navigate to grade level page
        WebElement setup = driver.findElement(By.xpath("//span[contains(text(),'Setup')]"));
        setup.click();

        WebElement parameters = driver.findElement(By.xpath("//span[contains(text(),'Parameters')]"));
        parameters.click();

        WebElement gradeLevel = driver.findElement(By.xpath("//span[contains(text(),'Grade Levels')]"));
        gradeLevel.click();


        // add grade level and fill in information
        WebElement plusIcon = driver.findElement(By.xpath("//ms-add-button/div[@fxlayout='row']/button"));
        plusIcon.click();

        WebElement gradeNameInput = driver.findElement(By.xpath("//ms-text-field/input"));
        gradeNameInput.sendKeys(gradeName);

        WebElement gradeShortNameInput = driver.findElement(By.xpath("//div/ms-text-field[@formcontrolname='shortName']/input"));
        gradeShortNameInput.sendKeys(gradeShortName);

        WebElement gradeOrderInput = driver.findElement(By.xpath("//div/ms-text-field[@formcontrolname='order']/input"));
        gradeOrderInput.sendKeys(gradeOrder);

        WebElement saveButton = driver.findElement(By.xpath("//ms-save-button/button"));
        saveButton.click();
    }

    @Test
    @Parameters({"name","code"})
    public void createSchoolDepartment (String name,String code) {
        //click setup
        WebElement setup =driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(1)"));
        setup.click();
        //click school setup
        WebElement school =driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(1) > .children > .nav-item:nth-child(2)"));
        school.click();
        //click departments
        WebElement click =driver.findElement(By.cssSelector("fuse-navigation .group-items > .nav-item:nth-child(1) > .children > .nav-item:nth-child(2) > .children > .nav-item:nth-child(5)"));
        click.click();
        //Click on plus icon
        WebElement plus =driver.findElement(By.cssSelector("svg[data-icon='plus']"));
        plus.click();
        //school departmemt
        // String name= "xyz";
        WebElement departmanname=driver.findElement(By.cssSelector("ms-text-field[formcontrolname='name']>input"));
        departmanname.sendKeys(name);
        //String code="123";
        WebElement codename=driver.findElement(By.cssSelector("ms-text-field[formcontrolname='code']>input"));
        codename.sendKeys(code);
    }

    @AfterMethod
    public void cleanUp () {
        driver.close();
    }







}