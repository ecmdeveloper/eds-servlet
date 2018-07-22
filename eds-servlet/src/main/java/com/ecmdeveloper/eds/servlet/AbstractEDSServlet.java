package com.ecmdeveloper.eds.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.ecmdeveloper.eds.model.impl.PingInfo;
import com.ecmdeveloper.eds.model.impl.TraceItem;
import com.ecmdeveloper.eds.model.impl.TraceItems;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * An abstract servlet implementation handling all the internals of EDS 
 * processing.
 * 
 * @author Ricardo Belfor
 */
public abstract class AbstractEDSServlet extends HttpServlet {
	
	private static final int TRACE_LIST_SIZE = 10;

	private static final String SOURCE_CLASS = "AbstractEDSServlet";

	public static Logger logger = Logger.getLogger("com.ecmdeveloper.eds");
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private static TraceItems traceItems = new TraceItems(TRACE_LIST_SIZE);
	private static PingInfo pingInfo = new PingInfo();
	
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
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		pingInfo.setStartTimeInfo( "EDS Servlet started at " + sdf.format( new Date() ) );
    }
  
	/**
	 * Returns the names of the object types handled by this EDS implementation. The id of the
	 * repository is passed as a parameter. Also handles the requests from the ping page.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String servletPath = request.getServletPath();
		
		if ("/types".equals( servletPath ) ) {
			String repositoryId = request.getParameter("repositoryId");
	        mapper.writeValue(response.getWriter(), getObjectTypes(repositoryId));
		} else if ("/ping".equals( servletPath ) ) {
			getPingInfo(request, response);
		}
	}

	private void streamResource(HttpServletResponse response, String resource) throws IOException {
	
	    PrintWriter writer = response.getWriter();
		InputStream inputStream = this.getClass().getResourceAsStream(resource);

		if ( inputStream != null ) {
		    InputStreamReader isr = new InputStreamReader(inputStream);
		    BufferedReader reader = new BufferedReader(isr);
		    String text;

		    while ((text = reader.readLine()) != null) {
		        writer.println(text);
		    }
		} else {
			writer.println("Resource '" + resource + "' not found!");
		}
	}

	private void getPingInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String pathInfo = request.getPathInfo();
		
		if ( pathInfo != null ) {
			if (pathInfo.equals("/info") ) {
		        mapper.writeValue(response.getWriter(), pingInfo);
			} else if ( pathInfo.equals("/start") ) {
				pingInfo.setTraceStarted(true);
		        mapper.writeValue(response.getWriter(), pingInfo);
			} else if (pathInfo.equals("/stop") ) {
				pingInfo.setTraceStarted(false);
		        mapper.writeValue(response.getWriter(), pingInfo);
			} else {
				int index = parseIndex(request);
				mapper.writeValue(response.getWriter(), traceItems.get(index) );
			}
		} else {
			streamResource(response, "/com/ecmdeveloper/eds/ping/index.html");		
		}
	}

	private int parseIndex(HttpServletRequest request) {
		int index;
		try {
			index = Integer.parseInt(request.getPathInfo().substring(1) );
		} catch (NumberFormatException e) {
			index = 0;
		}
		return index;
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
		
		ExternalDataRequestImpl dataRequest = null;

		try {
			logger.entering(SOURCE_CLASS, "doPost");
			logger.fine(request.getPathInfo());
			
			String objectType = request.getPathInfo().substring(request.getPathInfo().lastIndexOf("/") + 1 );
			dataRequest = mapper.readValue(request.getInputStream(), ExternalDataRequestImpl.class);
			dataRequest.setObjectType(objectType);
			ExternalDataResponse dataResponse = new ExternalDataResponseImpl();
			handleRequest(dataRequest, dataResponse);
			
			addTrace(dataRequest, dataResponse);
			mapper.writeValue(response.getWriter(), dataResponse);

			logger.exiting(SOURCE_CLASS, "doPost");
		} catch (Exception e ) {
			response.sendError(500);
		    ErrorResponse errorResponse = getErrorResponse(e);
			addTrace(dataRequest, errorResponse);
			mapper.writeValue(response.getWriter(), errorResponse );
			logger.log(Level.SEVERE, getServletName() +":doPost", e);
		}
	}
	
	private void addTrace(ExternalDataRequest dataRequest,
			Object dataResponse) throws JsonProcessingException {
		
		if (pingInfo.isTraceStarted() ) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
			
			TraceItem lastResponse = new TraceItem(mapper.writeValueAsString(dataRequest == null? "{}" : dataRequest ), 
								 mapper.writeValueAsString(dataResponse),
								dataRequest.getObjectType() + " at " + dateFormat.format(new Date() ) );
			
			traceItems.add(lastResponse);
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
	
	/**
	 * Handles the requests to the External Data Server. Subclasses should implement
	 * this method.
	 * 
	 * @param dataRequest
	 *            the object containing data request.
	 * @param dataResponse
	 *            the object receiving the data response.
	 */
	public abstract void handleRequest(ExternalDataRequest dataRequest, ExternalDataResponse dataResponse);
	
	/**
	 * Returns the names of the all the object types handled by this servlet. This
	 * is only relevant for IBM Content Navigator implementations.
	 * 
	 * @param repositoryId
	 *            the id of the repository.
	 * 
	 * @return a list of object types handled by this servlet.
	 */
	public String[] getObjectTypeNames(String repositoryId) {
		return new String[0];
	}
}
