package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.UserData;

public class ResetPasswordTests extends TestBase {

    @Test
    public void testResetPassword() throws Exception {
        UserData user = app.db().users().iterator().next();


        //app.registration().finish(confirmationLink, password);
        //assertTrue (app.newSession().login(user, password));
    }
}
