package jp.co.systena.tigerscave.shoppingcartdb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import jp.co.systena.tigerscave.shoppingcartdb.common.Constants.Url;

@Controller
public class TitleController {
    /** ロギング */
    private static final Logger LOGGER = LoggerFactory.getLogger(TitleController.class);

    /** 表示画面 */
    private static final String Title_VIEW = "Title";

    /**
     * 画面表示メソッド
     * @return モデル/ビュー情報
     */
    @GetMapping(Url.Title)
    public ModelAndView showCartInfo() {
        LOGGER.info("Title start");
        ModelAndView mav = new ModelAndView(Title_VIEW);
        return mav;
    }
}
