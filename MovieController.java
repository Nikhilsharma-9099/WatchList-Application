package com.example.nikhilsharma.watchlist.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.nikhilsharma.watchlist.entity.Movie;
import com.example.nikhilsharma.watchlist.service.DatabaseService;


@RestController
public class MovieController {
	
	@Autowired
	DatabaseService databaseService;
	@GetMapping("/watchlistItemForm")
	public ModelAndView showwatchListForm(@RequestParam(required=false)Integer id) {
		
		System.out.println(id);
		String viewName = "watchlistItemForm";
		
		Map<String, Object> model = new HashMap<>();
		
		if(id==null) {
			model.put("watchlistItem", new Movie());			
		} else {
			model.put("watchlistItem", databaseService.getmoviebyId(id));
		}
		
//		Movie dummyMovie = new Movie();
//		dummyMovie.setTitle("dummy");
//		dummyMovie.setRating(0);
//		dummyMovie.setPriority("low");
//		dummyMovie.setComment("Average");
//		model.put("watchlistItem", dummyMovie);
		
		
		return new ModelAndView(viewName, model);
		
	}
	
	@PostMapping("/watchlistItemForm")
	public ModelAndView submitWatchListForm(@Valid @ModelAttribute("watchlistItem") Movie movie, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			
			System.out.println(bindingResult.hasErrors());
			return new ModelAndView("watchlistItemForm");
		}
		
		databaseService.create(movie);
		
		Integer id = movie.getId();
		if(id==null) {
			databaseService.create(movie);
		}else {
			databaseService.update(movie, id);
		}
		
		RedirectView rd = new RedirectView();
		rd.setUrl("/watchlist");
		return new ModelAndView(rd);
		
	}
	
	
	@GetMapping("/watchlist")
	public ModelAndView getWatchList() {
		
		String viewName = "watchlist";
		Map<String, Object> model = new HashMap<>();
		List<Movie> movieList = databaseService.getAllMovies();
		model.put("watchlistrows", movieList);
		model.put("numberOfMovies", movieList.size());
		return new ModelAndView(viewName,model);
	}

}
