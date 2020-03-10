package org.artur.validador.internal;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PostRequestFormat {

	String numberToValidate;
	Date requestTime;
	Boolean valid;
	
	public PostRequestFormat() {
		super();
	}

	public PostRequestFormat(String numberToValidate) {
		super();
		this.numberToValidate = numberToValidate;
	}

	public PostRequestFormat(String numberToValidate, Boolean valid) {
		super();
		this.numberToValidate = numberToValidate;
		Date date = new Date();
		this.requestTime = date;
		this.valid = valid;
	}
	public String getNumberToValidate() {
		return numberToValidate;
	}
	public void setNumberToValidate(String numberToValidate) {
		this.numberToValidate = numberToValidate;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	
	
}
