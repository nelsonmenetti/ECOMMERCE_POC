package com.br.kemart.ecommerce.core;

import java.io.Serializable;

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


@XmlRootElement(name = "client")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Client implements Serializable{

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
	private String userName;
	
	@Column 
	@XmlAttribute 
	private String clientName;
		
	@NotFound(action = NotFoundAction.IGNORE) 
	@OneToOne(cascade = CascadeType.ALL) 
	@JoinColumn(name = "deliveryAddressId") 
	@XmlElement (name="deliveryAddress") 
	private Address deliveryAddress;	
	
	
	
	@NotFound(action = NotFoundAction.IGNORE) 
	@OneToOne(cascade = CascadeType.ALL) 
	@JoinColumn(name = "billingAddressId") 
	@XmlElement (name="billingAddress") 
	private BillingAddress billingAddress;
	
	@Column 
	@XmlAttribute 
	private String fixedlinePhone;
	@Column 
	@XmlAttribute 
	private String cellphone;
	
	@Column String password ;
	
	
	public Client(){		
	}
	
	public Client(Long id, String userName, String clientName,
			Address deliveryAddress, BillingAddress billingAddress,
			String fixedlinePhone, String cellphone, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.clientName = clientName;
		this.deliveryAddress = deliveryAddress;
		this.billingAddress = billingAddress;
		this.fixedlinePhone = fixedlinePhone;
		this.cellphone = cellphone;
		this.password = password;
	}



	

	public String getFixedlinePhone() {
		return fixedlinePhone;
	}

	public void setFixedlinePhone(String fixedlinePhone) {
		this.fixedlinePhone = fixedlinePhone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public BillingAddress getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((billingAddress == null) ? 0 : billingAddress.hashCode());
		result = prime * result
				+ ((clientName == null) ? 0 : clientName.hashCode());
		result = prime * result
				+ ((deliveryAddress == null) ? 0 : deliveryAddress.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		Client other = (Client) obj;
		if (billingAddress == null) {
			if (other.billingAddress != null)
				return false;
		} else if (!billingAddress.equals(other.billingAddress))
			return false;
		if (clientName == null) {
			if (other.clientName != null)
				return false;
		} else if (!clientName.equals(other.clientName))
			return false;
		if (deliveryAddress == null) {
			if (other.deliveryAddress != null)
				return false;
		} else if (!deliveryAddress.equals(other.deliveryAddress))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", userName=" + userName + ", clientName="
				+ clientName + ", deliveryAddress=" + deliveryAddress
				+ ", billingAddress=" + billingAddress + "]";
	}
}
