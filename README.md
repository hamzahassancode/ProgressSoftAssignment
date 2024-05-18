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
   ```https://github.com/hamzahassancode/ProgressSoft```
2. Install Dependencies: Install docker from the Docker website: https://www.docker.com/
4. open cmd on the path of the project then cd to the ReadyEnv: ```cd runWebApp```.
5. type the command: ```docker compose up -d```.

__You can build the jar using the command `mvn install -Dmaven.test.skip=true`__
#### UserInterface
The user will see this interface when first entering the website:



#### UserInteraction and functionality
The user is supposed to choose a csv file that includes the deals he wants to upload, when selecting the file, the user will see:



After that, he will press the upload file button, and see a brief illustration of the process on the backend, see figure below.

![HOMEpage](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/f1b14e3b-129a-45db-8929-8b87c1fd6999)

insert single data successfully :
![insertSingleData](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/e2bffa26-27ed-49d0-b696-9ea72cb66b2a)

insert from the csv file :
![respose Of ADD SUccesfully](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/af7f2425-379f-4e50-9ecf-9fab180ed5b3)

![pariall success](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/ac2f1720-a5b6-4c6c-b860-e04e51ea6687)


Deals may have problems, like duplicate Id or wrong format, see figure: 
![FAILED](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/3dfea404-e5a3-4359-9899-c7aefb919436)
![partialSucccess](https://github.com/hamzahassancode/ProgressSoftAssignment/assets/133760155/d0b07f21-4f4c-4ef3-802f-863c308d8842)


## Cache
implements a cache using a LinkedHashMap. It stores DealModel objects with their IDs as keys and evicts the least recently used entries when the cache exceeds a certain size.
this cache is important for:

- Performance: It improves performance by storing frequently accessed data, reducing the need for fetching from slower sources like databases.

- Resource Efficiency: By storing data in memory, it reduces the load from databases, enhancing scalability and responsiveness.

- Consistency and Availability: It helps maintain data consistency and availability by storing frequently accessed data closer to the application.

- Cost Reduction: By reducing requests to external services, it can lead to cost savings, especially for services billed based on usage.

## UnitTesting
I have crafted comprehensive unit tests, employing JUnit alongside mocks to cover a wide array of potential scenarios.
