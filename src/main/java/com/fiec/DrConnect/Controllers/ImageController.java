package com.fiec.DrConnect.Controllers;

import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final String FILE_PATH_ROOT = "uploads/";

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable("filename") String filename){
        byte[] image = new byte[0];
        try{
            image = FileUtils.readFileToByteArray(new File(FILE_PATH_ROOT + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }
}