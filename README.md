# Job Management Service

This project is a Job Management Service built with Spring Boot. It provides RESTful APIs for managing job postings, including functionalities for creating, reading, updating, and deleting jobs. The service uses PostgreSQL for data storage and integrates with Zipkin for distributed tracing.

## Project Structure

The project includes the following main components:

- **JobController**: REST controller for managing job postings.
- **JobService**: Service layer for business logic.
- **application.properties**: Configuration file for setting up the application properties.


API Endpoints

List all the available API endpoints with their corresponding HTTP methods (GET, POST, PUT, DELETE).
Use clear and descriptive names for the endpoints.
Example:

## API Endpoints

* **GET /jobs** - Retrieves a list of all jobs.
* **POST /jobs** - Creates a new job.
* **GET /jobs/{id}** - Retrieves a specific job by its ID.
* **DELETE /jobs/{id}** - Deletes a job by its ID.
* **PUT /jobs/{id}** - Updates an existing job by its ID.
Request and Response Formats

Define the format of the request body (if applicable) for POST and PUT requests.
Specify the data types expected in the request body using JSON schema or a similar approach.
Describe the structure of the JSON response for each endpoint, including details about the properties and their data types.
Example:

### GET /jobs

**Response:**

```json
[
    {
        "id": 1,
        "title": "SDE -1",
        "description": "Looking for Java backend developers with 2 years experience",
        "minSalary": "60000",
        "maxSalary": "80000",
        "reviews": {
               "title": "SDE -2",
               "description": "Interview experience",
              "rating" : "4.3"
         }
        "company": {
            "id": 1,
            "description": "Looking for java backend developers1",
            "name": "abc"
        },
        "location": "Bangalore"
    }
]


-Response:

```JSON
"Job added"
