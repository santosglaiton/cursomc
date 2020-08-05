package com.santosglaiton.cursomc.service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.logging.Logger;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3cliente;

    @Value("${s3.bucket}")
    private String bucketName;

    private Logger LOG = (Logger) LoggerFactory.getLogger(S3Service.class);

    public void uploadFile(String localFilePath) {
        try {
            File file = new File(localFilePath);
            LOG.info("Iniciando upload.");
            s3cliente.putObject(new PutObjectRequest(bucketName, "teste", file));
            LOG.info("Upload realizado.");
        }catch (AmazonServiceException e){
            LOG.info("AmazonServiceException: "+ e.getErrorMessage());
            LOG.info("Status code: "+ e.getErrorCode());
        }catch (AmazonClientException e){
            LOG.info("AmazonClientException: "+e.getMessage());
        }
    }

}
