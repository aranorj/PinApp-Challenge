# PinApp-Challenge
Challenge técnico de JAVA Backend para la empresa PinApp

## Descripción

- Microservicios desarrollado en JAVASpring boot
- API Rest documentada en Swagger
- Deployado en: 

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

Antes de ejecutar la aplicación, asegúrate de tener instalado Java 17 y MySQL en tu máquina.

1. Clona este repositorio en tu máquina.
2. Crea o abre una base de datos en MySQL.
4. Deberás agregar opciones a la maquina virtual (VM Options) con los datos de conexión a tu base de datos:

### En IntelliJ IDEA:

1. Abre la configuración de ejecución de la aplicación en IntelliJ IDEA.

2. Navega hasta la pestaña "VM options".

3. Agrega las opciones de la máquina virtual 

```VM Options
-Dspring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_la_base_de_datos 
-Dspring.datasource.username=nombre_de_usuario 
-Dspring.datasource.password=contraseña_de_usuario
```

!! Reemplaza "nombre_de_la_base_de_datos", "nombre_de_usuario" y "contraseña_de_usuario" con la información correspondiente. (Si es necesario también reemplaza el  puerto)

4. Guarda la configuración de ejecución.

### En la línea de comandos:

1. Ejecuta el comando 

`java -jar -Dspring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_la_base_de_datos -Dspring.datasource.username=nombre_de_usuario -Dspring.datasource.password=contraseña_de_usuario nombre_de_la_aplicacion.jar` 

!! Reemplaza "nombre_de_la_base_de_datos", "nombre_de_usuario" y "contraseña_de_usuario" con la información correspondiente. (Si es necesario también reemplaza el  puerto)

2. Abre tu terminal y navega hasta la carpeta raíz del proyecto.

3. Ejecuta el siguiente comando para compilar y empaquetar la aplicación:

`
./mvnw clean package 
`

### Una vez finalizados estos pasos, ya puedes levantar el proyecto normalmente de forma local! 

Podrás acceder a las Apis documentadas en Swagger siguiendo una ruta como esta: http://localhost:8080/challenge/swagger-ui/index.html 

*Recuerda cambiar el puerto 8080 por el correspondiente de acuerdo a tu servidor local 
