package com.ecmdeveloper.eds.example.servlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.ecmdeveloper.eds.model.Choice;
import com.ecmdeveloper.eds.model.ChoiceList;
import com.ecmdeveloper.eds.model.ExternalDataRequest;
import com.ecmdeveloper.eds.model.ExternalDataResponse;
import com.ecmdeveloper.eds.model.Property;
import com.ecmdeveloper.eds.model.constants.DisplayMode;
import com.ecmdeveloper.eds.model.constants.RequestMode;
import com.ecmdeveloper.eds.servlet.AbstractEDSServlet;

/**
 * Servlet implementation class EDSExampleServlet
 * @author Ricardo Belfor
 */
@WebServlet(
		description = "An example of an EDS servlet.", 
		urlPatterns = { "/type/*", "/types", "/error", "/ping/*"
		})
public class EDSExampleServlet extends AbstractEDSServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void handleRequest(ExternalDataRequest dataRequest, ExternalDataResponse dataResponse) {
		
		if ( dataRequest.getObjectType().equals("TST_testDocType") ) {
			
			Property test = dataRequest.getProperty("TST_TestProp");
			test.setValue( "This is the default value" );
			dataResponse.addProperty(test);
			
			Property department = dataRequest.getProperty("TST_Departement");
			ChoiceList choiceList = new ChoiceList();
			choiceList.setDisplayName("Departments");
			
			List<Choice> choices = new ArrayList<Choice>();
			for ( String name : Arrays.asList( "Accounting","IT","Human Resources","Marketing and Advertising","Production","Sales" ) ) {
				choices.add( new Choice(name) );
			}
			choiceList.setChoices(choices);
			department.setChoiceList(choiceList);
			dataResponse.addProperty(department);
			
		} else if (dataRequest.getObjectType().equals("Customer") ) {
					
			Property name = dataRequest.getProperty("TST_ExternalProperty1");
			name.setDisplayMode(DisplayMode.readonly);
			name.setValue("John Doe");
			dataResponse.addProperty(name);
			
		} else if (dataRequest.getObjectType().equals("Email") ) {

			Property from = dataRequest.getProperty("From");
			from.setValue("john.doe@example.com");
			from.setFormat("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
			from.setFormatDescription("e-mail address");
			dataResponse.addProperty(from);
		}
	}

	@Override
	public String[] getObjectTypeNames(String repositoryId) {
		return new String[] {"Email" };
	}
}
