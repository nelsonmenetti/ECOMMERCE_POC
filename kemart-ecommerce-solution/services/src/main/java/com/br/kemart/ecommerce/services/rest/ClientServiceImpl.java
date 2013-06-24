package com.br.kemart.ecommerce.services.rest;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBException;

import org.apache.commons.codec.digest.DigestUtils;

import com.br.kemart.ecommerce.core.Address;
import com.br.kemart.ecommerce.core.BillingAddress;
import com.br.kemart.ecommerce.core.Client;
import com.br.kemart.ecommerce.persistence.ClientDAO;

@Path("client")
public class ClientServiceImpl {

	ClientDAO clientDAO;

	public ClientDAO getClientDao() {

		if (clientDAO == null) {
			try {
				clientDAO = (ClientDAO) (new InitialContext())
						.lookup("java:global/kwikemart-online-store/ClientDaoImpl");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clientDAO;
	}

	@GET
	@Path("search")
	@Produces("application/xml")
	public Client getClientDataByUserName(@QueryParam(value = "username") String username)
			throws JAXBException {
		
		
		Client client = null;
		
		try{
			 client = getClientDao().findClientByUsername(username);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return client;
	}

	@POST
	@Path("add")
	@Produces("text/html")
	public String createClient(@FormParam (value="userName")String userName,
							   @FormParam (value="cellphone")String cellphone,
							   @FormParam (value="clientName")String clientName,
							   @FormParam (value="fixedlinePhone")String fixedlinePhone,
							   @FormParam (value="password")String password,
							   @FormParam (value="dstreet")String dstreet,
							   @FormParam (value="dcity")String dcity,
							   @FormParam (value="dnumber")String dnumber,
							   @FormParam (value="dcountry")String dcountry,
							   @FormParam (value="dzipCode")String dzipCode,
							   @FormParam (value="bstreet")String bstreet,
							   @FormParam (value="bcity")String bcity,
							   @FormParam (value="bnumber")String bnumber,
							   @FormParam (value="bcountry")String bcountry,
							   @FormParam (value="bzipCode")String bzipCode	
			) throws JAXBException {
		String goodResult = "Your account was created successfully !";
		String badResult  = "Your account was NOT created, please please try again later !";
		
		try{
		Client client = new Client();
		Address deliveryAddress = new Address();
		BillingAddress billingAddress = new BillingAddress();
		client.setBillingAddress(billingAddress);
		client.setDeliveryAddress(deliveryAddress);
		client.setUserName(userName);
		client.setCellphone(cellphone);
		client.setClientName(clientName);
		client.setFixedlinePhone(fixedlinePhone);
		String sha1password = DigestUtils.sha512Hex(password);
		client.setPassword(sha1password);
		
		deliveryAddress.setCity(dcity);
		deliveryAddress.setCountry(dcountry);
		deliveryAddress.setNumber(dnumber);
		deliveryAddress.setStreet(dstreet);
		deliveryAddress.setZipCode(dzipCode);

		billingAddress.setCity(bcity);
		billingAddress.setCountry(bcountry);
		billingAddress.setNumber(bnumber);
		billingAddress.setStreet(bstreet);
		billingAddress.setZipCode(bzipCode);

		getClientDao().addUpdateClient(client);
		}catch (Exception e) {
			e.printStackTrace();
			return badResult;
		} 
		return goodResult;
	}
}
