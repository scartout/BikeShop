# BikeShop - developed by Pawel Dudek

©2018
Version 1.0.8


## How to use:

1. Install MySQLWorkbench. Create new empty schemas "bikeshop" and "bikeshoptest".
2. Install Spring Tool Suite or IntelliJ IDEA Ultimate.
3. Run the application as Spring Boot App.
4. Go to URL https://localhost:8443. Don't worry about ERR_SSL_VERSION_OR_CIPHER_MISMATCH error (add exception).
5. Notice that application might not start with combination of installed JDK9 and old SpringToolSuite version (tested on JDK8 and SpringToolSuite 3.9.1).


## Admin panel:

Go to URL "https://localhost:8443/admin". You have to change your default role from "user" to "admin" in MySqlWorkbench after registration.


## Invoices:

Invoices default folder is "C:/Users/{Current User}/Desktop". You might change it in pl.scartout.controller.OrderController.java class (constant INVOICE_FOLDER).


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
- JavaScript
- JQuery
- ITextPDF
- SHA
- HTTPS
- Maven
- JUnit
- AssertJ
- Google Guava
- EqualsVerifier