package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }.getType());
            return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation(ContactData contact) {
        File photo = new File("src/test/resources/stru.jpg");
        contact.withMiddlename("Petrovich")
                .withNickname("Trynadcatiy").withTitle("Test").withCompany("DXBX")
                .withAddress("SPb").withFax("79003002002").withEmail("p.voronin@fakemail.ru")
                .withEmail2("test1@fakemail.ru").withEmail3("test2@fakemail.ru").withHomepage("dxbx.ru").withBday("1")
                .withBmonth("January").withByear("1987").withAday("2").withAmonth("February").withAyear("1988")
                .withAddress2("Test16").withPhone2("Test17").withNotes("Test18").withGroup("Test7").withPhoto(photo);
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
        Contacts before = app.db().contacts();
        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}
