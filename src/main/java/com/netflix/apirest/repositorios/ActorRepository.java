package com.netflix.apirest.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.apirest.entidades.Actor;

public interface ActorRepository extends JpaRepository<Actor, Integer>{

}
