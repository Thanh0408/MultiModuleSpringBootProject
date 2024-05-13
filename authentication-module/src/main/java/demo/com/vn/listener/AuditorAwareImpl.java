package demo.com.vn.listener;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // your custom logic
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.of(Optional.ofNullable(username).orElse("SYS"));
    }
}
