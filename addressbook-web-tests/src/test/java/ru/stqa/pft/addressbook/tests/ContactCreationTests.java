package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
                app.group().create(new GroupData().withName("Test13"));
            app.goTo().homePage();
        }
        Groups groups = app.db().groups();
        File photo = new File("src/test/resources/stru.jpg");
        contact.withMiddlename("Petrovich")
                .withNickname("Trynadcatiy").withTitle("Test").withCompany("DXBX")
                .withAddress("SPb").withFax("79003002002").withEmail("p.voronin@fakemail.ru")
                .withEmail2("test1@fakemail.ru").withEmail3("test2@fakemail.ru").withHomepage("dxbx.ru").withBday("1")
                .withBmonth("January").withByear("1987").withAday("2").withAmonth("February").withAyear("1988")
                .withAddress2("Test16").withPhone2("Test17").withNotes("Test18").withPhoto(photo).inGroup(groups.iterator().next());
        app.goTo().homePage();
        // если не указано название группы - переходим к созданию контакта
        Contacts before = app.db().contacts();
        app.contact().create(contact);

        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
        verifyContactListInUI();
    }

}
