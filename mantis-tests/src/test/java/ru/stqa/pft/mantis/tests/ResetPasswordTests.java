package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() throws IOException, MessagingException {
        app.mail().start();

        if (app.db().users().size() == 0) {
            long now = System.currentTimeMillis();
            String user = String.format("user%s", now);
            String password = "password";
            String email = String.format("user%s@localhost", now);
            app.registration().start(user, email);
            List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
            app.mail().clean();
            String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
            app.registration().finish(confirmationLink, password);
            assertTrue(app.newSession().login(user, password));
            }
    }
    @Test
    public void testResetPassword() throws Exception {
        UserData user = app.db().users().iterator().next();
        String password = "password";

        app.goTo().login("administrator", "root");
        app.goTo().manage();
        app.goTo().users();
        app.user().chooseUser(user);
        app.user().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, user.getEmail());

        app.user().confirmChangePassword(confirmationLink, password);
        assertTrue(app.newSession().login(user.getUsername(), password));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
