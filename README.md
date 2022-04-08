
# APP-LOCATING-AVAILABLE-RESTAURANTS-JAVA

<p>
Back End de uma aplicação de fast food. Nela é possível localizar os locais mais próximos do usuário dado uma latitude e longitude, encontrar o local mais próximo e que realize entregas no área do usuário, criar novos locais e realizar, buscar por id ou documento.
</p>

### :pushpin: Features

- [x] Criar parceiro.
- [x] Buscar parceiro pelo ID.
- [x] Buscar parceiro pelo documento.
- [x] Buscar todos os parceiros.
- [x] Buscar parceiros mais próximo.
- [x] Buscar parceiro que atenda na localização atual.
- [x] Visualizar configurações.
- [x] Alterar configurações.
- [x] Gerar dados automaticamente.

### :hammer: Pré-requisitos

Antes de começar será necessário que a máquina possua o banco não relacional [MongoDB](https://www.mongodb.com/cloud/atlas/lp/try2?utm_source=google&utm_campaign=gs_americas_brazil_search_core_brand_atlas_desktop&utm_term=mongodb&utm_medium=cpc_paid_search&utm_ad=e&utm_ad_campaign_id=12212624308&adgroup=115749706023&gclid=CjwKCAjwrqqSBhBbEiwAlQeqGkrdA0pMxJVavy0QMLhd-BdMMwXtwAqrzjX3xgyjNcLQdq83w7PlVhoC5bMQAvD_BwE) ou [Docker](https://www.docker.com/) instalado. 

### 🎲 Iniciando projeto pela primeira vez

```bash
# Baixe uma imagem do mongo atraves do docker em um terminal
docker pull mongo

# Apos o download execute o seguinte comando para a criação de uma nova instância com o banco de dados MongoDB
docker run --name mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=leonardo -e MONGO_INITDB_ROOT_PASSWORD=123456 mongo

# Clone este repositório
git clone https://github.com/leonardodantas/spring-kafka-docker.git

# Inicie a aplicação com uma IDE de sua preferência

# Execute os seguintes comandos em terminal do MongoDB
db.partner.createIndex({address:"2dsphere"});
db.partner.createIndex({coverageArea:"2dsphere"});

#Acesse o seguinte endereço no navegador
http://localhost:8080/swagger-ui/

```

### 🛠 Detalhes Tecnicos

- Java 17
- Utilização de Records
- Geospatial Queries do mongo
- Durante grande parte de desenvolvimento, o banco de dados era apenas um detalhe, por isso utilizei um arquivo JSON para ler e escrever dados
- A geração de dados automática utiliza o arquivo json que já possui dados e insere no mongo

## Documentação da API

### Adiciona um novo parceiro

```
  POST /partner
```
A requisição precisa de um body com os seguintes parâmetros:
| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `tradingName` | `string` | **Obrigatório**. Nome comercial |
| `ownerName` | `double` | **Obrigatório**. Nome do proprietário |
| `document` | `string` | **Obrigatório**. Documento do parceiro |
| `coverageArea` | `GeoJSON MultiPolygon` | **Obrigatório**. Área de cobertura |
| `address` | `GeoJSON Point` | **Obrigatório**. Endereço |

### Retorna uma lista com todos os parceiros

```
  GET /partner/list
```

### Retorna uma página com todos os parceiros

```
  GET /partner/page
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `page` | `int` | **Obrigatório**. Oágina da requisição |
| `size` | `int` | **Obrigatório**. Quantidade de itens na página |

### Retorna detalhes de um parceiro a partir de um documento

```
  GET /partner/document/{document}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `document` | `string` | **Obrigatório**. Documento |

### Retorna detalhes de um parceiro a partir de um id

```
  GET /partner/document/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `string` | **Obrigatório**. ID |

### Retorna uma lista de parceiros a partir da latitude, longitude e configurações da aplicação

```
  GET /partner/distance
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `latitude` | `double` | **Obrigatório**. Latitude |
| `longitude` | `double` | **Obrigatório**. Longitude |

### Retorna o parceiro mais proximo que atenda dentro da área de cobertura do usuário

```
  GET /partner
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `latitude` | `double` | **Obrigatório**. Latitude |
| `longitude` | `double` | **Obrigatório**. Longitude |

### Geração de dados

```
  POST /generate
```

### Recupera a configuração da aplicação

```
  GET /config
```

### Atualiza a configuração da aplicação

```
  PUT /config
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `maxDistance` | `double` | **Obrigatório**. Distancia máxima permitida |
| `quantityPartners` | `double` | **Obrigatório**. Quantidade máxima de parceiros |

## Tecnologias

<div style="display: inline_block">

  <img align="center" alt="mongo" src="https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white" />
  <img align="center" alt="java" src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white" />
  <img align="center" alt="spring" src="https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white" />
  <img align="center" alt="swagger" src="https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white" />

</div>

## Apêndice

O projeto foi construido a partir do seguinte desafio https://github.com/ZXVentures/ze-code-challenges/blob/master/backend_pt.md encontrado no repositório https://github.com/CollabCodeTech/backend-challenges


### :sunglasses: Autor
Criado por Leonardo Rodrigues Dantas.

[![Linkedin Badge](https://img.shields.io/badge/-Leonardo-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/leonardo-rodrigues-dantas/)](https://www.linkedin.com/in/leonardo-rodrigues-dantas/) 
[![Gmail Badge](https://img.shields.io/badge/-leonardordnt1317@gmail.com-c14438?style=flat-square&logo=Gmail&logoColor=white&link=mailto:leonardordnt1317@gmail.com)](mailto:leonardordnt1317@gmail.com)

## Licença
Este projeto esta sobe a licença MIT.
