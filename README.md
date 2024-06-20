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