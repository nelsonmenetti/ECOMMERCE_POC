package com.br.kemart.ecommerce.services.rest;

import java.net.HttpURLConnection;

import javax.ws.rs.WebApplicationException;
import javax.xml.bind.JAXBException;

import org.apache.commons.codec.digest.DigestUtils;

import com.br.kemart.ecommerce.core.Client;

public class LoginServiceImpl {


	public int login(String username , String password) throws JAXBException{
		
		ClientServiceImpl clientService = new ClientServiceImpl();
		try{
		Client client= clientService.getClientDataByUserName(username);
		String hashedPassword = DigestUtils.sha512Hex(password);
		
		if(hashedPassword.equals(client.getPassword())){
			return HttpURLConnection.HTTP_OK;
		}
		else{		
		 throw new WebApplicationException(HttpURLConnection.HTTP_UNAUTHORIZED);
		}}catch (Exception e) {
		 throw new WebApplicationException(HttpURLConnection.HTTP_UNAUTHORIZED);
		}
	}
}
