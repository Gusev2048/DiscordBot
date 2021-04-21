package com.BestBot.Core.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    private Long id;
    @Column
    private String name;
    @Column
    private String category;
    @Column
    private String amount;
    @Column
    private int sellPrice;
    @Column
    private int buyPrice;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.sellPrice = sellPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public ItemEntity() {
    }

    public ItemEntity(Long id, String name, String category, String amount) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.amount = amount;
    }
}
