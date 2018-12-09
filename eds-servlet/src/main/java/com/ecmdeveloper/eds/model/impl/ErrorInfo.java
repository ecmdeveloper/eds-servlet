/**
 * 
 */
package com.ecmdeveloper.eds.model.impl;

import java.util.ArrayList;

/**
 * This class stores the information about an exception. This information is
 * used for tracing.
 * 
 * @author Ricardo Belfor
 *
 */
public class ErrorInfo {

	private final String message;
	private final ArrayList<String> causes = new ArrayList<String>();

	public ErrorInfo(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	/**
	 * @return the causes
	 */
	public ArrayList<String> getCauses() {
		return causes;
	}

	public void addCause(String cause) {
		causes.add(cause);
	}
}
