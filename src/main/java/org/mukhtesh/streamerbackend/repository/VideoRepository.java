package org.mukhtesh.streamerbackend.repository;

import org.mukhtesh.streamerbackend.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {

    Optional<Video> findVideoByTitle(String title);

}
