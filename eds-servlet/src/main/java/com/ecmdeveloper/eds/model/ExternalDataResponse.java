/**
 * 
 */
package com.ecmdeveloper.eds.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Administrator
 *
 */
@JsonInclude(Include.NON_NULL)
public class ExternalDataResponse {

	private String externalDataIdentifier;
	public List<Property> properties = new ArrayList<Property>();

	public void addProperty(Property property) {
		properties.add(property);
	}

	/**
	 * @return the externalDataIdentifier
	 */
	public String getExternalDataIdentifier() {
		return externalDataIdentifier;
	}

	/**
	 * @param externalDataIdentifier the externalDataIdentifier to set
	 */
	public void setExternalDataIdentifier(String externalDataIdentifier) {
		this.externalDataIdentifier = externalDataIdentifier;
	}
}
