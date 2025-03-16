### DISCLAIMER: 
This repository was created for education and practice purposes by following a tutorial from CoderFromScratch titled "Create a Simple HTTP Server in Java Tutorial Series." You can check <a href="https://www.youtube.com/playlist?list=PLAuGQNR28pW56GigraPdiI0oKwcs8gglW">the playlist</a> for more.

# Simple HTTP Server

A simple Java-based HTTP Request parser that extracts essential components such as **HTTP Method**, **URL**, **Headers**, and **Body** from incoming HTTP requests. The project implements basic parsing for HTTP request lines, validation for HTTP version, and provides custom error handling.

## Features  
- Parse HTTP request line: Method, URL, and HTTP version  
- Extract headers and body  
- Validate HTTP version with regular expression  
- Custom exception handling for HTTP parsing errors  
- SLF4J logging for debugging  

## Technologies  
- **Java 8+**  
- **SLF4J Logger**  
- **Regular Expressions** for HTTP version parsing  

## Project Structure  
```
src/
│── org/project/http/
│   ├── HttpMessage.java
│   ├── HttpMethod.java
│   ├── HttpParser.java
│   ├── HttpParsingException.java
│   ├── HttpRequest.java
│   ├── HttpStatusCode.java
│   └── HttpVersion.java
```

## Error Handling  
- Custom exceptions such as `HttpParsingException` are thrown for malformed requests or unsupported HTTP versions.
- Common HTTP status codes are provided for error handling (e.g., **400 Bad Request**, **500 Internal Server Error**).
