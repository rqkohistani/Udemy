package model;

import java.io.*;
import java.util.Iterator;

public class ContactData {

    private String phoneFilePath = ("PhoneContactData.txt");

    public void saveRegistry(RegisteryMobilePhone registeryMobilePhone) {

        StringBuilder printer = new StringBuilder();
        Iterator<Contact> contactIterator = registeryMobilePhone.getContactIterator();
        try {
            try (PrintWriter outputFile = new PrintWriter(new FileOutputStream(this.phoneFilePath, false))) {
                while (contactIterator.hasNext()) {
                    Contact contact = contactIterator.next();
                    printer.append(contact.getName()).append("%").append(contact.getPhoneNumber()).append("%%");
                    outputFile.println(printer);
                    printer = new StringBuilder();
                }
            }
        } catch (IOException e1) {

        }
    }

    /* Method for loading a registry from a text file */
    public void loadRegistry(RegisteryMobilePhone registeryMobilePhone) throws FileNotFoundException {
        File file = new File(this.phoneFilePath);
        /* If file exists and if it ends with .txt */
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
                registeryMobilePhone.getContacts().clear();
                String name = "";
                String pNumber = "";
                String strLine;
                char symbol = '%';
                try {
                    while ((strLine = bufferedReader.readLine()) != null) {
                        String reader = "";
                        int counter = 0;
                        /* Loop that goes through the file */
                        for (int i = 0; i < strLine.length() + 1; i++) {
                            if (counter == 1) {// If there is 1 % symbol, set value to name
                                name = reader;
                                reader = "";
                                counter = 0;

                            } else if (counter == 2) {// If there are 2 % symbols, set value to pNumber
                                pNumber = reader;
                                reader = "";
                                counter = 0;

                                registeryMobilePhone.addNewContact(name, pNumber);

                            }
                            String start = strLine + "       ";
                            if (start.charAt(i) == symbol) {
                                counter = 1;
                                if (start.charAt(i + 1) == symbol) {
                                    counter = 2;
                                    i++;
                                }
                            } else {
                                if (i != strLine.length()) {
                                    reader += strLine.charAt(i);
                                }
                            }

                        }
                    }
                } catch (IOException e) {
                    e.getMessage();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
