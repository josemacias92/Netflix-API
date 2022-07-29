package com.netflix.apirest.repositorios;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.netflix.apirest.entidades.Title;

@RepositoryRestResource(collectionResourceRel = "title", path = "titles")
public interface TitleRepository extends JpaRepository<Title, String>{
	@Query("select t from Title t order by user_rating desc")
	List<Title> findAllOrderByUserRatingDesc(Pageable pageable);
	
	@Query("select t from Title t join t.category c where c.id = ?1 order by user_rating desc")
	List<Title> findBestRatedByCategory(int idCategory, Pageable pageable);
	
	@Query("select t from Title t where t.name like (%?1%)")
	List<Title> searchTitleByName(String name, Pageable pageable);

	@Query("select t from Title t where t.release_year = ?1")
	List<Title> searchTitleByReleaseYear(String year, Pageable pageable);

	@Query("select t from Title t where t.description like (%?1%)")
	List<Title> searchTitleByDescription(String description, Pageable pageable);

	@Query("select t from Title t join t.category c where c.id = ?1")
	List<Title> findByCategory(int idCategory, Pageable pageable);
	
	@Query("select t from Title t join t.actor a where a.id = ?1")
	List<Title> findByActor(int idActor, Pageable pageable);
	
	@Query("select t from Title t join t.director d where d.id = ?1")
	List<Title> findByDirector(int idDirector, Pageable pageable);
}
