package com.ecmdeveloper.eds.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Choice {
	
   	private String displayName;
   	private Object value;
   	private List<Choice> choices;

 	public Choice(String displayName, Object value) {
		this.displayName = displayName != null ? displayName : value.toString();
		this.value = value != null ? value : displayName;
	}

 	public Choice(String value) {
		this(value, value);
	}
 	
	public String getDisplayName() {
		return this.displayName;
	}
 	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
 	public Object getValue() {
		return this.value;
	}
 	
	public void setValue(String value) {
		this.value = value;
	}

	public List<Choice> getChoices(){
		return this.choices;
	}

	public void setChoices(List<Choice> choices){
		this.choices = choices;
	}

	@Override
	public String toString() {
		return "Choice [displayName=" + displayName + ", value=" + value + "]";
	}
	
	
}