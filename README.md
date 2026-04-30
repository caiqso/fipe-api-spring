# FIPE API - Spring Boot 🚗

🇧🇷 API REST para consulta de veículos baseada na Tabela FIPE.  
🇺🇸 REST API for vehicle price lookup using FIPE data.

---

## About / Sobre

🇺🇸  
This project is a REST API built with Spring Boot that consumes an external FIPE API and exposes structured endpoints following REST best practices.

🇧🇷  
Este projeto é uma API REST desenvolvida com Spring Boot que consome uma API externa da FIPE e expõe endpoints organizados seguindo boas práticas REST.

---

## Tech Stack 💻

- Java 17
- Spring Boot
- Spring Web
- Maven
- Jackson
- Swagger (OpenAPI)

---

## Architecture / Arquitetura

🇺🇸  
The project follows a layered architecture:

🇧🇷  
O projeto segue uma arquitetura em camadas:

- `controller` → REST endpoints
- `service` → business logic
- `client` → external API consumption
- `model` → DTOs (records)
- `exception` → global error handling

---

## 🌐 Endpoints

### 🔹 Get brands / Listar marcas

- `GET /fipe/{tipo}/marcas`


  Example:

- `/fipe/carros/marcas`

---

### 🔹 Get models / Listar modelos

- `GET /fipe/{tipo}/marcas/{marca}/modelos`

Example:

- `/fipe/carros/marcas/7/modelos`

---

### 🔹 Get vehicles by year / Veículos por ano

- `GET /fipe/{tipo}/marcas/{marca}/modelos/{modelo}/veiculos`

Example: 

- `/fipe/carros/marcas/7/modelos/161/veiculos`

---

## ⚠️ Validation & Errors / Validações e Erros

🇺🇸
- Allowed types: `carros`, `motos`, `caminhoes`
- Global exception handling
- Clean and readable error responses

🇧🇷
- Tipos permitidos: `carros`, `motos`, `caminhoes`
- Tratamento global de erros
- Respostas claras e padronizadas

---

## 📄 API Docs (Swagger)

Access: http://localhost:8080/swagger-ui/index.html

---

## ▶️ Running the project / Como rodar

```bash
git clone https://github.com/caiqso/fipe-api-spring.git
cd fipe-api-spring
mvn spring-boot:run
```
---

## 💡 Future Improvements / Melhorias futuras

🇺🇸

Caching layer
Unit tests
Docker support
Cloud deployment

🇧🇷

Cache para reduzir chamadas externas
Testes unitários
Docker
Deploy em nuvem

---

👨‍💻 Author

Developed by Caíque

[My LinkedIn Profile](https://www.linkedin.com/in/caiquesoaress)
