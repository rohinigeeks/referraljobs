/**
 * 
 */
package com.appspot.rfrrljbs;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.util.*;

/**
 * @author Amit
 *
 */
@PersistenceCapable
public class Profile {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	
	@Persistent
	private User user;
	
	@Persistent
	private List<ProfessionalExperience> professionalExp;
	
	@Persistent
	private List<EducationalExperience> educationalExp;
	
	public Profile(User user){
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
	
	public List<ProfessionalExperience> getProfessionalExp(){
		return professionalExp;
	}
	
	public void setProfessionalExp(List<ProfessionalExperience> professionalExp){
		if(this.professionalExp == null)
			this.professionalExp = new ArrayList<ProfessionalExperience>();
		this.professionalExp.addAll(professionalExp);		
	}
	
	public void setProfessionalExp(ProfessionalExperience professionalExp){
		if(this.professionalExp == null)
			this.professionalExp = new ArrayList<ProfessionalExperience>();		
		this.professionalExp.add(professionalExp);
	}
	
	public List<EducationalExperience> getEducationalExp(){
		return educationalExp;
	}
	
	public void setEducationalExp(List<EducationalExperience> educationalExp){
		if(this.educationalExp == null)
			this.educationalExp = new ArrayList<EducationalExperience>();
		this.educationalExp.addAll(educationalExp);		
	}
	
	public void setEducationalExp(EducationalExperience educationalExp){
		if(this.educationalExp == null)
			this.educationalExp = new ArrayList<EducationalExperience>();		
		this.educationalExp.add(educationalExp);
	}

}
