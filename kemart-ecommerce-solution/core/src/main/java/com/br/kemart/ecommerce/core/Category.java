package com.br.kemart.ecommerce.core;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Field;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table (name="category")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlAttribute
	private Long id;
	
	@Column 
	@XmlAttribute 
	@Field
	private String categoryName;
	
	public Category(){
	}
	
	public Category(Long id, String categoryName) {
		super();
		this.id = id;
		this.categoryName = categoryName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}




	
	
}
