package com.carrot.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class AWSS3 {

    @Autowired
    AmazonS3Client s3Client;

    public void uploadImage(int itemPostId, List<MultipartFile> imageList) throws IOException {
        for (int i = 0; i < imageList.size(); i++) {
            String imageFileName = getFileName(itemPostId, i, imageList.get(i));
            String suffix = getSuffix(imageList.get(i));
            try {
                ObjectMetadata metadata = new ObjectMetadata();
                String contentType = "image/" + suffix;
                metadata.setContentType(contentType);
                metadata.setContentLength(imageList.get(i).getResource().contentLength());
                PutObjectRequest request = new PutObjectRequest("carrot-world",
                        imageFileName, imageList.get(i).getInputStream(), metadata);
                s3Client.putObject(request);
            } catch (AmazonServiceException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteImage(List<String> keys) {
        for (String key : keys) {
            try {
                s3Client.deleteObject(new DeleteObjectRequest("carrot-world", key));
            } catch (AmazonServiceException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileName(int itemPostId, int cnt, MultipartFile file) {
        StringBuilder sb = new StringBuilder();
        String[] suffix = file.getOriginalFilename().split("\\.");
        sb.append(itemPostId).append("_").append(cnt).append(".").append(suffix[suffix.length - 1]);
        return sb.toString();
    }

    public String getSuffix(MultipartFile file) {
        String[] fileName = file.getOriginalFilename().split("\\.");
        return fileName[fileName.length - 1];
    }
}
