package com.gamezone.common.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
public class Subscription implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6129107643021140954L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private Long id;

	@Column(name = "LAST_MODIFIED", nullable = false)
    private Date lastModified;

    @Column(name = "BUYER_ID", nullable = false)
    private long buyerId;
    
    @Column(name = "PAYMENT_PROFILE_ID", nullable = false)
    private Long paymentProfileId;
    
    @Column(name = "CREATED", nullable = false)
    private Date created;
    
    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;
    
    @Column(name = "PURCHASE_ORDER_ID")
    private Long purchaseOrderId;
    
    @Column(name = "PRODUCT_ID")
    private Long productId;
    
    @Column(name = "OFFER_ID")
    private Long offerId;
    
    @Column(name = "PRICE_ID")
    private Long priceId;
    
    @Column(name = "STATUS")
    private SubscriptionStatus status;
    
    @Column(name = "NEXT_BILLING_DATE", nullable = false)
    private Date nextBillingDate;
    
    
    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

    public long getBuyerId() {
        return buyerId;
    }

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPaymentProfileId() {
		return paymentProfileId;
	}

	public void setPaymentProfileId(Long paymentProfileId) {
		this.paymentProfileId = paymentProfileId;
	}

	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}

	public Long getPriceId() {
		return priceId;
	}

	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}

	public SubscriptionStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}

	public Date getNextBillingDate() {
		return nextBillingDate;
	}

	public void setNextBillingDate(Date nextBillingDate) {
		this.nextBillingDate = nextBillingDate;
	}
}
