package com.netflix.apirest.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.apirest.entidades.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
