## Housi Challenger

### Getting Started
This project requires Docker to be installed on your computer. 
### How to Run
#### Steps
- `git clone https://github.com/anytitleyoulike/desafio-backend-housi.git`
- `docker compose up`

project starts on: http://localhost:3000 and the
collections with endpoints is on assets folder.

## Endpoints
### 1.  GET    /properties/{id}/busy-dates
#### Request:
```curl
curl --location 'http://localhost:3000/properties/56b3aa2578dc1a90d3de53ee/busy-dates
```
#### Response:
```json
[
    "2024-01-05",
    "2024-01-06",
    "2024-01-08",
    "2024-01-07",
    "2024-01-09",
    "2024-01-10",
    "2024-01-11",
    "2024-01-12",
    "2024-01-13",
    "2024-01-14",
    "2024-01-15",
    "2024-01-16",
    "2024-01-17"
    ...
]
```

### 2. GET /properties/{id}/busy-dates/start=?&end=?
#### Request:
```curl
curl --location 'http://localhost:3000/properties/56b3aa2578dc1a90d3de53ee/busy-dates?start=2024-01-02&end=2024-05-25'
```

#### Response:
```json
[
  "2024-04-28",
  "2024-04-29",
  "2024-04-30",
  "2024-05-01",
  "2024-05-02",
  "2024-05-03",
  "2024-05-04",
  "2024-05-05",
  "2024-05-06",
  "2024-05-07",
  "2024-05-08",
  "2024-05-09",
  "2024-05-10"
]
```

### 3. /properties/{id}
#### Request:
```curl
 curl --location 'http://localhost:3000/properties/56b3aa2578dc1a90d3de53ee'
```
#### Response: 
```json
[
  {
    "id": "65b432ae59bfacb3ffc4b0ba",
    "property": {
      "id": "56b3aa2578dc1a90d3de53ee",
      "name": "Unidade 2"
    },
    "checkIn": "2024-05-01",
    "checkOut": "2024-05-02",
    "residents": [
      {
        "userId": "45b3aa2578dc1a90d3de53ee",
        "name": "Morador 1",
        "email": "morador1@email.com",
        "phone": "(11) 99999-9999"
      }
    ]
  },
  {
    "id": "65b432ae59bfacb3ffc4b0bb",
    "property": {
      "id": "56b3aa2578dc1a90d3de53ee",
      "name": "Unidade 2"
    },
    "checkIn": "2024-07-01",
    "checkOut": "2024-12-10",
    "residents": [
      {
        "userId": "45b3aa2578dc1a90d3de53ee",
        "name": "Morador 1",
        "email": "morador1@email.com",
        "phone": "(11) 99999-9999"
      }
    ]
  }
]


```





<div align="center">
  <img
    src="assets/banner.png"
    alt="Desafio Backend"
    style="width: 100%"
  />
  <p> Desafio Back-end - Housi</p>
</div>

**Objetivo:** Desenvolver uma API backend para gerenciar reservas, utilizando MongoDB. A API deve ser capaz de retornar um conjunto de datas bloqueadas baseadas em um intervalo de tempo definido.

## Tarefa Principal:

- Implementar uma rota que interaja com um banco de dados MongoDB contendo informações de reservas.
- Esta rota deve aceitar dois parâmetros, `start` e `end`, que definem o início e o fim de um período.
- A API deve processar esses parâmetros e retornar um array de datas que representem todas as datas bloqueadas dentro do intervalo especificado. Caso não seja informado, deve retornar todos os bloqueios de hoje em diante.
- A solução deve ser replicável utilizando Docker, garantindo facilidade de configuração e execução.
- Providenciar documentação clara para a instalação e execução do projeto.

**Exemplo de Uso:**

```json
{
  "host": "localhost:3000",
  "endpoint": "/properties/:id/busy-dates",
  "method": "GET",
  "queryParams": {
    "start": "2024-01-01", // opcional
    "end": "2024-01-30" // opcional
  },
  "response": [
    "2024-01-03",
    "2024-01-05",
    "2024-01-08",
    "2024-01-09",
    "2024-01-10",
    ...
  ]
}
```

## Bônus (Opcional)

- Criação de uma documentação detalhada para a API (utilizando ferramentas como Swagger).
- Implementação de índices apropriados no MongoDB para otimizar a consulta das datas bloqueadas.
- Desenvolvimento de testes unitários para garantir a funcionalidade e a qualidade do código.

## OBS

> Utilize o arquivo `seed.json` para popular seu banco de dados no MongoDB.

> A escolha de linguagem de programação e framework é livre. Se optar por utilizar Node.js, o uso de TypeScript é obrigatório.
