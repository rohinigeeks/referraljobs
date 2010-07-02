/**
 * 
 */
package com.appspot.rfrrljbs;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import java.util.Date;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.appspot.rfrrljbs.Company;

/**
 * @author Amit
 *
 */
@PersistenceCapable
public class Referral {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
    private User author;
	
	@Persistent
    private String position;
	
	@Persistent
    private String desc;	
	
	@Persistent
    private Integer numPosition;	
		
	@Persistent
	private Company company;
	
	@Persistent
	private String location;
	
	@Persistent
    private Date lastDate;

    public Referral(User author, String position, String desc, Integer numPosition, Company company,String location, Date lastDate) {
        this.author = author;
        this.position = position;
        this.desc = desc;
        this.numPosition = numPosition;
        this.company = company;
        this.location = location;
        this.lastDate = lastDate;
    }

    public Key getKey() {
        return key;
    }
    
    public User getUser(){
    	return author;
    }
    
    public void setUser(User author){
    	this.author = author;
    }
    
    public String getPosition(){
    	return position;
    }
    
    public void setPosition(String position){
    	this.position = position;
    }
    
    public String getDesc(){
    	return desc;
    }
    
    public void setDesc(String desc){
    	this.desc = desc;
    }
    
    public Integer getNumber(){
    	return numPosition;
    }
    
    public void setNumber(Integer numPosition){
    	this.numPosition = numPosition;
    }
    
    public String Location(){
    	return location;
    }
    
    public void setLocation(String location){
    	this.location = location;
    }
    
    public Company getCompany(){
    	return company;
    }
    
    public void setCompany(Company company){
    	this.company = company;
    }
    
	public Date getLastDate() {
        return lastDate;
    }
	
	public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }
}
