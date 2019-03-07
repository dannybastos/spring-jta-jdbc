# spring-jta-jdbc
It's a very simple example to show how use JTA without JEE, only with spring and atomikos;

In this example, a tipical sell have to perform 2 actions in 2 differents databases (Stock and Invoice).

# Requirements
- docker
- docker-compose

# How to run
To run this project just use : docker-compose up -d

In this case, the stock of the products was initialized with the quantity of 10, and when we try to perform a sell with the quantity bigger then stock, we receive the message: "Out of stock"
```
curl -vd "product_id=1010&qty=100" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -X POST http://localhost:8080/sell
```
