/**
 * 
 */
package com.ecmdeveloper.eds.model.impl;

import java.util.ArrayList;
import java.util.List;

import com.ecmdeveloper.eds.model.ExternalDataResponse;
import com.ecmdeveloper.eds.model.Property;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Administrator
 *
 */
@JsonInclude(Include.NON_NULL)
public class ExternalDataResponseImpl implements ExternalDataResponse {

	private String externalDataIdentifier = "EDS API";
	public List<Property> properties = new ArrayList<Property>();

	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.ExternalDataResponse#addProperty(com.ecmdeveloper.eds.model.Property)
	 */
	@Override
	public void addProperty(Property property) {
		properties.add(property);
	}

	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.ExternalDataResponse#getExternalDataIdentifier()
	 */
	@Override
	public String getExternalDataIdentifier() {
		return externalDataIdentifier;
	}

	/* (non-Javadoc)
	 * @see com.ecmdeveloper.eds.model.ExternalDataResponse#setExternalDataIdentifier(java.lang.String)
	 */
	@Override
	public void setExternalDataIdentifier(String externalDataIdentifier) {
		this.externalDataIdentifier = externalDataIdentifier;
	}
}
