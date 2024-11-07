package org.mukhtesh.streamerbackend.service;

import org.mukhtesh.streamerbackend.entity.Video;
import org.mukhtesh.streamerbackend.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;

    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public void saveVideo(Video video) {
        videoRepository.save(video);
    }

    public Optional<Video> findVideoByTitle(String title) {
        return videoRepository.findVideoByTitle(title);
    }

}
