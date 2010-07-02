/**
 * 
 */
package com.appspot.rfrrljbs;
import com.google.appengine.api.datastore.Key;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import java.util.Date;

/**
 * @author Amit
 *
 */
@PersistenceCapable
public abstract class Experience {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
	private String desc;
	
	@Persistent
	private Date start;
	
	@Persistent
	private Date end;
	
	public Experience(String desc, Date start, Date end){
		this.desc = desc;
		this.start = start;
		this.end = end;
	}
	
	public Key getKey(){
		return key;
	}
	
	public String getDesc(){
		return desc;
	}
	
	public void setDesc(String desc){
		this.desc = desc;
	}
	
	public Date getStart(){
		return start;
	}
	
	public void setStart(Date start){
		this.start = start;
	}
	
	public Date getEnd(){
		return end;
	}
	
	public void setEnd(Date end){
		this.end = end;
	}
	
}
