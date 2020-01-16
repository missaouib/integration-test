package com.anderscore.testcontainers.extension;

import com.anderscore.testcontainers.util.DatabaseContainerHolder;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//tag::DbContainerExtension[]
public class DbContainerExtension implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        DatabaseContainerHolder containerHolder = SpringExtension.getApplicationContext(extensionContext)
                .getBean(DatabaseContainerHolder.class);

        Flyway flyWay = SpringExtension.getApplicationContext(extensionContext).getBean(Flyway.class);

        containerHolder.refresh();
        flyWay.migrate();
    }
}
//tag::DbContainerExtension[]