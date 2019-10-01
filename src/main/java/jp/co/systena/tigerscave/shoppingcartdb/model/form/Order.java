package jp.co.systena.tigerscave.shoppingcartdb.model.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Order {

  @NotEmpty()
  @Pattern(regexp="^[0-9]*$")
  @Size(min=0)
  private String itemCount = "0";

  public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

}
