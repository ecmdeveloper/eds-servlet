package com.ecmdeveloper.eds.model.impl;

import java.util.List;

import com.ecmdeveloper.eds.model.ChoiceList;
import com.ecmdeveloper.eds.model.Property;
import com.ecmdeveloper.eds.model.constants.DisplayMode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PropertyImpl implements Property {
	
   	private String symbolicName;
	private Object value;
	private String customValidationError;
	private List<Integer> customInvalidItems;
	private DisplayMode displayMode;
	private Boolean required;
	private Boolean hidden;
	private Object maxValue;
	private Object minValue;
	private Integer maxLength;
	private ChoiceList choiceList;
   	private Boolean hasDependentProperties;
	private String label;
	private String format;
	private String formatDescription;
	
   	@Override
	public String toString() {
		return getSymbolicName();
	}

	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getChoiceList()
	 */
	@Override
	public ChoiceList getChoiceList() {
		return this.choiceList;
	}
 	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setChoiceList(com.ecmdeveloper.eds.model.ChoiceList)
	 */
	@Override
	public void setChoiceList(ChoiceList choiceList) {
		this.choiceList = choiceList;
	}
	
 	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getCustomInvalidItems()
	 */
 	@Override
	public List<Integer> getCustomInvalidItems() {
		return this.customInvalidItems;
	}
 	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setCustomInvalidItems(java.util.List)
	 */
	@Override
	public void setCustomInvalidItems(List<Integer> customInvalidItems) {
		this.customInvalidItems = customInvalidItems;
	}
	
 	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getCustomValidationError()
	 */
 	@Override
	public String getCustomValidationError() {
		return this.customValidationError;
	}
 	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setCustomValidationError(java.lang.String)
	 */
	@Override
	public void setCustomValidationError(String customValidationError) {
		this.customValidationError = customValidationError;
	}
	
 	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getDisplayMode()
	 */
 	@Override
	public DisplayMode getDisplayMode() {
		return this.displayMode;
	}
 	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setDisplayMode(com.ecmdeveloper.eds.model.constants.DisplayMode)
	 */
	@Override
	public void setDisplayMode(DisplayMode displayMode) {
		this.displayMode = displayMode;
	}
	
 	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getHasDependentProperties()
	 */
 	@Override
	public Boolean getHasDependentProperties() {
		return this.hasDependentProperties;
	}
 	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setHasDependentProperties(java.lang.Boolean)
	 */
	@Override
	public void setHasDependentProperties(Boolean hasDependentProperties) {
		this.hasDependentProperties = hasDependentProperties;
	}
	
 	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getHidden()
	 */
 	@Override
	public Boolean getHidden() {
		return this.hidden;
	}
 	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setHidden(java.lang.Boolean)
	 */
	@Override
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	
 	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getMaxLength()
	 */
 	@Override
	public Integer getMaxLength() {
		return this.maxLength;
	}
 	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setMaxLength(java.lang.Integer)
	 */
	@Override
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	
 	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getMaxValue()
	 */
 	@Override
	public Object getMaxValue() {
		return this.maxValue;
	}
 	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setMaxValue(java.lang.Object)
	 */
	@Override
	public void setMaxValue(Object maxValue) {
		this.maxValue = maxValue;
	}
	
 	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getMinValue()
	 */
 	@Override
	public Object getMinValue() {
		return this.minValue;
	}
 	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setMinValue(java.lang.Object)
	 */
	@Override
	public void setMinValue(Object minValue) {
		this.minValue = minValue;
	}
	
 	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getRequired()
	 */
 	@Override
	public Boolean getRequired() {
		return this.required;
	}
 	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setRequired(java.lang.Boolean)
	 */
	@Override
	public void setRequired(Boolean required) {
		this.required = required;
	}
	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getSymbolicName()
	 */
 	@Override
	public String getSymbolicName() {
		return this.symbolicName;
	}

 	/**
 	 * Sets the symbolic name of the property. 
 	 * 
 	 * @param symbolicName the symbolic name.
 	 */
	public void setSymbolicName(String symbolicName) {
		this.symbolicName = symbolicName;
	}
	
 	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getValue()
	 */
 	@Override
	public Object getValue() {
		return this.value;
	}
 	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object value) {
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setLabel(java.lang.String)
	 */
	@Override
	public void setLabel(String label) {
		this.label = label;
	}

	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getLabel()
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getFormatDescription()
	 */
	@Override
	public String getFormatDescription() {
		return formatDescription;
	}

	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setFormatDescription(java.lang.String)
	 */
	@Override
	public void setFormatDescription(String formatDescription) {
		this.formatDescription = formatDescription;
	}

	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#getFormat()
	 */
	@Override
	public String getFormat() {
		return format;
	}

	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.Property#setFormat(java.lang.String)
	 */
	@Override
	public void setFormat(String format) {
		this.format = format;
	}
}