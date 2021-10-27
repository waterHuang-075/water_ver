package com.example.demoMaven.dao;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@Table(name = "cart", schema = "public")
public class CartT {
	  public CartT() {}

	    public CartT(CartT value) {
	        this.cartNumber = value.cartNumber;
	        this.customerName = value.customerName;
	        this.totalAmount = value.totalAmount;
	        this.currentVersion = value.currentVersion;
	        this.createdBy = value.createdBy;
	        this.createdDate = value.createdDate;
	        this.lastModifiedBy = value.lastModifiedBy;
	        this.lastModifiedDate = value.lastModifiedDate;
	    }

    private String        cartNumber;
    private String        customerName;
    private Integer       totalAmount;
    private Integer       currentVersion;
    private String        createdBy;
    private LocalDateTime createdDate;
    private String        lastModifiedBy;
    private LocalDateTime lastModifiedDate;
    public CartT(
            String        cartNumber,
            String        customerName,
            Integer       totalAmount,
            Integer       currentVersion,
            String        createdBy,
            LocalDateTime createdDate,
            String        lastModifiedBy,
            LocalDateTime lastModifiedDate
        ) {
            this.cartNumber = cartNumber;
            this.customerName = customerName;
            this.totalAmount = totalAmount;
            this.currentVersion = currentVersion;
            this.createdBy = createdBy;
            this.createdDate = createdDate;
            this.lastModifiedBy = lastModifiedBy;
            this.lastModifiedDate = lastModifiedDate;
        }
    @Id
    @Column(name = "cart_number", nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    public String getCartNumber() {
        return this.cartNumber;
    }

    public void setCartNumber(String cartNumber) {
        this.cartNumber = cartNumber;
    }

    @Column(name = "customer_name", nullable = false, length = 20)
    @NotNull
    @Size(max = 20)
    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Column(name = "total_amount", nullable = false, precision = 32)
    @NotNull
    public Integer getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Column(name = "current_version", nullable = false, precision = 32)
    @NotNull
    public Integer getCurrentVersion() {
        return this.currentVersion;
    }

    public void setCurrentVersion(Integer currentVersion) {
        this.currentVersion = currentVersion;
    }

    @Column(name = "created_by", nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "created_date", nullable = false)
    @NotNull
    public LocalDateTime getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Column(name = "last_modified_by", nullable = false, length = 60)
    @NotNull
    @Size(max = 60)
    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Column(name = "last_modified_date", nullable = false)
    @NotNull
    public LocalDateTime getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
