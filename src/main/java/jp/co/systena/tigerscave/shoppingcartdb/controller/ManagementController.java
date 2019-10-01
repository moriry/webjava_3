package jp.co.systena.tigerscave.shoppingcartdb.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.shoppingcartdb.common.Constants.Url;
import jp.co.systena.tigerscave.shoppingcartdb.model.display.Item;
import jp.co.systena.tigerscave.shoppingcartdb.service.ListService;

@Controller
public class ManagementController {
    /** ロギング */
    private static final Logger LOGGER = LoggerFactory.getLogger(ManagementController.class);

    /** 表示画面 */
    private static final String MANAGEMENT_VIEW = "Management";

    @Autowired
    private HttpSession session;

    @Autowired
    private ListService listService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 画面表示メソッド
     * @return モデル/ビュー情報
     */
    @GetMapping(Url.MANAGEMENT)
    public ModelAndView management() {
        LOGGER.info("ManagementController show start");
        session.removeAttribute("order");
        ModelAndView mav = new ModelAndView(MANAGEMENT_VIEW);
        List<Item> item = listService.getItemList();
        mav.addObject("item", item);
        return mav;
    }

    /**
     * 商品詳細
     * @return モデル/ビュー情報
     */
    @PostMapping(Url.ITEM_DETAIL)
    public ModelAndView itemDetail() {
        LOGGER.info("Item detail start");
        return new ModelAndView("redirect:" + Url.ITEM_EDIT);
    }

}
