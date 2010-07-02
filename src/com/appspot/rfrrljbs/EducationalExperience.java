/**
 * 
 */
package com.appspot.rfrrljbs;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import java.util.Date;

/**
 * @author Amit
 *
 */
@PersistenceCapable
public class EducationalExperience extends Experience {
	
	@Persistent
	private String course;
	
	@Persistent
	private String institution;
	
	public EducationalExperience(String desc, Date start, Date end, String course, String institution){
		super(desc, start, end);
		this.course = course;
		this.institution = institution;
	}	
	
	
	public String getCourse(){
		return course;
	}
	
	public void setCourse(String course){
		this.course = course;
	}
	
	public String getInstitution(){
		return institution;
	}
	
	public void setInstitution(String institution){
		this.institution = institution;
	}
}
