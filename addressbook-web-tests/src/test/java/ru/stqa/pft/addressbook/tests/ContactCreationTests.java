package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        ContactData contact = new ContactData("Pavel", "Petrovich", "Voronin", "Trynadcatiy",
                "Test", "DXBX", "SPb", "12-34-567", "+79003002000", "+79003002001",
                "79003002002", "p.voronin@fakemail.ru", "test1@fakemail.ru", "test2@fakemail.ru", "dxbx.ru",
                "1", "January", "1987", "2", "February", "1988", "Test16",
                "Test17", "Test18", "Test7");

        app.getNavigationHelper().gotoHomePage();

        // если не указано название группы - переходим к созданию контакта
        if (contact.getGroup() != null){
            app.getNavigationHelper().gotoGroupPage();
            // проверяем, есть ли группа с нужным названием в справочнике
            if (! app.getGroupHelper().isThereAGroup(contact.getGroup())){
                // создаем группу с нужным названием, если такой нет
                app.getGroupHelper().createGroup(new GroupData(contact.getGroup(), null, null));
                app.getNavigationHelper().gotoHomePage();
            }

        }

        app.getContactHelper().createContact(contact);
    }

}
