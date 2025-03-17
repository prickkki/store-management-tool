Hello guys, hope you are having a greate day.

Here is a bit of my thinking proces for implementing this, if you want to do it well you need to spent some time on it. I tryed to do a minimal think.
So we have a store management tool.

What do we store? 
-> Products
What kind of products ?
-> Here we need to abstract, we dont know what kind of products. We are going to have multiple types of products for sure.
Do we have multiple store?
-> I think yes. Lets say in multiple citys, and we need a Big hub.
 Where do we get the products from?
-> Lets say we have some predefined vendors.
 Do we store data about history of the product? 
-> Yes, price and vendors.
Do we automate the process of buying products when we are low on sorage.
-> YES.
A store has employees !!!!
-> Keep the stock of product updated.

Products_table.	
{ id: 1;
  Name: Telefon Samsung XXA
  Quantity: 100;
  Specification:
  Description:
  CategoryId: 1
  VendorId: 1;        //can you buy for multiple vendors???????
  ProductTypeId =1;
  StoreUnitId =1;
  PriceVendor= 100;
  SellingPrice=120;
  Discount=100;
}




CategoryNomenclator{
    Nume,Discount
}

Users->
{
  Userid:
  Name:
  email:
  phone;
  addres:
  age:
  salary:
  function:
  password:;
  storeId
}

Role
 {id:
  name:
}
User_Roles
{ userId
  roleid
}


VendorNomenclator{
    Nume,Descriere,Tara
}

StoreUnitNomenclator{
 Id:
 Nume: 
 Manager:
 Locatie:
 Capacity:
}


PriceHistory:{
  productid:
  date:
  price:
}
