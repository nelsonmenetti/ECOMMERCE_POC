package com.br.kemart.ecommerce.persistence;

import java.util.Collection;
import com.br.kemart.ecommerce.core.Client;

public interface ClientDAO {

	public void addUpdateClient(Client client) ;
	public void deleteClient(Client Client) ;
	public Collection<Client> findAllClients() ;
	public Client findClientByName(final String clientName) ;
	public Client findClientById(final long id) ;
	public Client findClientByUsername(final String userName) ;

}
