package jp.co.systena.tigerscave.shoppingcartdb.model.display;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Item {

    @Pattern(regexp="^[0-9]*$")
    private String itemId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

  @NotEmpty(message="商品名は必須項目です")
  private String itemName;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @NotEmpty(message="値段は必須項目です")
    @Pattern(regexp="^[0-9]*$", message="値段は0以上の整数を入力してください")
    @Size(min=0, message="値段は0以上の整数を入力してください")
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
