package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getContactHelper().getContactCount();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Pavel", "Petrovich", "Voronin", "Trynadcatiy",
                    "Test", "DXBX", "SPb", "12-34-567", "+79003002000", "+79003002001",
                    "79003002002", "p.voronin@fakemail.ru", "test1@fakemail.ru", "test2@fakemail.ru", "dxbx.ru",
                    "1", "January", "1987", "2", "February", "1988", "Test16",
                    "Test17", "Test18", null));
        }
        app.getContactHelper().initContactModification(before - 1);
        app.getContactHelper().fillContactForm(new ContactData("Pavel", "Petrovich", "Voronin",
                "Trynadcatiy", "Test", "DXBX", "SPb", "12-34-567",
                "+79003002000", "+79003002001", "79003002002", "p.voronin@fakemail.ru",
                "test1@fakemail.ru", "test2@fakemail.ru", "dxbx.ru", "1", "January",
                "1987", "2", "February", "1988", "Test16", "Test17",
                "Test18", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
