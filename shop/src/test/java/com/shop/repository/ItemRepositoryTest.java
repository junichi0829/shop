package com.shop.repository;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("商品 貯藏 테스트")
    public void createItemTest() {
        Item item = new Item();
        item.setItemNm("테스트 商品");
        item.setPrice(10000);
        item.setItemDetail("테스트 商品 詳細 說明");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }

    public void createItemList() {
        for (int i=1; i<=10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 商品" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 商品 詳細　說明" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("商品名 照會 테스트")
    public void findByItemNmTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 商品1");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("商品名、商品詳細說明 or 테스트")
    public void findByItemNmOrItemDetail() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 商品1", "테스트 商品 詳細 說明5");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("價格 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("價格 내림次順 照會 테스트")
    public void findByPriceLessThanOrderByPriceDesc() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("@Query를 利用한 商品 照會 테스트")
    public void findByItemDetailTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 商品 詳細 說明");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }
    
    @Test
    @DisplayName("nativeQuery 屬性을 利用한 商品 照會 테스트")
    public void findByItemDetailByNative() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetailByNative("테스트 商品 詳細 說明");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }
}