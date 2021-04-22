package com.BestBot.Core.Entity;

import com.BestBot.Core.Repository.ItemEntityRepository;
import lombok.Data;
import org.springframework.context.ApplicationContext;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    private Long id;
    private String name;
    private String Category;
    private int sellPrice;
    private int buyPrice;
    private int sellOffers;
    private int buyOrders;

    public ItemEntity() {
    }
}
