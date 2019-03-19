package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstname("Pavel").withMiddlename("Petrovich").withLastname("Voronin")
                    .withNickname("Trynadcatiy").withTitle("Test").withCompany("DXBX")
                    .withAddress("SPb").withHomeTelephone("12-34-567").withMobileTelephone("+79003002000")
                    .withWorkTelephone("+79003002001").withFax("79003002002").withEmail("p.voronin@fakemail.ru")
                    .withEmail2("test1@fakemail.ru").withEmail3("test2@fakemail.ru").withHomepage("dxbx.ru").withBday("1")
                    .withBmonth("January").withByear("1987").withAday("2").withAmonth("February").withAyear("1988")
                    .withAddress2("Test16").withPhone2("Test17").withNotes("Test18").withGroup("Test7"));
        }
    }

    @Test (enabled = true)
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
