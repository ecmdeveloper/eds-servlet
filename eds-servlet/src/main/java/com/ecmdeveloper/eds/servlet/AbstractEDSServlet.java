package com.ecmdeveloper.eds.servlet;

import java.io.IOException;
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

import com.ecmdeveloper.eds.model.ExternalDataRequest;
import com.ecmdeveloper.eds.model.ExternalDataResponse;
import com.ecmdeveloper.eds.model.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Servlet implementation class UpdateObjectTypesServlet
 */
public class AbstractEDSServlet extends HttpServlet {
	
	private static final String SOURCE_CLASS = "AbstractEDSServlet";

	public static Logger logger = Logger.getLogger("com.ecmdeveloper.eds");
	
	private ObjectMapper mapper = new ObjectMapper();
	private RequestHandler requestHandler;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AbstractEDSServlet() {
        super();

        DateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH); 
		mapper.setDateFormat(formatter1);
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().write( "The time is " + (new Date()).toString() );
		System.out.println( "The time is " + (new Date()).toString() );
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			logger.entering(SOURCE_CLASS, "doPost");
			
			String objectType = request.getPathInfo().substring(request.getPathInfo().lastIndexOf("/") + 1 );
			ExternalDataRequest dataRequest = mapper.readValue(request.getInputStream(), ExternalDataRequest.class);
			ExternalDataResponse dataResponse = new ExternalDataResponse();
			requestHandler.handleRequest(objectType, dataRequest, dataResponse);
			dataResponse.setExternalDataIdentifier("EDS API");
			mapper.writeValue(response.getWriter(), dataResponse);

			logger.exiting(SOURCE_CLASS, "doPost");
		} catch (Exception e ) {
			logger.log(Level.SEVERE, getServletName() +":doPost", e);
		}
	}
}
