to run the demo:

```mvn clean springboot:run```

Data is stored in a H2 memory database, initialized by data.sql script.
The test src/test/java/pl/square/fdasearch/OpenFdaIT.java calls direct OpenFDA, is to be run manually. 

access is possible via Swagger UI:
http://localhost:8080/swagger-ui/

Persisting entry require an application number, retrives data from OpenFDA and then stores in database. 

-----
known limitations:
- some tests are missing or not implemented
- no paging is implemented

possible improvements:
- introduce cache on SearchService methods
- use OpenFeign instead of RestTemplate
