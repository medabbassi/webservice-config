package com.example.movies.repository;

import com.example.movies.entity.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movies, Long> {

    List<Movies> findByTitle(String title);

}
