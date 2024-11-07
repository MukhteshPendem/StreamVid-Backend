package org.mukhtesh.streamerbackend.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Videos")
@NoArgsConstructor
@Data
public class Video {

    @Id
    private String title;

    private String description;

    private String url;

    private LocalDate uploadedDate;

    public Video(String title, String description, String url) {

        this.title = title;
        this.description = description;
        this.url = url;
        uploadedDate = LocalDate.now();
    }


}
