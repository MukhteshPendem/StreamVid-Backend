package org.mukhtesh.streamerbackend.controller;

import org.mukhtesh.streamerbackend.entity.Video;
import org.mukhtesh.streamerbackend.service.S3Service;
import org.mukhtesh.streamerbackend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private final S3Service s3Service;

    private final VideoService videoService;

    @Autowired
    public VideoController(S3Service s3Service, VideoService videoService) {
        this.s3Service = s3Service;
        this.videoService = videoService;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description)

    {
        try {
            String fileUrl = s3Service.uploadFile(file);

            Video video = videoService.findVideoByTitle(title)!=null
                    ?videoService.findVideoByTitle(title)
                    :new Video(title,description,fileUrl) ;
            if (video != null) {

                videoService.saveVideo(video);

            }

            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload video: " + e.getMessage());
        }
    }
}
