package com.br.kemart.ecommerce.services.rest;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

@Path("lucene")
public class LuceneIndexCreatorImpl {

	
	
	private EntityManager getEntityManager() throws NamingException{
		EntityManagerFactory entityManagerFactory = (EntityManagerFactory) (new InitialContext()).lookup("java:jboss/KmartEntityManagerFactory");
		EntityManager em = entityManagerFactory.createEntityManager();
		return em;
	}
	
	@GET
	@Path("create")
	@Produces("text/html")
    public void indexLucene() throws NamingException, InterruptedException{
		EntityManager em = getEntityManager();
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		fullTextEntityManager.createIndexer().startAndWait();
		System.out.println("Lucene index created .");
		em.close();
	}	
	

}
