package com.vpiremax.FlickHat.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_movie")
@EntityListeners(AuditingEntityListener.class)
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private String description;
    private LocalDateTime releaseDate;
    private Integer ageRating;
    private Integer duration;
//    private Language languages;

   @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL,  orphanRemoval = true)
   private List<MovieStream> movieStreams;
   private String coverImageUrl;
   private String trailerUrl;
}
