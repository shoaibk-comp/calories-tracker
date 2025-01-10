package calories.tracker.app.config;

import calories.tracker.app.init.TestDataInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile("test")
@EnableTransactionManagement
public class TestConfiguration {

    @Bean
    public CommandLineRunner initTestData(TestDataInitializer initializer) {
        return args -> initializer.init();
    }
}

