package jp.co.systena.tigerscave.shoppingcartdb.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.shoppingcartdb.common.Constants.Url;
import jp.co.systena.tigerscave.shoppingcartdb.model.display.Item;
import jp.co.systena.tigerscave.shoppingcartdb.model.form.OrderListForm;
import jp.co.systena.tigerscave.shoppingcartdb.service.ListService;

@Controller
public class CartInfoController {
    /** ロギング */
    private static final Logger LOGGER = LoggerFactory.getLogger(CartInfoController.class);

    /** 表示画面 */
    private static final String CART_VIEW = "CartInfo";

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
    @GetMapping(Url.CART)
    public ModelAndView showCartInfo() {
        LOGGER.info("CartInfoController start");
        ModelAndView mav = new ModelAndView(CART_VIEW);
        OrderListForm order = (OrderListForm)session.getAttribute("order");
        List<Item> itemList = listService.getItemList();
        mav.addObject("item", itemList);
        mav.addObject("order", order);
        int totalPrice = 0;
        if (itemList != null && order != null) {
          for (int i =0; i < itemList.size(); i++){
            totalPrice = totalPrice + (
                Integer.parseInt(itemList.get(i).getPrice()) *
                Integer.parseInt(order.getOrderList().get(i).getItemCount()));
          }
        }
        mav.addObject("totalPrice", totalPrice);
        return mav;
    }
}
