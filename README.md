# Backend

#### Recomendamos que façam o exercicio sugerido em [Exercicio_Sugerido.md](https://github.com/yanpgabriel/hackathon-javaee/blob/main/Exercicio_Sugerido.md)

### Pré-Requisitos *obrigatórios*:
 - Git
 - JDK 11+
 - Maven 3.8.1

### Pré-Requisitos *opcionais*:
 - Docker (Caso queira usar o mysql que tem no projeto)
 - Docker Compose (Normalmente vem com o docker)

## Rodar a aplicação em modo de desenvolvimento

## Docker
**Lembrando que esses comando são no terminal dentro da pasta _"./src/main/docker"_**
<br/><br/>
E caso queira usar o MySQL no docker:<br/>
<small>Obs: Não use o "-d" caso queira que o terminal do docker fique aberto.</small>
```
docker-compose up -d 
```
<br/>

E para parar o MySQL no docker:<br/>
<small>Obs: Não precisa caso não tenha usado o "-d"</small>
```
docker-compose down
```

## Maven
**Lembrando que precisa do MySQL já esta rodando!**<br/>

Use o seguinte comando para iniciar o servidor:<br/>
<small>Obs: Esse comando deve ser executado na _raiz_ do projeto!</small>
```shell script
./mvnw clean compile quarkus:dev
```


#### Alguns guias relacionados:
- Maven ([guia](https://maven.apache.org/what-is-maven.html))
- Hibernate ORM (Implementação do JPA) ([guia](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html))
- RESTEasy JAX-RS ([guia](https://docs.jboss.org/resteasy/docs/3.0.9.Final/userguide/html_single/index.html))
- MySQL ([guia](https://dev.mysql.com/doc/))

Opcionais:
- Docker([guia](https://docs.docker.com/))

## Endpoints criados

|Verbo | Endpoint | Parâmetro | Body|
|---|---|---|---|
|GET|usuarios|N/A|Schema usuário|
|POST|usuarios|N/A|Schema usuário|
|PUT|usuarios/{id}|id|Schema usuário|
|DELETE|usuarios/{id}|id|N/A|
|GET|usuarios/emails|N/A|N/A|
|GET|usuarios/aniversariantes/{numeroDoMes}|numeroDoMes|N/A|
|GET|usuarios/primeira-letra-nome/{letra}|letra|N/A|

## Schema usuário:
```json
{
  "id": 1,
  "nome": "string",
  "login": "string",
  "email": "string",
  "senha": "string",
  "dataDeNascimento": "0000/00/00"
}
```
