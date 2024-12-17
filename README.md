### **Project Structure**
```
com.mobile.tourism/
├── config/
├── controllers/
├── dto/
├── entities/
│   ├── OurUsers.java
│   ├── Place.java
│   └── Comment.java
├── mappers/
├── repositories/
│   ├── UsersRepo.java
│   ├── PlaceRepository.java
│   └── CommentRepository.java
├── services/
└── security/
```

### **Features**
1. **User Authentication (existing)**
2. **Tourist Places Management**
3. **Comments and Ratings System**
4. **Role-based Access Control**

### **API Endpoints**

#### **Places**

- **Get all places**  
  `curl -X GET http://localhost:8081/places`

- **Get place by ID**  
  `curl -X GET http://localhost:8081/places/{id}`

- **Create new place (ADMIN only)**  
  `curl -X POST http://localhost:8081/places`  
  `-H "Authorization: Bearer YOUR_JWT_TOKEN"`  
  `-H "Content-Type: application/json"`  
  `-d '{ "name": "Eiffel Tower", "description": "Famous landmark in Paris", "location": "Paris, France" }'`

- **Update place (ADMIN only)**  
  `curl -X PUT http://localhost:8081/places/{id}`  
  `-H "Authorization: Bearer YOUR_JWT_TOKEN"`  
  `-H "Content-Type: application/json"`  
  `-d '{ "name": "Updated Name", "description": "Updated description", "location": "Updated location" }'`

- **Delete place (ADMIN only)**  
  `curl -X DELETE http://localhost:8081/places/{id}`  
  `-H "Authorization: Bearer YOUR_JWT_TOKEN"`

#### **Comments**

- **Add comment to place**  
  `curl -X POST http://localhost:8081/comments`  
  `-H "Authorization: Bearer YOUR_JWT_TOKEN"`  
  `-H "Content-Type: application/json"`  
  `-d '{ "text": "Great place!", "placeId": 1 }'`

- **Upvote comment**  
  `curl -X POST http://localhost:8081/comments/upvote/{id}`  
  `-H "Authorization: Bearer YOUR_JWT_TOKEN"`

- **Downvote comment**  
  `curl -X POST http://localhost:8081/comments/downvote/{id}`  
  `-H "Authorization: Bearer YOUR_JWT_TOKEN"`

### **Testing with Postman/Curl**

1. **First, get a JWT token:**

   ```bash
   curl -X POST http://localhost:8081/auth/login \
   -H "Content-Type: application/json" \
   -d '{ "email": "your.email@example.com", "password": "yourpassword" }'
   ```

2. **Use the token in subsequent requests:**

   ```bash
   curl -X GET http://localhost:8081/places \
   -H "Authorization: Bearer YOUR_JWT_TOKEN"
   ```
