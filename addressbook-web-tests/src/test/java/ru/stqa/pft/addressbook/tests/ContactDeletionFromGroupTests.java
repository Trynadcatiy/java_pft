package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroupTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test13"));
            app.goTo().homePage();
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("Pavel").withMiddlename("Petrovich").withLastname("Voronin")
                    .withNickname("Trynadcatiy").withTitle("Test").withCompany("DXBX")
                    .withAddress("SPb").withHomePhone("12-34-567").withMobilePhone("+79003002000")
                    .withWorkPhone("+79003002001").withFax("79003002002").withEmail("p.voronin@fakemail.ru")
                    .withEmail2("test1@fakemail.ru").withEmail3("test2@fakemail.ru").withHomepage("dxbx.ru").withBday("1")
                    .withBmonth("January").withByear("1987").withAday("2").withAmonth("February").withAyear("1988")
                    .withAddress2("Test16").withPhone2("Test17").withNotes("Test18").inGroup(app.db().groups().iterator().next()));
        } else if (app.db().contacts().stream()
                .allMatch((c) -> c.getGroups().size() == 0)) {
            app.contact().addToGroup(app.db().contacts().iterator().next(), app.db().groups().iterator().next());
        }
    }

    @Test
    public void testContactDeletionFromGroup() {
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        ContactData before = contacts.stream()
                .filter((c) -> c.getGroups().size() > 0)
                .iterator().next();
        GroupData group = groups.stream()
                .filter((g) -> before.getGroups().contains(g))
                .iterator().next();
        app.goTo().homePage();
        app.contact().removeFromGroup(before, group);

        ContactData after = app.db().contacts().stream()
                .filter((c) -> c.getId() == before.getId())
                .findFirst().get();
        assertThat(after.getGroups(), equalTo(before.outGroup(group).getGroups()));
    }
}
