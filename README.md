![find-out-my-loan](https://user-images.githubusercontent.com/48466124/221420470-94b82141-d852-4405-a299-13d42e6faea7.png)

# FIND OUT MY LOAN

This project is loan application system.
Loan application process: The loan application process allows customers to apply for loans by filling out an online form
that includes personal and financial information. Once the application is submitted, the system evaluates the
application and assigns a loan limit based on the customer's creditworthiness.

Loan limit: The loan limit is the amount of money that a customer can borrow from the system. The loan limit is
determined based on the customer's credit score, income, and other financial information.

Registration and login: To use the system, customers need to register and create an account. The registration process
includes providing personal information such as name, address, and contact details. Once registered, customers can log
in to their accounts using their username and password.

Loan request history: After logging in, customers can view their loan request history, including past and current loan
applications. This feature helps customers keep track of their loan accounts and payment schedules.

Account management: Customers can update their account information, such as contact details, income, and name surname.
They can also delete their account if they no longer want to use the system.

Loan approval process: Once a customer applies for a loan, the system reviews the application and approves or rejects it
based on the customer's creditworthiness. The system also checks for any outstanding loans or pending loan requests by
the customer.

Loan disbursement: If the loan application is approved, the system disburses the loan amount to the customer's bank
account. The customer can then use the funds for the intended purpose, such as purchasing a car or paying for education.

Customer support: The loan application system provides customer support phone. Customers can reach out to the support
team if they have any questions or concerns regarding their loan accounts.

Surety option: If a customer selects the surety option, they will need to provide information about the surety The
system will create a surety object for the customer, which will be used to evaluate the loan application.

Collateral option: If a customer selects the collateral option, they will need to provide information about the
collateral, including its value, location, and ownership details. The system will create a collateral object for the
customer, which will be used to evaluate the loan application.

Both option: If a customer selects both options, they will need to provide information about both the surety and the
collateral. The system will create a surety object and a collateral object for the customer, which will be used to
evaluate the loan application.

By offering the surety, collateral, and both options, the loan application system provides more flexibility and
customized loan solutions to customers based on their individual financial situation and creditworthiness.

## Related Repositories

* [Credit Score Service](https://github.com/MeftunH/credit-score-service)
* [Find Out My Loan Frontend](https://github.com/MeftunH/find-out-my-loan-frontend)

## Credit Score Service

* I used PCG algorithm for randomize credit score.
* PCG algorithm is a good choice for applications that require high-quality random numbers with low computational
  overhead. It is widely used in gaming, simulations, cryptography, and other areas where random numbers are needed.One
  of the key features of the PCG algorithm is that it uses a permutation of the output values to improve the statistical
  properties of the generated numbers. This means that the output values are not generated in a sequential or linear
  order, but rather in a randomly permuted order. This helps to reduce correlations between the generated numbers and
  improve their randomness.One of the key features of the PCG algorithm is that it uses a permutation of the output
  values to improve the statistical properties of the generated numbers. This means that the output values are not
  generated in a sequential or linear order, but rather in a randomly permuted order. This helps to reduce correlations
  between the generated numbers and improve their randomness.
* PCG vs other algorithms
  ![image](https://user-images.githubusercontent.com/48466124/221462284-524ffeb2-2a8d-425c-827d-77847fd45826.png)

## Find Out My Loan Frontend

* Login Page
  ![image](https://user-images.githubusercontent.com/48466124/221462486-68dc0b1f-e9a5-49cc-9448-743ba49ce4cb.png)
* Register Page
  ![image](https://user-images.githubusercontent.com/48466124/221462778-bd34be56-3ff9-4da3-9ee8-c7277490c158.png)
* Loan Apply Page
  ![image](https://user-images.githubusercontent.com/48466124/221463396-64d6accd-6339-40f6-ad06-a716f39f9cd8.png)
  ![image](https://user-images.githubusercontent.com/48466124/221463416-e50bdfaa-8a2f-4d89-96e0-c90b11d67c36.png)
  ![image](https://user-images.githubusercontent.com/48466124/221463444-9f25521e-d87c-4f9d-af46-d8927f9576c8.png)
  ![image](https://user-images.githubusercontent.com/48466124/221463484-74469290-2676-4e5b-ba25-f7eb85bf5aaa.png)
* Update Profile Page
  ![image](https://user-images.githubusercontent.com/48466124/221463588-dc8b2241-1375-4b20-b80a-738d88bf3999.png)
* Delete Account Page
  ![image](https://user-images.githubusercontent.com/48466124/221463646-f4bf3ff4-1960-4f89-a9ee-81c199507690.png)
* Find Loans
  ![image](https://user-images.githubusercontent.com/48466124/221463711-b63e3edf-f470-49c7-ba43-863bdd8fa3c2.png)
  ![image](https://user-images.githubusercontent.com/48466124/221463742-5c967d11-d49c-40ac-879f-635ccdd55b39.png)
  ![image](https://user-images.githubusercontent.com/48466124/221463966-5a93cba1-0af8-4659-9d25-c2a25a847a94.png)

## Tech Stack

**Frontend:**

* JS (Programming Language),
* React(Frontend Framework),
* React-Router (Routing),
* Redux(predictable state container),
* TailwindCSS (CSS Framework),
* Axios (HTTP Client),
* Formik (Form Management),

**Backend:**

* Java 17 (Programming Language),
* Spring Boot (Backend Framework),
* Spring Security (Authentication and Authorization),
* Spring Data JPA (ORM),
* Spring Validation (Validation),
* Maven (Build Tool),
* H2 (In-Memory Database),
* Lombok (Code Generator),
* MapStruct (Object Mapper),
* OPENAPI (API Documentation),
* Hibernate (ORM),
* JUnit (Testing Framework),
* Mockito (Mocking Framework),
* AssertJ (Assertion Library),
* Hateoas (Hypermedia As The Engine Of Application State)

**Database:**

* PostgreSQL (Relational Database Management System)

## TECHNICAL DECISIONS

### 1. Why did you choose react?

* Because react is a JavaScript library for building user interfaces. It is maintained by Facebook and a community of
  individual developers and companies. React can be used as a base in the development of single-page or mobile
  applications. However, React is only concerned with rendering data to the DOM, and so creating React applications
  usually requires the use of additional libraries for state management and routing. Redux and React Router are
  respective examples of such libraries.

### 2. Why did you choose monolithic architecture?

* I think monolithic architecture can be a good choice for certain types of projects. For example, if the project has a
  relatively small codebase and a straightforward functionality, a monolithic architecture can simplify development and
  testing. Additionally, if the project does not require a lot of scalability and flexibility, a monolithic architecture
  can be easier to deploy and manage.
  Simplicity: Monolithic architecture is simpler to design, develop, and test than microservices. Since all the
  components of the system are contained within a single codebase, it is easier to understand how they interact and to
  maintain overall system consistency.

  Performance: Monolithic architecture can provide faster performance than microservices, since all the components are
  running on a single server or set of servers. This reduces the overhead of network communication and can result in
  faster response times.

  Easier deployment: Monolithic architecture is easier to deploy than microservices, since all the components are
  packaged together and can be deployed as a single unit. This can simplify deployment and make it more predictable.

  Lower operational overhead: Monolithic architecture requires less operational overhead than microservices, since there
  are fewer moving parts and less complexity in the system. This can reduce the cost and effort required to maintain and
  support the system.

### 3. Why did you choose relational database?

* Relational databases are the most common type of database. They are based on the relational model, an intuitive,
  straightforward way of representing data in tables. Each row in the table is a record with a unique ID called the key.
  The columns of the table hold attributes of the data, and each record usually has a value for each attribute, making
  it easy to establish the relationships among data items.Relational databases have been widely used for many years, and
  they offer several advantages over other types of data storage systems. Some of the key advantages of relational
  databases include:

  Data consistency and integrity: Relational databases use constraints and rules to enforce consistency and integrity of
  data. This ensures that the data is accurate, complete, and up-to-date.

  Structured organization: Relational databases use a tabular structure, with each table containing rows of data and
  columns that represent specific attributes or properties. This structured organization makes it easier to manage and
  query large amounts of data.

  Querying capabilities: Relational databases provide powerful query languages, such as SQL, that allow users to
  retrieve, filter, and sort data in a flexible and efficient way. This enables users to quickly and easily retrieve the
  information they need.

  Scalability: Relational databases can be scaled vertically (by adding more resources to a single server) or
  horizontally (by adding more servers to a distributed system). This makes it possible to handle large amounts of data
  and accommodate increasing numbers of users.

  Security: Relational databases provide a range of security features, including user authentication, access control,
  and encryption, to ensure that data is protected from unauthorized access or manipulation.

Overall, relational databases offer a reliable, flexible, and efficient way to manage and store large amounts of data.
They are well-suited for applications that require high levels of data consistency, queryability, and security.

### 4. Why did you choose Postgres?

* PostgreSQL is a popular open-source relational database management system that offers several advantages over other
  database systems.
  Extensibility: PostgreSQL has a modular architecture that allows developers to extend its functionality with custom
  data types, operators, and functions. This makes it a highly flexible and customizable database system.

  Reliability: PostgreSQL is known for its high level of reliability and data integrity. It uses a multi-version
  concurrency control (MVCC) system that ensures that transactions are isolated and do not interfere with each other,
  reducing the risk of data corruption.

  Scalability: PostgreSQL is designed to scale horizontally and vertically. It supports partitioning and clustering to
  distribute data across multiple nodes, and it can handle large volumes of data and high levels of concurrency.

  ACID compliance: PostgreSQL is fully ACID compliant, which means that it ensures atomicity, consistency, isolation,
  and durability of transactions. This ensures that data is always in a consistent and valid state.

  Rich feature set: PostgreSQL offers a rich set of features, including support for JSON, XML, and spatial data, as well
  as full-text search, advanced indexing, and transaction management.

  Community support: PostgreSQL has a large and active community of developers and users who contribute to its
  development, documentation, and support. This community ensures that PostgreSQL is continuously improved and kept
  up-to-date with the latest technologies.
  Overall, PostgreSQL is a powerful and reliable database system that is well-suited for applications that require high
  levels of data consistency, reliability, and scalability. It offers a rich set of features and is highly extensible,
  making it a popular choice for developers and organizations of all sizes.

## Business Rules

* The credit limit multiplier is 4 by default.

1. [✅] New users can be defined in the system, existing customers can be updated or deleted.
2. [✅] If the credit score is below 500, the user is rejected. (Loan result: REJECTED)
3. [✅] If the credit score is between 500 points and 1000 points and the monthly income is below 5000 $, the user's loan
   application is approved and a limit of 10.000$ is assigned to the user. (Loan Result: APPROVED).
4. [✅] If he has given a guarantee, 10 percent of the amount of the guarantee is added to the credit limit.
5. [✅] If the credit score is between 500 points and 1000 points and the monthly income is between 5000$ and 10,000 $,
   the user's loan application is approved and a 20,000 $ limit is assigned to the user. (Loan Result: APPROVED)
6. [✅] If a guarantee has been given, 20 percent of the guarantee amount is added to the credit limit.
7. [✅] If the credit score is between 500 points and 1000 points and the monthly income is above 10.000 $, the loan
   application of the user is approved and the user is assigned a limit of MONTHLY INCOME INFORMATION * CREDIT LIMIT
   MULTIPLIER/2. (Credit Result: APPROVED)
8. [✅] If a guarantee is given, 25 percent of the guarantee amount is added to the credit limit.
9. [✅] If the credit score is equal to or above 1000 points, the user is assigned a limit equal to MONTHLY INCOME *
   CREDIT LIMIT MULTIPLIER. (Credit Result: APPROVED)
10. [✅] If the collateral is given, 50 percent of the collateral amount is added to the credit limit.
11. [✅] As a result of the conclusion of the loan, the relevant application is recorded in the database.
12. [✅] Afterwards, an informative SMS is sent to the relevant phone number and the approval status information (
    rejection or approval), limit information is returned from the endpoint.
13. [✅] A completed loan application can only be queried with the ID number and date of birth. If the date of birth and
    identity information do not match, it should not be questioned.

## 1. ER Diagram

![foml-erm-diagram](https://user-images.githubusercontent.com/48466124/221461286-be9893d7-1284-4a6b-b1e3-545d50a3ab06.png)

## 2. USE CASE DIAGRAM

![foml-use-case](https://user-images.githubusercontent.com/48466124/221461386-9391646e-ebc7-40e3-87cc-e6a8fe8eab91.png)

## 3. Design Of System

![foml-software-architecture](https://user-images.githubusercontent.com/48466124/221466231-f17d4297-3c2d-4f9f-8d64-8d5998242a31.png)

## Register Operation Flow

![register-flow](https://user-images.githubusercontent.com/48466124/221466341-417151e1-9140-42eb-b773-553eed651812.png)

## Login Operation Flow

![login-flow](https://user-images.githubusercontent.com/48466124/221466381-746019c9-cf98-4128-9b9b-843984cbdcb9.png)

## Ask For Loan Operation Flow

![ask-loan-flow](https://user-images.githubusercontent.com/48466124/221466490-4a652ec9-832f-4a97-a67a-460c268f9045.png)

## Loan Validation Operation Flow

### [Excalidraw Link](https://excalidraw.com/#json=UVg_OBrjqtN9cp11iUqTT,NZXdtDjvBwI3rsai5YFhPw)

![loan-application-validation-flow](https://user-images.githubusercontent.com/48466124/221466618-d9fd7c2c-28eb-412b-bb0b-4364e960aadf.png)

## Loan Application Save And Notify Customer Operation Flow

### [Excalidraw Link](https://excalidraw.com/#json=viHSK3bC-bhbK1CRqjMbf,Lrr7FJDXYkKPeXx-lDFiLA)

![loan-application-save-and-notify-users-flow](https://user-images.githubusercontent.com/48466124/221466924-d7a01e44-ad6b-4543-aa4d-34a48f9f35c1.png)

## Tests

I added integration tests for controllers (to test the flow and API path) and unit tests for service layers by mocking.
All services and controllers are fully covered by tests.Happy case, empty case, validations, and null parameters tests
are written.
AVERAGE TEST COVERAGE IS 98%
![image](https://user-images.githubusercontent.com/48466124/221472328-e2ba6849-3d72-43e7-b758-bc0f46028a09.png)
![image](https://user-images.githubusercontent.com/48466124/221472360-485f972b-e625-4b5b-9fa3-83fe6a289a9d.png)
![image](https://user-images.githubusercontent.com/48466124/221472388-3d3098ff-1a0e-4768-9f1e-88575547b983.png)

#### Parameterized Unit Test Example

![image](https://user-images.githubusercontent.com/48466124/220831401-e48814ea-7482-4fd2-9731-024fdf385928.png)

#### Repeated Unit Test Example

![image](https://user-images.githubusercontent.com/48466124/220831494-7ee41050-92be-40cf-bc21-f55dd46eb5d7.png)

## GOF Design Patterns Which I Used In This Project

### 1. Singleton Pattern

The Singleton Pattern is a design pattern in software engineering that restricts the instantiation of a class to one
object. This means that only one instance of the class can exist in the entire system. The purpose of the Singleton
Pattern is to ensure that there is a single point of access to a specific object or resource, and to prevent multiple
instances from being created, which can cause issues such as conflicting updates and inconsistent behavior.

To implement the Singleton Pattern, a class must have a private constructor that prevents the creation of new instances
of the class from outside the class itself. The class also needs a static method that provides access to the single
instance of the class. This method is responsible for creating the instance if it does not already exist and returning
the existing instance if it does.

Singleton Pattern can be useful in scenarios such as database connections, logging services, and configuration settings,
where there should only be one instance of the class throughout the application.

* I use it in the Logger class to create a single instance of the class.
  ![image](https://user-images.githubusercontent.com/48466124/221475924-38795381-373d-4f1d-8282-d2a7bab84e64.png)
  ![image](https://user-images.githubusercontent.com/48466124/221476021-234f6752-e608-4be4-a8bf-7aab548286ea.png)

### 2. Factory Pattern

The Factory Pattern is a design pattern in software engineering that provides a way to create objects without specifying
the exact class of object that will be created. The purpose of the Factory Pattern is to separate the creation of
objects from their use and to provide a single point of control for creating objects.

In the Factory Pattern, a factory class is used to create objects of a specific type. The factory class contains a
method or methods that take parameters specifying the type of object to be created and any additional information needed
to create the object. The factory class then creates and returns the appropriate object based on the parameters passed
in.

One common implementation of the Factory Pattern is the use of a factory method. This is a method within a class that
creates and returns an object of a specific type. The factory method may be static or non-static and may take parameters
specifying the type of object to be created.

The Factory Pattern is often used in scenarios where the creation of objects is complex, or when the creation of objects
needs to be controlled centrally. It is also useful when the exact class of object to be created is not known at the
time of creation. Examples of applications that use the Factory Pattern include database access libraries, graphical
user interface frameworks, and game engines.

* I used it in the notification service to create a notification object according to the type of notification.
  ![image](https://user-images.githubusercontent.com/48466124/221476845-5f8f3abf-77f8-4068-928d-cb4dca512cfc.png)
  ![image](https://user-images.githubusercontent.com/48466124/221476886-8c6c4642-d975-4b2a-aa12-0f6b9d6623a8.png)

### 3.Command Pattern

The Command Pattern is a design pattern in software engineering that encapsulates a request as an object, thereby
decoupling the sender of the request from the receiver. This allows for greater flexibility in the way requests are
handled, and makes it possible to implement features such as undo/redo and queuing of requests.

In the Command Pattern, a command object is used to represent a specific request or action. The command object contains
all the information necessary to execute the request, including a reference to the object that will perform the action.
The sender of the request simply creates a command object and passes it to the receiver, which executes the command.

One of the benefits of the Command Pattern is that it allows for the separation of concerns between the sender and
receiver of a request. The sender does not need to know anything about how the request will be handled or what actions
will be taken, other than the fact that the command object will be executed. This allows for greater flexibility and
modularity in the design of the system.

* I use it for the Guarantee Type Validation on SuretyValidationService and CollateralValidationService

![image](https://user-images.githubusercontent.com/48466124/221477192-3bf897dc-027d-40a4-9c28-2981ac7d1372.png)
![image](https://user-images.githubusercontent.com/48466124/221477240-3b3f8d6a-8cf6-4fff-8821-eb927bb45c03.png)
![image](https://user-images.githubusercontent.com/48466124/221477329-f6ee74cd-48b7-4d2b-8f9c-803383ac8fd2.png)

### 4.Facade Pattern

The Facade Pattern is a design pattern in software engineering that provides a simplified interface to a complex system
of classes, libraries or APIs. The purpose of the Facade Pattern is to provide a unified and simplified interface to a
complex subsystem, making it easier to use and reducing the overall complexity of the system.

In the Facade Pattern, a facade object is created that provides a simplified interface to a complex subsystem. The
facade object wraps one or more classes or libraries, providing a simplified interface to their functionality. The
facade object may also provide additional functionality that is not available in the subsystem itself.

One of the benefits of the Facade Pattern is that it reduces the coupling between the client code and the subsystem.
Since the client code only interacts with the facade object, it does not need to know about the internal details of the
subsystem. This makes it easier to maintain and modify the subsystem without affecting the client code.

* I used it in the LoanController to hide the complexity of the loan application process.
  ![image](https://user-images.githubusercontent.com/48466124/221477649-51b2ae87-b049-4542-b568-0c39c6d71d98.png)
  ![image](https://user-images.githubusercontent.com/48466124/221477689-07244ad4-4bc8-4e85-9eb8-6b3fce7c25aa.png)
  ![image](https://user-images.githubusercontent.com/48466124/221477752-e540ddfe-683c-4464-a79b-993715682bef.png)

### 5.Builder Pattern

The Builder Pattern is a design pattern in software engineering that provides a way to create complex objects in a
step-by-step manner. The purpose of the Builder Pattern is to separate the construction of a complex object from its
representation, allowing the same construction process to create different representations.
One of the benefits of the Builder Pattern is that it allows for the creation of complex objects without requiring the
client code to know the details of the construction process. The client code simply calls the appropriate methods on the
builder object, and the object is constructed automatically. This makes it easier to use complex objects and reduces the
likelihood of errors.
In the Builder Pattern, a builder object is used to create a complex object. The builder object contains a series of
methods that add components or features to the object being built. These methods are typically named in a way that makes
the construction process easy to understand, such as "setFoo()" or "addBar()".

* For example, I use it in the Notification class to create a SmsDTO object.
  ![image](https://user-images.githubusercontent.com/48466124/221477879-71bc70bc-e4ac-4c59-96ad-178c36c768b3.png)
  ![image](https://user-images.githubusercontent.com/48466124/221477936-dcadcd39-b299-44b8-97d9-7ea9b21b6e7a.png)

### 6.Chain Of Responsibility Pattern

The Chain of Responsibility Pattern is a design pattern in software engineering that provides a way to handle a request
through a series of processing objects. The purpose of the Chain of Responsibility Pattern is to decouple the sender of
a request from its receivers, allowing multiple objects to handle the request without the sender needing to know which
object will handle it.

In the Chain of Responsibility Pattern, a chain of processing objects is created. Each processing object contains a
reference to the next processing object in the chain, forming a linked list. When a request is received, the first
processing object in the chain attempts to handle it. If the first processing object is unable to handle the request, it
passes it on to the next processing object in the chain, and so on, until the request is handled or the end of the chain
is reached.One of the benefits of the Chain of Responsibility Pattern is that it allows for greater flexibility in the
way requests are handled. Since multiple processing objects can be added to the chain, it is possible to change the way
requests are handled by modifying the chain. This makes it easier to add or remove processing objects as needed, and
allows for more dynamic behavior in the system.

* I use it for the profiling user
  ![image](https://user-images.githubusercontent.com/48466124/221476312-239a2b4b-608e-4c34-874c-7f17cd313b4c.png)
  ![image](https://user-images.githubusercontent.com/48466124/221476357-b0008b7e-65e1-452f-9d77-8e5a0113dfa0.png)
  ![image](https://user-images.githubusercontent.com/48466124/221476529-a547558a-ca87-4528-ae85-e5abb13731a6.png)
  ![image](https://user-images.githubusercontent.com/48466124/221476599-f57fa5c0-82e7-47c1-9967-25f23f85ce9e.png)

### 7.Strategy Pattern

The Strategy Pattern is a design pattern in software engineering that defines a family of interchangeable algorithms,
encapsulates each one, and makes them interchangeable within a context object. The purpose of the Strategy Pattern is to
allow the selection of an algorithm at runtime without modifying the context object that uses it.

In the Strategy Pattern, a context object is created that contains a reference to a strategy object. The strategy object
contains an algorithm that can be used by the context object to perform a certain task. The context object is designed
to be flexible, allowing different strategy objects to be used interchangeably.

One of the benefits of the Strategy Pattern is that it promotes the principle of separation of concerns. Since each
strategy object encapsulates a different algorithm, it is possible to isolate and encapsulate different concerns in
different objects. This makes it easier to maintain and modify the system over time, and reduces the likelihood of
errors.

* I use it for the calculation based by for customer profile
  ![image](https://user-images.githubusercontent.com/48466124/221478227-132084cb-8d80-471b-a249-a413f55ab007.png)
  ![image](https://user-images.githubusercontent.com/48466124/221478247-ea339128-df23-436f-9d4f-9bcff20b490d.png)
  ![image](https://user-images.githubusercontent.com/48466124/221478275-98db1adb-2498-4e46-a80c-738ed0254691.png)
  ![image](https://user-images.githubusercontent.com/48466124/221478310-d15548e9-a8b3-4432-9d64-bc247eb36ba8.png)
  ![image](https://user-images.githubusercontent.com/48466124/221478335-955f3a42-da8a-4af4-8381-f5717b963bc2.png)
  ![image](https://user-images.githubusercontent.com/48466124/221478362-438e1d85-26ec-4b8f-899d-3a8876f2ee2f.png)

### 8.Observer Pattern

The Observer Pattern is a design pattern in software engineering that defines a one-to-many dependency between objects,
such that when one object changes state, all its dependents are notified and updated automatically. The purpose of the
Observer Pattern is to provide a way to notify interested objects when a certain event occurs, without tightly coupling
the objects together.

In the Observer Pattern, there are two main types of objects: the subject and the observers. The subject is the object
that is being observed, and it maintains a list of all the observers that are interested in its state. When the state of
the subject changes, it notifies all its observers by calling a method on each one. The observers then update their
state accordingly.

One of the benefits of the Observer Pattern is that it allows for greater flexibility in the way objects are updated.
Since multiple observers can be added to the subject, it is possible to change the behavior of the system by adding or
removing observers as needed. This makes it easier to modify the system over time, and allows for more dynamic behavior.
I use it for the notification service to send notification to the user
![image](https://user-images.githubusercontent.com/48466124/221478605-21e6d1d7-ca64-491d-a826-0479e518ee9d.png)
![image](https://user-images.githubusercontent.com/48466124/221478674-4f97b582-58ce-4aff-8456-87049f5632e9.png)
![image](https://user-images.githubusercontent.com/48466124/221478710-8f464606-688e-4941-bbeb-b80319ea1a3e.png)

## Transactions

Spring Boot provides built-in support for transactions through the Spring Framework's transaction management
capabilities. Transactions ensure that a group of database operations are executed as a single unit of work, either
succeeding or failing as a whole.

Spring Boot's transaction management features make it easy to manage transactions declaratively using annotations. Here
are some key concepts to keep in mind:

Transactions are managed by the underlying transaction manager, which can be either a JDBC-based manager or a JTA-based
manager.

Transactions can be managed programmatically using the TransactionTemplate class or declaratively using annotations such
as @Transactional.

The @Transactional annotation can be applied to methods or classes, and allows you to specify the transactional behavior
of the method or class.

The @Transactional annotation supports various attributes, such as propagation, isolation, timeout, and read-only, which
allow you to customize the behavior of the transaction.
The @Transactional annotation in Spring Boot supports several types of transactional behavior that can be customized
according to the needs of your application. Here are some of the most commonly used types of transactional behavior:

REQUIRED: This is the default behavior of @Transactional. If a transactional method is called within the scope of an
existing transaction, it will be executed within that transaction. If there is no existing transaction, a new
transaction will be created.

REQUIRES_NEW: This type of behavior always creates a new transaction, even if an existing transaction is already in
progress. When the method is complete, the new transaction is committed independently of any outer transactions.

MANDATORY: This type of behavior requires that an existing transaction already be in progress when the method is called.
If there is no existing transaction, a TransactionRequiredException will be thrown.

NEVER: This type of behavior ensures that no transaction is in progress when the method is called. If an existing
transaction is in progress, a TransactionException will be thrown.

SUPPORTS: This type of behavior allows the method to execute within an existing transaction, but also allows it to be
executed outside of a transaction.

NOT_SUPPORTED: This type of behavior ensures that the method is executed outside of any existing transaction. If a
transaction is already in progress, it is suspended for the duration of the method call.

NESTED: This type of behavior creates a nested transaction within an existing transaction. If an existing transaction is
in progress, the nested transaction will be executed within the scope of the outer transaction. If the nested
transaction fails, only the nested transaction is rolled back, not the outer transaction.

* For example:
  ![image](https://user-images.githubusercontent.com/48466124/221490518-a64542de-90df-4922-a228-ff7e051a6e14.png)
  ![image](https://user-images.githubusercontent.com/48466124/221490603-a739f2de-2101-4611-90c1-148509776991.png)

## ENDPOINTS, REQUEST AND RESPONSE EXAMPLES

You can reach Insomnia request set
from [here](https://drive.google.com/file/d/13_eP8g8HN2ws9dfXfINQlTel1YOZ3HPQ/view?usp=sharing)

* CREDIT SCORE 
```http://localhost:8081/api/v1/credit-score``` GET
 NO REQUEST BODY
```agsl
{
	"creditScore": 486,
	"customerIdentityNo": 1
}
```
* LOGIN:
  `http://localhost:8082/auth/login` POST

``` 
 {
  "identityNo":88056746318,
  "password": "123MmM"
  }
```

```agsl
{
	"data": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0NDA2IiwiaWF0IjoxNjc3NDc5NDE3LCJleHAiOjE2Nzc1NjU4MTd9.dW-k40No-ONnu-6i30RNYGBpER0iXrVaJ0tGJgeXGLmoyNLcxF2-2jue5M_02UDWWenttUTUoh8N1jUuU0w8EA",
	"responseDate": "2023-02-27T06:30:17.024+00:00",
	"message": null,
	"success": true
}
```

```agsl
{
	 "identityNo":88056546318,
	"password": "123MmM"
}
```

```agsl
{
	"data": {
		"errorDate": "2023-02-27T06:39:42.940+00:00",
		"message": "Customer Identity No Invalid!",
		"detail": "Please check the identity no."
	},
	"responseDate": "2023-02-27T06:39:42.940+00:00",
	"message": "Customer Identity No Invalid!",
	"success": false
}
```

* REGISTER:
  `http://localhost:8082/auth/register` POST

```agsl
{
"name": "John",
 "surname": "Doe",
  "identityNo": 88056746318,
  "birthDate": "1999-01-01",   
  "phoneNumber": "5125245556",
  "personType": "CUSTOMER",
  "monthlyIncome": 5000.0,
  "password": "123MmM"
}
```
```agsl
{
	"data": {
		"name": "John",
		"surname": "Doe",
		"identityNo": 88056746318,
		"birthDate": "1999-01-01",
		"phoneNumber": "5125245556",
		"personType": "CUSTOMER",
		"monthlyIncome": 5000.0,
		"customerLimit": 0.0,
		"baseAdditionalFieldsCreatedDate": "2023-02-27T06:30:11.825+00:00",
		"baseAdditionalFieldsUpdatedDate": "2023-02-27T06:30:11.825+00:00"
	},
	"responseDate": "2023-02-27T06:30:11.921+00:00",
	"message": null,
	"success": true
}
```

```agsl
{
	"data": {
		"errorDate": "2023-02-27T06:42:51.326+00:00",
		"message": "Customer Identity No Must Be Unique!",
		"detail": "Please check the identity no of the customer."
	},
	"responseDate": "2023-02-27T06:42:51.326+00:00",
	"message": "Customer Identity No Must Be Unique!",
	"success": false
}
```
```agsl
{
	"data": {
		"errorDate": "2023-02-27T06:43:23.708+00:00",
		"message": "Customer Identity No Invalid!",
		"detail": "Please check the identity no."
	},
	"responseDate": "2023-02-27T06:43:23.708+00:00",
	"message": "Customer Identity No Invalid!",
	"success": false
}
```
```agsl
{
	"data": {
		"errorDate": "2023-02-27T06:44:04.274+00:00",
		"message": "Customer Phone Number Must Be Unique!",
		"detail": "Please check the phone number."
	},
	"responseDate": "2023-02-27T06:44:04.274+00:00",
	"message": "Customer Phone Number Must Be Unique!",
	"success": false
}
```
* UPDATE CUSTOMER
  ```http://localhost:8082/api/v1/customer``` PUT
```agsl
{
	"name": "Updated John 65",
  "surname": "Doe",
  "identityNo": 66079804482,
"birthDate": "1999-01-01",   
  "phoneNumber": "2555555555",
  "personType": "CUSTOMER",
  "monthlyIncome": 5000.0
}
```

```
{
	"data": {
		"errorDate": "2023-02-27T06:47:16.123+00:00",
		"message": "Customer Not Found!",
		"detail": "Please check the id of the customer."
	},
	"responseDate": "2023-02-27T06:47:16.123+00:00",
	"message": "Customer Not Found!",
	"success": false
}
```
* DELETE CUSTOMER 
```http://localhost:8082/api/v1/customer```
NO REQUEST BODY
```agsl
{
	"data": null,
	"responseDate": "2023-02-15T16:13:20.259+00:00",
	"message": null,
	"success": true
}


```
* GET CUSTOMER
```http://localhost:8082/api/v1/customer``` GET
 NO REQUEST BODY
```agsl
{
  "data": {
    "value": {
      "name": "John",
      "surname": "Doe",
      "identityNo": 48764713606,
      "birthDate": "1999-12-22",
      "phoneNumber": "5657555556",
      "personType": "CUSTOMER",
      "monthlyIncome": 5000,
      "customerLimit": 0,
      "baseAdditionalFieldsCreatedDate": "2023-02-27T06:12:28.467+00:00",
      "baseAdditionalFieldsUpdatedDate": "2023-02-27T06:12:28.467+00:00",
      "links": [
        {
          "rel": "deleteCustomerById",
          "href": "http://localhost:8082/api/v1/customer"
        }
      ]
    },
    "serializationView": null,
    "filters": null
  },
  "responseDate": "2023-02-27T06:49:22.388+00:00",
  "message": null,
  "success": true
}
```
* FIND LOAN
```http://localhost:8082/api/v1/customer/{identityNo}/{birthday}/find-loans``` GET
NO REQUEST BODY
```agsl
{
  "data": {
    "errorDate": "2023-02-27T06:50:22.307+00:00",
    "message": "Customer Information Mismatch!",
    "detail": "Please make sure you have entered your information correctly."
  },
  "responseDate": "2023-02-27T06:50:22.307+00:00",
  "message": "Customer Information Mismatch!",
  "success": false
}
```
* APPLY LOAN
  ```http://localhost:8082/api/v1/loan/apply``` POST
 PAYBACK GUARANTEE TYPE: COLLATERAL
```agsl
{
  "paybackGuaranteeType": "COLLATERAL",
	"collateralType": "MONEY",
	"collateralWorth": 1928.0
}
```
```agsl
{
	"data": {
		"paybackGuaranteeType": "COLLATERAL",
		"amount": 20385.6,
		"result": "APPROVED",
		"customerLimit": 131715.6,
		"baseAdditionalFieldsCreatedDate": "2023-02-27T01:12:21.618+00:00",
		"baseAdditionalFieldsUpdatedDate": "2023-02-27T01:12:21.618+00:00"
	},
	"responseDate": "2023-02-27T02:55:45.435+00:00",
	"message": null,
	"success": true
}
```
PAYBACK GUARANTEE TYPE SURETY
```agsl
{
  "suretyName": "Surety Jane",
  "suretySurname": "Doe",
	"suretyType": "JOINT",
  "suretyIdentityNo": 47701739716,
  "suretyBirthDate": "1980-01-01",
  "suretyPhoneNumber": "55125559",
  "suretyPersonType": "SURETY",
  "paybackGuaranteeType": "SURETY"
}
```
```agsl
{
	"data": {
		"errorDate": "2023-02-27T00:22:03.177+00:00",
		"message": "Surety Phone Number Must Be Unique!",
		"detail": "Please check the phone number."
	},
	"responseDate": "2023-02-27T00:22:03.178+00:00",
	"message": "Surety Phone Number Must Be Unique!",
	"success": false
}
```
PAYBACK GUARANTEE TYPE BOTH OF THEM
```agsl
{
  "suretyName": "Jane",
  "suretySurname": "Doe",
	"suretyType": "JOINT",
  "suretyIdentityNo": 97898017852,
  "suretyBirthDate": "1980-01-01",
  "suretyPhoneNumber": "51665547",
  "suretyPersonType": "SURETY",
  "paybackGuaranteeType": "ALL_OF_THEM",
		"collateralType": "MONEY",
	"collateralWorth": 2000.0
}
```
```agsl
{
	"data": {
		"paybackGuaranteeType": "ALL_OF_THEM",
		"amount": 10200.0,
		"result": "APPROVED",
		"customerLimit": 20392.8,
		"baseAdditionalFieldsCreatedDate": "2023-02-24T09:24:03.995+00:00",
		"baseAdditionalFieldsUpdatedDate": "2023-02-24T09:24:03.995+00:00"
	},
	"responseDate": "2023-02-24T09:24:04.012+00:00",
	"message": null,
	"success": true
}
```
## Authors

- [@maftunhashimli](https://www.github.com/MeftunH)

## License

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)
[![GPLv3 License](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/)
[![AGPL License](https://img.shields.io/badge/license-AGPL-blue.svg)](http://www.gnu.org/licenses/agpl-3.0)

## Feedback

If you have any feedback, please reach out to us at ```maftunhashimli@gmail.com```




