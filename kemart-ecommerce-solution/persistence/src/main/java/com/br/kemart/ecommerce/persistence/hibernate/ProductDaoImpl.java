package com.br.kemart.ecommerce.persistence.hibernate;

import java.util.Collection;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.QueryParam;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

import com.br.kemart.ecommerce.core.Category;
import com.br.kemart.ecommerce.core.Product;
import com.br.kemart.ecommerce.core.ProductListing;
import com.br.kemart.ecommerce.persistence.ProductDAO;


@Stateless
@SuppressWarnings("unchecked")
public class ProductDaoImpl  implements ProductDAO {

	@PersistenceContext (unitName="manager") 
	EntityManager em;
	
	public ProductDaoImpl() {
	}

	public void addUpdateProduct(Product product) {
		em.merge(product);
	}
	public void deleteProduct(Product Product) {
		em.remove(Product);
	}

	public Collection<Product> findAllProducts() {
		Query query=  em.createQuery("from Product c");
		return query.getResultList();
	}

	public Product findProductByName(final String productName) {
		 Query query=  em.createQuery("FROM Product c WHERE c.productName =:productName");
		 query.setParameter("productName", productName);
		 return (Product) query.getSingleResult();
	}

	public Product findProductById(final long id) {
		return (Product) em.find(Product.class, id);
	}
	
	public Product findProductByCategory( Category category) {
		 Query query=  em.createQuery("FROM Product c WHERE c.category.categoryName =:category.name");
		 query.setParameter("category.name", category.getCategoryName());
		 return (Product) query.getSingleResult();
	}

	public List<Product> searchProductsLucene(String searchForMe) {
		FullTextEntityManager fullTextEntityManager =org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		// create native Lucene query unsing the query DSL
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity( Product.class ).get();
		org.apache.lucene.search.Query query = qb
		  .keyword()
		  .onFields("productName", "description", "category.categoryName")
		  .matching(searchForMe)
		  .createQuery();

		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query persistenceQuery =  fullTextEntityManager.createFullTextQuery(query, Product.class);

		// execute search
		List<Product> result = (List<Product>) persistenceQuery.getResultList();
        return  result;
	}

	
}
