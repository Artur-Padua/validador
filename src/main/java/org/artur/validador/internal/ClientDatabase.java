package org.artur.validador.internal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;

import java.io.*;

	// Implements a Database founded on Clients.xml file
	// Clients should be added through Clients.xml file
public class ClientDatabase {

	private static final float POST_SERVICE_PRICE = 0.1f;
	private static final String FILE_NAME = "/home/artur/eclipse-workspace/validador/src/main/java/org/artur/validador/internal/Clients.xml";
	
		// XML related variables
	DocumentBuilderFactory factory;
	DocumentBuilder builder;
	Document document;
	NodeList nList;
	
		// database data structure
	HashMap<String, Node> clientsMapOfNodeList;
	public HashMap<String, ClientEntity> clientsMap = new HashMap<String, ClientEntity>(); // used in other classes
	List<ClientEntity> clients;
	
		// Constructor that can instantiate database and parse XML file
	public ClientDatabase() {
		super();
		try {
			this.openXMLDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.parseClientsXML();	// parse XML file
	}
	
	//public void addUniqueRequest(String username) {
	//	ClientEntity client = clientsMap.get(username);
	//	client.setNumberOfRequests(client.getNumberOfRequests() + 1);
	//	client.setNumberOfUniqueRequests(client.getNumberOfUniqueRequests() + 1);
	//}
	
		// Increments NonUniqueRequest
	public void addNonUniqueRequest(String username) {
		ClientEntity client = clientsMap.get(username);
		client.setNumberOfRequests(client.getNumberOfRequests() + 1);
		clientsMap.replace(username, client);
		System.out.println("updated numberOfRequests = " + clientsMap.get(username).getNumberOfRequests());
	}
	
		// updates valorDevido for 1 request
	public void updateValorDevido(String username) {
		ClientEntity client = clientsMap.get(username);
		client.setValorDevido(client.getValorDevido() + POST_SERVICE_PRICE);
		clientsMap.replace(username, client);
		System.out.println("updated valorDevido = " + clientsMap.get(username).getValorDevido());
	}
	
	private void openXMLDocument() throws ParserConfigurationException, SAXException, IOException {
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		document = builder.parse(new File(FILE_NAME));
		document.getDocumentElement().normalize();
	}
	
	private void setClientsXML(String username) {
		//nodeClientElement.setNodeValue(arg0);
	}
	
	private List<ClientEntity> parseClientsXML() {
	    	//Initialize a list of clients
		clientsMapOfNodeList = new HashMap<String, Node>();
		clients = new ArrayList<ClientEntity>();
		ClientEntity client = null;
		
		nList = document.getElementsByTagName("client");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nodeClient = nList.item(i);
			if (nodeClient.getNodeType() == Node.ELEMENT_NODE) {
			    Element nodeClientElement = (Element) nodeClient;
			    client = new ClientEntity();
			    client.setUsername(nodeClientElement.getAttribute("username"));
			    //System.out.println("TEST user: " + nodeClientElement.getElementsByTagName("username").item(0).getTextContent());
			    client.setPassword(nodeClientElement.getElementsByTagName("password").item(0).getTextContent());
			    if (!nodeClientElement.getElementsByTagName("numberOfUniqueRequests").item(0).getTextContent().equals(""))
			    	client.setNumberOfUniqueRequests(Long.parseLong(nodeClientElement.getElementsByTagName("numberOfUniqueRequests").item(0).getTextContent()));
			    	//System.out.println("TEST numberOfUniqueRequests: " + nodeClientElement.getElementsByTagName("numberOfUniqueRequests").item(0).getTextContent());
			    if (!nodeClientElement.getElementsByTagName("numberOfRequests").item(0).getTextContent().equals(""))
			    	client.setNumberOfRequests(Long.parseLong(nodeClientElement.getElementsByTagName("numberOfRequests").item(0).getTextContent()));
			    client.setLastRequest(nodeClientElement.getElementsByTagName("lastRequest").item(0).getTextContent());
			    
			    
			    //Date
			    /*
			    if (!nodeClientElement.getElementsByTagName("dateOfLastRequest").item(0).getTextContent().equals("")) {
				    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-ddTHH:mm:ss.SSSZz"); //2020-03-10T02:19:06.103Z[UTC]
				    
				    try {
						//Date date = dt.parse(nodeClientElement.getElementsByTagName("dateOfLastRequest").item(0).getTextContent());
						//client.setDateOflastRequest(date);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }
			    */
			    
			    clients.add(client);
			    clientsMapOfNodeList.put(client.username, nList.item(i));
			    clientsMap.put(client.username, client);
			    System.out.println(" username:" + (client.username) + " password:" + (client.password) 
			    					+ " numberOfUniqueRequests:" + client.numberOfUniqueRequests 
			    					+ " numberOfRequests:" + client.numberOfRequests 
			    					+ " lastRequest:" + client.lastRequest
			    					+ " dateOfLastRequest:" + client.dateOflastRequest);
			}
		}
		return clients;
	}
	
	
}
