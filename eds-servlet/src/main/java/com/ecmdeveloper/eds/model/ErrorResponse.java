package com.ecmdeveloper.eds.model;

import java.util.ArrayList;

public class ErrorResponse {

	private final UserMessage userMessage;
	private final UnderlyingDetails underlyingDetails;
	
	public ErrorResponse() {
		userMessage = new UserMessage();
		underlyingDetails = new UnderlyingDetails();
	}
	
	public UserMessage getUserMessage() {
		return userMessage;
	}
	
	public void addCause(String cause) {
		getUnderlyingDetails().getCauses().add(cause);
	}

	/**
	 * @return the underlyingDetails
	 */
	public UnderlyingDetails getUnderlyingDetails() {
		return underlyingDetails;
	}

	class UnderlyingDetails {
		private final ArrayList<String> causes;
		
		public UnderlyingDetails() {
			causes = new ArrayList<String>();
		}

		/**
		 * @return the causes
		 */
		public ArrayList<String> getCauses() {
			return causes;
		}
	}
	
	class UserMessage {

		private String text;

		/**
		 * @return the text
		 */
		public String getText() {
			return text;
		}

		/**
		 * @param text the text to set
		 */
		public void setText(String text) {
			this.text = text;
		}
	}

	public void setText(String string) {
		userMessage.setText(string);
	}

//	/**
//	 * @return the bla
//	 */
//	public String getBla() {
//		return bla;
//	}
//
//	/**
//	 * @param bla the bla to set
//	 */
//	public void setBla(String bla) {
//		this.bla = bla;
//	}	
}


