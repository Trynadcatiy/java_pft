package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        type(By.name("home"), contactData.getHomeTelephone());
        type(By.name("mobile"), contactData.getMobileTelephone());
        type(By.name("work"), contactData.getWorkTelephone());
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

        if (! creation) {
            Assert.assertFalse(isElementPresent(By.name("new_group")));

        } else if (contactData.getGroup() != null) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("[none]");
        }
    }


    public void initContactCreation() {
        click(By.linkText("add new"));
    }


    public void initContactModificationById(int id) {
        wd.findElement(By.xpath("//input[@value='"+id+"']/../..//img[@title='Edit']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }


    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
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
        returnToHomePage();

    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        initContactDeletion();
        acceptContactDeletion();
        contactDeletionConfirmed();
    }

    public boolean isThereAContact(){
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("entry")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));

        for (WebElement element : elements){
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname);
            contacts.add(contact);
        }
        return contacts;
    }
}
