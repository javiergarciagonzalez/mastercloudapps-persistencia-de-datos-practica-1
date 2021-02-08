# mastercloudapps-persistencia-de-datos-practica-1

Comando docker alternativo propuesto por el compañero Jaime Hernandez para levantar MYSQL
```docker run -p 3306:3306 --name mysql-db -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=test -e -d mysql/mysql-server:8.0.23```

En esta práctica hemos tomado las siguientes asunciones:

# Herencia

Creemos que la herencia que más encaja es `TABLE_PER_CLASS`.

`SINGLE_TABLE` No nos convencía porque parece que hay dos tipos de entidades, los mecánicos y la tripulación y tenerlo todo en una tabla no parecía la mejor solución. No se van a realizar consultas polimórficas ni hay joins entre esas dos tablas.
`JOINED` Nos parecía demasiado compleja para el caso, dado que siempre se necesitan los atributos de la clase padre en las consultas, esto implicaría muchos joins con lo que el rendimiento se vería afectado.
`TABLE_PER_CLASS` Con `MappedSuperclass` Nos encaja porque no se van a realizar consultas polimórficas y de esta forma se hacen lecturas sin joins y se escribe en una única tabla.


# Relaciones

- `Airplane` puede tener muchas revisiones, pero una revision solo es de un avión
- `MechanicalEmployee` puede hacer muchas revisiones, pero una revision solo es realizada por un mecánico
- `TechnicalReview` guarda una referencia de `Airplane`, `MechanicalEmployee` y `Airport`
- `Airport` puede tener muchas revisiones, pero una revision se hace solo en un aeropuerto
# Propagación

- `Airplane` se propaga como cascade `CascadeType.ALL` ya que cuando un avión se guarda o se elimina parece que no tiene sentido que sus revisiones existan. También se ha usado `orphanRemoval = true` para que se hagan los deletes de las órdenes si se borran desde los aviones.
- `MechanicalEmployee` no se propaga, parece interesante que las revisiones se queden aunque un mecánico no este en la compañía y que no se guarde una revision cuando el mecánico es guardado.
- `Airport` no se propaga, parece interesante que las revisiones se queden aunque un aeropuerto deje de estar operativo
