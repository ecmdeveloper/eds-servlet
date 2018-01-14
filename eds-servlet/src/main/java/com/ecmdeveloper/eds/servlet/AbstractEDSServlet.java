package com.ecmdeveloper.eds.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecmdeveloper.eds.model.ErrorResponse;
import com.ecmdeveloper.eds.model.ExternalDataRequest;
import com.ecmdeveloper.eds.model.ExternalDataResponse;
import com.ecmdeveloper.eds.model.ObjectType;
import com.ecmdeveloper.eds.model.impl.ExternalDataRequestImpl;
import com.ecmdeveloper.eds.model.impl.ExternalDataResponseImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Servlet implementation class UpdateObjectTypesServlet
 */
public abstract class AbstractEDSServlet extends HttpServlet {
	
	private static final int TRACE_LIST_SIZE = 10;

	private static final String SOURCE_CLASS = "AbstractEDSServlet";

	public static Logger logger = Logger.getLogger("com.ecmdeveloper.eds");
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private static LinkedList<String> traceList = new LinkedList<String>();
	private static String pingInfo = "";
	
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
		
		if ( pingInfo.isEmpty() ) {
			SimpleDateFormat sdf = new SimpleDateFormat();
			pingInfo = "EDS Servlet started at " + sdf.format( new Date() );
		}
    }
  
	/**
	 * Returns the names of the object types handled by this EDS implementation. The id of the
	 * repository is passed as a parameter.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println("=======> " + request.getContextPath());
		System.out.println("=======> " + request.getPathInfo() );
		String servletPath = request.getServletPath();
		System.out.println("=======> " + servletPath);
		
		if ("/types".equals( servletPath ) ) {
			String repositoryId = request.getParameter("repositoryId");
	        mapper.writeValue(response.getWriter(), getObjectTypes(repositoryId));
		} else if ("/ping".equals( servletPath ) ) {
			getPingInfo(request, response);
		}
	}

	private void getPingInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if ( request.getPathInfo() != null ) {
			
			int index;
			try {
				index = Integer.parseInt(request.getPathInfo().substring(1) );
			} catch (NumberFormatException e) {
				index = 0;
			}
			if ( index < traceList.size() ) {
				response.getWriter().write(traceList.get(index) );
			} else {
				response.getWriter().write("{\"request\": {}, \"response\": {}, \"timestamp\": \"No data\"}");
			}
		} else {
			response.getWriter().write(pingInfo);
		}
	}

	private ObjectType[] getObjectTypes(String repositoryId) {
		final String[] objectTypeNames = getObjectTypeNames(repositoryId);
        final ObjectType[] objectTypes = new ObjectType[objectTypeNames.length];
        int index = 0;
        for ( String objectTypeName : objectTypeNames ) {
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
			logger.fine(request.getPathInfo());
			
			String objectType = request.getPathInfo().substring(request.getPathInfo().lastIndexOf("/") + 1 );
			ExternalDataRequestImpl dataRequest = mapper.readValue(request.getInputStream(), ExternalDataRequestImpl.class);
			dataRequest.setObjectType(objectType);
			ExternalDataResponse dataResponse = new ExternalDataResponseImpl();
			handleRequest(dataRequest, dataResponse);
			
			addTrace(dataRequest, dataResponse);
			
			
			mapper.writeValue(response.getWriter(), dataResponse);

			logger.exiting(SOURCE_CLASS, "doPost");
		} catch (Exception e ) {
			response.sendError(500);
		    mapper.writeValue(response.getWriter(), getErrorResponse(e) );
			logger.log(Level.SEVERE, getServletName() +":doPost", e);
		}
	}

	
	private void addTrace(ExternalDataRequestImpl dataRequest,
			ExternalDataResponse dataResponse) throws JsonProcessingException {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
		
		String lastResponse = "{ \"request\": " + mapper.writeValueAsString(dataRequest) + 
							", \"response\": " + mapper.writeValueAsString(dataResponse) +
							", \"timestamp\": \"" + dataRequest.getObjectType() + " at " + dateFormat.format(new Date() ) + "\"" +
							"}";
		
		traceList.addFirst(lastResponse);
		if ( traceList.size() > TRACE_LIST_SIZE ) {
			traceList.removeFirst();
		}
	}

	private ErrorResponse getErrorResponse(Exception e) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setText(e.getLocalizedMessage() );
		
		StackTraceElement[] stack = e.getStackTrace();
		for (StackTraceElement s : stack) {
			errorResponse.addCause(s.toString());
		}
		return errorResponse;
	}
	
	public abstract void handleRequest(ExternalDataRequest dataRequest, ExternalDataResponse dataResponse);
	
	public String[] getObjectTypeNames(String repositoryId) {
		return new String[0];
	}
}
