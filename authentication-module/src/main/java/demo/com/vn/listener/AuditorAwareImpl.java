package demo.com.vn.listener;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // your custom logic
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String username = null;
        if (securityContext.getAuthentication() != null) {
             username = securityContext.getAuthentication().getName();
        }
        return Optional.of(Optional.ofNullable(username).orElse("SYS"));
    }
}
