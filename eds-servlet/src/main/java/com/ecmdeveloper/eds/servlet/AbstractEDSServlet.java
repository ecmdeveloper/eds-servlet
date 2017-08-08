package com.ecmdeveloper.eds.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecmdeveloper.eds.model.ExternalDataRequest;
import com.ecmdeveloper.eds.model.ExternalDataResponse;
import com.ecmdeveloper.eds.model.ObjectType;
import com.ecmdeveloper.eds.model.impl.ExternalDataRequestImpl;
import com.ecmdeveloper.eds.model.impl.ExternalDataResponseImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Servlet implementation class UpdateObjectTypesServlet
 */
public abstract class AbstractEDSServlet extends HttpServlet {
	
	private static final String SOURCE_CLASS = "AbstractEDSServlet";

	public static Logger logger = Logger.getLogger("com.ecmdeveloper.eds");
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbstractEDSServlet() {
        super();

        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH); 
		mapper.setDateFormat(formatter1);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		
		mapper.addMixInAnnotations(ExternalDataRequest.class, ExternalDataRequestImpl.class);
    }

	/**
	 * Returns the names of the object types handled by this EDS implementation. The id of the
	 * repository is passed as a parameter.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String repositoryId = request.getParameter("repositoryId");
        mapper.writeValue(response.getWriter(), getObjectTypes(repositoryId));
	}

	private ObjectType[] getObjectTypes(String repositoryId) {
		final String[] objectTypeNames = getObjectTypeNames();
        final ObjectType[] objectTypes = new ObjectType[objectTypeNames.length];
        int index = 0;
        for ( String objectTypeName : getObjectTypeNames() ) {
        	objectTypes[index++] = new ObjectType(objectTypeName);
        }
		return objectTypes;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			logger.entering(SOURCE_CLASS, "doPost");
			
			String objectType = request.getPathInfo().substring(request.getPathInfo().lastIndexOf("/") + 1 );
			ExternalDataRequest dataRequest = mapper.readValue(request.getInputStream(), ExternalDataRequestImpl.class);
			ExternalDataResponse dataResponse = new ExternalDataResponseImpl();
			handleRequest(objectType, dataRequest, dataResponse);
			mapper.writeValue(response.getWriter(), dataResponse);

			logger.exiting(SOURCE_CLASS, "doPost");
		} catch (Exception e ) {
			e.printStackTrace();
			logger.log(Level.SEVERE, getServletName() +":doPost", e);
		}
	}
	
	public abstract void handleRequest(String objectType, ExternalDataRequest dataRequest, ExternalDataResponse dataResponse);
	
	public String[] getObjectTypeNames() {
		return new String[0];
	}
}
