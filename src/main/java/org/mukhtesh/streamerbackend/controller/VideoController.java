package org.mukhtesh.streamerbackend.controller;

import org.mukhtesh.streamerbackend.entity.Video;
import org.mukhtesh.streamerbackend.service.S3Service;
import org.mukhtesh.streamerbackend.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/"})
@RequestMapping("/api/videos")
public class VideoController {

    private final S3Service s3Service;

    private final VideoService videoService;

    @Autowired
    public VideoController(S3Service s3Service, VideoService videoService) {
        this.s3Service = s3Service;
        this.videoService = videoService;
    }

    @GetMapping("/{title}")
    public Video getVideo(@PathVariable String title) {


        Optional<Video> existingVideo = videoService.findVideoByTitle(title);
        return existingVideo.orElseGet(Video::new);

    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description)

    {
        try {
            String fileUrl = "";


            Optional<Video> existingVideo = videoService.findVideoByTitle(title);

            if (existingVideo.isEmpty()) {
                fileUrl = s3Service.uploadFile(file);
                Video video = new Video(title, description, fileUrl);
                videoService.saveVideo(video);
            }

            else{

                fileUrl = existingVideo.get().getUrl();

            }

            return ResponseEntity.ok(fileUrl);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload video: " + e.getMessage());
        }
    }
}
