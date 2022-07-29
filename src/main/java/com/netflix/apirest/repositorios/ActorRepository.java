package com.netflix.apirest.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.netflix.apirest.entidades.Actor;

@RepositoryRestResource(collectionResourceRel = "actor", path = "actors")
public interface ActorRepository extends JpaRepository<Actor, Integer>{

}
