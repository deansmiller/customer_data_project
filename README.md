
Just an educational project which complete the tasks (based on a java test) below, in Clojure

getCustomersByEyeColour - this should return the customers that have the specified eye colour.
getCustomersOrderedByEmail - this should sort the customers by email.
lookupAddress - given a long / lat coordinate, this should use the Google Geocoding API to determine the Address for each Customer. To use the Geocoding API you will need to obtain an API key from google. See below for details: https://developers.google.com/maps/documentation/geocoding/#ReverseGeocoding
findClosestCustomer - This should determine which of the customers are closest to the given customer. Note - we are not looking for the absolute distance. DO NOT use a third party API.

Main Method Tasks

These can be completed in any way you wish using any number of additional methods / classes etc, but you must use the methods you have implemented in the service classes to perform the core logic

Load the data into an in memory collection using the ModelLoader.
Determine and output which eye colour is the most popular
Output all email addresses sorted alphabetically in ascending order
Using the provided ExecutorService or other form of concurrency, populate the Address field of each customer. As this is a long running task, we expect some form of parallelism.
Using the above ExecutorService or other form of concurrency, determine which 2 Customers live closest to each other.
Using the ModuleLoader, write the Customer list back to JSON, including the new Address information
