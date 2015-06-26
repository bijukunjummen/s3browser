package bk.s3browser.service;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;

import java.util.List;

/**
 * Responsible for Listing information from S3
 *
 * @author Biju Kunjummen
 */
public interface S3ListingService {
    List<Bucket> listBuckets();
    ObjectListing listObjects(String bucketName, String prefix);
}