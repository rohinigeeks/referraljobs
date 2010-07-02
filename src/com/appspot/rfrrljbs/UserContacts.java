/**
 * 
 */
package com.appspot.rfrrljbs;
import com.google.appengine.api.datastore.Email;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.util.*;

/**
 * @author KumarAM
 *
 */
@PersistenceCapable
public class UserContacts {

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private User user;
	
	private List<Email> userContacts;
	
	public UserContacts(User user){
		this.user = user;
	}
	
	public Key getKey(){
		return key;
	}
	
	public User getUser(){
		return user;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public List<Email> getUserContacts(){
		return userContacts;
	}
	
	public void setUserContacts(List<Email> userContacts){
		if(this.userContacts == null)
			this.userContacts = new ArrayList<Email>(userContacts);
		else
			this.userContacts.addAll(userContacts);
	}
	
}
