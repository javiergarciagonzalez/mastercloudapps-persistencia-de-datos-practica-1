# mastercloudapps-persistencia-de-datos-practica-1


Asunciones

- El mecánico encargado de la revision es solo 1


Usamos docker, nos funciona con este comando, no hemos tenido que poner `-e MYSQL_ROOT_HOST=%` como han comentado otros compañeros en slack

```docker run -p 3306:3306 --name mysql-db -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=test -e -d mysql:8.0.23```
