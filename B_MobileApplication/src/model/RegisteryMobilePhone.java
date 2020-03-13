package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class RegisteryMobilePhone {
    private ArrayList<Contact> myContacts=new ArrayList<>();
    private String filePath = ("PhoneContactData.txt");


    public ArrayList<Contact> getContacts() {
        return myContacts;
    }


    public void addNewContact(String name, String phoneNumber){

        Contact contact=new Contact(name, phoneNumber);

        myContacts.add(contact);

    }

    //    public boolean modifyContact(Contact currentName,Contact newContact){
//        int foundPosition=findContact(currentName);//returning integer value of this contact
//       if(foundPosition<0){
//           return false;
//       }
//
//        this.myContacts.set(foundPosition,newContact);
//       return true;
//    }
    public Contact queryContact(String name){//searching for a name
        int position=findContact(name);
        if(position>=0){
            return this.myContacts.get(position);//returning index of this contact
        }
        return null;
    }
    private int findContact(Contact contact) {//finding the index of this contact
        return this.myContacts.indexOf(contact);
    }

    private int findContact(String contactName){
        for(int i =0; i<this.myContacts.size();i++){
            Contact contact=this.myContacts.get(i);//get this contact
            if(contact.getName().equals(contactName)){
                return i;
            }
        }
        return -1; // why i or -1 because i will use later in the code if this.list >=0 then do this
    }


    public boolean removeContact(Contact contact) {
        int foundPosition = findContact(contact);
        if(foundPosition <0) {
            System.out.println(contact.getName() +", was not found.");
            return false;
        }
        this.myContacts.remove(foundPosition);
        System.out.println(contact.getName() + ", was deleted.");
        return true;
    }
    /*
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

     */
    /* Method for returning all of the members */
    public Iterator<Contact> getContactIterator() {
        return this.myContacts.iterator();
    }

    // Method for saving a registry to a text file
    public void saveRegistry(){
        StringBuilder printer=new StringBuilder();
        Iterator<Contact> contactIterator=this.getContactIterator();
        try{
            PrintWriter outputFile=new PrintWriter(new FileOutputStream(this.filePath, false));
            while (contactIterator.hasNext())  {
                Contact contact=contactIterator.next();
                printer.append(contact.getName()).append("%").append(contact.getPhoneNumber()).append("%%");
                outputFile.println(printer);
                printer=new StringBuilder();
            }
            outputFile.close();
        }catch (IOException e1  ){

        }
    }
    /* Method for loading a registry from a text file */
    public void loadRegistry() throws FileNotFoundException {
//        filepath=("C://ProjectFilesDesktop20//JavaProgrammingMasterClassJan20//src//E_arraysLists//E_GroceryList//B_MobileApplication//PhoneContactData.txt");
        File file = new File(this.filePath);
        /* If file exists and if it ends with .txt */
        if(file.exists()){
            FileInputStream fileInputStream=new FileInputStream(file);
            BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(fileInputStream));
            this.myContacts.clear();
            String name="";
            String pNumber="";
            String strLine;
            char symbol='%';
            try{
                while((strLine=bufferedReader.readLine())!=null){
                    String reader = "";
                    int counter = 0;
                    /* Loop that goes through the file */
                    for(int i =0; i<strLine.length()+1;i++){
                        if(counter==1){// If there is 1 % symbol, set value to name
                            name=reader;
                            reader="";
                            counter=0;

                        }else if(counter==2){// If there are 2 % symbols, set value to pNumber
                            pNumber=reader;
                            reader="";
                            counter=0;
                            addNewContact(name,pNumber);

                        }
                        String start=strLine+ "       ";
                        if(start.charAt(i)==symbol){
                            counter=1;
                            if(start.charAt(i+1)==symbol){
                                counter=2;
                                i++;
                            }
                        }else {
                            if(i!=strLine.length()){
                                reader +=strLine.charAt(i);
                            }
                        }

                    }
                }
            }catch (IOException e){
                e.getMessage();
            }
        }

    }
}

