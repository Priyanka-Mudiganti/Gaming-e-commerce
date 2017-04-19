package com.gamezone.common.model;

import java.io.Serializable;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Offer implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2126429291520092063L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
	private Long id;
    @Column(name = "PRODUCT_ID")
    @JsonIgnore
	private Long productId;
    @Column(name = "TERM")
	private String term;
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "OFFER_ID")
    private List<OfferPrice> prices;

    
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

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<OfferPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<OfferPrice> prices) {
		this.prices = prices;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((productId == null) ? 0 : productId.hashCode());
        result = prime * result + ((term == null) ? 0 : term.hashCode());

        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Offer other = (Offer) obj;
        if (productId == null) {
            if (other.productId != null)
                return false;
        } else if (!(productId.equals(other.productId) && term.equals(other.term)))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Offer [id=").append(id).append(",");
        builder.append("term=").append(term).append("]");
        return builder.toString();
    }
}
