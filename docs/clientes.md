# API de Clientes

### Listar Clientes
````
curl --location 'localhost:8080/api/v1/clientes'
````

### Pesquisar um Cliente
````
curl --location GET 'localhost:8080/api/v1/clientes/3'
````

### Criar um Cliente
````
curl --location 'localhost:8080/api/v1/clientes' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome" : "Cliente de Teste",
    "cnpj" : "26.803.754/0001-73",
    "email" : "teste@teste.com",
    "telefone" : "11988990011",
    "enderecos" : [
        {
            "logradouro" : "Rua de Teste",
            "numero" : 168,
            "complemento" : null,
            "cep" : "012222-000",
            "bairro" : "Parque de Teste",
            "cidade" : "Americana",
            "uf" : "SP"
        }
    ]
}'
````

### Atualizar um Cliente
````
curl --location --request PUT 'localhost:8080/api/v1/clientes/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "nome" : "Cliente de Atualização 00002",
    "cnpj" : "26.803.754/0001-73",
    "email" : "teste@teste.com",
    "telefone" : "11988990011",
    "enderecos" : [
        {
            "id" : 1,
            "logradouro" : "Rua de Teste 00002",
            "numero" : 168,
            "complemento" : null,
            "cep" : "012222-000",
            "bairro" : "Parque de Teste",
            "cidade" : "Americana",
            "uf" : "SP"
        }
    ]
}'
````

### Deletar um cliente
````
curl --location --request DELETE 'localhost:8080/api/v1/clientes/3'
````

