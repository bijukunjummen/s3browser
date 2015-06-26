package bk.s3browser.web.s3;

import bk.s3browser.service.S3ListingService;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Responsible for the rest interface to return the details of a s3 bucket
 * @author Biju Kunjummen
 */

@RestController
@RequestMapping("/s3")
public class S3ListingRestController {

    @Autowired
    private S3ListingService s3ListingService;

    @RequestMapping("/listRootBuckets")
    public List<Bucket> listBucket() {
        return s3ListingService.listBuckets();
    }

    @RequestMapping("/listKeysInBucket")
    public ObjectListing listObjects(@RequestParam("bucket") String bucket) {
        return this.s3ListingService.listObjects(bucket, "");
    }


    @RequestMapping("/listKeysInBucketWithPrefix")
    public ObjectListing listObjectsWithPrefix(@RequestParam("bucket") String bucket,
                                               @RequestParam("prefix") String prefix) {
        return this.s3ListingService.listObjects(bucket, prefix);
    }
}
