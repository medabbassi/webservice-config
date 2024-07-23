package com.example.movies.service;


import com.example.movies.dto.MovieDto;
import com.example.movies.entity.Movies;
import com.example.movies.repository.MovieRepository;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class MovieService {
    private  final KafkaTemplate<String, String> kafkaTemplate;

    MovieRepository movieRepository;




 public List<Movies> getAllMovies(){
     List<Movies> movies = movieRepository.findAll();
     log.info("Movies found: {}", movies);
     return movies;
 }
    public List<Movies> findByTitle(String title) {
        List<Movies> movies = movieRepository.findByTitle(title);
        if (movies.isEmpty()) {
            log.info("No movies found with title: {}", title);
            return null;
        }
        log.info("Movies found: {}", movies);
        return movies;
    }

 public void saveMovie(Movies movie){
     Movies savedMovie = movieRepository.save(movie);


     log.info("Movie saved: {}", movie);
     MovieDto movieDto = MovieDto.builder()
             .id(savedMovie.getId())
             .title(savedMovie.getTitle())
             .genre(savedMovie.getGenre())
             .year(savedMovie.getYear())
             .rating(savedMovie.getRating())
             .duration(savedMovie.getDuration())
             .build();

     Gson gson = new Gson();
        String movieJson = gson.toJson(movieDto);
        kafkaTemplate.send("movies-topic", movieJson);

 }
}
