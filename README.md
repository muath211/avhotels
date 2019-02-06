# avhotels

Setup:

Configure third party endpoints
- navigate to yml config files "avhotels/src/main/resources/application-{profile}.yml"
  and configure your endpoints
  
*********************************
Dummy Hotel providers
https://github.com/muath211/besthotels.git
https://github.com/muath211/crazy.git
*********************************

Build (Maven must be installed)
- mvn clean install

Run the app
- mvn spring-boot:run


*********************************
Application APIs docs can be accessed through swagger
http://localhost:8080/avhotels/swagger-ui.html


*********************************
Addtional hotel proviers can be added through implmeniting com.avhotels.clients.Client Interface.


*********************************
You may not find every class is docuemented in the project as I believe in Self-Documented code [https://en.wikipedia.org/wiki/Self-documenting_code]
