FROM mysql:8.0

ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=authentication
ENV MYSQL_USER=authen
ENV MYSQL_PASSWORD=authen
  
# Copy all SQL scripts inside the initialization directory
COPY init/ /docker-entrypoint-initdb.d/
