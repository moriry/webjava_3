package jp.co.systena.tigerscave.shoppingcartdb.model.display;

import java.util.List;
import javax.validation.Valid;

public class ItemListForm {

    @Valid
    private List<Item> itemList;

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

}
