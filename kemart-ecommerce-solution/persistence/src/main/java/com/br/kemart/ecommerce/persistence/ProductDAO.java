package com.br.kemart.ecommerce.persistence;

import java.util.Collection;
import java.util.List;

import com.br.kemart.ecommerce.core.Product;
import com.br.kemart.ecommerce.core.Category;


public interface ProductDAO {

	public void addUpdateProduct(Product Product) ;
	public void deleteProduct(Product Product) ;
	public Collection<Product> findAllProducts() ;
	public Product findProductByName(final String productName) ;
	public Product findProductById(final long id) ;
	public Product findProductByCategory(Category category) ;
	public List<Product> searchProductsLucene(String searchForMe) ;

}
