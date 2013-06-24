package com.br.kemart.ecommerce.services.business;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.br.kemart.ecommerce.services.rest.ClientServiceImpl;
import com.br.kemart.ecommerce.services.rest.LuceneIndexCreatorImpl;
import com.br.kemart.ecommerce.services.rest.ProductServiceImpl;


@ApplicationPath("/services")
public class RestApplications extends Application{

	   HashSet<Object> singletons = new HashSet<Object>();

	   public RestApplications()
	   {
	      singletons.add(new ClientServiceImpl());
	      singletons.add(new ProductServiceImpl());
	      singletons.add(new LuceneIndexCreatorImpl());

	      
	   }
	   
	   @Override
	   public Set<Class<?>> getClasses()
	   {
	      HashSet<Class<?>> set = new HashSet<Class<?>>();
	      return set;
	   }

	   @Override
	   public Set<Object> getSingletons()
	   {
	      return singletons;  
	   }
	
}
