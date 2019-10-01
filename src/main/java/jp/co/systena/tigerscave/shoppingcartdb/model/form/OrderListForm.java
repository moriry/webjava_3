package jp.co.systena.tigerscave.shoppingcartdb.model.form;

import java.util.List;
import javax.validation.Valid;

public class OrderListForm {
  @Valid
  private List<Order> orderList;

  public List<Order> getOrderList() {
      return orderList;
  }

  public void setOrderList(List<Order> orderList) {
      this.orderList = orderList;
  }

}
