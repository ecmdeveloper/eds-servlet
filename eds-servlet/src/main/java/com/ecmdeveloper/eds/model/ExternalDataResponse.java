package com.ecmdeveloper.eds.model;

public interface ExternalDataResponse {

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