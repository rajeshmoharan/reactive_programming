package org.rajesh.users.infrastructor;

import org.h2.tools.Server;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;

import java.sql.SQLException;

@Configuration
@Profile("!prod")
public class H2ConsoleConfiguration {

    private final String WEB_PORT = "8082";
    private  Server SERVER;

    @EventListener(ApplicationStartedEvent.class)
    public void start() throws SQLException {
        this.SERVER = Server.createWebServer("-webPort",WEB_PORT).start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        this.SERVER.stop();
    }


}
