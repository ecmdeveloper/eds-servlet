package com.ecmdeveloper.eds.model;

/**
 * This interface describes the response returned by the service.
 * 
 * @author Ricardo Belfor
 *
 */
public interface ExternalDataResponse {

	/**
	 * Adds a Property object to the external data response. The property object
	 * describes the required behavior of the corresponding property.
	 * 
	 * @param property
	 *            the property to add.
	 */
	void addProperty(Property property);

	/**
	 * Returns a string that indicates the state of the data that was returned
	 * by the external data service.
	 * 
	 * @return the value of the external data identifier.
	 */
	String getExternalDataIdentifier();

	/**
	 * Sets a string that indicates the state of the data that was returned by
	 * the external data service.
	 * 
	 * @param externalDataIdentifier
	 *            the externalDataIdentifier to set
	 */
	void setExternalDataIdentifier(String externalDataIdentifier);
}