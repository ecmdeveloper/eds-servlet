/**
 * 
 */
package com.ecmdeveloper.eds.model.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ricardo Belfor
 *
 */
public class PingInfo {

	private String startTimeInfo;
	private boolean traceStarted;
	private String traceInfo;
	private SimpleDateFormat sdf = new SimpleDateFormat();
	
	public String getStartTimeInfo() {
		return startTimeInfo;
	}

	public void setStartTimeInfo(String startTimeInfo) {
		this.startTimeInfo = startTimeInfo;
	}
	
	public String getTraceInfo() {
		return traceInfo;
	}
	
	public void setTraceInfo(String traceInfo) {
		this.traceInfo = traceInfo;
	}

	public boolean isTraceStarted() {
		return traceStarted;
	}
	
	public void setTraceStarted(boolean traceStarted) {
		this.traceStarted = traceStarted;
		this.traceInfo = "EDS tracing " + (traceStarted?"started" : "stopped") + " at " + sdf.format( new Date() ) ;
	}
}
