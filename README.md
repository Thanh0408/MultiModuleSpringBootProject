# How do I create multi module spring boot project
1. Open intelliJ IDE
2. Click: File -> New -> Project... -> New Project <br>
    Modify when u need:<br>
   > Name: name of the parent module.<br>
   > Location: where is the project in ?<br>
   > Build system: Maven ?<br>
   > JDK: Version of java.<br>
   > GroupId: package path.<br>
   
    Now u have parent Module -> next to, U should add new child module
3. Right click to name of the parent module<br>
    Click: New -> Module...<br>
    Modify Name, ... like Step 1

Now I have what I want

# Using Docker to create database
1. Build image:
   > docker build -t custom-mysql .   
2. Run container
   > docker run --name my-mysql -p 3307:3306 -d custom-mysql
3. Stop container
   > docker stop my-mysql
4. Start container
   > docker start my-mysql

# Jenkins
1. Run container
    > docker run -p 9090:8080 -p 50000:50000 jenkins/jenkins:lts
2. Get password - account: admin (replace jenkins in the command above with the actual container name or ID)
    > docker exec jenkins cat /var/jenkins_home/secrets/initialAdminPassword
