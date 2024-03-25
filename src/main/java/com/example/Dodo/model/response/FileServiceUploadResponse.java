package com.example.Dodo.model.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileServiceUploadResponse {


    String downloadUri;
    String fileName;
    String fileType;
    Integer size;
}