package com.damidev.dd.shared.dataaccess;

public class Contact {
	
	//private variables
	private int id;

	private String email;

	private String phone;

	private String name;

	private String lastname;

/*    private File lastname;*/

	private String fid;

	private String description;
	
	// Empty constructor
	public Contact(){
	}

    public Contact(int id, String email, String phone, String name, String lastname, String fid, String description) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.lastname = lastname;
        this.fid = fid;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
