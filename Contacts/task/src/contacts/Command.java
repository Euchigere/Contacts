package contacts;

import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class Command {
    final Scanner in = new Scanner(System.in);
    List<Record> records = new ArrayList<>();
    final File filename = new File("PhoneBook.db");
    String newLine = "";
    boolean runApp = true;

    /*Command() {
        if (filename.isFile()) {
            try {
                records = (ArrayList) SerializationUtil.deserialize(filename);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            records = new ArrayList<>();
        }
    }*/

    void menu() {
        while (runApp) {
            String action = getInput(newLine + "[menu] Enter action (add, list, search, count, exit):");
            newLine = "\n";
            
            switch (action) {
                case "add":
                    add();
                    break;
                case "list":
                    list();
                        break;
                case "search":
                    search();
                    break;
                case "count":
                    System.out.println("The  Phone Book has " + count() + " records.");
                    break;
                default:
                    runApp = false;
            }
        }
    }
    
    void search() {
        String query = getInput("Enter search query:").toLowerCase();
        List<Integer> indexArray = new ArrayList<>();
        Record record;
        int count = 0;

        for (int i = 0; i < count(); i++) {
            record = getRecord(i);
            if (record.fullName().toLowerCase().contains(query) || record.getNumber().contains(query)) {
                indexArray.add(i);
                count++;
            }
        }

        System.out.println("Found " + count + " result:");
        if (count > 0) {
            count = 0;
            for (int index : indexArray) {
                System.out.println(++count + ". " + getRecord(index).fullName());
            }
        }

        String action = getInput("\n[search] Enter action ([number], back, again):");
        if ("back".equals(action)) {
            menu();
        } else if ("again".equals(action)) {
            search();
        } else {
            record(indexArray.get(Integer.parseInt(action) - 1));
        }
    }

    void record(int index) {
        System.out.println(getRecord(index).info());

        String action = getInput("\n[record] Enter action (edit, delete, menu):");
        if ("edit".equals(action)) {
            edit(getRecord(index));
        }  else if ("delete".equals(action)) {
            deleteRecord(index);
        } else {
            menu();
        }
    }

    void list() {
        if (count() <= 0) {
            System.out.println("Empty record!");
            return;
        }
        int count = 0;
        for (Record record : records) {
            System.out.println(++count + ". " + record.fullName());
        }

        String action = getInput("\n[list] Enter action ([number], back):");
        if ("back".equals(action)) {
            menu();
            return;
        }
        record(Integer.parseInt(action) - 1);
    }

    String getInput(String message) {
        System.out.print(message + " ");
        return in.nextLine();
    }

    void add() {
        Record record;
        String recordType = getInput("\nEnter the type (person, organization)");
        if ("person".equals(recordType)) {
            record = new Person();
            record.setField("name", getInput("Enter the name of the person:"));
            record.setField("surname", getInput("Enter the surname of the person:"));
            record.setField("birthDate", getInput("Enter the birth date:"));
            record.setField("gender", getInput("Enter the gender:"));
            record.setField("number", getInput("Enter the number:"));
            record.setTimeCreated();
            record.setLastEdited();
        } else {
            record = new Organization();
            record.setField("name", getInput("Enter the organization name:"));
            record.setField("address", getInput("Enter the address:"));
            record.setField("number", getInput("Enter the number:"));

            record.setTimeCreated();
            record.setLastEdited();
        }
        records.add(record);
        System.out.println("\nThe record added.\n" + record.info());

        /*try {
            SerializationUtil.serialize(records, filename);
            records = (ArrayList) SerializationUtil.deserialize(filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    void edit(Record record) {
        String fieldName = getInput("Select a field ("
            + Arrays.toString(record.getEditableFields())
                .replace("[", "").replace("]", "").trim()
            + "):");
        record.setField(fieldName,
                getInput("Enter " + fieldName));
        record.setLastEdited();
        System.out.println("Saved\n" + record.info());

        /*try {
            SerializationUtil.serialize(records, filename);
            records =  (ArrayList) SerializationUtil.deserialize(filename);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
        record(records.indexOf(record));
    }

    int count() {
        return records.size();
    }

    Record getRecord(int index) {
        return records.get(index);
    }

    void deleteRecord(int index) {
        records.remove(index);
    }
}