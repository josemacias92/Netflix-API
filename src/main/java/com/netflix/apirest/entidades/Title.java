package com.netflix.apirest.entidades;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Title {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String name;
	private String date_added;
	private String release_year;
	private String rating;
	private String duration;
	private String description;
	private double user_rating;
	
	@ManyToMany
	Set<Actor> actor;
	
	@ManyToMany
	Set<Director> director;
	
	@ManyToMany
	Set<Category> category;

	public Title() {
		super();
	}

	public Title(String id, String name, String date_added, String release_year, String rating, String duration,
			String description, double user_rating, Set<Actor> actorId, Set<Director> directorId,
			Set<Category> categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.date_added = date_added;
		this.release_year = release_year;
		this.rating = rating;
		this.duration = duration;
		this.description = description;
		this.user_rating = user_rating;
		this.actor = actorId;
		this.director = directorId;
		this.category = categoryId;
	}



	public Set<Actor> getActorId() {
		return actor;
	}

	public void setActorId(Set<Actor> actorId) {
		this.actor = actorId;
	}



	public Set<Director> getDirectorId() {
		return director;
	}



	public void setDirectorId(Set<Director> directorId) {
		this.director = directorId;
	}



	public Set<Category> getCategoryId() {
		return category;
	}



	public void setCategoryId(Set<Category> categoryId) {
		this.category = categoryId;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate_added() {
		return date_added;
	}

	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}

	public String getRelease_year() {
		return release_year;
	}

	public void setRelease_year(String release_year) {
		this.release_year = release_year;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUser_rating() {
		return user_rating;
	}

	public void setUser_rating(double user_rating) {
		this.user_rating = user_rating;
	}

	@Override
	public String toString() {
		return "Title [id=" + id + ", name=" + name + ", date_added=" + date_added + ", release_year=" + release_year
				+ ", rating=" + rating + ", duration=" + duration + ", description=" + description + ", user_rating="
				+ user_rating + ", actorId=" + actor + ", directorId=" + director + ", categoryId=" + category
				+ "]";
	}
	
	
}