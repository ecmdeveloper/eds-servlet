package com.ecmdeveloper.eds.model.impl;

/**
 * This class wraps the error message in a response recognized by IBM Case
 * Manager (userMesssage) and IBM Content Navigator (userMessage).
 * 
 * @author Ricardo Belfor
 */
public class ErrorResponse {

	private String userMessage;
	private final UserMessage userMesssage;
	
	public ErrorResponse() {
		userMesssage = new UserMessage();
	}
	
	public String getUserMessage() {
		return userMessage;
	}

	public UserMessage getUserMesssage() {
		return userMesssage;
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
		userMessage = string;
		userMesssage.setText(string);
	}
}


