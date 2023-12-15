#!/bin/bash
docker stop mein-mysql-container

docker run -d --name mein-mysql-container -p 3308:3306 -e MYSQL_ROOT_PASSWORD=root -v ./scripts:/docker-entrypoint-initdb.d mysql:latest
# Wartezeit, um sicherzustellen, dass der MySQL-Server gestartet ist
sleep 10

# Ausführung des MySQL-Skripts
docker exec mein-mysql-container sh -c 'mysql -h localhost -u root -proot < /scripts/mock_data.sql'

