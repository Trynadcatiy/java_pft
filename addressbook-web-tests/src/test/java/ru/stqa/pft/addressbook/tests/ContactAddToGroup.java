package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class ContactAddToGroup extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            if (app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName("Test13"));
                app.goTo().homePage();
            }
            Groups groups = app.db().groups();
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("Pavel").withMiddlename("Petrovich").withLastname("Voronin")
                    .withNickname("Trynadcatiy").withTitle("Test").withCompany("DXBX")
                    .withAddress("SPb").withHomePhone("12-34-567").withMobilePhone("+79003002000")
                    .withWorkPhone("+79003002001").withFax("79003002002").withEmail("p.voronin@fakemail.ru")
                    .withEmail2("test1@fakemail.ru").withEmail3("test2@fakemail.ru").withHomepage("dxbx.ru").withBday("1")
                    .withBmonth("January").withByear("1987").withAday("2").withAmonth("February").withAyear("1988")
                    .withAddress2("Test16").withPhone2("Test17").withNotes("Test18").inGroup(groups.iterator().next()));
        }
    }

    @Test
    public void testContactAddToGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        //Получаю список контактов, не связанные хотябы с одной из имеющихся групп и беру одну
        ContactData contact = contacts.stream()
                .filter((c) -> groups.stream()
                        .anyMatch((g)->!c.getGroups().contains(g)))
                .iterator().next();
        //Получаю список групп не связанных с выбранным контактом и беру одну
        GroupData group = groups.stream()
                .filter((g)->!contact.getGroups().contains(g))
                .iterator().next();
        app.contact().addToGroup(contact, group);

    }
}
