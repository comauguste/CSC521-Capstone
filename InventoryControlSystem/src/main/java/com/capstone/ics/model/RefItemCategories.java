package com.capstone.ics.model;
// Generated Mar 23, 2016 6:34:53 PM by Hibernate Tools 4.3.1



/**
 * RefItemCategories generated by hbm2java
 */
public class RefItemCategories  implements java.io.Serializable {


     private Integer pkItemCategoryCode;
     private String itemName;
     private String itemCategoryDescription;

    public RefItemCategories() {
    }

    public RefItemCategories(String itemName, String itemCategoryDescription) {
       this.itemName = itemName;
       this.itemCategoryDescription = itemCategoryDescription;
    }
   
    public Integer getPkItemCategoryCode() {
        return this.pkItemCategoryCode;
    }
    
    public void setPkItemCategoryCode(Integer pkItemCategoryCode) {
        this.pkItemCategoryCode = pkItemCategoryCode;
    }
    public String getItemName() {
        return this.itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public String getItemCategoryDescription() {
        return this.itemCategoryDescription;
    }
    
    public void setItemCategoryDescription(String itemCategoryDescription) {
        this.itemCategoryDescription = itemCategoryDescription;
    }




}


