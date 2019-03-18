package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test (enabled = true)
    public void testContactCreation() {

        ContactData contact = new ContactData("Pavel", "Petrovich", "Voronin", "Trynadcatiy",
                "Test", "DXBX", "SPb", "12-34-567", "+79003002000", "+79003002001",
                "79003002002", "p.voronin@fakemail.ru", "test1@fakemail.ru", "test2@fakemail.ru", "dxbx.ru",
                "1", "January", "1987", "2", "February", "1988", "Test16",
                "Test17", "Test18", "Test7");

        app.goTo().gotoHomePage();
        // если не указано название группы - переходим к созданию контакта
        if (contact.getGroup() != null) {
            app.goTo().groupPage();
            // проверяем, есть ли группа с нужным названием в справочнике
            if (!app.group().isThereAGroup(contact.getGroup())) {
                // создаем группу с нужным названием, если такой нет
                app.group().create(new GroupData().withName(contact.getGroup()));
            }
            app.goTo().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
