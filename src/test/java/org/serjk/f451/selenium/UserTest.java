package org.serjk.f451.selenium;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */

public class UserTest extends TestCase {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost:8080";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testCreateUser() throws Exception {
        driver.get(baseUrl + "/user/index#");
        driver.findElement(By.id("reg_button")).click();
        driver.findElement(By.id("input_firstName")).sendKeys("Иван");
        driver.findElement(By.id("input_lastName")).sendKeys("Иванов");
        driver.findElement(By.id("input_address")).sendKeys("Москва");
        driver.findElement(By.id("input_login")).sendKeys("login");
        driver.findElement(By.id("input_password1")).clear();
        driver.findElement(By.id("input_password1")).sendKeys("pass");
        driver.findElement(By.id("input_password2")).clear();
        driver.findElement(By.id("input_password2")).sendKeys("pass");
        driver.findElement(By.linkText("Зарегистрироваться")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String result = driver.findElement(By.id("reg_result")).getText();
        assertEquals("Пользователь создан, ввойдите в систему", result);
    }

    @Test
    public void testCreateExistUser() throws Exception {
        driver.get(baseUrl + "/user/index#");
        driver.findElement(By.cssSelector("#reg_button > p")).click();
        driver.findElement(By.id("input_firstName")).sendKeys("Иван");
        driver.findElement(By.id("input_lastName")).sendKeys("Иванов");
        driver.findElement(By.id("input_address")).sendKeys("Москва");
        driver.findElement(By.id("input_login")).sendKeys("login");
        driver.findElement(By.id("input_password1")).clear();
        driver.findElement(By.id("input_password1")).sendKeys("pass");
        driver.findElement(By.id("input_password2")).clear();
        driver.findElement(By.id("input_password2")).sendKeys("pass");
        driver.findElement(By.linkText("Зарегистрироваться")).click();
        assertEquals("Пользователь уже существует в системе", driver.findElement(By.id("reg_result")).getText());
    }

    @Test
    public void testAutenticationUser1() throws Exception {
        driver.get(baseUrl + "/user/index");
        driver.findElement(By.cssSelector("#login_button > p")).click();
        driver.findElement(By.id("j_login")).sendKeys("userrr");
        driver.findElement(By.id("j_password")).sendKeys("user");
        driver.findElement(By.id("sub_buton")).click();
        assertEquals("Пользователь не найден в системе", driver.findElement(By.id("login_error")).getText());
    }

    @Test
    public void testAutenticationUser2() throws Exception {
        driver.get(baseUrl + "/user/index");
        driver.findElement(By.cssSelector("#login_button > p")).click();
        driver.findElement(By.id("j_login")).sendKeys("user");
        driver.findElement(By.id("j_password")).sendKeys("12345");
        driver.findElement(By.id("sub_buton")).click();
        assertEquals("Неверный пароль", driver.findElement(By.id("login_error")).getText());
    }

    @Test
    public void testAutenticationUser3() throws Exception {
        driver.get(baseUrl + "/user/index");
        driver.findElement(By.cssSelector("#login_button > p")).click();
        driver.findElement(By.id("j_login")).sendKeys("user");
        driver.findElement(By.id("j_password")).sendKeys("user");
        driver.findElement(By.id("sub_buton")).click();
    }

    @Test
    public void testCreateDenun() throws Exception {
        driver.get(baseUrl + "/user/index");
        driver.findElement(By.cssSelector("#login_button > p")).click();
        driver.findElement(By.id("j_login")).sendKeys("user");
        driver.findElement(By.id("j_password")).sendKeys("user");
        driver.findElement(By.id("sub_buton")).click();
        driver.findElement(By.id("wow")).click();
        driver.findElement(By.name("firstName")).sendKeys("Георгий");
        driver.findElement(By.id("find")).click();
        driver.findElement(By.id("but")).click();
        driver.findElement(By.id("summary_id")).sendKeys("Читает книги");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("thanks")).click();
    }

    @Test
    public void testViewArchive() throws Exception {
        driver.get(baseUrl + "/user/index");
        driver.findElement(By.cssSelector("#login_button > p")).click();
        driver.findElement(By.id("j_login")).sendKeys("user");
        driver.findElement(By.id("j_password")).sendKeys("user");
        driver.findElement(By.id("sub_buton")).click();
        driver.findElement(By.id("arch")).click();
        //driver.findElement(By.linkText("Подробно...")).click();
    }

    @Test
    public void testViewNews() throws Exception {
        driver.get(baseUrl + "/user/index");
        driver.findElement(By.cssSelector("#login_button > p")).click();
        driver.findElement(By.id("j_login")).sendKeys("user");
        driver.findElement(By.id("j_password")).sendKeys("user");
        driver.findElement(By.id("sub_buton")).click();
        driver.findElement(By.id("news")).click();
        driver.findElement(By.id("news_day")).click();
        //driver.findElement(By.linkText("Подробно...")).click();
    }

    @Test
    public void testProfile() throws Exception {
        driver.get(baseUrl + "/user/index");
        driver.findElement(By.cssSelector("#login_button > p")).click();
        driver.findElement(By.id("j_login")).sendKeys("user");
        driver.findElement(By.id("j_password")).sendKeys("user");
        driver.findElement(By.id("sub_buton")).click();
        driver.findElement(By.id("profile")).click();
    }

    @Test
    public void testCreateNew() throws Exception {
        driver.get(baseUrl + "/user/index");
        driver.findElement(By.cssSelector("#login_button > p")).click();
        driver.findElement(By.id("j_login")).sendKeys("admin");
        driver.findElement(By.id("j_password")).sendKeys("admin");
        driver.findElement(By.id("sub_buton")).click();
        driver.findElement(By.id("news")).click();
        driver.findElement(By.id("cr_new")).click();
        driver.findElement(By.id("title")).sendKeys("Приезд губернатора");
        driver.findElement(By.id("summary")).sendKeys("5 июн 2014 года город посетил губернатор Каштанов А.В.");
        driver.findElement(By.id("description")).sendKeys("Ну приехал губернатор, ну и что?");
        driver.findElement(By.id("add_new")).click();
    }

    @Test
    public void testRateChange() throws Exception {
        driver.get(baseUrl + "/user/index");
        driver.findElement(By.cssSelector("#login_button > p")).click();
        driver.findElement(By.id("j_login")).sendKeys("admin");
        driver.findElement(By.id("j_password")).sendKeys("admin");
        driver.findElement(By.id("sub_buton")).click();
        driver.findElement(By.id("rate")).click();
        driver.findElement(By.id("change")).click();
        driver.findElement(By.id("value")).sendKeys("dmkiog");
        driver.findElement(By.id("change2")).click();
        driver.findElement(By.id("value")).clear();
        driver.findElement(By.id("value")).sendKeys("200");
        driver.findElement(By.id("change2")).click();
    }

    @Test
    public void testBank() throws Exception {
        driver.get(baseUrl + "/user/index");
        driver.findElement(By.cssSelector("#login_button > p")).click();
        driver.findElement(By.id("j_login")).sendKeys("admin");
        driver.findElement(By.id("j_password")).sendKeys("admin");
        driver.findElement(By.id("sub_buton")).click();
        driver.findElement(By.id("bank")).click();
        driver.findElement(By.id("add_bal")).click();
        driver.findElement(By.id("put")).click();
        driver.findElement(By.id("add_bal")).click();
        new Select(driver.findElement(By.id("value"))).selectByVisibleText("400");
        driver.findElement(By.id("put")).click();
        driver.findElement(By.id("add_bal")).click();
        driver.findElement(By.id("cancel")).click();
    }

    @Test
    public void testPayment() throws Exception {
        driver.get(baseUrl + "/user/index");
        driver.findElement(By.cssSelector("#login_button > p")).click();
        driver.findElement(By.id("j_login")).sendKeys("admin");
        driver.findElement(By.id("j_password")).sendKeys("admin");
        driver.findElement(By.id("sub_buton")).click();
        driver.findElement(By.id("payment")).click();
    }

//    @Test
//    public void testCorrectLogin() throws Exception {
//        driver.get(baseUrl + "/user/index");
//        driver.findElement(By.cssSelector("#login_button > p")).click();
//        driver.findElement(By.name("j_username")).sendKeys("login");
//        driver.findElement(By.name("j_password")).sendKeys("pass");
//        driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
//    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
