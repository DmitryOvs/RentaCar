Создание контейнера Docker базы данных арендованных машин
--  docker run --name catalog-db -p 3306:3306 -e MYSQL_ROOT_DB=catalog -e MYSQL_ROOT_USER=catalog -e MYSQL_ROOT_PASSWORD=catalog -d mysql:latest

Создание контейнера Docker базы данных Авторизации
--  docker run --name manager-db -p 3307:3306 -e MYSQL_ROOT_DB=manager -e MYSQL_ROOT_USER=manager -e MYSQL_ROOT_PASSWORD=manager -d mysql:latest

Создание контейнера keyCloak
-- docker run --name carRent-keycloak -p 8082:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -v ./config/keycloak/import quay.io/keycloak/keycloak:26.3.2 start-dev --import-realm