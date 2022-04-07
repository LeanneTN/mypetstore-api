# mypetstore-api接口文档

## Catalog

### 1.获取所有Category

**Request**

- Method: **GET**

- URL: **/catalog/categories**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "categoryId": "BIRDS",
              "name": "Birds",
              "description": "<image src=\"../images/birds_icon.gif\"><font size=\"5\" color=\"blue\"> Birds</font>"
          },
          {
              "categoryId": "CATS",
              "name": "Cats",
              "description": "<image src=\"../images/cats_icon.gif\"><font size=\"5\" color=\"blue\"> Cats</font>"
          }，
          ...
      ]
  }
  ```

-------

### 2.获取单个Category

**Request**

- Method: **GET**

- URL: **/catalog/categories/{id}**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": {
          "name": "Dogs",
          "description": "<image src=\"../images/dogs_icon.gif\"><font size=\"5\" color=\"blue\"> Dogs</font>",
          "categoryId": "DOGS"
      }
  }
  ```

-------

### 3.获取某个Category分类下的所有Product信息

**Request**

- Method: **GET**

- URL: **/catalog/categories/{id}/products**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "productId": "K9-BD-01",
              "categoryId": "DOGS",
              "name": "Bulldog",
              "description": "Friendly dog from England",
              "image": "../images/dog2.gif"
          },
          //...
      ]
  }
  ```

-------

### 4.获取单个Product小分类的信息

**Request**

- Method: **GET**

- URL: **/catalog/products/{id}**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": {
          "productId": "AV-CB-01",
          "categoryId": "BIRDS",
          "name": "Amazon Parrot",
          "description": "Great companion for 75 years",
          "image": "../images/bird2.gif"
      }
  }
  ```

-------

### 5.获取某个Product分类下的所有Item信息

**Request**

- Method: **GET**

- URL: **/catalog/products/{id}/items**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "itemId": "EST-20",
              "productId": "FI-FW-02",
              "listPrice": 5.50,
              "unitCost": 2.00,
              "supplierId": 1,
              "status": "P",
              "attribute1": "Adult Male",
              "attribute2": null,
              "attribute3": null,
              "attribute4": null,
              "attribute5": null,
              "categoryId": "FISH",
              "name": "Goldfish",
              "description": "Fresh Water fish from China",
              "image": "../images/fish2.gif",
              "quantity": 10000
          },
          //...
      ]
  }
  ```

-------

### 6.获取单个Item商品的信息

**Request**

- Method: **GET**

- URL: **/catalog/items/{id}**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": {
          "itemId": "EST-10",
          "productId": "K9-DL-01",
          "listPrice": 18.50,
          "unitCost": 12.00,
          "supplierId": 2,
          "status": "P",
          "attribute1": "Spotted Adult Female",
          "attribute2": null,
          "attribute3": null,
          "attribute4": null,
          "attribute5": null,
          "categoryId": "DOGS",
          "name": "Dalmation",
          "description": "Great dog for a Fire Station",
          "image": "../images/dog5.gif",
          "quantity": 10000
      }
  }
  ```

---------

### 7.获取搜索的Product

**Request**

- Method: **GET**

- URL: **/catalog/products/search**

- Parameters:

  ```
  {
  	keywords: 'a'
  }
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "productId": "AV-CB-01",
              "categoryId": "BIRDS",
              "name": "Amazon Parrot",
              "description": "Great companion for 75 years",
              "image": "../images/bird2.gif"
          },
          {
              "productId": "FI-SW-01",
              "categoryId": "FISH",
              "name": "Angelfish",
              "description": "Salt Water fish from Australia",
              "image": "../images/fish1.gif"
          },
          //...
      ]
  }
  ```

-------

## Account

### 1.登录

**Request**

- Method: **POST**

- URL: /user/login

- Parameters:

  ```
  {
  	username, 
  	password
  }
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "用户名或密码不正确"
  }
  ```

- success

  ```
  {
  	"status": 0,
  	"data": {Account}
  }
  ```

-----

### 2.注册

**Request**

- Method: **POST**

- URL: /user/register

- Parameters:

  ```
  {
  	username, 
  	password,
  	code
  }
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "用户名已存在"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": {
          "username": "aaa",
          "email": null,
          "firstName": null,
          "lastName": null,
          "status": null,
          "address1": null,
          "address2": null,
          "city": null,
          "state": null,
          "zip": null,
          "country": null,
          "phone": null,
          "favouriteCategoryId": null,
          "languagePreference": null,
          "listOption": false,
          "bannerOption": false,
          "bannerName": null
      }
  }
  ```

-----
### 3.查看已登录账号信息

**Request**

- Method: **POST**

- URL: /user/get_login_account_info

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
  ```

- success

  ```
  {
      "status": 0,
      "data": {
          "username": "aaa",
          "email": null,
          "firstName": null,
          "lastName": null,
          "status": null,
          "address1": null,
          "address2": null,
          "city": null,
          "state": null,
          "zip": null,
          "country": null,
          "phone": null,
          "favouriteCategoryId": null,
          "languagePreference": null,
          "listOption": false,
          "bannerOption": false,
          "bannerName": null
      }
  }
  ```

-----

### 4.更改已登录账号信息

**Request**

- Method: **POST**

- URL: /user/update_account

- Parameters:

  ```
  {
      firstName: "张",
      lastName: "三",
      email: "123456@qq.com",
      phone: null,
      address1: null,
      address2: null,
      city: null,
      state: null,
      zip: null,
      country: null,
      languagePreference: null,
      favouriteCategoryId: null,
      listOption: false,
      bannerOption: false
  }
  ```

**Response**

- fail

  ```
  {
      "status": 10,
      "msg": "请先登录！"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": {
          "username": "aaa",
          "email": "123@qq.com",
          "firstName": "张",
          "lastName": "三",
          "status": null,
          "address1": null,
          "address2": null,
          "city": null,
          "state": null,
          "zip": null,
          "country": null,
          "phone": null,
          "favouriteCategoryId": null,
          "languagePreference": null,
          "listOption": false,
          "bannerOption": false,
          "bannerName": null
      }
  }
  ```

-----
### 5.判断用户名

**Request**

- Method: **POST**

- URL: /user/username

- Parameters:

  ```
  {
  	username: "aaa"
  }
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "用户名不存在"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": {
          "username": "aaa",
          "email": null,
          "firstName": null,
          "lastName": null,
          "status": null,
          "address1": null,
          "address2": null,
          "city": null,
          "state": null,
          "zip": null,
          "country": null,
          "phone": null,
          "favouriteCategoryId": null,
          "languagePreference": null,
          "listOption": false,
          "bannerOption": false,
          "bannerName": null
      }
  }
  ```
-----


### 6.退出已登录的账号

**Request**

- Method: **POST**

- URL: /user/sign_out

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "请先登录！"
  }
  ```

- success

  ```
  {
      "status": 0,
      "msg": "退出成功！"
  }
  ```

-------

## Cart
### 1.获取已登录账号的Cart

**Request**

- Method: **GET**

- URL: **/cart/myCart**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "请先登录！"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": {
          "itemList": [
              {
                  "itemId": "EST-14",
                  "productId": "FL-DSH-01",
                  "descn": "Tailless Manx",
                  "inStock": true,
                  "quantity": 2,
                  "listPrice": 58.50,
                  "totalPrice": 117.00,
                  "buyerName": "aaa"
              },
              {
                  "itemId": "EST-13",
                  "productId": "RP-LI-02",
                  "descn": "Green Adult Iguana",
                  "inStock": true,
                  "quantity": 2,
                  "listPrice": 18.50,
                  "totalPrice": 37.00,
                  "buyerName": "aaa"
              }
          ]
      }
  }
  ```

-------

### 2.向已登录账号的购物车添加商品

**Request**

- Method: **POST**

- URL: **/cart/myCart**

- Parameters:

  ```
  {
  	itemId: "EST-14"
  }
  ```

**Response**

- fail

  ```
  {
      "status": 10,
      "msg": "请先登录！"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": {
          "itemList": [
              {
                  "itemId": "EST-14",
                  "productId": "FL-DSH-01",
                  "descn": "Tailless Manx",
                  "inStock": true,
                  "quantity": 7,
                  "listPrice": 58.50,
                  "totalPrice": 117.00,
                  "buyerName": "aaa"
              },
              {
                  "itemId": "EST-13",
                  "productId": "RP-LI-02",
                  "descn": "Green Adult Iguana",
                  "inStock": true,
                  "quantity": 2,
                  "listPrice": 18.50,
                  "totalPrice": 37.00,
                  "buyerName": "aaa"
              }
          ]
      }
  }
  ```

-------

### 3.移除已登录账号购物车中的商品

**Request**

- Method: **DELETE**

- URL: **/cart/myCart/cartItems**

- Parameters:

  ```
  {
  	itemId: "EST-14"
  }
  ```

**Response**

- fail

  ```
  {
      "status": 10,
      "msg": "请先登录！"
  }
  
  ```

  ```
  {
      "status": 1,
      "msg": "购物车中没有该商品!"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": {
          "itemList": [
              {
                  "itemId": "EST-13",
                  "productId": "RP-LI-02",
                  "descn": "Green Adult Iguana",
                  "inStock": true,
                  "quantity": 2,
                  "listPrice": 18.50,
                  "totalPrice": 37.00,
                  "buyerName": "aaa"
              }
          ]
      }
  }
  ```

-------

### 4.更新已登录账号购物车中商品的数量

**Request**

- Method: **POST**

- URL: **/cart/myCart/cartItems**

- Parameters:

  ```
  {
  	itemId: "EST-13",
  	quantity: 100	//请确保quantity是自然数
  }
  ```

**Response**

- fail

  ```
  {
      "status": 10,
      "msg": "请先登录！"
  }
  ```

  ```
  {
      "status": 1,
      "msg": "购物车中没有该商品!"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": {
          "itemList": [
              {
                  "itemId": "EST-13",
                  "productId": "RP-LI-02",
                  "descn": "Friendly green friend",
                  "inStock": true,
                  "quantity": 100,
                  "listPrice": 18.50,
                  "totalPrice": 18.50,
                  "buyerName": "aaa"
              },
              {
                  "itemId": "EST-14",
                  "productId": "FL-DSH-01",
                  "descn": "Great for reducing mouse populations",
                  "inStock": true,
                  "quantity": 2,
                  "listPrice": 58.50,
                  "totalPrice": 58.50,
                  "buyerName": "aaa"
              }
          ]
      }
  }
  ```


------

## Order

### 1.获取Cart信息

**Request**

- Method: **GET**

- URL: **/cart/{username}**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "itemList": [
              	{
              		CartItem...
              	},
              	{
              		CartItem...
              	}
              ]
          }
      ]
  }
  ```

-------

### 1.获取Cart信息

**Request**

- Method: **GET**

- URL: **/cart/{username}**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "itemList": [
              	{
              		CartItem...
              	},
              	{
              		CartItem...
              	}
              ]
          }
      ]
  }
  ```

-------

### 1.获取Cart信息

**Request**

- Method: **GET**

- URL: **/cart/{username}**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "itemList": [
              	{
              		CartItem...
              	},
              	{
              		CartItem...
              	}
              ]
          }
      ]
  }
  ```

-------

### 1.获取Cart信息

**Request**

- Method: **GET**

- URL: **/cart/{username}**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "itemList": [
              	{
              		CartItem...
              	},
              	{
              		CartItem...
              	}
              ]
          }
      ]
  }
  ```

-------

### 1.获取Cart信息

**Request**

- Method: **GET**

- URL: **/cart/{username}**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "itemList": [
              	{
              		CartItem...
              	},
              	{
              		CartItem...
              	}
              ]
          }
      ]
  }
  ```

-------

### 1.获取Cart信息

**Request**

- Method: **GET**

- URL: **/cart/{username}**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "itemList": [
              	{
              		CartItem...
              	},
              	{
              		CartItem...
              	}
              ]
          }
      ]
  }
  ```

-------

### 1.获取Cart信息

**Request**

- Method: **GET**

- URL: **/cart/{username}**

- Parameters:

  ```
  
  ```

**Response**

- fail

  ```
  {
      "status": 1,
      "msg": "服务器异常"
  }
  ```

- success

  ```
  {
      "status": 0,
      "data": [
          {
              "itemList": [
              	{
              		CartItem...
              	},
              	{
              		CartItem...
              	}
              ]
          }
      ]
  }
  ```

-------