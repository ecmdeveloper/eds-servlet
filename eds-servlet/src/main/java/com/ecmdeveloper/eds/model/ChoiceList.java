package com.ecmdeveloper.eds.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class is used to build an choice list for a specific property. Just
 * create an instance, set the values and assign it to a property object.
 * 
 * @author Ricardo Belfor
 *
 */
public class ChoiceList {

	private List<Choice> choices;
   	private String displayName;

	/**
	 * Returns a list of list of choice items for this choice list. In case of
	 * an hierarchical choice list these are the root items.
	 * 
	 * @return the list of choices.
	 */
 	public List<Choice> getChoices(){
		return this.choices;
	}

	/**
	 * Sets the choices of the choice list. In case of an hierarchical choice
	 * list these are the root items.
	 * 
	 * @param choices the list of choices.
	 */
	public void setChoices(List<Choice> choices){
		this.choices = choices;
	}
 	
	/**
	 * Returns the display name of the choice list.
	 * 
	 * @return the displaty name.
	 */
	public String getDisplayName(){
		return this.displayName;
	}
	
	/**
	 * Sets the display name of the choice list.
	 * 
	 * @param displayName
	 */
	public void setDisplayName(String displayName){
		this.displayName = displayName;
	}
	
	/**
	 * Convenience method the creates a simple string choice list.
	 * 
	 * @param values
	 *            the values of the choice list.
	 */
	@JsonIgnore
	public void add(String... values) {
		choices = new ArrayList<Choice>();
		for (String value : values) {
			choices.add( new Choice(value) );
		}
	}
}