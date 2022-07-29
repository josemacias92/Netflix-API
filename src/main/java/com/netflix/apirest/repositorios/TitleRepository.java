package com.netflix.apirest.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netflix.apirest.entidades.Title;

public interface TitleRepository extends JpaRepository<Title, Integer>{

}
