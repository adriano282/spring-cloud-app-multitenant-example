# spring-cloud-app-multitenant-example

It is a sample API: data clients suggests.

Some Applied Tecnologies:

    Zuul Server as Edge Server;
    Eureka Server as Service Discovery and Registration
    Spring Cloud Config to centralization of configurations
    Docker to virtualization environment
    Docker Compose to orchestrate the docker containers
    Spring Boot 2.0
   
The multitenant logic is applied through the Zuul Filter and Filter from Servlet lib.

Steps to run this project in local machine:

1. Clone repository:
    https://github.com/adriano282/spring-cloud-app-multitenant-example.git
    
2. Build the entire project:
    mvn clean install
    
3. From the root project folder, run:
    docker-compose -f docker/common/docker-compose.yml up
    
    
Necessary programs to run:
    1. Java 1.8
    2. docker
    3. docker-compose
    4. maven
    
    
Available endpoints through ZuulServer:

    POST http://localhost:5555/api/customersuggestion/v1/suggests
    Example Body:
    {
        "document": 1234567826,
        "companyName": "Company S.A.",
        "address": "av. dr. ricardo vilela",
        "city": "São PAulo",
        "state": "SP",
        "zone": null,
        "quarter": "Vila Brasil",
        "postalCode": "99999999"
    }
    
    GET http://localhost:5555/api/customersuggestion/v1/suggests/{document-number}
    
    GET http://localhost:5555/api/customersuggestion/v1/suggests
