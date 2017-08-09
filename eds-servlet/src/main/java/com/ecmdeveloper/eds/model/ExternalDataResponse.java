package com.ecmdeveloper.eds.model;

/**
 * This class describes the response returned by the service.
 * @author Ricardo Belfor
 *
 */
public interface ExternalDataResponse {

	/**
	 * Adds a Property object to the external data response. The property object describes the
	 * required behavior of the corresponding property.
	 * @param property the property to add.
	 */
	void addProperty(Property property);

	/**
	 * @return the externalDataIdentifier
	 */
	String getExternalDataIdentifier();

	/**
	 * @param externalDataIdentifier the externalDataIdentifier to set
	 */
	void setExternalDataIdentifier(String externalDataIdentifier);

}