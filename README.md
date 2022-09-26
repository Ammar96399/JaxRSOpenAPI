## TAA Lab 2 - From servlets to Webservice

### Presentation

This is the repo for the second lab of the course.

The goal of this lab is to work with the notion of servlet, in a small case study, then combine the servlet with JPA to produce a webservice application, and finally integrate with these the REST API and swagger.

### Structure

This repo could be mainly divided into business layer and servlet layer and other utilities.

The business layer is located in `jaxrs` package and contains the following parts:

-   Persistent objects, aka models, are simply data classes with adequate JPA annotations, especially the `OneToMany` and `ManyToOne` relations.

    In our case, we have created models to reflect a medical appointment system.

-    DAOs, each model is linked to a DAO where various CRUD operations are defined. These operations calls the `EntityManager` in JPA to handle a certain logic that is expressed by a SQL query.

     The `EntityManager` has been encapsulated into a singleton in a thread-safe way and we have also made usage of generics to create a DAO that enables several operations by default.

-   Service layer, which dispatches method calls to operations in DAOs.

-   REST resources, each resource is an entrypoint that is linked to a certain model and contains several methods that have likely a path and consume/produce some data.

    In the body of these methods, some verifications are done and different responses are produced regarding different scenarii, e.g. if the resource is fetched, or there is no such resource ...

    We have made our own exception class to handle some specific error cases, don't know if this is the good way. In these functions, the updates to the models are dispatched to DAOs, it would be better to make usage of Service layers instead of calling directly DAOs.

    There is also a resource that is created for swagger to make it possible to expose the API structures.

### Usage

First run the h2 sql DB with the `run-hsqldb-server.bat` or `run-hsqldb-server.sh` depending on the OS.

Then launch the `RestServer` class in `jaxrs` package, either with IDEA or in terminal console.

The list of available APIs could be found in `http://localhost:8080/api`.

### Remark

In the lab 1 we have seen how writing a servlet without any framework could be really cumbersome and diffcult for debugging and testing. The REST API and JAXRS have largely helped, notably by allowing to define routing roles in a simpler and intuitive way. However, writing DAOs and Resource layer could still be error prone, especially when handling null or empty values or facing type mismatches. We will see in the next lab how more curated framework such as spring boot could help resolve this.