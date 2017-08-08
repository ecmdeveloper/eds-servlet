/**
 * 
 */
package com.ecmdeveloper.eds.model.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ecmdeveloper.eds.model.ExternalDataRequest;
import com.ecmdeveloper.eds.model.ExternalDataResponse;
import com.ecmdeveloper.eds.model.Property;
import com.ecmdeveloper.eds.model.constants.DisplayMode;
import com.ecmdeveloper.eds.model.constants.RequestMode;
import com.ecmdeveloper.eds.model.impl.ExternalDataRequestImpl;
import com.ecmdeveloper.eds.model.impl.ExternalDataResponseImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author micro
 *
 */
public class ExternalDataResponseTest {

	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {			
		File file = new File("src\\test\\resources\\request.json");
		ExternalDataRequest dataRequest = mapper.readValue(file, ExternalDataRequestImpl.class);
			
		Property creator = dataRequest.getProperty("Creator");
		creator.setDisplayMode(DisplayMode.readonly);
		creator.setRequired(true);
		
		ExternalDataResponse dataResponse = new ExternalDataResponseImpl();
		dataResponse.addProperty(creator);
		
		System.out.println(mapper.writeValueAsString(dataResponse));
		
		assertEquals("{\"externalDataIdentifier\":\"EDS API\",\"properties\":[{\"symbolicName\":\"Creator\",\"displayMode\":\"readonly\",\"required\":true}]}",
					 mapper.writeValueAsString(dataResponse) );
	}
}
