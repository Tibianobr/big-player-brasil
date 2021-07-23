package com.bpb.bigplayerbrasil.utils;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class VideoUtils {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    public static Binary converterVideoMultiPartParaBinary(MultipartFile video) throws IOException {
        return new Binary(BsonBinarySubType.BINARY, video.getBytes());
    }

    public static byte[] converterParaDownload(Binary binary) {
        return binary.getData();
    }
}
