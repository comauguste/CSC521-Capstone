package com.capstone.ics.model;



import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ref_item_categories")
@Access(value = AccessType.PROPERTY)
public class RefItemCategories  implements java.io.Serializable {


     private Integer pkItemCategoryCode;
     private String itemCategoryName;
     private String itenCategoryDescription;

    public RefItemCategories() {
    }
    
    public RefItemCategories(String itemCategoryName) {
        
        this.itemCategoryName = itemCategoryName;
    }


    public RefItemCategories(String itemCategoryName, String itenCategoryDescription) {
       this.itemCategoryName = itemCategoryName;
       this.itenCategoryDescription = itenCategoryDescription;
       
    }
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_ITEM_CATEGORY_CODE")
    public Integer getPkItemCategoryCode() {
        return this.pkItemCategoryCode;
    }
    
    public void setPkItemCategoryCode(Integer pkItemCategoryCode) {
        this.pkItemCategoryCode = pkItemCategoryCode;
    }
    
    @Column(name = "ITEM_CATEGORY_NAME")
    public String getItemCategoryName() {
        return this.itemCategoryName;
    }
    
    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName;
    }
    
    @Column(name = "ITEN_CATEGORY_DESCRIPTION")
    public String getItenCategoryDescription() {
        return this.itenCategoryDescription;
    }
    
    public void setItenCategoryDescription(String itenCategoryDescription) {
        this.itenCategoryDescription = itenCategoryDescription;
    }
    
}


