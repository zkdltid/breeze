package com.zkdl.breeze.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //一個product可以被加入到很多個order(cart_item)
    // CascadeType => 聯及, 合併處理相關table
//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    private int quantity;
}
