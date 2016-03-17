package com.capstone.ics.model;
// Generated Mar 17, 2016 5:45:32 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * InventoryItems generated by hbm2java
 */
public class InventoryItems  implements java.io.Serializable {


     private Integer pkItemId;
     private RefItemCategories refItemCategories;
     private Site site;
     private String itemType;
     private String itemDescription;
     private BigDecimal itemPrice;
     private Integer reorderLevel;
     private Integer reoderQuantity;
     private String otherItemDetails;
     private Integer quantityInStock;
     private Integer isActive;
     private String lastUpdatedBy;
     private Date lastUpdatedDate;
     private String createdBy;
     private Date createdDate;

    public InventoryItems() {
    }

	
    public InventoryItems(RefItemCategories refItemCategories, Site site, String lastUpdatedBy, Date lastUpdatedDate, String createdBy, Date createdDate) {
        this.refItemCategories = refItemCategories;
        this.site = site;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDate = lastUpdatedDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }
    public InventoryItems(RefItemCategories refItemCategories, Site site, String itemType, String itemDescription, BigDecimal itemPrice, Integer reorderLevel, Integer reoderQuantity, String otherItemDetails, Integer quantityInStock, Integer isActive, String lastUpdatedBy, Date lastUpdatedDate, String createdBy, Date createdDate) {
       this.refItemCategories = refItemCategories;
       this.site = site;
       this.itemType = itemType;
       this.itemDescription = itemDescription;
       this.itemPrice = itemPrice;
       this.reorderLevel = reorderLevel;
       this.reoderQuantity = reoderQuantity;
       this.otherItemDetails = otherItemDetails;
       this.quantityInStock = quantityInStock;
       this.isActive = isActive;
       this.lastUpdatedBy = lastUpdatedBy;
       this.lastUpdatedDate = lastUpdatedDate;
       this.createdBy = createdBy;
       this.createdDate = createdDate;
    }
   
    public Integer getPkItemId() {
        return this.pkItemId;
    }
    
    public void setPkItemId(Integer pkItemId) {
        this.pkItemId = pkItemId;
    }
    public RefItemCategories getRefItemCategories() {
        return this.refItemCategories;
    }
    
    public void setRefItemCategories(RefItemCategories refItemCategories) {
        this.refItemCategories = refItemCategories;
    }
    public Site getSite() {
        return this.site;
    }
    
    public void setSite(Site site) {
        this.site = site;
    }
    public String getItemType() {
        return this.itemType;
    }
    
    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
    public String getItemDescription() {
        return this.itemDescription;
    }
    
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    public BigDecimal getItemPrice() {
        return this.itemPrice;
    }
    
    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }
    public Integer getReorderLevel() {
        return this.reorderLevel;
    }
    
    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }
    public Integer getReoderQuantity() {
        return this.reoderQuantity;
    }
    
    public void setReoderQuantity(Integer reoderQuantity) {
        this.reoderQuantity = reoderQuantity;
    }
    public String getOtherItemDetails() {
        return this.otherItemDetails;
    }
    
    public void setOtherItemDetails(String otherItemDetails) {
        this.otherItemDetails = otherItemDetails;
    }
    public Integer getQuantityInStock() {
        return this.quantityInStock;
    }
    
    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    public Integer getIsActive() {
        return this.isActive;
    }
    
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }
    
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }
    
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
    public String getCreatedBy() {
        return this.createdBy;
    }
    
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedDate() {
        return this.createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }




}

