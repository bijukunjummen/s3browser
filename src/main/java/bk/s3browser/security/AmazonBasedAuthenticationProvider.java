package bk.s3browser.security;

import bk.s3browser.domain.AWSCredentialType;
import bk.s3browser.domain.AmazonUser;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeRegionsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

/**
 *
 * Responsible for validating AWS access key, secret key by making a simple AWS call.
 *
 * @author Biju Kunjummen
 */
public class AmazonBasedAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonBasedAuthenticationProvider.class);

    //A No-op for now
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        String accessKey = username;
        String secretKey = authentication.getCredentials().toString();
        if (StringUtils.isEmpty(accessKey) || StringUtils.isEmpty(secretKey)) throw new BadCredentialsException("Invalid Credentials");
        AmazonEC2Client ec2Client = new AmazonEC2Client(new BasicAWSCredentials(accessKey, secretKey));
        try {
            DescribeRegionsResult regionsResult = ec2Client.describeRegions();
            LOGGER.info("Validated credentials by trying out a sample region lookup, got back {}", regionsResult.toString());
        }catch (AmazonServiceException serviceException) {
            throw new BadCredentialsException("Invalid Credentials");
        }
        AmazonUser userDetails = new AmazonUser(accessKey, secretKey, AWSCredentialType.USER_SPECIFIED);
        return userDetails;
    }
}
