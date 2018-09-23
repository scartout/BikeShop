# BikeShop - developed by Pawel Dudek

©2018
Version 1.3.1


## How to use:

1. Install MySQLWorkbench. Create new empty schemas "bikeshop" and "bikeshoptest".
2. Install Spring Tool Suite or IntelliJ IDEA Ultimate.
3. Install Python. Add Python location to PATH environmental variable.
4. Load products records (see "Products inserting procedure").
5. Run the application as Spring Boot App.
6. Go to URL https://localhost:8443. Don't worry about ERR_SSL_VERSION_OR_CIPHER_MISMATCH error (add exception).
7. Notice that application might not start with combination of installed JDK9 and old SpringToolSuite version (tested on JDK8 and SpringToolSuite 3.9.1).


## Products inserting procedure:

1. Go to /data/bin
2. Open products.xlsm worksheet and add products information. Run macro.
3. Run products.py


## Invoices downloading:

Invoices default folder is "C:/Users/{Current User}/Desktop". You might change it in pl.scartout.controller.OrderController.java class (constant INVOICE_FOLDER).


## Business goal:

BikeShop has been being implemented an example of this kind of portals.


## Technologies used:

- Spring, Spring Boot, Spring Security, Hibernate, JPA
- JavaScript, JQuery, HTML, CSS, Bootstrap, Thymeleaf
- Python
- ITextPDF
- Maven
- JUnit, AssertJ, Google Guava, EqualsVerifier