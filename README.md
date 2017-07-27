# Addressbook
Service to manage contacts in an address book.<br/>
Design	and	implement	a	RESTFUL	address	book in	Java:<br/>
This	address	book	stores	contact	information	for	a	given	user	and	allows	this	
information	to	be	retrieved	as	well.	The	contact	information	to	store	is	first	name,	
last	name,	phone	number,	address,	email,	and	last	contact	date.	The	interface	
should	also	support	updating	and	deleting	this	contact	information.	This	data	can	
simply	be	stored	in	memory	and	does	not	have	to	persist	through	application	
restarts.	
Please	provide	unit/integration	tests	that	test	the	endpoints.


Bonus:<br/>	
     Design	a	RESTFUL	search/filtering	interface	for	these	use	cases:<br/>
     Show	all	the	contacts	from	a	list	of	states.<br/>
     Show	all	the	contacts	last	contacted	in	a	date	range.<br/>
     Show	all	the	contacts	from	a	given	area	code	assuming	the	phone	number	
     is	in	the	format(xxx-xxx-xxxx)

## Summary
This is a application built using spring boot and maven that provides APIs to manage contacts in an address book. This service uses an In-memory database.

The service supports most of the CRUD operations and allows for managing contacts in an address book.
The basic requirement is complete. For the bonus, only the "Show	all	the	contacts	based on state" is complete.


### Tech Stack:

Spring boot
Spring JPA in memory database
Hibernate based ORM
Junit testing
Swagger API documentation

## Requirements

You will need<br/>

1) Maven to build this project to build the jar.<br/>
2) Postman client to easily download and test the integration test suite

## Deployment

To deploy :

Jar: The jar can be built by using maven. Once built we can run the jar as indicated below


      $> mvn clean install
      $> java -jar Addressbook/target/1-1.0-SNAPSHOT.jar
      
      
The service would be deployed at localhost:8080
{code}

## API docs

The API documentation is powered by swagger and can be located at /target/generated/swagger.json

To generate a html version of the API documentation you can use bootprint



      npm install -g bootprint
      npm install bootprint-swagger 
      bootprint swagger target/generated/swagger.json docs
      
You'll see the index.html file created under the docs folder with all the css

## Integration test suite

For integration testing a postman collection is provided. Please use addressbook.postman_collection file to import the collection into postman and run the collection.

To import a collection into postman refer: https://www.getpostman.com/docs/collections To run the suite from the collection refer: https://www.getpostman.com/docs/running_collections




