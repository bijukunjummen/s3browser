package bk.s3browser.service;

import org.springframework.security.core.Authentication;

/**
 * Retrieves the details of the currently logged in user
 *
 * @author Biju Kunjummen
 *
 */
public interface UserDetailsService {
    Authentication getUser();
}
