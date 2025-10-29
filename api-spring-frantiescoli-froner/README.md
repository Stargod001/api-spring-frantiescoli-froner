# API Spring Boot

## Stack
Spring Boot Web, Spring Data JPA, Validation, Security, DevTools, MySQL, JUnit

## Executar
1. Criar um banco MySQL acessível pela URL definida em `application.properties`.
2. Ajustar `DB_USERNAME` e `DB_PASSWORD` via variáveis de ambiente ou editar o arquivo.
3. `./mvnw spring-boot:run` ou `mvn spring-boot:run`.

Credenciais: `admin`:`123456` ou `user`:`123456` via HTTP Basic.

## Endpoints
`POST /produtos` cria produto. Exemplo body:
```
{
  "nome": "Teclado",
  "preco": 199.90,
  "estoque": 10,
  "descricao": "ABNT2"
}
```
`GET /produtos` lista produtos.
`GET /produtos/{id}` busca por id.
`PUT /produtos/{id}` atualiza produto.
`DELETE /produtos/{id}` exclui produto.

Respostas de erro de validação retornam 400 com lista de campos.

## Testes
`mvn test` executa os testes com H2.
