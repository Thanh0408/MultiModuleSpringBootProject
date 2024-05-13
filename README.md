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

# Using database
> mysql, u do not ussing upper case when create database

# Using Docker to create database
1. Account database
    > run docker: docker run --name mysql -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql:latest