/**
 * 
 */
package com.ecmdeveloper.eds.validation.servlet;

import javax.servlet.annotation.WebServlet;

import org.iban4j.IbanFormatException;
import org.iban4j.IbanUtil;
import org.iban4j.InvalidCheckDigitException;
import org.iban4j.UnsupportedCountryException;

import com.ecmdeveloper.eds.model.ExternalDataRequest;
import com.ecmdeveloper.eds.model.ExternalDataResponse;
import com.ecmdeveloper.eds.model.Property;
import com.ecmdeveloper.eds.model.constants.DisplayMode;
import com.ecmdeveloper.eds.servlet.AbstractEDSServlet;

/**
 * Advanced validation servlet implementation
 * 
 * @author Ricardo Belfor
 */
@WebServlet(
		description = "An example of an EDS servlet.", 
		urlPatterns = { "/type/*", "/types", "/error", "/ping/*"
		})
public class EDSAdvancedValidationServlet extends AbstractEDSServlet{

	private static final long serialVersionUID = 0xC00L;

	@Override
	public String[] getObjectTypeNames(String repositoryId) {
		return new String[] {"Customer" };
	}

	@Override
	public void handleRequest(ExternalDataRequest dataRequest, ExternalDataResponse dataResponse) {
		handleIbanRequestVersion3(dataRequest, dataResponse);
	}

	/**
	 * Simple IBAN validation using a regular expression.
	 * 
	 * @param dataRequest the data request
	 * @param dataResponse the data response
	 */
	public void handleIbanRequestVersion1(ExternalDataRequest dataRequest, ExternalDataResponse dataResponse) {

		Property iban = dataRequest.getProperty("DEV_IBAN");
		
		if (iban != null) {

			iban.setFormat("([A-Z]{2})(\\d{2})([A-Za-z\\d]+)$");
			iban.setFormatDescription("<br><ul><li>Country code</li>"
					+ "<li>Two check digits</li>"
					+ "<li>Basic Bank Account Number</li></ul>");
			dataResponse.addProperty(iban);
		}
	}

	/**
	 * Advanced IBAN validation using a validation library.
	 * 
	 * @param dataRequest the data request
	 * @param dataResponse the data response
	 */
	public void handleIbanRequestVersion2(ExternalDataRequest dataRequest, ExternalDataResponse dataResponse) {

		Property iban = dataRequest.getProperty("DEV_IBAN");

		if (iban != null) {

			iban.setHasDependentProperties(true);

			String ibanValue = (String)iban.getValue();
			if ( !ibanValue.isEmpty() ) {
				try {
					IbanUtil.validate(ibanValue);
				} catch (IbanFormatException |
						InvalidCheckDigitException | 
						UnsupportedCountryException e ) {
					iban.setCustomValidationError(e.getMessage() );
				}
			}
			dataResponse.addProperty(iban);
		}
	}

	/**
	 * Advanced IBAN validation with extra feedback in the user interface.
	 *  
	 * @param dataRequest the data request
	 * @param dataResponse the data response
	 */
	public void handleIbanRequestVersion3(ExternalDataRequest dataRequest, ExternalDataResponse dataResponse) {
		
		Property iban = dataRequest.getProperty("DEV_IBAN");
		Property validationError = dataRequest.getProperty("ValidationError");

		if (iban != null && validationError != null) {

			iban.setHasDependentProperties(true);

			validationError.setLabel("IBAN validation");
			validationError.setDisplayMode(DisplayMode.readonly);
			validationError.setValue("");
			validationError.setFormat("^(?![\\s\\S])");
			dataResponse.addProperty(validationError);

			String ibanValue = (String)iban.getValue();
			if ( ibanValue != null && !ibanValue.isEmpty() ) {
				try {
					IbanUtil.validate(ibanValue);
				} catch (IbanFormatException |
						InvalidCheckDigitException | 
						UnsupportedCountryException e ) {
					iban.setCustomValidationError(e.getMessage() );
					validationError.setValue(e.getMessage());
					validationError.setCustomValidationError("Invalid IBAN");
				}
			}

			dataResponse.addProperty(iban);
		}
	}
}
