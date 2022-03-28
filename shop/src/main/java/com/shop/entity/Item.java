package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    //商品코드


    @Column(nullable = false, length = 50)
    private String itemNm; //商品名

    @Column(name = "price", nullable = false)
    private int price;  //價格

    @Column(nullable = false)
    private int stockNumber;    //在庫數量

    @Lob
    @Column(nullable = false)
    private String itemDetail;  //商品詳細說明

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  //商品販賣狀態

    private LocalDateTime regTime;  //登錄時間

    private LocalDateTime updateTime;   //修正時間
}
