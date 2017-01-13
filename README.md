# BeBaMa Services

## Steps to use our service
* clone the repo
* set up the db connection in your IDE
* run ```/src/main/resources/public/sql/clear_db.sql``` to create the db structure
* you need to have an existing webshop to store the related data
* run ```Server``` - it creates the necessary server configurations
* open ```localhost:60000``` with the selected route and parameters
* all routes require your webshopId in the following format: **webshopId=xx**
* when applies, time format to use: **yyyyMMddHHmmss**
### Enjoy!

 ****

## Webshop Analytics - beta version
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
 * ***note*** that it needs more integration with the webshop, so \*very beta\* version
 * parameters to search for a specific interval:
      * startTime
      * endTime
* ```/api/location_visits``` returns the top locations from which the webshop was visited in a "count - location" format
  * parameters to search for a specific interval:
    * startTime
    * endTime