Springboot TODO app with docker support. 

TO run using docker: 

I. without using docker-compose:

Step 1 : setup network -
> docker network create todo  --driver bridge

Step 2: Start mysql -
> docker run --detach -p 3360:3360 -v mysql_data:/var/lib/mysql --network todo --name demoDB  -e MYSQL_ROOT_PASSWORD=todoPassword -e MYSQL_DATABASE=TODODemo -e MYSQL_USER=todoUser -e MYSQL_PASSWORD=todoPassword mysql

Step 3: Start docker image : 
>docker run -p 8080:8080 --network todo vikrantkc/todo_springboot_docker

explore : http://localhost:8080/

II. Using docker-compose: (added)

> docker-compose up -d

explore : http://localhost:8080/


Deploying using kubernetes : (using minikube locally)


Step 1 : creating deployment and service yaml files using kompose
> kompose convert



Step2 : applying it 
> kubectl apply -f k8s/


Step3 : verify all of them are running
> kubectl get all


Step4: port forwading
> kubectl port-forward svc/todo-service  8080:8080






