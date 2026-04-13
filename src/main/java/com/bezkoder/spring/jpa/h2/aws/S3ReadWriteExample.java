package com.bezkoder.spring.jpa.h2.aws;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class S3ReadWriteExample {

    public static void main(String[] args) {
        String bucketName = "my-example-bucket";
        String keyName = "example.txt";
        String uploadFilePath = "upload.txt";
        String downloadFilePath = "downloaded.txt";

        // Create a sample file to upload
        try {
            Files.writeString(Paths.get(uploadFilePath), "Hello from AWS S3 via Java!");
        } catch (IOException e) {
            System.err.println("Error creating upload file: " + e.getMessage());
            return;
        }

        // Create S3 client
        try (S3Client s3 = S3Client.builder()
                .region(Region.US_EAST_1) // Change to your region
                .credentialsProvider(ProfileCredentialsProvider.create()) // Uses ~/.aws/credentials
                .build()) {

            // Ensure bucket exists
            createBucketIfNotExists(s3, bucketName);

            // Upload file
            uploadFile(s3, bucketName, keyName, uploadFilePath);

            // Download file
            downloadFile(s3, bucketName, keyName, downloadFilePath);

        } catch (S3Exception e) {
            System.err.println("S3 error: " + e.awsErrorDetails().errorMessage());
        }
    }

    private static void createBucketIfNotExists(S3Client s3, String bucketName) {
        try {
            s3.headBucket(HeadBucketRequest.builder().bucket(bucketName).build());
            System.out.println("Bucket already exists: " + bucketName);
        } catch (NoSuchBucketException e) {
            System.out.println("Creating bucket: " + bucketName);
            s3.createBucket(CreateBucketRequest.builder().bucket(bucketName).build());
        }
    }

    private static void uploadFile(S3Client s3, String bucketName, String keyName, String filePath) {
        s3.putObject(PutObjectRequest.builder()
                        .bucket(bucketName)
                        .key(keyName)
                        .build(),
                RequestBody.fromFile(Paths.get(filePath)));
        System.out.println("Uploaded file to S3: " + keyName);
    }

    private static void downloadFile(S3Client s3, String bucketName, String keyName, String downloadPath) {
        try {
            s3.getObject(GetObjectRequest.builder()
                            .bucket(bucketName)
                            .key(keyName)
                            .build(),
                    Paths.get(downloadPath));
            System.out.println("Downloaded file from S3 to: " + downloadPath);
        } catch (S3Exception e) {
            System.err.println("Download failed: " + e.awsErrorDetails().errorMessage());
        }
    }
}
