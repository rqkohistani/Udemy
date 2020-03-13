package view;

import java.util.Scanner;

import model.RegisteryMobilePhone;

public class MainView {

    public void startPhone() {

            System.out.println("Starting phone...");
        }

    public  void printActions() {
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0  - to shutdown\n" +
                "1  - to print contacts\n" +
                "2  - to add a new contact\n" +
                "3  - to update existing an existing contact\n" +
                "4  - to remove an existing contact\n" +
                "5  - query if an existing contact exists\n" +
                "6  - to print a list of available actions.");
        System.out.println("Choose your action: ");
    }
    public Scanner input(){
        return new Scanner(System.in);
    }

    public void printContactLists(RegisteryMobilePhone registeryMobilePhone  ) {
        for (int i = 0; i < registeryMobilePhone.getContacts().size(); i++) {
            System.out.println((1 + i) + " :" + registeryMobilePhone.getContacts().get(i).getName() +"->"+
                    registeryMobilePhone.getContacts().get(i).getPhoneNumber());
        }
    }














    }

