# ClusteredData Warehouse
## Introduction
Greetings to the ClusteredData Warehouse initiative! This README offers a synopsis of the project and its capabilities.
## Objective
The main goal of this project is to receive FX deal information, verify the correctness of the input, and securely store the validated data in a database. It's crucial for the system to maintain data integrity by accurately capturing information and preventing the duplication of requests.
## Technologies Utilized
1. Java: Version 17.
2. Spring Boot
3. Spring Data JPA
4. MySQL 
5. JUnit5
6. Lombok
## Logic
The system executes requests based on the following principles:

#### Fields:
* Deal Unique Id -> long
* From Currency -> Currency code
* To Currency -> Currency code
* Deal Timestamp -> (yyyy-MM-dd HH:mm:ss)
* Deal Amount -> double
#### Validation:
- Validates the structure of each row.
- checking for missing fields.
- checking correct data types.
#### Duplication Check:
- The system won't bring in the same request more than once. If it already has a request with the same unique ID, it won't import it again.
#### Persistence:
* Once validated, the system should save each imported row into the database. Rollback does not happen.
## Getting Started
To get started with the ClusteredData Warehouse project, follow these steps:
1. Clone the Repository:
   ```https://github.com/hamzahassancode/ProgressSoftAssignment```
2. Install Dependencies: Install docker from the Docker website: https://www.docker.com/
4. open cmd on the path of the project then cd to the ReadyEnv: ```cd runWebApp```.
5. type the command: ```docker compose up -d```.

__You can build the jar using the command `mvn install -Dmaven.test.skip=true`__
#### UserInterface
The user will see this interface when first entering the website:



#### UserInteraction 
There are two methods for adding a deal to the database: you can either manually insert a single deal or upload multiple deals at once by selecting a CSV file that contains the deals you want to add.

![HOMEpage](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/f1b14e3b-129a-45db-8929-8b87c1fd6999)

- insert single data :
![insertSingleData](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/e2bffa26-27ed-49d0-b696-9ea72cb66b2a)
- select csv file :
  ![image](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/0d0a3540-99da-4c29-9c81-d24251dff2d9)

After adding the deal and pressing submit, you will receive a response. A brief illustration of the backend process will then be displayed. Refer to the figure below for more details.

#### FROM CSV file

- success:
![SUCCESS](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/d53848d3-88a1-46b5-bd79-ca222fddd7d6)

- Deals may have problems, like duplicate Id or wrong format:
- failed:
![FALED](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/8f53404d-4d78-4ebb-94b0-fd21f850bc87)
partial success:
![pariall success](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/d4da6b68-7811-43ee-810c-302da09150f6)

#### ADD single DEAL file
![dealsusses](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/a97104b0-0c26-410f-a3dd-716fb62b7fe1)

## Cache
implements a cache using a LinkedHashMap. It stores DealModel objects with their IDs as keys and evicts the least recently used entries when the cache exceeds a certain size.
this cache is important for:

- Performance: It improves performance by storing frequently accessed data, reducing the need for fetching from slower sources like databases.

- Resource Efficiency: By storing data in memory, it reduces the load from databases, enhancing scalability and responsiveness.

- Consistency and Availability: It helps maintain data consistency and availability by storing frequently accessed data closer to the application.

- Cost Reduction: By reducing requests to external services, it can lead to cost savings, especially for services billed based on usage.

## UnitTesting
I have crafted comprehensive unit tests, employing JUnit alongside mocks to cover a wide array of potential scenarios.
