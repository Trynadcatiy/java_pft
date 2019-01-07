package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        initContactCreation();
        fillContactForm(new ContactData("Pavel", "Petrovich", "Voronin", "Trynadcatiy", "Test", "DXBX", "SPb", "12-34-567", "+79003002000", "+79003002001", "79003002002", "p.voronin@fakemail.ru", "test1@fakemail.ru", "test2@fakemail.ru", "dxbx.ru", "1", "January", "1987", "2", "February", "1988", "Test1", "Test16", "Test17", "Test18"));
        submitContactCreation();
        returnToHomePage();
    }

}
