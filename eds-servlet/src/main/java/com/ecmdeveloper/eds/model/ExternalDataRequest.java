package com.ecmdeveloper.eds.model;

import java.util.Map;

import com.ecmdeveloper.eds.model.constants.RequestMode;

/**
 * The class describes the request that is made to the service. The Iterable interface 
 * can by used to walk through all the property objects.
 * 
 * @author Ricardo Belfor
 *
 */
public interface ExternalDataRequest extends Iterable<Property> {
	
	/**
	 * The globally unique identifier (GUID) or persistent identifier (PID) that
	 * identifies the item that is being edited.
	 * 
	 * @return the identifier.
	 */
	public String getObjectId();

	/**
	 * The object type of the item that is being edited.
	 * 
	 * @return object type.
	 */
	public String getObjectType();
	
	/**
	 * The name of the target external data store that contains the property
	 * data.
	 * 
	 * @return the repository id.
	 */
	public String getRepositoryId();

	/**
	 * Returns the reason that the service is being called. The value can be one
	 * of these values:
	 * <ul>
	 * <li>initialNewObject</li>
	 * <li>initialExistingObject</li>
	 * <li>inProgressChanges</li>
	 * <li>finalNewObject</li>
	 * <li>finalExistingObject</li>
	 * </ul>
	 * 
	 * @return one of the request modes.
	 */
	public RequestMode getRequestMode();

	/**
	 * An map that contains a series of key value pairs that specify contextual
	 * information for a specific class or item type. The information depends on
	 * the action that is being performed by the user.
	 * 
	 * @return a map containing the data.
	 */
	public Map<String, Object> getClientContext();
	
	/**
	 * Returns a specific property identified by it's symbolic name.
	 * 
	 * @param name
	 *            the symbolic name of the property.
	 * @return the property.
	 */
	public Property getProperty(String name);

	/**
	 * A string that indicates the state of the data that was returned by the
	 * external data service. The request must include this identifier if the
	 * requestMode parameter is set to one of these values:
	 * <ul>
	 * <li>inProgressChanges</li>
	 * <li>finalNewObject</li>
	 * <li>finalExistingObject</li>
	 * </ul>
	 * This API makes sure it has a default value.
	 * 
	 * @return
	 */
	public String getExternalDataIdentifier();
}