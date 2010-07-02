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
public class ProfessionalExperience extends Experience {
	
	@Persistent
	private String position;
	
	@Persistent
	private String company;
	
	public ProfessionalExperience(String desc, Date start, Date end, String position, String company){
		super(desc, start, end);
		this.position = position;
		this.company = company;
	}
	
	public String getPosition(){
		return position;
	}
	
	public void setPosition(String position){
		this.position = position;
	}
	
	public String getCompany(){
		return company;
	}
	
	public void setCompany(String company){
		this.company = company;
	}
}
