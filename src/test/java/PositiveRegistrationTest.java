import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PositiveRegistrationTest<User> extends TestBase {
    @BeforeMethod
    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logout();
        }
    }
    @Test
    public void regTest(){
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        models.User data = new models.User()
                .withEmail("name" + i + "@hot.com")
                .withPassword("Asdf1235$")
                .withName("Name"+i)
                .withLastname("LastMane"+i);

        app.getUser().openRegForm();
        app.getUser().fillRegForm(data);

        app.getUser().clickCheckboxButton();

        app.getUser().submitReg();
        app.getUser().pause(30);
        app.getUser().clickOkButton();
        Assert.assertTrue(app.getUser().isElementPresent(By.xpath("//a[contains(.,'Delete account')]")));

    }
}