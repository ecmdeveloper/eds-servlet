/**
 * 
 */
package com.ecmdeveloper.eds.model.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ecmdeveloper.eds.model.ExternalDataRequest;
import com.ecmdeveloper.eds.model.Property;
import com.ecmdeveloper.eds.model.constants.RequestMode;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The MixIn class to handle external data request deserialization.
 * 
 * @author Ricardo Belfor
 *
 */
public class ExternalDataRequestImpl implements ExternalDataRequest {

	/**
	 * The symbolic name of the target external data store that contains the
	 * property data.
	 */
	private String repositoryId;

	/**
	 * The globally unique identifier (GUID) or persistent identifier (PID) that
	 * identifies the item that is being edited.
	 */
	private String objectId;

	/**
	 * One of the following request modes that indicates the reason that the
	 * POST method is being called:
	 * <ul>
	 * <li>initialNewObject</li>
	 * <li>initialExistingObject</li>
	 * <li>inProgressChanges</li>
	 * <li>finalNewObject</li>
	 * <li>finalExistingObject</li>
	 * </ul>
	 */
	private RequestMode requestMode;

	/**
	 * A string that indicates the state of the data that was returned by the
	 * external data service. The request must include this identifier if the
	 * requestMode parameter is set to one of these values:
	 * <ul>
	 * <li>inProgressChanges</li>
	 * <li>finalNewObject</li>
	 * <li>finalExistingObject</li>
	 * </ul>
	 */
	private String externalDataIdentifier;
	
	/**
	 * An array that contains values for the properties that are defined for the
	 * class or item type. For each property, the request contains the symbolic
	 * name and the property value.
	 */
	private List<Property> properties;

	private Map<String,Property> propertyMap;
	
	/**
	 * An array that contains a series of key value pairs that specify
	 * contextual information for a specific class or item type. This parameter
	 * is used to send information to an external data service when an IBM
	 * Content Navigator user begins to add a document, add a folder, use an
	 * entry template, or create a search.
	 */
	private Map<String, Object> clientContext;

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

	public List<Property> getProperties() {
		return this.properties;
	}

	public void setProperties(List<Property> properties) {
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
		if ( propertyMap == null ) {
			initializePropertyMap();
		}
		return propertyMap.get(name);
	}

	private void initializePropertyMap() {
		propertyMap = new HashMap<String, Property>();
		for (Property property : properties) {
			propertyMap.put(property.getSymbolicName(), property);
		}
	}	
}
