package com.br.kemart.ecommerce.persistence;

import java.util.Collection;

import com.br.kemart.ecommerce.core.Category;

public interface CategoryDAO {

	public void addUpdateCategory(Category category) ;
	public void deleteCategory(Category category) ;
	public Collection<Category> findAllCategorys() ;
	public Category findCategoryByName(final String categoryName) ;
	public Category findCategoryById(final long id) ;

}
