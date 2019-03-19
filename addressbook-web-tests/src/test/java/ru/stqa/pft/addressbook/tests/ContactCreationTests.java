package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @Test (enabled = true)
    public void testContactCreation() {
        ContactData contact = new ContactData()
                .withFirstname("Pavel").withMiddlename("Petrovich").withLastname("Voronin")
                .withNickname("Trynadcatiy").withTitle("Test").withCompany("DXBX")
                .withAddress("SPb").withHomeTelephone("12-34-567").withMobileTelephone("+79003002000")
                .withWorkTelephone("+79003002001").withFax("79003002002").withEmail("p.voronin@fakemail.ru")
                .withEmail2("test1@fakemail.ru").withEmail3("test2@fakemail.ru").withHomepage("dxbx.ru").withBday("1")
                .withBmonth("January").withByear("1987").withAday("2").withAmonth("February").withAyear("1988")
                .withAddress2("Test16").withPhone2("Test17").withNotes("Test18").withGroup("Test7");
        app.goTo().homePage();
        // если не указано название группы - переходим к созданию контакта
        if (contact.getGroup() != null) {
            app.goTo().groupPage();
            // проверяем, есть ли группа с нужным названием в справочнике
            if (!app.group().isThereAGroup(contact.getGroup())) {
                // создаем группу с нужным названием, если такой нет
                app.group().create(new GroupData().withName(contact.getGroup()));
            }
            app.goTo().homePage();
        }
        Contacts before = app.contact().all();
        app.contact().create(contact);
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) ->c.getId()).max().getAsInt()))));
    }

}
