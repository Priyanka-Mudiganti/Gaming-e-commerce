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
@Table(name="PURCHASE_ORDER")
public class PurchaseOrder implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1655559545749703560L;
	
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
    
    @Column(name = "FULFILLMENT_STATUS", nullable = true)
    private FulfillmentStatus fulfillmentStatus;
    
    @Column(name = "ORDER_STATE", nullable = true)
    private OrderState orderState;
    
    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;
    
    @Column(name = "ORDER_TYPE", nullable = false)
    private OrderType orderType = OrderType.NEW;
    
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "PURCHASE_ORDER_ID")
    private List<LineItem> lineItems;
    
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

    public FulfillmentStatus getFulfillmentStatus() {
        return fulfillmentStatus;
    }

    public void setFulfillmentStatus(FulfillmentStatus fulfillmentStatus) {
        this.fulfillmentStatus = fulfillmentStatus;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState state) {
        this.orderState = state;
    }

    public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LineItem> lineItems) {
		this.lineItems = lineItems;
	}
	
    public long getBuyerId() {
        return buyerId;
    }

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public OrderType getOrderType() {
        return orderType;
    }
    
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
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
}
