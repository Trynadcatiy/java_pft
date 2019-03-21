package ru.stqa.pft.addressbook.Generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
    public static void main(String[] args) throws IOException {
        int count = Integer.parseInt(args[0]);
        File file = new File(args[1]);

        List<ContactData> contacts = generatorContacts(count);
        save(contacts, file);
    }

    private static void save(List<ContactData> contacts, File file) throws IOException {
        Writer writer = new FileWriter(file);
        for (ContactData contact:contacts){
            writer.write(String.format("%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname()
                    , contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone()));
        }
        writer.close();
    }

    private static List<ContactData> generatorContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++){
            contacts.add(new ContactData()
                    .withFirstname(String.format("Firstname %s", i)).withLastname(String.format("Lastname %s", i))
                    .withHomePhone(String.format("+1 234 (567) 8%s", i)).withMobilePhone(String.format("+2 234 (567) 8%s", i))
                    .withWorkPhone(String.format("+3 234 (567) 8%s", i)));
        }
        return contacts;
    }
}
