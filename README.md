## Samsung Technical Challenge

Para solução do problema apresentado foi desenvolvido uma api usando Java como a linguagem, usando como base o Spring boot, por acelerar o desenvolvimento, facilitando o processo de configuração, e trazendo um container embarcado (por padrão o tomcat), trazendo agilidade para o desenvolvimento. 
Foi utilizado o swagger para documentar a api, e facilitar a interação.
Como base de dados foi escolhido o H2, por ser embarcado na aplicação, rápido e fácil de configurar, pois o problema apresentado não necessitar de uma estrutura robusta de banco de dados.
Para o front-end a aplicação foi desenvolvida em Angular, e em um projeto separado do back-end.

#### Pré-requisito para execultar a aplicação
1. java 8, ou superior 
2. maven instalado


#### Executando a aplicação
1. descompacte o projeto
2. abra o terminal dentro da pasta do projeto, e execulte o comando: (`mvn install`)
3. em sequida execute o Spring Boot usando o maven (`mvn spring-boot:run`)


* Endereço para acessar o swagger: http://localhost:9001/swagger-ui.html
* Endereço para acessar o banco de dados: http://localhost:9001/h2

```sh
 configurações:
    - database: car-rentaldb
    - usename: h2sa
    - password: admin
```    
    
    
