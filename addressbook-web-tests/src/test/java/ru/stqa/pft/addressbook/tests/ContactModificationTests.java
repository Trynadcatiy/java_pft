package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test (enabled = false)
    public void testContactModification() {
        app.goTo().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("Pavel", "Petrovich", "Voronin", "Trynadcatiy",
                    "Test", "DXBX", "SPb", "12-34-567", "+79003002000", "+79003002001",
                    "79003002002", "p.voronin@fakemail.ru", "test1@fakemail.ru", "test2@fakemail.ru", "dxbx.ru",
                    "1", "January", "1987", "2", "February", "1988", "Test16",
                    "Test17", "Test18", null));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData("Pavel", "Petrovich", "Voronin",
                "Trynadcatiy", "Test", "DXBX", "SPb", "12-34-567",
                "+79003002000", "+79003002001", "79003002002", "p.voronin@fakemail.ru",
                "test1@fakemail.ru", "test2@fakemail.ru", "dxbx.ru", "1", "January",
                "1987", "2", "February", "1988", "Test16", "Test17",
                "Test18", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.set(before.size() - 1, contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
