# PinApp-Challenge
Challenge técnico de JAVA Backend para la empresa PinApp

## Descripción

- Microservicios desarrollado en JAVASpring boot
- API Rest documentada en Swagger
- Disponible temporalmente en: https://capable-rainstorm-production.up.railway.app/swagger-ui/index.html

## Features:
● Endpoint de Entrada ```POST /creacliente ```
- Nombre
- Apellido
- Edad
- Fecha de nacimiento

● Endpoint de salida ``` GET /kpideclientes ```
- Promedio edad entre todos los clientes
- Desviación estándar entre las edades de todos los clientes

● Endpoint de salida ``` GET /listclientes ```
- Lista de personas con todos los datos + fecha probable de muerte de cada una


## Instrucciones para ejecutar la aplicación

Antes de ejecutar la aplicación, asegúrate de tener instalado Java 17 en tu máquina.

1. Clona este repositorio en tu máquina.

2. Actualmente el proyecto esta conectado a una Base de Datos en Railway. Sin embargo, no estará disponible para siempre y puede que sea conveniente conectarlo a tu propia base MySQL
Para ello puedes agregar estas opciones a la maquina virtual (VM Options) con los datos de conexión a tu base de datos:

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
### En IntelliJ IDEA:

a. Abre la configuración de ejecución de la aplicación en IntelliJ IDEA.

b. Navega hasta la pestaña "VM options".

c. Agrega las opciones de la máquina virtual 

```VM Options
-Dspring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_la_base_de_datos 
-Dspring.datasource.username=nombre_de_usuario 
-Dspring.datasource.password=contraseña_de_usuario
```

!! Reemplaza "nombre_de_la_base_de_datos", "nombre_de_usuario" y "contraseña_de_usuario" con la información correspondiente. (Si es necesario también reemplaza el  puerto)

d. Guarda la configuración de ejecución.


### En la línea de comandos:

a. Ejecuta el comando 

`java -jar -Dspring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_la_base_de_datos -Dspring.datasource.username=nombre_de_usuario -Dspring.datasource.password=contraseña_de_usuario nombre_de_la_aplicacion.jar` 

!! Reemplaza "nombre_de_la_base_de_datos", "nombre_de_usuario" y "contraseña_de_usuario" con la información correspondiente. (Si es necesario también reemplaza el  puerto)



3. Una vez hecho esto, elimina del archivo application.properties las lineas correspondientes a :

```application.properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```
De esta forma, la conexión quedará reemplazada por la configuracion de tu VM.

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

4. Abre tu terminal y navega hasta la carpeta raíz del proyecto.

5. Ejecuta el siguiente comando para compilar y empaquetar la aplicación:

`
./mvnw clean package 
`

### Una vez finalizados estos pasos, ya puedes levantar el proyecto normalmente de forma local! 

Podrás acceder a las Apis documentadas en Swagger siguiendo una ruta como esta: http://localhost:3000/swagger-ui/index.html 

-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

## Datos Extra 

### Cómo se realizo el cálculo de fecha de muerte probable: 

__Problema:__

Inicialmente la fecha de muerte probable se calculó en base a la esperanza de vida promedio mundial (73 años), sumándola a la fecha de nacimiento del cliente. 
Sin embargo, se encontró en que esto redundaba en que todos los clientes morian a la misma edad y los clientes mayores de 73 años ¡ya deberían estar muertos! 

__Solución:__ 

Se decidio utilizar la fórmula de la *Ley de Gompertz-Makeham* para la estimación de la expectativa de vida de nuestra población hipotética de clientes. 

La fórmula tiene tres parámetros, A, B y C, que representan diferentes factores que influyen en la mortalidad de una población.

El parámetro A representa la tasa de mortalidad inicial de una población, es decir, la probabilidad de morir a cualquier edad. El parámetro B representa la tasa de mortalidad que aumenta exponencialmente con la edad. Por último, el parámetro C representa la tasa de mortalidad que aumenta de manera acelerada a medida que una persona envejece.

*Se usaron parámetros hipotéticos para una población con baja tasa de mortalidad y alta expectativa de vida. Dividiéndola en 3 grupos etarios.*

La fórmula toma la edad actual de una persona y utiliza los parámetros A, B y C para calcular la probabilidad de que esa persona sobreviva hasta la siguiente edad. A partir de esta probabilidad, se puede calcular, iterando, la expectativa de vida restante de la persona, es decir, cuántos años se espera que viva a partir de su edad actual.

De este modo se tiene en cuenta que la persona ha vivido hasta la actualidad y se calcula su fecha de muerte probable a partir de su edad actual, con lo cual se obtienen resultados variables y siempre positivos dentro de un rango de edad máxima establecido en 120 años. 
