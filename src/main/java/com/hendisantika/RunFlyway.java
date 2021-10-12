package com.hendisantika;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.flywaydb.core.Flyway;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class RunFlyway {

    @ConfigProperty(name = "demo.flyway.migrate")
    boolean runMigration;

    @ConfigProperty(name = "quarkus.datasource.reactive.url")
    String datasourceUrl;
    @ConfigProperty(name = "quarkus.datasource.username")
    String datasourceUsername;
    @ConfigProperty(name = "quarkus.datasource.password")
    String datasourcePassword;

    public void runFlywayMigration(@Observes StartupEvent event) {
        if (runMigration) {
            Flyway flyway = Flyway.configure().dataSource("jdbc:" + datasourceUrl, datasourceUsername, datasourcePassword).load();
            flyway.migrate();
        }
    }
}
