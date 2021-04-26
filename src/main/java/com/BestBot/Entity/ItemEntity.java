package com.BestBot.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    private Long id;
    private String name;
    private String Category;
    private double sellPrice;
    private double buyPrice;
    private int sellOffers;
    private int buyOrders;
    private String lastUpdateTime;
    private String rarityId;
    private String rarity;

    public ItemEntity() {
    }
}
