package bk.s3browser.domain;

import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * To hold an AmazonS3Client scoped to a users session
 */

public class S3SessionScopedClient {
    private Optional<AmazonS3Client> s3Client = Optional.empty();

    public Optional<AmazonS3Client> getS3Client() {
        return s3Client;
    }

    public void setS3Client(AmazonS3Client s3Client) {
        this.s3Client = Optional.of(s3Client);
    }
}
