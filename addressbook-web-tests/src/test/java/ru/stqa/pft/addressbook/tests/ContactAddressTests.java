package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstname("Pavel").withMiddlename("Petrovich").withLastname("Voronin")
                    .withNickname("Trynadcatiy").withTitle("Test").withCompany("DXBX")
                    .withAddress("SPb").withHomePhone("12-34-567").withMobilePhone("+79003002000")
                    .withWorkPhone("+79003002001").withFax("79003002002").withEmail("p.voronin@fakemail.ru")
                    .withEmail2("test1@fakemail.ru").withEmail3("test2@fakemail.ru").withHomepage("dxbx.ru").withBday("1")
                    .withBmonth("January").withByear("1987").withAday("2").withAmonth("February").withAyear("1988")
                    .withAddress2("Test16").withPhone2("Test17").withNotes("Test18").withGroup("Test7"));
        }
    }

    @Test
    public void testContactAddress() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(),
                equalTo(contactInfoFromEditForm.getAddress().replaceAll("^[\\s]+|[\\s]+$", "")));
    }

}