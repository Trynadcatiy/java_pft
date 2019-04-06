package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @XStreamOmitField
    @Column(name = "middlename")
    private String middlename;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @XStreamOmitField
    @Column(name = "nickname")
    private String nickname;
    @XStreamOmitField
    @Column(name = "title")
    private String title;
    @XStreamOmitField
    @Column(name = "company")
    private String company;
    @XStreamOmitField
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @XStreamOmitField
    @Column(name = "fax")
    @Type(type = "text")
    private String fax;
    @XStreamOmitField
    @Column(name = "email")
    @Type(type = "text")
    private String email;
    @XStreamOmitField
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;
    @XStreamOmitField
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;
    @XStreamOmitField
    @Column(name = "homepage")
    @Type(type = "text")
    private String homepage;
    @XStreamOmitField
    @Column(name = "bday")
    @Type(type = "byte")
    private byte bday;
    @XStreamOmitField
    @Column(name = "bmonth")
    private String bmonth;
    @XStreamOmitField
    @Column(name = "byear")
    private String byear;
    @XStreamOmitField
    @Column(name = "aday")
    @Type(type = "byte")
    private byte aday;
    @XStreamOmitField
    @Column(name = "amonth")
    private String amonth;
    @XStreamOmitField
    @Column(name = "ayear")
    private String ayear;
    @XStreamOmitField
    @Column(name = "address2")
    @Type(type = "text")
    private String address2;
    @XStreamOmitField
    @Column(name = "phone2")
    @Type(type = "text")
    private String phone2;
    @XStreamOmitField
    @Column(name = "notes")
    @Type(type = "text")
    private String notes;
    @XStreamOmitField
    @Transient
    private String group;
    @XStreamOmitField
    @Transient
    private String allPhones;
    @XStreamOmitField
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;
    @XStreamOmitField
    @Transient
    private String allEmails;

    public ContactData(ContactData contact) {
        this.id = contact.getId();
        this.firstname = contact.getFirstname();
        this.middlename = contact.getMiddlename();
        this.lastname = contact.getLastname();
        this.nickname = contact.getNickname();
        this.title = contact.getTitle();
        this.company = contact.getCompany();
        this.address = contact.getAddress();
        this.homePhone = contact.getHomePhone();
        this.mobilePhone = contact.getMobilePhone();
        this.workPhone = contact.getWorkPhone();
        this.fax = contact.getFax();
        this.email = contact.getEmail();
        this.email2 = contact.getEmail2();
        this.email3 = contact.getEmail3();
        this.homepage = contact.getHomepage();
        this.bday = Byte.parseByte(contact.getBday());
        this.bmonth = contact.getBmonth();
        this.byear = contact.getByear();
        this.aday = Byte.parseByte(contact.getAday());
        this.amonth = contact.getAmonth();
        this.ayear = contact.getAyear();
        this.address2 = contact.getAddress2();
        this.phone2 = contact.getPhone2();
        this.notes = contact.getNotes();
        this.group = contact.getGroup();
        this.allPhones = contact.getAllPhones();
        this.allEmails = contact.getAllEmails();
    }

    public ContactData() {

    }

    public File getPhoto() {
        if (photo != null) {
            return new File(photo);
        }
        return null;
    }

    public String getAllPhones() {
        return allPhones;
    }


    public String getAllEmails() {
        return allEmails;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFax() {
        return fax;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBday() {
        return Byte.toString(bday);
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }

    public String getAday() {
        return Byte.toString(aday);
    }

    public String getAmonth() {
        return amonth;
    }

    public String getAyear() {
        return ayear;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getNotes() {
        return notes;
    }

    public String getGroup() {
        return group;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withMiddlename(String middlename) {
        this.middlename = middlename;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withTitle(String title) {
        this.title = title;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withFax(String fax) {
        this.fax = fax;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public ContactData withBday(String bday) {
        this.bday = Byte.parseByte(bday);
        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactData withAday(String aday) {
        this.aday = Byte.parseByte(aday);
        return this;
    }

    public ContactData withAmonth(String amonth) {
        this.amonth = amonth;
        return this;
    }

    public ContactData withAyear(String ayear) {
        this.ayear = ayear;
        return this;
    }

    public ContactData withAddress2(String address2) {
        this.address2 = address2;
        return this;
    }

    public ContactData withPhone2(String phone2) {
        this.phone2 = phone2;
        return this;
    }

    public ContactData withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id &&
                bday == that.bday &&
                aday == that.aday &&
                Objects.equals(firstname, that.firstname) &&
                Objects.equals(middlename, that.middlename) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(nickname, that.nickname) &&
                Objects.equals(title, that.title) &&
                Objects.equals(company, that.company) &&
                Objects.equals(address, that.address) &&
                Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(workPhone, that.workPhone) &&
                Objects.equals(fax, that.fax) &&
                Objects.equals(email, that.email) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3) &&
                Objects.equals(homepage, that.homepage) &&
                Objects.equals(bmonth, that.bmonth) &&
                Objects.equals(byear, that.byear) &&
                Objects.equals(ayear, that.ayear) &&
                Objects.equals(address2, that.address2) &&
                Objects.equals(phone2, that.phone2) &&
                Objects.equals(notes, that.notes) &&
                Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, middlename, lastname, nickname, title, company, address, homePhone, mobilePhone, workPhone, fax, email, email2, email3, homepage, bday, bmonth, byear, aday, ayear, address2, phone2, notes, group);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
