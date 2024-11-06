package org.mukhtesh.streamerbackend.repository;

import org.mukhtesh.streamerbackend.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

    Video findByTitle(String title);

}
