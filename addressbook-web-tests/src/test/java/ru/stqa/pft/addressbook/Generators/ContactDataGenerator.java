package ru.stqa.pft.addressbook.Generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter (names = "-c", description = "Contact count")
    public int count;

    @Parameter (names = "-f", description = "Target file")
    public String file;

    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }

        generator.run();
    }

    public void run() throws IOException {
        List<ContactData> contacts = generatorContacts(count);
        save(contacts, new File(file));
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
