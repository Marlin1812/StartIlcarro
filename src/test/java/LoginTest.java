import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest extends TestBase{

    @BeforeMethod
    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }

    @Test
    public void LoginTestSuccess(){

        User data = new User().withEmail("abc123@hot.com").withPassword("Asdf123$");


        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(data);
        app.getUser().submitLogin();
        app.getUser().pause(30);
        app.getUser().clickOkButton();
//        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[contains(.,'Delete account')]")));
//        Assert.assertTrue(app.getUser().isLoggedSuccess());
    }
    @Test
    public void loginTestNegative(){

        app.getUser().openLoginForm();
        app.getUser().fillLoginForm("abc123@hot.com", "Asd123$");
        app.getUser().submitLogin();
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//h1[.='Login failed']")));
    }

    @AfterMethod
    public void postCondition(){
        app.getUser().pause(3);
        app.getUser().clickOkButton();
    }
}