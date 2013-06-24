package com.br.kemart.ecommerce.core;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="productlisting")
public class ProductListing {

	
	private List<Product> products;

	   public ProductListing()
	   {
	   }

	   public ProductListing(List<Product> products)
	   {
	      this.products = products;
	   }

	   @XmlElement(name="products")
	   public List<Product> getProducts()
	   {
	      return products;
	   }
}
