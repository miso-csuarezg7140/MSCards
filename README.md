# MSCards

Microservicio encargado de la gestión de las tarjetas de BankInc.

## Stack tecnológico

- **Lenguaje de programación:** Java v21.0.1
- **Framework de desarrollo:** Springboot 3.3.0
- **Motor de base de datos:** PostgreSQL v16
- **Cloud:** Google Cloud Platform (GCP)
- **Servicios cloud usados:** Cloud SQL / Artifact Registry / Kubernetes Engine
- **Contenerización:** Docker
- **Orquestación de contenedores:** Kubernetes

## Modelo DB

Se plantea el uso de dos bases de datos para favorecer la arquitectura de microservicios y dentro de cada una, la
existencia de una tabla de acuerdo con el tema que trata cada microservicio.

![](/assets/modelodb.png)

## Arquitectura

Se plantea el realizar el desarrollo siguiente la arquitectura de microservicios donde cada microservicio servicio
desarrollado está conectado a su propia base de datos, sin ningún tipo de acoplamiento. También, con el objetivo
de cumplir con la arquitectura de microservicios, para la comunicación de estos se plantea la necesidad de contar
con un API Gateway que gestione estos temas.

![](/assets/arquitectura.png)

## Despliegue

El despliegue se realizó usando la contenerización y orquestación de contenedores con Docker y Kubernetes
respectivamente. Inicialmente, se generaron los compilados .jar de cada microservicio para posteriormente ejecutar
el despliegue de estos compilados en contenedores mediante Docker en un repositorio creado en Artifact Registry de
GCP.

Luego, en Kubernetes, se hizo uso de un componente Ingress que actuara como API Gateway de la
arquitectura de microservicios planteada. Es por esta razón que en la documentación Postman que se encuentra 
[aquí](/assets/Prueba%20técnica%20Novatec.postman_collection.json), en las variables se hace referencia a la 
ip **34.49.240.92** como la ip correspondiente al componente Ingress.

Una imagen ilustrativa de la estrategia de despliegue implementado en cuanto a este último punto de Kubernetes,
se encuentra a continuación.

![](/assets/despliegue.png)

## Enlaces a repositorios
- [Repositorio MSCards](https://github.com/miso-csuarezg7140/MSCards)

- [RepositorioMSTransactions](https://github.com/miso-csuarezg7140/MSTransactions/)

## Instrucciones de ejecución en nube

1) Descargar la colección Postman que se encuentra [aquí](/assets/Prueba%20técnica%20Novatec.postman_collection.json).
2) Importar la colección en la herramienta Postman.
3) Ejecutar los diferentes métodos presentes en la colección.

**Nota:** Por defecto, la variable presente en todos lo métodos de la colección es **{{cloudEnv}}**, lo cual hace que
se ejecute la aplicación en al nube de GCP, sin embargo, si se quisiera montar la aplicación en local con Docker,
se seguirían los pasos de la próxima sección.

## Instrucciones de ejecución local

1) Informar al desarrollador de este proyecto la IP local para dar los respectivos permisos a las base de datos de
   PostgreSQL en CloudSQL.
2) Contar con Docker descargado y corriendo.
3) En la ruta principal de cada proyecto (ver sección **"Enlace a repositorios"** arriba), ejecutar los siguientes
   comandos:

- **MSCards:**

``docker build -t ms-cards:latest .``

``docker run -d -p 8080:8080 ms-cards:latest``

- **MSTransactions:**

``docker build -t ms-transactions:latest .``

``docker run -d -p 8081:8081 ms-transactions:latest``

4) En los métodos de la colección de Postman es necesario cambiar el valor de **{{cloudEnv}}** por
   **{{localEnvCards}}** y **{{localEnvTransactions}}** de manera correspondiente tanto para el microservicio de
   tarjetas como el microservicio de transacciones.
5) Ejecutar los diferentes métodos presentes en la colección.
6) Si todo funciona correctamente, se podrá observar la documentación de cada microservicio con Swagger en los
   siguiente enlaces.

- [Swagger MSCards](http://localhost:8080/swagger-ui/index.html)
- [Swagger MSTransactions](http://localhost:8081/swagger-ui/index.html)

## Disclaimer final
Se recomienda avisar al desarrollador cuando hayan finalizado las validaciones sobre la solución implementada para
poder dar de baja los recursos de nube y de esta manera evitar cobros inesperados. Gracias.