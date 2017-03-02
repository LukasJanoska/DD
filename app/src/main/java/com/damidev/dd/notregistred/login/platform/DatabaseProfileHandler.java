package com.damidev.dd.notregistred.login.platform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.damidev.dd.notregistred.login.dataaccess.Profile;


public class DatabaseProfileHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "profilesManager";

	// profiles table name
	private static final String TABLE_PROFILES = "profiles";

	// profiles Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_EMAIL = "email";
	private static final String KEY_PHONE = "phone";
	private static final String KEY_NAME = "name";
	private static final String KEY_LAST_NAME = "last_name";
	private static final String KEY_RIGHTS = "rights";
	private static final String KEY_PHOTO = "photo";
	private static final String KEY_FID = "fID";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_TOKEN = "token";

	public DatabaseProfileHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PROFILES_TABLE = "CREATE TABLE " + TABLE_PROFILES + "("
				+ KEY_ID + " INTEGER PRIMARY KEY,"
				+ KEY_EMAIL + " TEXT,"
				+ KEY_PHONE + " TEXT,"
				+ KEY_NAME + " TEXT,"
				+ KEY_LAST_NAME + " TEXT,"
				+ KEY_RIGHTS + " TEXT,"
				+ KEY_PHOTO + " TEXT,"
				+ KEY_FID + " TEXT,"
				+ KEY_DESCRIPTION + " TEXT,"
				+ KEY_TOKEN + " TEXT" + ")";
		db.execSQL(CREATE_PROFILES_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);

		// Create tables again
		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new profile
	public void addProfile(Profile profile) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, profile.get_id());
		values.put(KEY_EMAIL, profile.get_email());
		values.put(KEY_PHONE, profile.get_phone());
		values.put(KEY_NAME, profile.get_name());
		values.put(KEY_LAST_NAME, profile.get_last_name());
		values.put(KEY_RIGHTS, profile.get_rights());
		values.put(KEY_PHOTO, profile.get_photo());
		values.put(KEY_FID, profile.get_fID());
		values.put(KEY_DESCRIPTION, profile.get_description());
		values.put(KEY_TOKEN, profile.get_token());

		// Inserting Row
		db.insert(TABLE_PROFILES, null, values);
		db.close(); // Closing database connection
	}

	// Getting single profile
	public Profile getProfile(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_PROFILES, new String[] { KEY_ID,
                KEY_EMAIL, KEY_PHONE, KEY_NAME, KEY_LAST_NAME, KEY_RIGHTS, KEY_PHOTO, KEY_FID, KEY_DESCRIPTION, KEY_TOKEN }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Profile profile = new Profile(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9));
		// return profile
		return profile;
	}

/*	// Updating single profile
	public int updateProfile(Profile profile) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, profile.getName());
		values.put(KEY_PH_NO, profile.getPhoneNumber());

		// updating row
		return db.update(TABLE_PROFILES, values, KEY_ID + " = ?",
				new String[] { String.valueOf(profile.getID()) });
	}

	// Deleting single profile
	public void deleteProfile(Profile profile) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PROFILES, KEY_ID + " = ?",
				new String[] { String.valueOf(profile.getID()) });
		db.close();
	}*/

}
