package com.example.nikhilsharma.watchlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.nikhilsharma.watchlist.entity.Movie;
import com.example.nikhilsharma.watchlist.repository.MovieRepo;

@Service
public class DatabaseService {
	
	@Autowired
	MovieRepo movierepo;
	
	@Autowired
	RatingService ratingService;
	
	public void create(Movie movie) {
		
		String rating = ratingService.getMovieRating(movie.getTitle());
		if(rating!=null) {
			movie.setRating(Float.parseFloat(rating));
		}
		movierepo.save(movie);
	}
	
	public List<Movie> getAllMovies() {
		
		return movierepo.findAll();
		
	}
	
	public Movie getmoviebyId(Integer id) {
		
		return movierepo.findById(id).get();
		
	}

	public void update(Movie movie, Integer id) {
		// TODO Auto-generated method stub
		Movie toBeUpdated = getmoviebyId(id);
		toBeUpdated.setTitle(movie.getTitle());
		toBeUpdated.setRating(movie.getRating());
		toBeUpdated.setComment(movie.getComment());
		toBeUpdated.setPriority(movie.getPriority());
		
		movierepo.save(toBeUpdated);
	}

}
