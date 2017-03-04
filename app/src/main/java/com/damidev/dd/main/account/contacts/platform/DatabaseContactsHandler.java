package com.damidev.dd.main.account.contacts.platform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.damidev.dd.shared.dataaccess.Contact;

import java.util.ArrayList;
import java.util.List;


public class DatabaseContactsHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "contactsManager";

	// contacts table name
	private static final String TABLE_CONTACTS = "contacts";

	// contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_PHONE = "phone";
	private static final String KEY_NAME = "name";
	private static final String KEY_LAST_NAME = "last_name";
	private static final String KEY_FID = "fID";
	private static final String KEY_DESCRIPTION = "description";

	public DatabaseContactsHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_EMAIL + " TEXT,"
				+ KEY_PHONE + " TEXT,"
				+ KEY_NAME + " TEXT,"
				+ KEY_LAST_NAME + " TEXT,"
				+ KEY_FID + " TEXT,"
				+ KEY_DESCRIPTION + " TEXT" + ")";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new contact
	public void addContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, contact.getId());
		values.put(KEY_EMAIL, contact.getEmail());
		values.put(KEY_PHONE, contact.getPhone());
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_LAST_NAME, contact.getLastname());
		values.put(KEY_FID, contact.getFid());
		values.put(KEY_DESCRIPTION, contact.getDescription());

		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close(); // Closing database connection
	}

	// Getting All Contacts
	public List<Contact> getAllContacts() {
		List<Contact> contactList = new ArrayList<Contact>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Contact contact = new Contact();
				contact.setId(Integer.parseInt(cursor.getString(0)));
				contact.setEmail(cursor.getString(1));
				contact.setPhone(cursor.getString(2));
				contact.setName(cursor.getString(3));
				contact.setLastname(cursor.getString(4));
				contact.setFid(cursor.getString(5));
				contact.setDescription(cursor.getString(6));
				// Adding contact to list
				contactList.add(contact);
			} while (cursor.moveToNext());
		}
		// return contact list
		return contactList;
	}

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getId()) });
        db.close();
    }

    // Deleting single contact
    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();

        List<Contact> contactList = new ArrayList<Contact>();
        contactList = getAllContacts();
        for (Contact c : contactList) {
            deleteContact(c);
        }
        db.close();
    }

	public int updateContact(Contact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_LAST_NAME, contact.getLastname());
        values.put(KEY_PHONE, contact.getPhone());
		values.put(KEY_EMAIL, contact.getEmail());
		values.put(KEY_DESCRIPTION, contact.getDescription());

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getId()) });
	}

	/*// Getting single contact
	public Profile getProfile(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                KEY_EMAIL, KEY_PHONE, KEY_NAME, KEY_LAST_NAME, KEY_RIGHTS, KEY_PHOTO, KEY_FID, KEY_DESCRIPTION, KEY_TOKEN }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Profile contact = new Profile(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9));
		// return contact
		return contact;
	}*/

	/*// Updating single contact
	public int updateProfile(Profile contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_EMAIL, contact.get_email());
		values.put(KEY_PHONE, contact.get_phone());
		values.put(KEY_NAME, contact.get_name());
		values.put(KEY_LAST_NAME, contact.get_last_name());
		values.put(KEY_DESCRIPTION, contact.get_description());

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.get_id()) });
	}
	*/

/*
	// Deleting single contact
	public void deleteProfile(Profile contact) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
		db.close();
	}
*/

}
