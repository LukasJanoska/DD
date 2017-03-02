package com.damidev.dd.notregistred.login.dataaccess;

public class Profile {

	//private variables
	int _id;
	String _email;
	String _phone;
	String _name;
	String _last_name;
	String _rights;
	String _photo;
	String _fID;
	String _description;
	String _token;

	// Empty constructor
	public Profile(){

	}

    public Profile(int _id, String _email, String _phone, String _name, String _last_name, String _rights, String _photo, String _fID, String _description, String _token) {
        this._id = _id;
        this._email = _email;
        this._phone = _phone;
        this._name = _name;
        this._last_name = _last_name;
        this._rights = _rights;
        this._photo = _photo;
        this._fID = _fID;
        this._description = _description;
        this._token = _token;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_phone() {
        return _phone;
    }

    public void set_phone(String _phone) {
        this._phone = _phone;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_last_name() {
        return _last_name;
    }

    public void set_last_name(String _last_name) {
        this._last_name = _last_name;
    }

    public String get_rights() {
        return _rights;
    }

    public void set_rights(String _rights) {
        this._rights = _rights;
    }

    public String get_photo() {
        return _photo;
    }

    public void set_photo(String _photo) {
        this._photo = _photo;
    }

    public String get_fID() {
        return _fID;
    }

    public void set_fID(String _fID) {
        this._fID = _fID;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_token() {
        return _token;
    }

    public void set_token(String _token) {
        this._token = _token;
    }
}
