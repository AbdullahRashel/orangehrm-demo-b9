import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTestRunner extends Setup {
    LoginPage loginPage;
    @Test(priority = 1)
    public void doLoginWithWrongCreds(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","wrongpass");
        String textActual= driver.findElement(By.className("oxd-alert-content-text")).getText();
        String textExpected="Invalid credentials";
        Assert.assertTrue(textActual.contains(textExpected));
    }
    @Test(priority = 2)
    public void doLoginWithvalidCreds(){
        loginPage=new LoginPage(driver);
        loginPage.doLogin("Admin","admin123");
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
        boolean isImageExists= driver.findElement(By.className("oxd-userdropdown-img")).isDisplayed();
        Assert.assertTrue(isImageExists);
    }
    @Test(priority = 3)
    public void doLogout(){
        loginPage=new LoginPage(driver);
        loginPage.doLogout();
        //String loginHeaderTitleActual= driver.findElement(By.className("oxd-text oxd-text--h5 orangehrm-login-title")).getText();
        String loginHeaderTitleActual= driver.findElement(By.xpath("//h5[normalize-space()='Login']")).getText();
        String loginHeaderExpected="Login";
        Assert.assertEquals(loginHeaderTitleActual,loginHeaderExpected);
    }

}