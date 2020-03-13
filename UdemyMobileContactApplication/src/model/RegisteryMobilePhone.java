package model;

import java.util.ArrayList;
import java.util.Iterator;

public class RegisteryMobilePhone {
	private ArrayList<Contact> myContacts = new ArrayList<>();
//    private String filePath = ("PhoneContactData.txt");

	public ArrayList<Contact> getContacts() {
		return myContacts;
	}

	public void addNewContact(String name, String phoneNumber) {

		Contact contact = new Contact(name, phoneNumber);

		myContacts.add(contact);

	}

	public Contact queryContact(String name) {// searching for a name
		int position = findContact(name);
		if (position >= 0) {
			return this.myContacts.get(position);// returning index of this contact
		}
		return null;
	}

	private int findContact(Contact contact) {// finding the index of this contact
		return this.myContacts.indexOf(contact);
	}

	private int findContact(String contactName) {
		for (int i = 0; i < this.myContacts.size(); i++) {
			Contact contact = this.myContacts.get(i);// get this contact
			if (contact.getName().equals(contactName)) {
				return i;
			}
		}
		return -1; // why i or -1 because i will use later in the code if this.list >=0 then do
					// this
	}

	public boolean removeContact(Contact contact) {
		int foundPosition = findContact(contact);
		if (foundPosition < 0) {
			System.out.println(contact.getName() + ", was not found.");
			return false;
		}
		this.myContacts.remove(foundPosition);
		System.out.println(contact.getName() + ", was deleted.");
		return true;
	}

	/* Method for returning all of the members */
	public Iterator<Contact> getContactIterator() {
        return this.myContacts.iterator();
}
}
