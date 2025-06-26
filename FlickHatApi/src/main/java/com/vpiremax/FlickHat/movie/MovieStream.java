package com.vpiremax.FlickHat.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_movie_stream")
@EntityListeners(AuditingEntityListener.class)
public class MovieStream {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
//    movieId

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_movie_id")
    private Movie movie;

    @Column(nullable = false)
    private Resolution resolution;

    @Column(nullable = false)
    private String videoUrl;

    @Column(nullable = false)
    private String language;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
