package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Pavel", "Petrovich", "Voronin", "Trynadcatiy",
                    "Test", "DXBX", "SPb", "12-34-567", "+79003002000", "+79003002001",
                    "79003002002", "p.voronin@fakemail.ru", "test1@fakemail.ru", "test2@fakemail.ru", "dxbx.ru",
                    "1", "January", "1987", "2", "February", "1988", "Test16",
                    "Test17", "Test18", null));
        }
    }

    @Test (enabled = true)
    public void testContactDeletion() {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().initContactDeletion();
        app.getContactHelper().acceptContactDeletion();
        app.getContactHelper().contactDeletionConfirmed();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();

        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size()-1);
        Assert.assertEquals(before, after);
    }
}
