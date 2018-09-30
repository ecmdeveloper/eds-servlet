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
import com.ecmdeveloper.eds.model.Property;
import com.ecmdeveloper.eds.model.constants.RequestMode;
import com.ecmdeveloper.eds.model.impl.ExternalDataRequestImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author micro
 *
 */
public class ExternalDataRequestTest {

	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		mapper.addMixInAnnotations(ExternalDataRequest.class, ExternalDataRequestImpl.class);
	}

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {			
		File file = new File("/Users/ricardobelfor/git/eds-servlet/eds-servlet/src/test/resources/request.json");
		ExternalDataRequest dataRequest = mapper.readValue(file, ExternalDataRequestImpl.class);
		assertEquals(RequestMode.initialNewObject, dataRequest.getRequestMode());
		assertEquals("TARGET", dataRequest.getRepositoryId());
		
		Property creator = dataRequest.getProperty("Creator");
		assertNotNull(creator);
		assertEquals(creator.getSymbolicName(), "Creator");
		assertNull(creator.getValue() );
		
		assertNull(dataRequest.getObjectId());
		
		assertNotNull(dataRequest.getClientContext() );
		assertEquals("TestSolution", dataRequest.getClientContext().get("currentSolution"));
		
		for (Property p : dataRequest) {
			System.out.println(p.getSymbolicName());
		}
	}
}
