package com.gamezone.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LineItem implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7085190944592645095L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private Long id;

	@Column(name = "PURCHASE_ORDER_ID",nullable = false)
	@JsonIgnore
	Long purchaseOrderId;
	@Column(name = "PRODUCT_ID")
	Long productId;
	@Column(name = "PRICE_ID")
	Long priceId;
	@Column(name = "QUANTITY")
	int quantity;
	@Column(name = "OFFER_ID")
	Long offerId;
	
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
	
	public Long getPriceId() {
		return priceId;
	}
	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Long getOfferId() {
		return offerId;
	}
	
	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
	
	@JsonIgnore
	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}
	
	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
}
