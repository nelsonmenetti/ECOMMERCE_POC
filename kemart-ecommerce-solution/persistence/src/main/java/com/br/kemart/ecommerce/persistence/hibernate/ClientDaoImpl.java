package com.br.kemart.ecommerce.persistence.hibernate;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.br.kemart.ecommerce.core.Client;
import com.br.kemart.ecommerce.persistence.ClientDAO;


@Stateless
@SuppressWarnings("unchecked")
public class ClientDaoImpl implements ClientDAO {

	@PersistenceContext (unitName="manager") 
	EntityManager em;
		
	public ClientDaoImpl() {
	}

	public void addUpdateClient(Client client) {
		em.persist(client);
		em.flush();
	}

	public void deleteClient(Client client) {
		em.remove(client);
		em.flush();
	}

	public Collection<Client> findAllClients() {
		Query query=  em.createQuery("from Client c");
		return query.getResultList();
	}

	public Client findClientByName(final String clientName) {
		 Query query=  em.createQuery("FROM Client c WHERE c.clientName =:clientName");
		 query.setParameter("categoryName", clientName);
		 return (Client) query.getSingleResult();
	}

	public Client findClientById(final long id) {
		return em.find(Client.class, id);
	}
	
	public Client findClientByUsername(final String userName) {
		 Query query=  em.createQuery("FROM Client c WHERE c.userName =:userName");
		 query.setParameter("userName", userName);
		 return (Client) query.getSingleResult();
	}

	

	
}
