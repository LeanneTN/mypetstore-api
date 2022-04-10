### 4.根据订单号获取该订单购买的商品

**Request**

- Method: **GET**

- URL: **/order/orders/{orderId}/items**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 10,
      "msg": "请先登录！"
  }
  {
  	"status": 1,
      "msg": "您查询的订单不存在！"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "orderId": 1002,
              "lineNumber": 1003,
              "itemId": "EST-15",
              "quantity": 2,
              "unitPrice": 23.50
          },
          {
              "orderId": 1002,
              "lineNumber": 1004,
              "itemId": "EST-14",
              "quantity": 1,
              "unitPrice": 58.50
          }
      ]
  }
  ```

-------