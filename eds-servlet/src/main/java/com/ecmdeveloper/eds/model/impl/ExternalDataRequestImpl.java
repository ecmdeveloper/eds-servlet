/**
 * 
 */
package com.ecmdeveloper.eds.model.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ecmdeveloper.eds.model.ExternalDataRequest;
import com.ecmdeveloper.eds.model.Property;
import com.ecmdeveloper.eds.model.constants.RequestMode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * This class is used to handle external data request deserialization.
 * @author Ricardo Belfor
 *
 */
@JsonDeserialize(converter=ExternalDataRequestSanitizer.class) 
public class ExternalDataRequestImpl implements ExternalDataRequest {

	private String repositoryId;
	private String objectId;
	private RequestMode requestMode;
	private String externalDataIdentifier;
	
	/**
	 * An array that contains values for the properties that are defined for the
	 * class or item type. For each property, the request contains the symbolic
	 * name and the property value.
	 */
	private List<PropertyImpl> properties;
	private Map<String,Property> propertyMap;
	private Map<String, Object> clientContext;
	private String objectType;

	@Override
	public String getExternalDataIdentifier() {
		return this.externalDataIdentifier;
	}

	public void setExternalDataIdentifier(String externalDataIdentifier) {
		this.externalDataIdentifier = externalDataIdentifier;
	}

	@Override
	public String getObjectId() {
		return this.objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	public List<PropertyImpl> getProperties() {
		return this.properties;
	}

	public void setProperties(List<PropertyImpl> properties) {
		this.properties = properties;
	}

	@Override
	public String getRepositoryId() {
		return this.repositoryId;
	}

	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}

	@Override
	public RequestMode getRequestMode() {
		return this.requestMode;
	}

	public void setRequestMode(RequestMode requestMode) {
		this.requestMode = requestMode;
	}

	@Override
	public Map<String, Object> getClientContext() {
		return clientContext;
	}

	public void setClientContext(Map<String, Object> clientContext) {
		this.clientContext = clientContext;
	}
	
	@JsonIgnore
	public Property getProperty(String name) {
		return propertyMap.get(name);
	}

	@Override
	public Iterator<Property> iterator() {
		return propertyMap.values().iterator();
	}

	void initializePropertyMap() {
		propertyMap = new HashMap<String, Property>();
		for (Property property : properties) {
			propertyMap.put(property.getSymbolicName(), property);
		}
	}
}
