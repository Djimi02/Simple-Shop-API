# Simple-Shop-API

## Simple REST API that supports all CRUD operations over the following ERD:

![](https://github.com/Djimi02/Simple-Shop-API/blob/main/images/ERD.png)

Database: PostgreSQL

## Services

- SubscriberService - Provide subscriber related operations
    - Save subscriber
    - Get subscriber by ID 
    - Add product to subscriber
    - Get all products subcribed by a subscriber
    - Update subscriber data
    - Delete subscriber by ID

- ProductService - Provide product related operations
    - Save product
    - Get product by ID
    - Get all subscribers to a product
    - Update product data
    - Delete product by ID

- AuditService - Provide general audit operations
    - Get total subscribers count
    - Retrieve filtered products count
    - Retrieve most popular products data

## Controllers

- SubscriberService - Provide subscriber related operations
    - Save subscriber: POST ("api/v1/subscribers")
    - Get subscriber by ID: GET ("api/v1/subscribers/{subscriberID}")
    - Add product to subscriber: POST ("api/v1/subscribers/{subscriberID}/products/{productID}")
    - Get all products subcribed by a subscriber: GET ("api/v1/subscribers/{subscriberID}/products")
    - Update subscriber data: PUT ("api/v1/subscribers/{subscriberID}")
    - Delete subscriber by ID: DELETE ("api/v1/subscribers/{subscriberID}")

- ProductService - Provide product related operations
    - Save product: POST ("api/v1/products")
    - Get product by ID: GET ("api/v1/products/{productID}")
    - Get all subscribers to a product: GET ("api/v1/products/{productID}/subscribers")
    - Update product data: PUT ("api/v1/products/{productID}")
    - Delete product by ID: DELETE ("api/v1/products/{productID}")

- AuditService - Provide general audit operations
    - Get total subscribers count: GET ("api/v1/audit/subscibers")
    - Retrieve filtered products count: GET ("api/v1/audit/soldproducts?date=??,isAvailable=??")
    - Retrieve most popular products data: GET ("api/v1/audit/topproducts?numOfProducts=??")

## Exception handling
I used a class annotated with @ControllerAdvise to handle exception thrown at controllers' methods. Upon exception, it returns ResponseEntity with code 400 and body containing appropriate error message.     