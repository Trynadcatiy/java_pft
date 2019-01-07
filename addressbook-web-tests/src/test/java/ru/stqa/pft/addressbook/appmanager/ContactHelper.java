package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper {
    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void submitContactCreation() {
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void fillContactForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
        wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
        wd.findElement(By.name("title")).sendKeys(contactData.getTitle());
        wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("home")).sendKeys(contactData.getHomeTelephone());
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobileTelephone());
        wd.findElement(By.name("work")).sendKeys(contactData.getWorkTelephone());
        wd.findElement(By.name("fax")).sendKeys(contactData.getFax());
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
        wd.findElement(By.name("email2")).sendKeys(contactData.getEmail2());
        wd.findElement(By.name("email3")).sendKeys(contactData.getEmail3());
        wd.findElement(By.name("homepage")).sendKeys(contactData.getHomepage());
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getBmonth());
        wd.findElement(By.name("byear")).sendKeys(contactData.getByear());
        new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getAday());
        new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getAmonth());
        wd.findElement(By.name("ayear")).sendKeys(contactData.getAyear());
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        wd.findElement(By.name("address2")).sendKeys(contactData.getAddress2());
        wd.findElement(By.name("phone2")).sendKeys(contactData.getPhone2());
        wd.findElement(By.name("notes")).sendKeys(contactData.getNotes());
    }

    public void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }
}
