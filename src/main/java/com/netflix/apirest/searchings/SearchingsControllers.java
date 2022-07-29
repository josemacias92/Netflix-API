package com.netflix.apirest.searchings;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.apirest.entidades.Title;
import com.netflix.apirest.servicios.TitleService;

@RestController
@RequestMapping("/api/search")
public class SearchingsControllers {

    @Autowired
	TitleService titleService;

    private Pageable topLimit = null;

    @GetMapping("name")
    public ResponseEntity<List<Title>> searchByName(@RequestParam String name, @RequestParam(required = false) Integer top){

        checkTopValueIsNull(top);

        List<Title> titles = titleService.searchTitleByName(name, topLimit);

        if(titles.isEmpty()) return ResponseEntity.noContent().build();

        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    @GetMapping("year")
    public ResponseEntity<List<Title>> searchByReleaseYear(@RequestParam String year, @RequestParam(required = false) Integer top){

        checkTopValueIsNull(top);

        List<Title> titles = titleService.searchTitleByReleaseYear(year, topLimit);

        if(titles.isEmpty()) return ResponseEntity.noContent().build();

        return new ResponseEntity<>(titles, HttpStatus.OK);
    }

    @GetMapping("desc")
    public ResponseEntity<List<Title>> searchByDescription(@RequestParam String desc, @RequestParam(required = false) Integer top){

        checkTopValueIsNull(top);

        List<Title> titles = titleService.searchTitleByDescription(desc, topLimit);

        if(titles.isEmpty()) return ResponseEntity.noContent().build();

        return new ResponseEntity<>(titles, HttpStatus.OK);
    }


    private void checkTopValueIsNull(Integer top){
        if(top!=null) topLimit = PageRequest.of(0, top);
    }
}
