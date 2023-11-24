package com.streamingsearcher.controller;

import com.streamingsearcher.entities.Favorites;
import com.streamingsearcher.models.MediaContent;
import com.streamingsearcher.models.MediaContentPlatform;
import com.streamingsearcher.models.MultimediaPlatforms.Content;
import com.streamingsearcher.models.MultimediaPlatforms.MultimediaImage;
import com.streamingsearcher.models.MultimediaPlatforms.ResultMultimedia;
import com.streamingsearcher.models.MultimediaPlatforms.StreamingInfo;
import com.streamingsearcher.models.Platform;
import com.streamingsearcher.security.JwtServices;
import com.streamingsearcher.services.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;


import java.util.*;


@RestController
@RequestMapping("/v1/title")
public class TitleController {

    @Autowired
    private JwtServices jwtServices;

    @Autowired
    private ManagerServiceImpl managerService;

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getInfoById(@PathVariable String id) {
        try {
            ResultMultimedia response = managerService.getMediaContentById(id);

            if(response == null){
                Map<String, String> responseMessage = new HashMap<>();
                responseMessage.put("message", "media content not found");
                return ResponseEntity.badRequest().body(responseMessage);
            }else {
                return ResponseEntity.ok(response);
            }

        } catch (DataAccessException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    @GetMapping("/{title}")
    public ResponseEntity<?> getInfoByTitle(@PathVariable String title,  @RequestHeader(name = "Authorization", required = false, defaultValue = "") String authorizationHeader) {
        try {
            String userId = "";
            if (!authorizationHeader.isEmpty()) {

                String token = authorizationHeader.substring(7);
                Claims claims = jwtServices.extractAllClaims(token);
                userId = (String) claims.get("id");
            }

            List<ResultMultimedia> response = managerService.getMediaContentsByTitle(title, userId);
            return ResponseEntity.ok(response);

        }catch (JwtException e){
            List<ResultMultimedia> response = managerService.getMediaContentsByTitle(title, "");
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
                Map<String, String> response = new HashMap<>();
                response.put("message", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
