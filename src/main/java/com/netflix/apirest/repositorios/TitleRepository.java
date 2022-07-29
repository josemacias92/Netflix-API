package com.netflix.apirest.repositorios;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.netflix.apirest.entidades.Title;

public interface TitleRepository extends JpaRepository<Title, Integer>{
	@Query("select t from Title t order by user_rating desc")
	List<Title> findAllOrderByUserRatingDesc(Pageable pageable);
	
	@Query("select t from Title t join t.category tc where tc.id = ?1 order by user_rating desc")
	List<Title> findBestRatedByCategory(int category, Pageable pageable);
	
}
