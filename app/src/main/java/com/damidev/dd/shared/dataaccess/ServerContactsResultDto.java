package com.damidev.dd.shared.dataaccess;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ServerContactsResultDto implements Serializable {

    @SerializedName("responseCodeText")
    private String responseCodeText;

    @SerializedName("responseCode")
    private int responseCode;

    @SerializedName("response")
    private ArrayList<ContactsResponse> contacts;

    public ServerContactsResultDto(String responseCodeText, int responseCode, ArrayList<ContactsResponse> contacts) {
        this.responseCodeText = responseCodeText;
        this.responseCode = responseCode;
        this.contacts = contacts;
    }

    public String getResponseCodeText() {
        return responseCodeText;
    }

    public void setResponseCodeText(String responseCodeText) {
        this.responseCodeText = responseCodeText;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public ArrayList<ContactsResponse> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<ContactsResponse> contacts) {
        this.contacts = contacts;
    }

    public class ContactsResponse implements Serializable {

        @SerializedName("id")
        private int id;

        @SerializedName("email")
        private String email;

        @SerializedName("phone")
        private String phone;

        @SerializedName("name")
        private String name;

        @SerializedName("lastname")
        private String lastname;

        /*@SerializedName("photo")
        private File lastname;*/

        @SerializedName("dID")
        private String fid;

        @SerializedName("description")
        private String description;

        public ContactsResponse(int id, String email, String phone, String name, String lastname, String fid, String description) {
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
}
