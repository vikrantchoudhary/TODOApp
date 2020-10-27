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

>docker-compose up -d

explore : http://localhost:8080/


Deploying using kubernetes : (using minikube locally)

Step 1 : creating deployment and service yaml files using kompose

> kompose convert
INFO Service name in docker-compose has been changed from "demoDB" to "demodb" 
INFO Kubernetes file "demodb-service.yaml" created 
INFO Kubernetes file "todo-service-service.yaml" created 
INFO Kubernetes file "demodb-deployment.yaml" created 
INFO Kubernetes file "demodb-claim0-persistentvolumeclaim.yaml" created 
INFO Kubernetes file "todo-service-deployment.yaml" created 

Step2 : applying it 
>> kubectl apply -f k8s/
persistentvolumeclaim/demodb-claim0 created
deployment.apps/demodb created
service/demodb created
deployment.apps/todo-service created
service/todo-service created

Step3 : verify all of them are running
> kubectl get all
NAME                                READY   STATUS    RESTARTS   AGE
pod/demodb-6b758cb48f-4q9k7         1/1     Running   0          5m37s
pod/todo-service-54d5556b7f-w8jtz   1/1     Running   3          5m37s


NAME                   TYPE        CLUSTER-IP       EXTERNAL-IP   PORT(S)    AGE
service/demodb         ClusterIP   10.103.13.131    <none>        3360/TCP   5m37s
service/kubernetes     ClusterIP   10.96.0.1        <none>        443/TCP    5h55m
service/todo-service   ClusterIP   10.110.155.220   <none>        8080/TCP   5m37s


NAME                           READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/demodb         1/1     1            1           5m37s
deployment.apps/todo-service   1/1     1            1           5m37s

NAME                                      DESIRED   CURRENT   READY   AGE
replicaset.apps/demodb-6b758cb48f         1         1         1       5m37s
replicaset.apps/todo-service-54d5556b7f   1         1         1       5m37s


> kubectl port-forward svc/todo-service  8080:8080
Forwarding from 127.0.0.1:8080 -> 8080
Forwarding from [::1]:8080 -> 8080
Handling connection for 8080
Handling connection for 8080
Handling connection for 8080





