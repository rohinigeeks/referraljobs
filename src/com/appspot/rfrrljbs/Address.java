/**
 * 
 */
package com.appspot.rfrrljbs;
import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.appspot.rfrrljbs.Address;

/**
 * @author Amit
 *
 */
@PersistenceCapable
public class Address {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private String address1;
	
	@Persistent
	private String address2;
	
	@Persistent
	private String city;
	
	@Persistent
	private String state;
	
	@Persistent
	private String country;
	
	@Persistent
	private String zipcode;
	
	
	public Address(String address1, String address2, String city, String state, String country, String zipcode){
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		
	}
	
	public Key getKey(){
		return key;
	}
	
	public String getAddress1(){
		return address1;
	}
	
	public void setAddress1(String address1){
		this.address1 = address1;
	}
	
	public String getAddress2(){
		return address2;
	}
	
	public void setAddress2(String address2){
		this.address2 = address2;
	}
	
	public String getCity(){
		return city;
	}
	
	public void setCity(String city){
		this.city = city;
	}
	
	public String getState(){
		return state;
	}
	
	public void setState(String state){
		this.state = state;
	}
	
	public String getCountry(){
		return country;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public String getZipCode(){
		return zipcode;
	}
	
	public void setZipCode(String zipcode){
		this.zipcode = zipcode;
	}
	
}
