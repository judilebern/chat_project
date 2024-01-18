## Praktinė užduotis CHAT

### Panaudota:
  - Java 17
  - SpringBoot
  - Gradle
  - Liquibase
  - Swagger
  - H2 database

### Pora pastabų
1. Seniau esu mokiusis naudoti Swagger, bet reikėjo dėti atinkamas anotacijas, config daryti. Tačiau dabar radau, kad su springboot 3, kaip ir nereikia dėti jokių anatocijų, tik papildomo dependancy reikia. Kiek testavau panašu, kad veikia.
2. Nesu dariusi autentifikacijų panaudojant JWT. Todėl nusprendžiau išskirti du skirtingus kontrolerius user ir admin.  
