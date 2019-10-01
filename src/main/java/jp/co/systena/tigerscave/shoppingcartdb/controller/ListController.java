package jp.co.systena.tigerscave.shoppingcartdb.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.shoppingcartdb.common.Constants.Url;
import jp.co.systena.tigerscave.shoppingcartdb.model.display.Item;
import jp.co.systena.tigerscave.shoppingcartdb.model.form.Order;
import jp.co.systena.tigerscave.shoppingcartdb.model.form.OrderListForm;
import jp.co.systena.tigerscave.shoppingcartdb.service.ListService;

@Controller
public class ListController {
    /** ロギング */
    private static final Logger LOGGER = LoggerFactory.getLogger(ListController.class);

    /** 表示画面 */
    private static final String LIST_VIEW = "ListView";

    @Autowired
    private HttpSession session;

    @Autowired
    private ListService listService;

    /**
     * 画面表示メソッド
     * @return モデル/ビュー情報
     */
    @GetMapping(Url.SHOPPING)
    public ModelAndView show() {
        LOGGER.info("ListController show start");
        ModelAndView mav = new ModelAndView(LIST_VIEW);
        OrderListForm order = new OrderListForm();
        List<Item> itemList = listService.getItemList();
        if (session.getAttribute("order") != null) {
            LOGGER.info("order isn't null");
            order = (OrderListForm)session.getAttribute("order");
        } else {
            LOGGER.info("order is null");
            List<Order> list = new ArrayList<>();
            for (int i =0; i < itemList.size(); i++){
              Order tmp = new Order();
              list.add(tmp);
            }
            order.setOrderList(list);
        }
        mav.addObject("order", order);
        mav.addObject("item", itemList);
        if (session.getAttribute("errorMessage") != null) {
          mav.addObject("errorMessage", session.getAttribute("errorMessage"));
        }
        session.removeAttribute("errorMessage");
        return mav;
    }

    /**
     * カート情報をセッションに保存
     * @return モデル/ビュー情報
     */
    @PostMapping(Url.SHOPPING)
    public ModelAndView addCart(@Valid OrderListForm order, BindingResult bindingResult) {
        LOGGER.info("addCart start");
        if (bindingResult.hasErrors()) {
            LOGGER.info("Validation Error");
            session.setAttribute("errorMessage", "個数は0以上の整数を入力してください");
            return new ModelAndView("redirect:" + Url.SHOPPING);
      }
        session.setAttribute("order", order);
        return new ModelAndView("redirect:" + Url.SHOPPING);
    }

    /**
     * カート情報画面へ遷移
     * @return モデル/ビュー情報
     */
    @PostMapping(Url.SHOW_CART_INFO)
    public ModelAndView showCartInfo() {
        LOGGER.info("Redirect cart info");
        return new ModelAndView("redirect:" + Url.CART);
    }

    /**
     * カート情報削除
     * @return モデル/ビュー情報
     */
    @PostMapping(Url.DELETE_CART_INFO)
    public ModelAndView deleteCartInfo() {
        LOGGER.info("Delete cart info");
        session.removeAttribute("order");
        return new ModelAndView("redirect:" + Url.SHOPPING);
    }

}
