package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
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
    public void testContactModification() {
        ContactData contact = new ContactData()
                .withFirstname("Pavel").withMiddlename("Petrovich").withLastname("Voronin")
                .withNickname("Trynadcatiy").withBday("2")
                .withAday("3").withAmonth("April");
        List<ContactData> before = app.contact().list();
        int index = before.size()-1;
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.set(index, contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
