
# Transaction API

Simple REST API Java Spring Boot dan MySQL untuk Technical Assesement.

## Quick Start

1. **Setup Database MySQL:**
   - Buat database: `transaction_db`

2. **Build & Run:**
   ```bash
   mvn clean package -DskipTests
   java -jar target/transaction-api-1.0.0.jar
   ```

3. **API Base URL:**
   ```
   http://localhost:8081/api/v1
   ```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/transactions` | Get all transactions |
| GET | `/transactions/{id}` | Get transaction by ID |
| POST | `/transactions` | Create new transaction |
| PUT | `/transactions/{id}` | Update transaction |
| DELETE | `/transactions/{id}` | Delete transaction |

## Sample Request

**Create Transaction:**
```bash
POST /api/v1/transactions
Content-Type: application/json

{
  "productID": "10001",
  "productName": "Test Product",
  "amount": "1000",
  "customerName": "Customer Name",
  "status": 0,
  "createBy": "admin"
}
```

## Testing

Import `Transaction_API_Postman_Collection.json` di Postman.