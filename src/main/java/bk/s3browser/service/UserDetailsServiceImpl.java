package bk.s3browser.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Biju Kunjummen
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public Authentication getUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
