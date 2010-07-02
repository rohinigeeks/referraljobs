package com.appspot.rfrrljbs;

import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.appspot.rfrrljbs.Address;

@PersistenceCapable
public class Company {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
	private String name;

	@Persistent
	private String desc;
	
	@Persistent
	private Address address;
	
	@Persistent
	private String phone;
	
	@Persistent
	private String fax;
	
	@Persistent
	private Email email;
	
	@Persistent
	private User creator;

	
	public Company(String name, String desc, Address address, String phone, Email email, String fax, User creator) {
        this.name = name;        
        this.desc = desc;
        this.address = address;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.creator = creator;
    }

    public Key getKey() {
        return key;
    }
	
    public String getName(){
    	return name;
    }
	
    public void setName(String name){
    	this.name = name;
    }
    
    public String getDesc(){
    	return desc;
    }
	
    public void setDesc(String desc){
    	this.desc = desc;
    }
    
    public Address getAddress(){
    	return address;
    }
    
    public void setAddress(Address address){
    	this.address = address;
    }
    
    public String getPhone(){
    	return phone;
    }
    
    public void setPhone(String phone){
    	this.phone = phone;
    }
    
    public String getFax(){
    	return fax;
    }
    
    public void setFax(String fax){
    	this.fax = fax;
    }
    
    public Email getEmail(){
    	return email;
    }
    
    public void setEmail(Email email){
    	this.email = email;
    }
    
    
    public User getCreator(){
		return creator;
	}
	
	public void setCreator(User creator){
		this.creator = creator;
	}
}
