package com.ecmdeveloper.eds.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ChoiceList{
   	private List<Choice> choices;
   	private String displayName;

 	public List<Choice> getChoices(){
		return this.choices;
	}
	public void setChoices(List<Choice> choices){
		this.choices = choices;
	}
 	public String getDisplayName(){
		return this.displayName;
	}
	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}
	
	@JsonIgnore
	public void add(String... values) {
		choices = new ArrayList<Choice>();
		for (String value : values) {
			choices.add( new Choice(value) );
		}
	}
}