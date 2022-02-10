# Employee Management System

### Description:
Employee-spring project is a java spring-boot application.
This application is used to create, read, update and delete employee data.
The operations on employee data is performed from REST endpoints.
The employee data is stored in an external database.
This application uses *spring-data-jpa* and *spring-data-rest* to provide the above functionality.
<br>
> **Note:** All commands should be run from *<local_path>/employee-spring*

### Table of Contents:
1. [Docker Setup](#docker-setup)
2. [Docker-Compose Run](#docker-compose-run)
3. [Kubernetes Run](#kubernetes-run)
4. [Helm Run](#helm-run)
5. [REST Operations](#rest-operations)

## Docker Setup
1. Create docker network *employee-net* <br/>
`docker network create employee-net`
2. Run db container *employee-db* <br/>
`docker run --rm -d --name employee-db -p 3306:3306 -e MYSQL_DATABASE=employee_db -e MYSQL_USER=employee -e MYSQL_PASSWORD=employee -e MYSQL_ROOT_PASSWORD=root --network employee-net mysql:8.0.27`
3. Build *employee-spring* application <br/>
`mvn clean install`
4. Copy *employee-spring-1.0.0.jar* to */bin* <br/>
`cp target/employee-spring-1.0.0.jar bin/`
5. Build *employee-app* image <br/>
`sh build.sh`
6. Run *employee-app* application container <br/>
`docker run --rm -d --name employee-app -p 8080:8080 -e DB_HOST=employee-db --network employee-net employee-app:1.0.0`
7. Check Rest Endpoints <br/>
`http://localhost:8080/employees` <br/>
`http://localhost:8080/first-message` <br/>
`http://localhost:8080/second-message` <br/>
`http://localhost:8080/third-message`
8. Cleanup <br/>
`docker stop employee-app` <br/>
`docker stop employee-db` <br/>
`docker network rm employee-net`

## Docker-Compose Run
1. Run *docker-compose* <br/>
`docker-compose -f bin/docker-compose.yml up -d`
<br/><br/>
2. Check Endpoints <br/>
`http://localhost:8080/employees` <br/>
`http://localhost:8080/first-message` <br/>
`http://localhost:8080/second-message` <br/>
`http://localhost:8080/third-message`
<br/><br/>
3. Stop *docker-compose* <br/>
`docker-compose -f bin/docker-compose.yml down --volumes`

## Kubernetes Run
1. Run *kubernetes* <br/>
`kubectl apply -f bin/kubernetes/secret.yaml` <br/>
`kubectl apply -f bin/kubernetes/configmap.yaml` <br/>
`kubectl apply -f bin/kubernetes/employee-db.yaml` <br/>
`kubectl apply -f bin/kubernetes/employee-app.yaml` <br/>
`watch kubectl get all`
<br/><br/>
2. Check Endpoints <br/>
`kubectl port-forward service/employee-app-service 8080:8080` <br/>
`http://localhost:8080/employees` <br/>
`http://localhost:8080/first-message` <br/>
`http://localhost:8080/second-message` <br/>
`http://localhost:8080/third-message` <br/>
**Note:** *To expose service from minikube*: `minikube service employee-app-service --url`
<br/><br/>
3. Stop *kubernetes* <br/>
`kubectl delete -f bin/kubernetes/secret.yaml` <br/>
`kubectl delete -f bin/kubernetes/configmap.yaml` <br/>
`kubectl delete -f bin/kubernetes/employee-db.yaml` <br/>
`kubectl delete -f bin/kubernetes/employee-app.yaml` <br/>
`watch kubectl get all`

## Helm Run
1. Deploy *helm chart* <br/>
`helm install employee-chart bin/helm/`
<br/><br/>
2. Check Endpoints <br/>
`kubectl port-forward service/employee-app-service 8080:8080` <br/>
`http://localhost:8080/employees` <br/>
`http://localhost:8080/first-message` <br/>
`http://localhost:8080/second-message` <br/>
`http://localhost:8080/third-message`
<br/><br/>
3. Undeploy *helm chart* <br/>
`helm uninstall employee-chart`

## Rest Operations
**1. Get List of Employees** <br/>
Request Type: **GET** <br/>
URL: `http://localhost:8080/employees`
<details>
  <summary>Json Response</summary>

```json
{
  "_embedded": {
    "employees": [
      {
        "name": "Leslie Andrews",
        "email": "leslie@google.com",
        "_links": {
          "self": {
            "href": "http://localhost:8080/employees/1"
          },
          "employee": {
            "href": "http://localhost:8080/employees/1"
          }
        }
      },
      {
        "name": "Emma Baumgarten",
        "email": "emma@google.com",
        "_links": {
          "self": {
            "href": "http://localhost:8080/employees/2"
          },
          "employee": {
            "href": "http://localhost:8080/employees/2"
          }
        }
      },
      {
        "name": "Avani Gupta",
        "email": "avani@google.com",
        "_links": {
          "self": {
            "href": "http://localhost:8080/employees/3"
          },
          "employee": {
            "href": "http://localhost:8080/employees/3"
          }
        }
      },
      {
        "name": "Yuri Petrov",
        "email": "yuri@google.com",
        "_links": {
          "self": {
            "href": "http://localhost:8080/employees/4"
          },
          "employee": {
            "href": "http://localhost:8080/employees/4"
          }
        }
      },
      {
        "name": "Juan Vega",
        "email": "juan@google.com",
        "_links": {
          "self": {
            "href": "http://localhost:8080/employees/5"
          },
          "employee": {
            "href": "http://localhost:8080/employees/5"
          }
        }
      }
    ]
  },
  "_links": {
    "self": {
      "href": "http://localhost:8080/employees"
    },
    "profile": {
      "href": "http://localhost:8080/profile/employees"
    }
  },
  "page": {
    "size": 20,
    "totalElements": 5,
    "totalPages": 1,
    "number": 0
  }
}
```
</details>

**2. Get Employee** <br/>
Request Type: **GET** <br/>
URL: `http://localhost:8080/employees/1`
<details>
  <summary>Json Response</summary>

```json
{
  "name": "Leslie Andrews",
  "email": "leslie@google.com",
  "_links": {
    "self": {
      "href": "http://localhost:8080/employees/1"
    },
    "employee": {
      "href": "http://localhost:8080/employees/1"
    }
  }
}
```
</details>

**3. Add Employee** <br/>
Request Type: **POST** <br/>
URL: `http://localhost:8080/employees`
<details>
  <summary>Json Request</summary>

```json
{
    "name": "New Employee",
    "email": "new@google.com"
}
```
</details>
<details>
  <summary>Json Response</summary>

```json
{
    "name": "New Employee",
    "email": "new@google.com",
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/6"
        },
        "employee": {
            "href": "http://localhost:8080/employees/6"
        }
    }
}
```
</details>

**4. Update Employee** <br/>
Request Type: **PUT** <br/>
URL: `http://localhost:8080/employees/1`
<details>
  <summary>Json Request</summary>

```json
{
  "name": "Update Employee",
  "email": "update@google.com"
}
```
</details>
<details>
  <summary>Json Response</summary>

```json
{
  "name": "Update Employee",
  "email": "update@google.com",
  "_links": {
    "self": {
      "href": "http://localhost:8080/employees/1"
    },
    "employee": {
      "href": "http://localhost:8080/employees/1"
    }
  }
}
```
</details>

**5. Delete Employee** <br/>
Request Type: **DELETE** <br/>
URL: `http://localhost:8080/employees/1` <br/>
Response Status: *204 No Content*