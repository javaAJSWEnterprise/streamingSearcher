package com.streamingsearcher.controller;

import com.streamingsearcher.models.Example;
import com.streamingsearcher.services.TitleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/title")
public class TitleController {

    @Autowired
    private TitleServiceImpl titleService;


   /* @GetMapping
    public ResponseEntity<?> findTitles(@RequestParam(name = "name")String name) {
        titleService.findTitles(name);

        return titleService.findTitles(name);
    }*/

    @GetMapping
    public ResponseEntity<Example> getInfoTitle(@RequestParam(name = "id")String id) {
        //titleService.getInfoTitle(id);
        return ResponseEntity.ok(titleService.getInfoTitle(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> test() {
        ResponseEntity<?> title = titleService.prueba();
        return titleService.prueba();
    }
}
