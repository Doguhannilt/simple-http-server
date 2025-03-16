package org.project;

import org.project.config.Configuration;
import org.project.config.ConfigurationManager;

public class HttpServer {
    public static void main(String[] args) {
        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();
        System.out.println("Using Port: " + conf.getPort());
        System.out.println("Using WebRoot: "+ conf.getWebroot());
    }
}