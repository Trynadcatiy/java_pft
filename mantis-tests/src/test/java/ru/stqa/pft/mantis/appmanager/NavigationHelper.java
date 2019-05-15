package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertTrue;

public class NavigationHelper extends HelperBase {

    NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        type(By.name("password"), password);
        click(By.cssSelector("input[type='submit']"));
        assertTrue(isLoggedIsAs(username));
    }

    public void manage() {
        click(By.linkText("Manage"));
    }

    public void users() {
        click(By.linkText("Manage Users"));
    }

    private boolean isLoggedIsAs(String username) {
        WebElement element = wd.findElement(By.cssSelector("span[class='italic']"));
        return element.getText().equals(username);
    }

}