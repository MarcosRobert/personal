/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restfully.shop.services;

import com.restfully.shop.domain.Customer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class CustomerResourceService implements CustomerResource {
private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
private AtomicInteger idCounter = new AtomicInteger();

public CustomerResourceService(){
}

	@Override
	public Response createCustomer(InputStream is) {
		Customer customer = readCustomer(is);
		customer.setId(idCounter.incrementAndGet());
		customerDB.put(customer.getId(), customer);
		System.out.println("Created customer " + customer.getId());
		return Response.created(URI.create("/customers/" + customer.getId())).build();
	}


@Override
	public StreamingOutput getCustomer(int id) {
		final Customer customer = customerDB.get(id);
		if (customer == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		return new StreamingOutput() {

			@Override
			public void write(OutputStream output) throws IOException, WebApplicationException {
				outputCustomer(output, customer);
			}
		};
	}
	
	@Override
	public void updateCustomer(int id, InputStream is) {
		Customer update = readCustomer(is);
		Customer current = customerDB.get(id);
		if (current == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		current.setFirstName(update.getFirstName());
		current.setLastName(update.getLastName());
		current.setStreet(update.getStreet());
		current.setState(update.getState());
		current.setZip(update.getZip());
		current.setCountry(update.getCountry());
	}

	protected void outputCustomer(OutputStream os, Customer cust) throws IOException {
		PrintStream writer = new PrintStream(os);
		writer.println("<customer id=\"" + cust.getId() + "\">");
		writer.println(" <first-name>" + cust.getFirstName()
				  + "</first-name>");
		writer.println(" <last-name>" + cust.getLastName()
				  + "</last-name>");
		writer.println(" <street>" + cust.getStreet() + "</street>");
		writer.println(" <city>" + cust.getCity() + "</city>");
		writer.println(" <state>" + cust.getState() + "</state>");
		writer.println(" <zip>" + cust.getZip() + "</zip>");
		writer.println(" <country>" + cust.getCountry() + "</country>");
		writer.println("</customer>");
	}

	protected Customer readCustomer(InputStream is) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(is);
			Element root = doc.getDocumentElement();
			Customer cust = new Customer();
			if (root.getAttribute("id") != null
					  && !root.getAttribute("id").trim().equals("")) {
				cust.setId(Integer.valueOf(root.getAttribute("id")));
			}
			NodeList nodes = root.getChildNodes();
			for (int i = 0; i < nodes.getLength(); i++) {
				Element element = (Element) nodes.item(i);
				if (element.getTagName().equals("first-name")) {
					cust.setFirstName(element.getTextContent());
				} else if (element.getTagName().equals("last-name")) {
					cust.setLastName(element.getTextContent());
				} else if (element.getTagName().equals("street")) {
					cust.setStreet(element.getTextContent());
				} else if (element.getTagName().equals("city")) {
					cust.setCity(element.getTextContent());
				} else if (element.getTagName().equals("state")) {
					cust.setState(element.getTextContent());
				} else if (element.getTagName().equals("zip")) {
					cust.setZip(element.getTextContent());
				} else if (element.getTagName().equals("country")) {
					cust.setCountry(element.getTextContent());
				}
			}
			return cust;
		} catch (ParserConfigurationException e) {
			throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
		} catch (SAXException e) {
			throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
		} catch (IOException e) {
			throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
		} catch (NumberFormatException e) {
			throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
		} catch (DOMException e) {
			throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
		}
	}

}
