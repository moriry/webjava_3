package jp.co.systena.tigerscave.shoppingcartdb.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import jp.co.systena.tigerscave.shoppingcartdb.model.display.Item;

@Service
public class ListService {

    /** ロギング */
    private static final Logger LOGGER = LoggerFactory.getLogger(ListService.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * データベースからアイテムデータ一覧を取得する
     * @return
     */
    public List<Item> getItemList() {
        //SELECTを使用してテーブルの情報をすべて取得する
        List<Item> list = jdbcTemplate.query("SELECT * FROM items ORDER BY item_id", new BeanPropertyRowMapper<Item>(Item.class));
        LOGGER.info("list = " + list);
        return list;
    }
}
