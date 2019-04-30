package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
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
    }

    @Test
    public void testContactDeletionFromGroup() {
        ContactData before = app.db().contacts().iterator().next();
        GroupData group = app.db().groups().iterator().next();
        app.goTo().homePage();
        app.contact().removeFromGroup(before, group);
    }
}
