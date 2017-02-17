# Patient Cross Registration Service

Run the application by using maven and installing. Access the endpoints using port 8080

- post for adding documents
localhost:8080/addDocument
issuer "government of canada"
id "Qazzxc123"

- post
localhost:8080/getDocuments
existingDocumentID "1"

- post
localhost:8080/linkDocument
existingDocumentID "1"
existingDocumentID "2"