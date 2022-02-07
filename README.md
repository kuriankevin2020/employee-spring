Employee-spring project is a springboot application, which can be used to perform crud operations on employee data stored in a mysql database over rest interface.

Steps to setup the Demo Project:
All commands should be run from <local_path>/employee-spring

-----Docker-----
Step1: Create employee-net
docker network create employee-net

Step2: Run employee-db
docker run --rm -d --name employee-db -p 3306:3306 -e MYSQL_DATABASE=employee_db -e MYSQL_USER=employee -e MYSQL_PASSWORD=employee -e MYSQL_ROOT_PASSWORD=root --network employee-net mysql:8.0.27

Step3: Build employee-spring project
mvn clean install

Step4: Copy employee-spring-1.0.0.jar
cp target/employee-spring-1.0.0.jar bin/

Step5: Build employee-app image
sh build.sh

Step6: Run employee-app
docker run --rm -d --name employee-app -p 8080:8080 -e DB_HOST=employee-db --network employee-net employee-app:1.0.0

Step7: Check Rest Endpoint
http://localhost:8080/employees
http://localhost:8080/log

Step8: Cleanup
docker stop employee-app
docker stop employee-db
docker network rm employee-net

-----Docker-Compose-----
Step1: Run docker-compose
docker-compose -f docker/docker-compose.yml up -d

Step2: Check endpoints
http://localhost:8080/employees
http://localhost:8080/log

Step3: Stop docker-compose
docker-compose -f docker/docker-compose.yml down --volumes

-----Kubernetes-----
Step1: Build employee-app image
minikube image build -t employee-app:1.0.0 docker/

Step2: To run kubernetes
kubectl apply -f kubernetes/secret.yaml
kubectl apply -f kubernetes/configmap.yaml
kubectl apply -f kubernetes/employee-db.yaml
kubectl apply -f kubernetes/employee-app.yaml
watch kubectl get all

Step3: Check endpoints
minikube service employee-app-service --url
http://127.0.0.1:45297/employees
http://127.0.0.1:45297/log

Step4: To stop kubernetes
kubectl delete -f kubernetes/secret.yaml
kubectl delete -f kubernetes/configmap.yaml
kubectl delete -f kubernetes/employee-db.yaml
kubectl delete -f kubernetes/employee-app.yaml
watch kubectl get all

-----Helm-----
Step1: To deploy helm chart
helm install employee-chart helm/

Step2: Check endpoints
kubectl port-forward service/employee-app-service 8080:8080
http://localhost:8080/employees
http://localhost:8080/log

Step3: To undeploy helm chart
helm uninstall employee-chart
