package demo.com.vn.config;

import demo.com.vn.entity.Account;
import demo.com.vn.entity.Role;
import demo.com.vn.enums.EnumRole;
import demo.com.vn.repository.IAccountRepository;
import demo.com.vn.repository.IRoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "123456@";

    @Bean
    @ConditionalOnProperty(
            prefix = "spring",
            value = "datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver")
    @Transactional
    ApplicationRunner applicationRunner(
            IAccountRepository accountRepository,
            IRoleRepository roleRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (accountRepository.findByUsername(ADMIN_USER_NAME).isPresent()) {
                log.warn("Admin account existed!");
            } else {
                roleRepository.saveAndFlush(Role.builder()
                        .name(EnumRole.USER.getCode())
                        .description("User role")
                        .build());

                Role adminRole =
                        roleRepository.save(Role.builder()
                                .name(EnumRole.ADMIN.getCode())
                                .description("Admin role")
                                .build());

                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);

                accountRepository.save(Account.builder()
                        .username(ADMIN_USER_NAME)
                        .password(passwordEncoder.encode(ADMIN_PASSWORD))
                        .roles(roles)
                        .build());


                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }

    /**
     * Load properties file from another file
     * @return properties
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer properties =
                new PropertySourcesPlaceholderConfigurer();
        String dir = System.getProperty("user.dir");
        Resource[] resources = new Resource[ ] {
                new FileSystemResource(dir + "/authentication-module/prop/config.properties"),
                new FileSystemResource(dir + "/authentication-module/src/main/resources/application.yaml")
        };
        properties.setLocations(resources);
        properties.setIgnoreResourceNotFound(false);
        return properties;
    }
}
