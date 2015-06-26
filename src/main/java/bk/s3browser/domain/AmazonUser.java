package bk.s3browser.domain;

import com.google.common.base.MoreObjects;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 *
 * @author Biju Kunjummen
 *
 */
public class AmazonUser implements UserDetails {

    private final String accessKey;
    private final String secretKey;
    private final AWSCredentialType credentialType;

    public AmazonUser(String accessKey, String secretKey, AWSCredentialType credentialType) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.credentialType = credentialType;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public AWSCredentialType getCredentialType() {
        return credentialType;
    }

    public String getSecretKey() {
        return secretKey;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("accessKey", accessKey)
                .add("secretKey", secretKey)
                .add("credentialType", credentialType)
                .toString();
    }
}
