package jp.co.systena.tigerscave.shoppingcartdb.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import jp.co.systena.tigerscave.shoppingcartdb.model.display.Item;

@Service
public class ManagementService {

    /** ロギング */
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementService.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * アイテム名取得
     * @param アイテムID
     * @return アイテム名
     */
    public String getItemName(int itemId) {
        String itemName = jdbcTemplate.queryForObject(
            "SELECT item_name FROM items WHERE item_id = ?",
            String.class,
            itemId);
        LOGGER.info("itemName = " + itemName);
        return itemName;
    }

    /**
     * 値段取得
     * @param アイテムID
     * @return 値段
     */
    public int getItemPrice(int itemId) {
        int itemPrice = Integer.parseInt(jdbcTemplate.queryForObject(
            "SELECT price FROM items WHERE item_id = ?",
            String.class,
            itemId));
        LOGGER.info("itemPrice = " + itemPrice);
        return itemPrice;
    }

    /**
     * 商品リスト追加
     * @param Itemフォーム
     */
    public void addList(Item item) {
        jdbcTemplate.update(
            "INSERT INTO items VALUES( ?, ?, ? )",
            jdbcTemplate.queryForObject("SELECT max(item_id) FROM items", Integer.class) + 1,
            item.getItemName(),
            Integer.parseInt(item.getPrice()));
    }

    /**
     * 商品リスト削除
     * @param itemId
     */
    public void deleteList(String itemId) {
      jdbcTemplate.update("DELETE FROM items WHERE item_id = ?", Integer.parseInt(itemId));
    }
}
