package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData("Pavel", "Petrovich", "Voronin", "Trynadcatiy",
                    "Test", "DXBX", "SPb", "12-34-567", "+79003002000", "+79003002001",
                    "79003002002", "p.voronin@fakemail.ru", "test1@fakemail.ru", "test2@fakemail.ru", "dxbx.ru",
                    "1", "January", "1987", "2", "February", "1988", "Test16",
                    "Test17", "Test18", null));
        }
    }

    @Test (enabled = true)
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
