/**
 * 
 */
package com.ecmdeveloper.eds.model.impl;

/**
 * @author Ricardo Belfor
 *
 */
public class TraceItem {

	private final String request;
	private final String response;
	private final String timestamp;
	
	public TraceItem(String request, String response, String timestamp) {
		this.request = request;
		this.response = response;
		this.timestamp = timestamp;
	}

	public String getRequest() {
		return request;
	}

	public String getResponse() {
		return response;
	}

	public String getTimestamp() {
		return timestamp;
	}
}
