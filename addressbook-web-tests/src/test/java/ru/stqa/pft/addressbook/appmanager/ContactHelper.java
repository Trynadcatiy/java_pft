package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFax());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("homepage"), contactData.getHomepage());
        select(By.name("bday"), contactData.getBday());
        select(By.name("bmonth"), contactData.getBmonth());
        type(By.name("byear"), contactData.getByear());
        select(By.name("aday"), contactData.getAday());
        select(By.name("amonth"), contactData.getAmonth());
        type(By.name("ayear"), contactData.getAyear());
        type(By.name("address2"), contactData.getAddress2());
        type(By.name("phone2"), contactData.getPhone2());
        type(By.name("notes"), contactData.getNotes());
        attach(By.name("photo"), contactData.getPhoto());

        if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                select(By.name("new_group"), contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }


    public void initContactModificationById(int id) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }


    public void selectContactById(int id) {
        click(By.cssSelector("input[value='" + id + "']"));
    }

    public void initContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void acceptContactDeletion() {
        acceptAlert();
    }

    public void contactDeletionConfirmed() {
        messageVisible("Record successful deleted");
    }

    public void create(ContactData contact) {

        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();

    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        initContactDeletion();
        acceptContactDeletion();
        contactCache = null;
        contactDeletionConfirmed();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("entry")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
            String firstname = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String address = cells.get(3).getText();
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email)
                .withEmail2(email2).withEmail3(email3).withAddress(address).withPhone2(phone2);
    }

    public void addToGroup(ContactData contact, GroupData group) {
        filterByGroup("[all]");
        selectContactById(contact.getId());
        initAddToGroup(group);
        contactAddtionToGroupConfirmed(group);
        returnToContactsFilteredByGroup(group);
    }

    public void returnToContactsFilteredByGroup(GroupData group) {
        click(By.linkText(String.format("group page \"%s\"", group.getName())));
    }

    public void contactAddtionToGroupConfirmed(GroupData group) {
        WebElement messegeBox = wd.findElement(By.xpath("//div[@class='msgbox']"));
        String expectedText = String.format("Users added.\nGo to group page \"%s\".", group.getName());
        if (messegeBox.getText().equals(expectedText)) {
            messegeBox.isDisplayed();
        }
    }

    public void initAddToGroup(GroupData group) {
        select(By.name("to_group"), group.getName());
        click(By.name("add"));
    }

    public void filterByGroup(String group) {
        select(By.name("group"), group);
    }
}
