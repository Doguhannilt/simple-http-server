package org.project.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListenerThread extends Thread {
    private final static Logger LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);
    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override
    public void run() {
        try {

            Socket socket = serverSocket.accept();
            LOGGER.info(" * Connection accepted: " + socket.getInetAddress());
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();


            String html = "<html><head><title>Page</title></head><body><h3>Hellloooo</h3></body></html>";
            final String CRLF = "\n\r"; // 13 , 10
            String response =
                    "HTTP/1.1 200 ok" + CRLF + // Status Line: HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
                            "Content-Length: " + html.getBytes().length + CRLF +  //HEADER
                            CRLF +
                            html +
                            CRLF + CRLF;

            outputStream.write(response.getBytes());


            inputStream.close();
            outputStream.close();
            socket.close();
            serverSocket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
