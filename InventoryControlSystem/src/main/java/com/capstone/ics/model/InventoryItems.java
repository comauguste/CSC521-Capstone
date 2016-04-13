package com.capstone.ics.model;

import java.math.BigDecimal;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "inventory_items")
@Access(value = AccessType.PROPERTY)
public class InventoryItems implements java.io.Serializable {

    private Integer pkItemId;    
    private int fkSiteId;
    private StringProperty itemName = new SimpleStringProperty(this, "ITEM_NAME");
    private StringProperty itemCategory = new SimpleStringProperty(this, "ITEM_CATEGORY");
    private String itemType;
    private String itemDescription;
    private BigDecimal itemPrice;
    private BigDecimal itemCost;
    private Integer reorderLevel;
    private String otherItemDetails;
    private Integer quantityInStock;
    private Integer isActive;
    private String lastUpdatedBy;
    private Date lastUpdatedDate;
    private String createdBy;
    private Date createdDate;

    public InventoryItems() {
    }

    public InventoryItems(RefItemCategories refItemCategories, int fkSiteId, String lastUpdatedBy, Date lastUpdatedDate, String createdBy, Date createdDate) {
//        this.refItemCategories = refItemCategories;
        this.fkSiteId = fkSiteId;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDate = lastUpdatedDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    public InventoryItems(RefItemCategories refItemCategories, String itemName, String itemType, String itemDescription, BigDecimal itemPrice) {
//        this.refItemCategories = refItemCategories;
        setItemName(itemName);
        this.itemType = itemType;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public InventoryItems(RefItemCategories refItemCategories, int fkSiteId, String itemName, String itemType, String itemDescription, BigDecimal itemPrice, BigDecimal itemCost, Integer reorderLevel, String otherItemDetails, Integer quantityInStock, Integer isActive, String lastUpdatedBy, Date lastUpdatedDate, String createdBy, Date createdDate) {
//        this.refItemCategories = refItemCategories;
        this.fkSiteId = fkSiteId;
        setItemName(itemName);
        this.itemType = itemType;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
        this.itemCost = itemCost;
        this.reorderLevel = reorderLevel;
        this.otherItemDetails = otherItemDetails;
        this.quantityInStock = quantityInStock;
        this.isActive = isActive;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedDate = lastUpdatedDate;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ITEM_ID")
    public Integer getPkItemId() {
        return this.pkItemId;
    }

    public void setPkItemId(Integer pkItemId) {
        this.pkItemId = pkItemId;
    }

    @Column(name = "ITEM_CATEGORY")
    public String getItemCategory() {
        return itemCategoryProperty().get();
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory.set(itemCategory);
    }
    
    public StringProperty itemCategoryProperty() {
        return this.itemCategory;
    }
        
    @Column(name = "FK_SITE_ID")
    public int getFkSiteId() {
        return this.fkSiteId;
    }

    public void setFkSiteId(int fkSiteId) {
        this.fkSiteId = fkSiteId;
    }

    @Column(name = "ITEM_NAME")
    public String getItemName() {
        return itemNameProperty().get();
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public StringProperty itemNameProperty() {
        return this.itemName;
    }

    @Column(name = "ITEM_TYPE")
    public String getItemType() {
        return this.itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    @Column(name = "ITEM_DESCRIPTION")
    public String getItemDescription() {
        return this.itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @Column(name = "ITEM_PRICE")
    public BigDecimal getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Column(name = "ITEM_COST")
    public BigDecimal getItemCost() {
        return this.itemCost;
    }

    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    @Column(name = "REORDER_LEVEL")
    public Integer getReorderLevel() {
        return this.reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    @Column(name = "OTHER_ITEM_DETAILS")
    public String getOtherItemDetails() {
        return this.otherItemDetails;
    }

    public void setOtherItemDetails(String otherItemDetails) {
        this.otherItemDetails = otherItemDetails;
    }

    @Column(name = "QUANTITY_IN_STOCK")
    public Integer getQuantityInStock() {
        return this.quantityInStock;
    }

    public void setQuantityInStock(Integer quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Column(name = "IS_ACTIVE")
    public Integer getIsActive() {
        return this.isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Column(name = "LAST_UPDATED_BY")
    public String getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Column(name = "LAST_UPDATED_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Column(name = "CREATED_BY")
    public String getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "CREATED_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
