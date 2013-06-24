package com.br.kemart.ecommerce.services.rest;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBException;

import com.br.kemart.ecommerce.core.Category;
import com.br.kemart.ecommerce.core.Product;
import com.br.kemart.ecommerce.core.ProductListing;
import com.br.kemart.ecommerce.persistence.ProductDAO;

@Path("product")
public class ProductServiceImpl {

	ProductDAO productDAO;

	public ProductDAO getProductDao() {

		if (productDAO == null) {
			try {
				productDAO = (ProductDAO) (new InitialContext())
						.lookup("java:global/kwikemart-online-store/ProductDaoImpl");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return productDAO;
	}
	
	@POST
	@Path("add")
	@Produces("text/html")
	public String createProduct(@FormParam (value="productName") String productName,
			@FormParam (value="manufacturer")String manufacturer,
			@FormParam (value="description")String description,
			@FormParam (value="value")Double value,
			@FormParam (value="discount")Double discount,
			@FormParam (value="productImageURL")String productImageURL,
			@FormParam (value="categoryName")String categoryName
			) throws JAXBException {
		String goodResult = "Your product was created successfully !";
		String badResult  = "Your product was NOT created, please verify the logs !";
		try{
			
			Product product = new Product();
			product.setProductName(productName);
			product.setManufacturer(manufacturer);
			product.setDescription(description);
			
			product.setValue(new BigDecimal(value));
			product.setDiscount(new BigDecimal(discount));
			
			product.setProductImageURL(productImageURL);
			
			Category category = new Category(null,categoryName);			
			product.setCategory(category);
			
			getProductDao().addUpdateProduct(product);
			
		}catch (Exception e) {
			e.printStackTrace();
			return badResult;
		}
		
		return goodResult;
	}
	
	@GET
	@Path("search")
	@Produces("application/xml")
	public ProductListing searchProducts(@QueryParam(value = "search") String searchForMe) throws NamingException{
		
		ProductListing listing = null;
		if(searchForMe !=null && !searchForMe.equals("")){
		   listing = new ProductListing(getProductDao().searchProductsLucene(searchForMe));
		}
		return listing;
	}
}
