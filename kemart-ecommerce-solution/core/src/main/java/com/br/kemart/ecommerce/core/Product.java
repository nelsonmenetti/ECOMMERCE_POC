package com.br.kemart.ecommerce.core;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;


@XmlRootElement(name = "product")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table (name="product")
@Indexed 
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlAttribute
	private Long id;
	
	@Column 
	@XmlAttribute
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String productName;
	
	@Column 
	@XmlAttribute
	private String productImageURL;
	
	@Column 
	@XmlAttribute
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String description;
	
	@Column 
	@XmlAttribute
	@Field(index=Index.YES, analyze=Analyze.YES, store=Store.NO)
	private String manufacturer;
	
	@Column 
	@XmlAttribute
	private BigDecimal discount;

	@Column 
	@XmlAttribute
	private BigDecimal value;

	@IndexedEmbedded
	@NotFound(action = NotFoundAction.IGNORE) 
	@OneToOne(cascade = CascadeType.ALL) 
	@JoinColumn(name = "categoryId") 
	@XmlElement (name="productCategory")
	private Category category;

	
	public Product(){		
	}
	
	public Product(Long id, String productName, String productImageURL,
			String description, String manufacturer, BigDecimal discount,
			BigDecimal value,Category category) {
		super();
		this.id = id;
		this.productName = productName;
		this.productImageURL = productImageURL;
		this.description = description;
		this.manufacturer = manufacturer;
		this.discount = discount;
		this.value = value;
		this.category =category;
	}
	
	
	
	

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImageURL() {
		return productImageURL;
	}
	public void setProductImageURL(String productImageURL) {
		this.productImageURL = productImageURL;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result
				+ ((discount == null) ? 0 : discount.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((manufacturer == null) ? 0 : manufacturer.hashCode());
		result = prime * result
				+ ((productImageURL == null) ? 0 : productImageURL.hashCode());
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (manufacturer == null) {
			if (other.manufacturer != null)
				return false;
		} else if (!manufacturer.equals(other.manufacturer))
			return false;
		if (productImageURL == null) {
			if (other.productImageURL != null)
				return false;
		} else if (!productImageURL.equals(other.productImageURL))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
}
