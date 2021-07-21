# Logística
[![NPM](https://img.shields.io/github/license/fabriciio95/logistica)](https://github.com/fabriciio95/logistica/blob/main/LICENSE) 

# Sobre o projeto

Logística é uma API REST desenvolvida para fins de aprendizado, onde clientes podem solicitar um serviço de entrega sendo que cada entrega tem objetos e possíveis ocorrências além de um motorista responsável.

## Modelo Lógico
![Logico](https://github.com/fabriciio95/arquivos-read-me/blob/master/arquivos-rep-logistica/logico-logistica.jpg)

# Tecnologias utilizadas
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Maven
- Jakarta Bean Validation
- JWT

# Arquitetura
- REST

# Como executar o projeto
Pré-requisitos: Java 11, MySQL

```bash

# clonar repositório
git clone https://github.com/fabriciio95/logistica

#Alterar no arquivo application.properties as propriedades de usuário e senha passando a senha de seu banco de dados local

# Entre na pasta raiz do projeto:
cd logistica

# E para rodar o projeto, você pode executar:
java -jar log-api-0.0.1-SNAPSHOT.jar
```
# Manual das requisições da API
Pré-requisitos: Postman

## Fazendo o login
Para fazer o login, você deve enviar uma requisição utilizando o método **POST** do protocolo HTTP com a URL:
```url
http://localhost:8080/login
```
E no corpo da requisição você precisará informar um usuário e senha válidos para serem autenticados e então receberá um token JWT que será utilizado posteriormente, pois todas as outras requisições necessitam de autorização. Para isso, você primeiro precisará enviar um objeto JSON com os campos:
```json
{
    "usuario" : "admin",
    "senha" : "admin"
}
```

![Login](https://github.com/fabriciio95/arquivos-read-me/blob/master/arquivos-rep-logistica/logar-request.png)

E se, e somente se, o usuário e senha forem válidos será retornado uma resposta com status 200 com um cabeçalho **Authorization** com o token JWT gerado pela API.

![Login](https://github.com/fabriciio95/arquivos-read-me/blob/master/arquivos-rep-logistica/logar-response.png)

## Acessando a documentação no Swagger
Para acessar a documentação no Swagger, no seu navegador com a aplicação executando entre com a url:
```url
http://localhost:8080/swagger-ui.html
```
E então, para autenticação informe usuário e senha válidos:
```text
USUÁRIO: admin
SENHA: admin
```

# Autor

Fabricio Siqueira Macedo

https://linkedin.com/in/fabricio-siqueira-macedo
