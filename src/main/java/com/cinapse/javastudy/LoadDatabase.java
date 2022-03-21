package com.cinapse.javastudy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(SwitchRepository switchRepository) {

        return args -> {
            switchRepository.save(new Switch("1.1.1.1", "admin", "admin"));
            switchRepository.save(new Switch("1.1.1.2", "admin", "admin"));
            switchRepository.findAll().forEach(sw -> {
                log.info("Preloaded " + sw);
            });
        };
    }
}
