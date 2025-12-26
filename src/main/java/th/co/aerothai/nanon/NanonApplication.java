package th.co.aerothai.nanon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Entry Point for the User Management Application.
 */
// Add @EnableJpaAuditing to support automatic timestamping.
@SpringBootApplication
@EnableJpaAuditing
public class NanonApplication {

    public static void main(String[] args) {
        SpringApplication.run(NanonApplication.class, args);
    }

}
