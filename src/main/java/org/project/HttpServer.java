package org.project;

import org.project.config.Configuration;
import org.project.config.ConfigurationManager;
import org.project.core.ServerListenerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpServer.class);
    public static void main(String[] args) {
        LOGGER.info("System running");
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");

        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        LOGGER.info("Using Port:" + conf.getPort());
        LOGGER.info("Using Webroot:" + conf.getWebroot());

        ServerListenerThread serverListenerThread = null;
        try {
            serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Starts a simple HTTP server that listens for client connections and responds with an HTML page.
         * <p>
         * This method performs the following steps:
         * <ul>
         *   <li>Creates a {@link ServerSocket} to listen on a specified port.</li>
         *   <li>Waits for a client connection using {@code accept()}.</li>
         *   <li>Retrieves the input and output streams of the connected {@link Socket}.</li>
         *   <li>Builds an HTTP response containing a simple HTML page.</li>
         *   <li>Sends the response to the client via the output stream.</li>
         *   <li>Closes all open resources (input/output streams, socket, and server socket).</li>
         * </ul>
         *
         * @throws RuntimeException if an {@link IOException} occurs during the process.
         */
        //        try {
        //            ServerSocket serverSocket = new ServerSocket(conf.getPort());
        //            Socket socket = serverSocket.accept();
        //
        //            InputStream inputStream = socket.getInputStream();
        //            OutputStream outputStream = socket.getOutputStream();
        //
        //
        //            String html = "<html><head><title>Page</title></head><body><h3>Hellloooo</h3></body></html>";
        //            final String CRLF="\n\r"; // 13 , 10
        //            String response =
        //                    "HTTP/1.1 200 ok"+ CRLF + // Status Line: HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE
        //                    "Content-Length: "+ html.getBytes().length + CRLF +  //HEADER
        //                        CRLF +
        //                            html+
        //                            CRLF + CRLF;
        //
        //            outputStream.write(response.getBytes());
        //
        //
        //            inputStream.close();
        //            outputStream.close();
        //            socket.close();
        //            serverSocket.close();
        //
        //        } catch (IOException e) {
        //            throw new RuntimeException(e);
        //        }
    }
}