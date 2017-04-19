package com.gamezone.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PAYMENT_PROFILE")
public class PaymentProfile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6959728994330691485L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "PAYMENT_METHOD")
	private String paymentMethod;
	@Column(name = "LAST_4_DIGITS")
	private String last4Digits;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EXPIRATION_DATE", nullable = true)
	private Date expirationDate;
	@Column(name = "TOKEN")
	private String token;
	@Column(name = "ADDR_ID")
	private Long billingAddressId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getLast4Digits() {
		return last4Digits;
	}

	public void setLast4Digits(String last4Digits) {
		this.last4Digits = last4Digits;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getBillingAddressId() {
		return billingAddressId;
	}

	public void setBillingAddressId(Long billingAddressId) {
		this.billingAddressId = billingAddressId;
	}
}
