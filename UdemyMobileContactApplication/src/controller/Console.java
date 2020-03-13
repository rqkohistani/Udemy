package controller;

import model.Contact;
import model.ContactData;
import model.RegisteryMobilePhone;
import view.MainView;

import java.io.IOException;

public class Console {
    public MainView mainView;
    private RegisteryMobilePhone registeryMobilePhone;
    private ContactData contactData;

    public Console() throws IOException {
        mainView = new MainView();

        registeryMobilePhone = new RegisteryMobilePhone();
        contactData = new ContactData();
        contactData.loadRegistry(this.registeryMobilePhone);
        mainView.startPhone();

    }

    public void start() {
        boolean quit = false;
        mainView.printActions();
        while (!quit) {
            System.out.println("\nEnter action: (6 to show available actions)");
            int action = mainView.input().nextInt();

            switch (action) {
                case 0:

                    System.out.println("\nShutting down...");
                    quit = true;
                    break;

                case 1:
                    printContacts();
                    break;

                case 2:
                    addNewContact();
                    break;

                case 3:
                    updateContact();
                    break;

                case 4:
                    removeContact();
                    break;

                case 5:
                    queryContact();
                    break;

                case 6:
                    printActions();
                    break;
            }
            exit();

        }

    }

    private void exit() {
        contactData.saveRegistry(this.registeryMobilePhone);

    }

    public void queryContact() {
        System.out.println("Enter Contact name");
        String name = mainView.input().nextLine();
        Contact contact = registeryMobilePhone.queryContact(name);
        if (contact != null) {
            System.out.println("Contact found " + registeryMobilePhone.queryContact(name).getName() + " -> "
                    + registeryMobilePhone.queryContact(name).getPhoneNumber());
        } else {
            System.out.println("contact not found");
        }

    }

    public void updateContact() {
        System.out.println("Enter Contact name");
        String oldContact = mainView.input().nextLine();

        Contact contact = registeryMobilePhone.queryContact(oldContact);

        if (contact == null) {
            System.out.println("Contact not found");
            return;
        }
        System.out.print("Enter new contact name: ");
        String newName = mainView.input().nextLine();
        System.out.print("Enter new contact phone number: ");
        String newNumber = mainView.input().nextLine();
        System.out.print(contact.getName() + " " + contact.getPhoneNumber());

        int t = this.registeryMobilePhone.getContacts().indexOf(contact);
        this.registeryMobilePhone.getContacts().get(t).setName(newName);
        this.registeryMobilePhone.getContacts().get(t).setPhoneNumber(newNumber);
        System.out.println(" has been modified to " + this.registeryMobilePhone.getContacts().get(t).getName() + " "
                + this.registeryMobilePhone.getContacts().get(t).getPhoneNumber());
    }

    public void removeContact() {
        System.out.println("Enter Contact name");
        String name = mainView.input().nextLine();
        Contact contact = registeryMobilePhone.queryContact(name);
        if (contact != null) {
            registeryMobilePhone.removeContact(this.registeryMobilePhone.queryContact(name));
        } else {
            System.out.println("contact not found");
        }

    }

    public void printContacts() {
        mainView.printContactLists(this.registeryMobilePhone);
        mainView.printActions();
    }

    private void addNewContact() {
        System.out.println("Enter new contact name: ");
        String name = mainView.input().nextLine();
        System.out.println("Enter phone number: ");
        String phone = mainView.input().nextLine();
        registeryMobilePhone.addNewContact(name, phone);
        System.out.println("New contact added: name = " + name + ", phone = " + phone);
    }

    public void printActions() {
        mainView.printContactLists(this.registeryMobilePhone);
        mainView.printActions();
    }

}