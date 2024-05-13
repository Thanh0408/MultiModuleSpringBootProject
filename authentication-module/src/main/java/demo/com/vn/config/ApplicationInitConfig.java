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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

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
                roleRepository.save(Role.builder()
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
                        .password(passwordEncoder().encode(ADMIN_PASSWORD))
                        .role(roles)
                        .build());


                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }
}
