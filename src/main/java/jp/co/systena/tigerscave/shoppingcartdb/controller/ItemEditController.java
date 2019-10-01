package jp.co.systena.tigerscave.shoppingcartdb.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.shoppingcartdb.common.Constants.Url;
import jp.co.systena.tigerscave.shoppingcartdb.model.display.Item;
import jp.co.systena.tigerscave.shoppingcartdb.service.ManagementService;

@Controller
public class ItemEditController {
    /** ロギング */
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemEditController.class);

    /** 表示画面 */
    private static final String ITEM_EDIT_VIEW = "ItemEdit";

    @Autowired
    private ManagementService ManagementService;

    /**
     * 画面表示メソッド
     * @return モデル/ビュー情報
     */
    @GetMapping(Url.ITEM_EDIT)
    public ModelAndView itemEdit() {
        LOGGER.info("ItemEditController show start");
        ModelAndView mav = new ModelAndView(ITEM_EDIT_VIEW);
        mav.addObject("item", new Item());
        return mav;
    }

    /**
     * 商品リスト追加
     * @return モデル/ビュー情報
     */
    @PostMapping(Url.CREATE_LIST)
    public ModelAndView createList(@Valid Item item, BindingResult bindingResult) {
        LOGGER.info("Create list start");
        if (bindingResult.hasErrors()) {
            ModelAndView mav = new ModelAndView(ITEM_EDIT_VIEW);
            mav.addObject("item", item);
            return mav;
        }
        ManagementService.addList(item);
        return new ModelAndView("redirect:" + Url.MANAGEMENT);
    }

    /**
     * 商品リスト削除
     * @return モデル/ビュー情報
     */
    @GetMapping(Url.DELETE_LIST)
    public ModelAndView deleteList(@RequestParam(name = "item_id", required = true) String itemId) {
        LOGGER.info("Delete list start");
        LOGGER.info("itemId = " + itemId);
        ManagementService.deleteList(itemId);
        return new ModelAndView("redirect:" + Url.MANAGEMENT);
    }

}
