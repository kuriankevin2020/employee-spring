# Employee Management System

### Description:
Employee-spring project is a java spring-boot application.
This application is used to create, read, update and delete employee data.
The operations on employee data is performed from REST endpoints.
The employee data is stored in an external database.
This application uses *spring-data-jpa* and *spring-data-rest* to provide the above functionality.
<br>
> **Note:** All commands should be run from *<local_path>/employee-spring*

##Docker
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
7. Check Rest Endpoint <br/>
`http://localhost:8080/employees` <br/>
`http://localhost:8080/first-message` <br/>
`http://localhost:8080/second-message` <br/>
`http://localhost:8080/third-message`
8. Cleanup <br/>
`docker stop employee-app` <br/>
`docker stop employee-db` <br/>
`docker network rm employee-net`

##Docker-Compose
1. Run docker-compose <br/>
`docker-compose -f bin/docker-compose.yml up -d`
<br/><br/>
2. Check endpoints <br/>
`http://localhost:8080/employees` <br/>
`http://localhost:8080/first-message` <br/>
`http://localhost:8080/second-message` <br/>
`http://localhost:8080/third-message`
<br/><br/>
3. Stop docker-compose <br/>
`docker-compose -f bin/docker-compose.yml down --volumes`

##Kubernetes
1. To run kubernetes <br/>
`kubectl apply -f bin/kubernetes/secret.yaml` <br/>
`kubectl apply -f bin/kubernetes/configmap.yaml` <br/>
`kubectl apply -f bin/kubernetes/employee-db.yaml` <br/>
`kubectl apply -f bin/kubernetes/employee-app.yaml` <br/>
`watch kubectl get all`
<br/><br/>
2. Check endpoints <br/>
`kubectl port-forward service/employee-app-service 8080:8080` <br/>
`http://localhost:8080/employees` <br/>
`http://localhost:8080/first-message` <br/>
`http://localhost:8080/second-message` <br/>
`http://localhost:8080/third-message` <br/>
**Note:** *To expose service from minikube*: `minikube service employee-app-service --url`
<br/><br/>
3. To stop kubernetes <br/>
`kubectl delete -f bin/kubernetes/secret.yaml` <br/>
`kubectl delete -f bin/kubernetes/configmap.yaml` <br/>
`kubectl delete -f bin/kubernetes/employee-db.yaml` <br/>
`kubectl delete -f bin/kubernetes/employee-app.yaml` <br/>
`watch kubectl get all`

##Helm
1. To deploy helm chart <br/>
`helm install employee-chart bin/helm/`
<br/><br/>
2. Check endpoints <br/>
`kubectl port-forward service/employee-app-service 8080:8080` <br/>
`http://localhost:8080/employees` <br/>
`http://localhost:8080/first-message` <br/>
`http://localhost:8080/second-message` <br/>
`http://localhost:8080/third-message`
<br/><br/>
3. To undeploy helm chart <br/>
`helm uninstall employee-chart`
