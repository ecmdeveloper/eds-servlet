package com.ecmdeveloper.eds.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * This class represents an item in a choice list. There a two types of choice
 * list items:
 * <ul>
 * <li>An item representing one value, either a string of an integer.</li>
 * <li>An item representing a leaf in a hierarchical choice list (currently only
 * supported by Content Navigator)</li>
 * </ul>
 * Both types are supported by this class.
 * 
 * @author Ricardo Belfor
 *
 */
@JsonInclude(Include.NON_NULL)
public class Choice {

	private String displayName;
	private Object value;
	private List<Choice> choices;

	/**
	 * Constructs a new choice list value. with a value and a display name.
	 * 
	 * @param displayName
	 *            the display name of the choice list item. If null the string
	 *            representation of the value is used.
	 * @param value
	 *            the value of the choice list item.
	 * 
	 */
	public Choice(String displayName, Object value) {
		this.displayName = displayName != null ? displayName : value.toString();
		this.value = value != null ? value : displayName;
	}

	/**
	 * Constructs a new choice list value with display name equal to the value.
	 * 
	 * @param value
	 *            the value of the choice list item.
	 */
	public Choice(String value) {
		this(value, value);
	}

	/**
	 * Returns the display name of the choice list item.
	 * 
	 * @return the value
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * Sets the display name of the choice list item.
	 * 
	 * @param displayName
	 *            the value
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Returns the value of the choice list item.
	 * 
	 * @return the value.
	 */
	public Object getValue() {
		return this.value;
	}

	/**
	 * Sets the string value of the choice list item.
	 * 
	 * @param value the string value of choice list item
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Returns a list of choice list items.
	 * 
	 * @return the list of choices.
	 */
	public List<Choice> getChoices() {
		return this.choices;
	}

	/**
	 * Sets the child choice items of this choice list items.
	 * 
	 * @param choices
	 *            the list of choices
	 */
	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	/**
	 * The string presentation of the choice list item.
	 */
	@Override
	public String toString() {
		return "Choice [displayName=" + displayName + ", value=" + value + "]";
	}
}