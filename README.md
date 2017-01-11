# from-python-to-java-microservices-bebama
from-python-to-java-microservices-bebama created by GitHub Classroom

# BeBaMa Services
* all routes require your webshopId in the following format: **webshopId=xx**
* when applies, time format to use: **yyyyMMddHHmmss**

 ****

## Webshop Analytics
* ```/api``` route returns basic data without search filters
  * example query: ```/api?webshopId=1```
* ```/api/visitor_count``` route returns the total number of visitors
  * parameters to search for a specific interval:
    * startTime
    * endTime
  * example query: ```/api?webshopId=1&startTime=20170101000000&endTime=20170120000000```
* ```/api/visit_time_count``` route returns the average visit time
    * parameters to search for a specific interval:
      * startTime
      * endTime
* ```/api/revenue``` route returns the total amount of revenue
    * parameters to search for a specific interval:
      * startTime
      * endTime
