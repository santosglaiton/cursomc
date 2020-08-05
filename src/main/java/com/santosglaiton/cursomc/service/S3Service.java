package com.santosglaiton.cursomc.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.santosglaiton.cursomc.service.exceptions.FileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3cliente;

    @Value("${s3.bucket}")
    private String bucketName;

    public URI uploadFile(MultipartFile multipartFile) {
        try {
            String fileName = multipartFile.getOriginalFilename();
            InputStream is = multipartFile.getInputStream();
            String contentType = multipartFile.getContentType();
            return uploadFile(is, fileName, contentType);
        }catch (IOException e){
            throw new FileException("Erro de IO: "+e.getMessage());
        }
    }

        public URI uploadFile(InputStream is, String fileName, String contentType){
            try {
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentType(contentType);
            s3cliente.putObject(bucketName, fileName, is, meta);
                return s3cliente.getUrl(bucketName, fileName).toURI();
            } catch (URISyntaxException e) {
                throw new FileException("Erro ao converter URL para URI");
            }
        }
    }

