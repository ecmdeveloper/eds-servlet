package com.ecmdeveloper.eds.model;

import java.util.Map;

import com.ecmdeveloper.eds.model.constants.RequestMode;

public interface ExternalDataRequest {
	
	public String getExternalDataIdentifier(); 

	public String getObjectId() ;

	public String getRepositoryId() ;

	public RequestMode getRequestMode();

	public Map<String, Object> getClientContext();
	
	public Property getProperty(String name);
}