package bk.s3browser.service;

import bk.s3browser.domain.S3SessionScopedClient;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class S3ListingServiceImpl implements S3ListingService {
    @Autowired
    private S3SessionScopedClient s3ScopedClient;

    @Override
    public List<Bucket> listBuckets() {
        return s3ScopedClient.getS3Client()
                .map(s3Client -> s3Client.listBuckets())
                .orElseThrow(RuntimeException::new);
    }


    @Override
    public ObjectListing listObjects(String bucketName, String prefix) {
        ListObjectsRequest listRequest = new ListObjectsRequest(bucketName, prefix, null, "/", null);
        return this.s3ScopedClient
                .getS3Client().map(s3Client -> s3Client.listObjects(listRequest))
                .orElseThrow(RuntimeException::new);
    }
}
