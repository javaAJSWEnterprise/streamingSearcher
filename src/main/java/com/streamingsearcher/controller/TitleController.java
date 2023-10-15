package com.streamingsearcher.controller;

import com.streamingsearcher.models.Example;
import com.streamingsearcher.services.TitleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/title")
public class TitleController {

    @Autowired
    private TitleServiceImpl titleService;


   /* @GetMapping
    public ResponseEntity<?> findTitles(@RequestParam(name = "name")String name) {
        titleService.findTitles(name);

        return titleService.findTitles(name);
    }*/

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getInfoById( @PathVariable String id) {
        //titleService.getInfoTitle(id);
        return ResponseEntity.ok(titleService.getMultimediaById(id).getResult());
    }


    @GetMapping("/{title}")
    public ResponseEntity<?> getInfoByTitle(@PathVariable String title) {
        //titleService.getInfoTitle(id);
        return ResponseEntity.ok(titleService.findTitles(title).getResults());
    }

}
