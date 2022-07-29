package com.netflix.apirest.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.netflix.apirest.entidades.Director;

@RepositoryRestResource(collectionResourceRel = "director", path = "directors")
public interface DirectorRepository extends JpaRepository<Director, Integer>{

}
