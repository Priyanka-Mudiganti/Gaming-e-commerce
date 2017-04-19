package com.gamezone.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="OFFER_PRICE")
public class OfferPrice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7107585627997881981L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private Long id;
    @Column(name = "PRODUCT_ID")
    @JsonIgnore
	private Long productId;
    @Column(name = "OFFER_ID")
    @JsonIgnore
	private Long offerID;
    @Column(name = "AMOUNT")
	private BigDecimal amount;
    @Column(name = "CURRENCY")
	private String currency;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getOfferID() {
		return offerID;
	}
	public void setOfferID(Long offerID) {
		this.offerID = offerID;
	}
	public BigDecimal getAmount() {
		return amount.setScale(2, RoundingMode.HALF_EVEN);
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
}
