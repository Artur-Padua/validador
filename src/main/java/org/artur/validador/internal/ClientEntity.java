package org.artur.validador.internal;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class ClientEntity {

	String username;
	long numberOfUniqueRequests;
	long numberOfRequests;
	float valorDevido;
	String lastRequest;
	Date dateOflastRequest;
	
	//@XmlTransient
	String password;
	
	public ClientEntity() {
		super();
		this.valorDevido = 0.0f;
		this.numberOfUniqueRequests = 0;
		this.numberOfRequests = 0;
	}

	public ClientEntity(String username, String password, long numberOfUniqueRequests,
			long numberOfRequests, String lastRequest, Date dateOflastRequest) {
		super();
		this.username = username;
		this.password = password;
		this.numberOfUniqueRequests = numberOfUniqueRequests;
		this.numberOfRequests = numberOfRequests;
		this.lastRequest = lastRequest;
		this.dateOflastRequest = dateOflastRequest;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public float getValorDevido() {
		return valorDevido;
	}
	public void setValorDevido(float valorDevido) {
		this.valorDevido = valorDevido;
	}
	public long getNumberOfUniqueRequests() {
		return numberOfUniqueRequests;
	}
	public void setNumberOfUniqueRequests(long numberOfUniqueRequests) {
		this.numberOfUniqueRequests = numberOfUniqueRequests;
	}
	public long getNumberOfRequests() {
		return numberOfRequests;
	}
	public void setNumberOfRequests(long numberOfRequests) {
		this.numberOfRequests = numberOfRequests;
	}
	public String getLastRequest() {
		return lastRequest;
	}
	public void setLastRequest(String lastRequest) {
		this.lastRequest = lastRequest;
	}
	public Date getDateOflastRequest() {
		return dateOflastRequest;
	}
	public void setDateOflastRequest(Date dateOflastRequest) {
		this.dateOflastRequest = dateOflastRequest;
	}
	
}
