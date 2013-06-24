package com.br.kemart.ecommerce.persistence.hibernate;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.br.kemart.ecommerce.core.Category;
import com.br.kemart.ecommerce.persistence.CategoryDAO;


@Stateless
@SuppressWarnings("unchecked")
public class CategoryDaoImpl implements CategoryDAO {

	
	
	@PersistenceContext(unitName = "manager") 
	EntityManager em;
	

	public CategoryDaoImpl() {
	}

	public void addUpdateCategory(Category category) {
		em .merge(category);
	}
	public void deleteCategory(Category Category) {
		em .remove(Category);
	}

	public Collection<Category> findAllCategorys() {
		 Query query=  em .createQuery("from Category c");
		 
		 return query.getResultList();
	}

	public Category findCategoryByName(final String categoryName) {
	
		 Query query=  em .createQuery("FROM Category c WHERE c.categoryName =:categoryName");
		 query.setParameter("categoryName", categoryName);
		 return (Category) query.getSingleResult();

	}

	public Category findCategoryById(final long id) {
		return (Category) em .find(Category.class, id);
	}
		
}
