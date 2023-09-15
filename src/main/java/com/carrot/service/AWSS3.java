package com.carrot.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class AWSS3 {

    @Autowired
    AmazonS3Client s3Client;
    
    public String getImgTag(MultipartFile file) throws IOException { //동네생활게시판 등록시 imgtag변환
        UUID uuid = UUID.randomUUID();
        String originfileName = file.getOriginalFilename();
        String suffix = getSuffix(file);
        System.out.println("suffix : " + suffix);
        
        String imageFileName = uuid+originfileName;
         
            try {
                ObjectMetadata metadata = new ObjectMetadata();
                String contentType = "image/" + suffix;
                metadata.setContentType(contentType);
                metadata.setContentLength(file.getResource().contentLength());
                PutObjectRequest request = new PutObjectRequest("carrot-world",
                        imageFileName, file.getInputStream(), metadata);
                s3Client.putObject(request);
                return imageFileName;
            } catch (AmazonServiceException e) {
                e.printStackTrace();
            }
            return null;
        }
    

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
