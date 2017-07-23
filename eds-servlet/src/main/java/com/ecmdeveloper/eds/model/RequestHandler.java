package com.ecmdeveloper.eds.model;

public interface RequestHandler {
	void handleRequest(String objectType, ExternalDataRequest dataRequest, ExternalDataResponse dataResponse) throws Exception;
}
