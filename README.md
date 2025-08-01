Создание контейнера Docker базы данных арендованных машин
--  docker run --name catalog-db -p 3306:3306 -e MYSQL_ROOT_DB=catalog -e MYSQL_ROOT_USER=catalog -e MYSQL_ROOT_PASSWORD=catalog -d mysql:latest

Создание контейнера Docker базы данных Авторизации
--  docker run --name manager-db -p 3307:3306 -e MYSQL_ROOT_DB=manager -e MYSQL_ROOT_USER=manager -e MYSQL_ROOT_PASSWORD=manager -d mysql:latest
