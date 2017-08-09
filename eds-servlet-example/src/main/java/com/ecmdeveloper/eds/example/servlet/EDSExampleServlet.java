package com.ecmdeveloper.eds.example.servlet;

import javax.servlet.annotation.WebServlet;

import com.ecmdeveloper.eds.model.ExternalDataRequest;
import com.ecmdeveloper.eds.model.ExternalDataResponse;
import com.ecmdeveloper.eds.model.Property;
import com.ecmdeveloper.eds.servlet.AbstractEDSServlet;

/**
 * Servlet implementation class EDSExampleServlet
 * @author Ricardo Belfor
 */
@WebServlet(
		description = "An example of an EDS servlet.", 
		urlPatterns = { "/type/*", "/types"
		})
public class EDSExampleServlet extends AbstractEDSServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void handleRequest(ExternalDataRequest dataRequest, ExternalDataResponse dataResponse) {
		
		Property name = dataRequest.getProperty("EDSX_Name");
		name.setRequired(true);
		name.setValue("John Doe");
		
		dataResponse.addProperty(name);
	}
}
