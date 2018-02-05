# BikeShop - developed by Pawel Dudek

©2018
Version 0.3.4


## How to use:

1. Install MySQLWorkbench. Create new empty schema "bikeshop".
2. Install Spring Tool Suite or IntelliJ IDEA Ultimate.
3. Run the application as Spring Boot App.


## About version:

Some buttons might be inactive due to JavaScript parsing problems in methods (filtering by price and name, record limiter). Problem will be solved soon.


## Admin panel:

Look path "localhost:8080/admin". You have to change your default role from "user" to "admin" in MySql after registration.


## Invoices:

Invoices default folder is "D:/". You might change it in pl.scartout.controller.OrderController.java class (constant INVOICE_FOLDER).


## Business goal:

BikeShop has been being implemented an example of this kind of portals.


## Technologies used:

- Spring
- Spring Boot
- Spring Security
- Hibernate
- HTML
- CSS
- Thymeleaf
- JPA
- Bootstrap
- Javascript
- ITextPDF
- Maven
- JUnit
- EqualsVerifier