package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class UserHelper extends HelperBase {
    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void chooseUser(UserData user) {
        click(By.linkText(user.getUsername()));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void confirmChangePassword(String resetPasswordLink, String password) {
        wd.get(resetPasswordLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
