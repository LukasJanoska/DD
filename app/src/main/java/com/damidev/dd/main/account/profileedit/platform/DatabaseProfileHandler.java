/*
package com.damidev.dd.main.account.profileedit.platform;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.damidev.dd.shared.dataaccess.Profile;


public class DatabaseProfileHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "profilesManager";

	// profiles table name
	private static final String TABLE_PROFILES = "profiles";

	// profiles Table Columns names
	private static final String KEY_PROFILE_ID = "id";
	private static final String KEY_PROFILE_EMAIL = "email";
	private static final String KEY_PROFILE_PHONE = "phone";
	private static final String KEY_PROFILE_NAME = "name";
	private static final String KEY_PROFILE_LAST_NAME = "last_name";
	private static final String KEY_PROFILE_RIGHTS = "rights";
	private static final String KEY_PROFILE_PHOTO = "photo";
	private static final String KEY_PROFILE_FID = "fID";
	private static final String KEY_PROFILE_DESCRIPTION = "description";
	private static final String KEY_PROFILE_TOKEN = "token";
	private static final String KEY_PROFILE_MAP = "map";

	public DatabaseProfileHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_PROFILES_TABLE = "CREATE TABLE " + TABLE_PROFILES + "("
				+ KEY_PROFILE_ID + " INTEGER PRIMARY KEY,"
				+ KEY_PROFILE_EMAIL + " TEXT,"
				+ KEY_PROFILE_PHONE + " TEXT,"
				+ KEY_PROFILE_NAME + " TEXT,"
				+ KEY_PROFILE_LAST_NAME + " TEXT,"
				+ KEY_PROFILE_RIGHTS + " TEXT,"
				+ KEY_PROFILE_PHOTO + " TEXT,"
				+ KEY_PROFILE_FID + " TEXT,"
				+ KEY_PROFILE_DESCRIPTION + " TEXT,"
				+ KEY_PROFILE_TOKEN + " TEXT,"
				+ KEY_PROFILE_MAP + " TEXT" + ")";
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

	*/
/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 *//*


	// Adding new profile
	public void addProfile(Profile profile) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PROFILE_ID, profile.get_id());
		values.put(KEY_PROFILE_EMAIL, profile.get_email());
		values.put(KEY_PROFILE_PHONE, profile.get_phone());
		values.put(KEY_PROFILE_NAME, profile.get_name());
		values.put(KEY_PROFILE_LAST_NAME, profile.get_last_name());
		values.put(KEY_PROFILE_RIGHTS, profile.get_rights());
		values.put(KEY_PROFILE_PHOTO, profile.get_photo());
		values.put(KEY_PROFILE_FID, profile.get_fID());
		values.put(KEY_PROFILE_DESCRIPTION, profile.get_description());
		values.put(KEY_PROFILE_TOKEN, profile.get_token());
		values.put(KEY_PROFILE_MAP, profile.get_map());

		// Inserting Row
		db.insert(TABLE_PROFILES, null, values);
		db.close(); // Closing database connection
	}

	// Getting single profile
	public Profile getProfile(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_PROFILES, new String[] { KEY_PROFILE_ID,
                KEY_PROFILE_EMAIL, KEY_PROFILE_PHONE, KEY_PROFILE_NAME, KEY_PROFILE_LAST_NAME, KEY_PROFILE_RIGHTS, KEY_PROFILE_PHOTO, KEY_PROFILE_FID, KEY_PROFILE_DESCRIPTION, KEY_PROFILE_TOKEN, KEY_PROFILE_MAP }, KEY_PROFILE_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Profile profile = new Profile(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10));
		// return profile
		return profile;
	}

	// Updating single profile
	public int updateProfile(Profile profile) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PROFILE_EMAIL, profile.get_email());
		values.put(KEY_PROFILE_PHONE, profile.get_phone());
		values.put(KEY_PROFILE_NAME, profile.get_name());
		values.put(KEY_PROFILE_LAST_NAME, profile.get_last_name());
		values.put(KEY_PROFILE_DESCRIPTION, profile.get_description());

		// updating row
		return db.update(TABLE_PROFILES, values, KEY_PROFILE_ID + " = ?",
				new String[] { String.valueOf(profile.get_id()) });
	}

*/
/*
	// Deleting single profile
	public void deleteProfile(Profile profile) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_PROFILES, KEY_PROFILE_ID + " = ?",
				new String[] { String.valueOf(profile.getID()) });
		db.close();
	}
*//*


}
*/
