/**
 * 
 */
package com.ecmdeveloper.eds.model.impl;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * This class makes sure that the internal property map is initialized after deserializing.
 * @author Ricardo Belfor
 *
 */
public class ExternalDataRequestSanitizer extends StdConverter<ExternalDataRequestImpl, ExternalDataRequestImpl> {

	@Override
	public ExternalDataRequestImpl convert(ExternalDataRequestImpl request) {
		request.initializePropertyMap();
		return request;
	}
}
