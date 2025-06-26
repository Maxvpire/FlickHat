package com.vpiremax.FlickHat.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findByTitleLike(String title);

//    @Query("SELECT u FROM User u WHERE u.username LIKE %:keyword%")
//    List<User> searchByUsername(@Param("keyword") String keyword);

}
