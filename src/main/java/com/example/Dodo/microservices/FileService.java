package com.example.Dodo.microservices;

import com.example.Dodo.model.response.FileServiceUploadResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Service
@FeignClient(name = "${micro.file.name}", url = "${micro.file.url}")
public interface FileService {

    @PostMapping(value = "/api/v1/file/upload", consumes = "multipart/form-data")
    FileServiceUploadResponse upload(@RequestPart MultipartFile file);

}