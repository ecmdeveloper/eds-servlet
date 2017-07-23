package com.ecmdeveloper.eds.model;

import java.util.List;

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
}