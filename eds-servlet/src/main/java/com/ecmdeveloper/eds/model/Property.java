package com.ecmdeveloper.eds.model;

import java.util.List;

import com.ecmdeveloper.eds.model.constants.DisplayMode;

/**
 * The property interface. This interface is used to configure the behavior of a specific property. 
 * Once a property is modified, it has to be added to the ExternalDataResponse to be effective.
 * 
 * @author Ricardo Belfor
 *
 */
public interface Property {

	/**
	 * Returns the value of this property.
	 * @return the value of the property.
	 */
	Object getValue();

	/**
	 * Sets the value of this property.
	 * @param value the new value of this property.
	 */
	void setValue(Object value);

	/**
	 * Returns the display mode of the property.
	 * @return the display mode of this property.
	 */
	DisplayMode getDisplayMode();

	/**
	 * Sets the display mode of the property
	 * @param displayMode
	 */
	void setDisplayMode(DisplayMode displayMode);

	/**
	 * Returns the choice objects of the property.
	 * @return choice object.
	 */
	ChoiceList getChoiceList();

	/**
	 * Sets the choice objects of the property.
	 * @param choiceList the new value.
	 */
	void setChoiceList(ChoiceList choiceList);

	/** 
	 * Returns a collection containing the indices of the invalid items of a multi-value property.
	 * @return an collection.
	 */
	List<Integer> getCustomInvalidItems();

	/**
	 * Sets a collection containing the indices of the invalid items of a multi-value property.
	 * @param customInvalidItems the collection.
	 */
	void setCustomInvalidItems(List<Integer> customInvalidItems);

	/**
	 * Returns the description why a property value is invalid.
	 * @return the description.
	 */
	String getCustomValidationError();

	/**
	 * Sets the description why a property value is invalid.
	 * @param customValidationError the new value.
	 */
	void setCustomValidationError(String customValidationError);

	/**
	 * Returns a boolean value indicating if the value of this property controls 
	 * the value of other properties.
	 * @return the boolean value.
	 */
	Boolean getHasDependentProperties();

	/**
	 * Sets a boolean value indicating if the value of this property controls 
	 * the value of other properties.
	 * @param hasDependentProperties the new value.
	 */
	void setHasDependentProperties(Boolean hasDependentProperties);

	/**
	 * Returns a boolean value indicating if the property is hidden.
	 * @return the boolean value.
	 */
	Boolean getHidden();

	/**
	 * Sets a boolean value indicating if the property is hidden. 
	 * @param hidden the new value.
	 */
	void setHidden(Boolean hidden);

	/**
	 * Returns the maximum length of the property.
	 * @return the maximum length.
	 */
	Integer getMaxLength();

	/**
	 * Set the maximum lenght of the property.
	 * @param maxLength the new value.
	 */
	void setMaxLength(Integer maxLength);

	/**
	 * Returns the overridden maximum value of the property.
	 * @return the maximum value.
	 */
	Object getMaxValue();

	/**
	 * Set the overridden maximum value of the property.
	 * @param maxValue the new value.
	 */
	void setMaxValue(Object maxValue);

	/**
	 * Returns the overridden minimum value of the property.
	 * @return the minimum value.
	 */
	Object getMinValue();

	/**
	 * Sets the overridden minimum value of the property.
	 * @param minValue
	 */
	void setMinValue(Object minValue);

	/**
	 * Returns a boolean value indicating if the property is required.
	 * @return the boolean value.
	 */
	Boolean getRequired();

	/**
	 * Sets a boolean value indicating if the property is required.
	 * @param required the new value.
	 */
	void setRequired(Boolean required);

	/**
	 * Returns the symbolic name of the property. 
	 * @return the symbolic name.
	 */
	String getSymbolicName();

	/**
	 * Sets the label to put in front of the property editor <b>(Content Navigator only)</b>
	 * @param label the new value.
	 */
	void setLabel(String label);

	/**
	 * Returns the label to put in front of the property editor <b>(Content Navigator only)</b>.
	 * @return the label.
	 */
	String getLabel();

	/**
	 * Returns the description that is displayed in a tooltip if the user enters a format 
	 * that does not match the expression specified in the format parameter.
	 * @return the format description
	 */
	String getFormatDescription();

	/**
	 * Sets the description that is displayed in a tooltip if the user enters a format 
	 * that does not match the expression specified in the format parameter 
	 * <b>(Content Navigator only)</b>.
	 * @param formatDescription the new value.
	 */
	void setFormatDescription(String formatDescription);

	/**
	 * Returns a regular expression describing the format of the property <b>(Content Navigator only)</b>.
	 * @return the format
	 */
	String getFormat();

	/**
	 * Sets a regular expression describing the format of the property <b>(Content Navigator only)</b>
	 * @param format the new value.
	 */
	void setFormat(String format);
}