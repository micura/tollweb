# tollweb

## The program is able to:
- The main goal of the program is manage the contacts.
- Download phone contacts from Google account with Google Contacts API.
- Upload these contacts to Database.
- Show these contacts in front-end. (Now Thymeleaf)

### Future developments:
- Change front-end to React JS or other modern framework. 
- Download contacts from Facebook and Gmal.

### Setup instructions:
**I deleted some confidential file from the project. So if you want to try or use this app you have to do these steps**:

  1. Download your credentials.json for Google Contacts API and place in ```src\main\resources``` folder.
  2. Create an ```application.properties``` file and place ```src\main\resources``` folder.
  
### Template for file
```
spring.datasource.url=YOUR_DATABASE_URL
spring.datasource.username=root
spring.datasource.password=YOUR_USER_PASSWORD
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver

//Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto=update
```
