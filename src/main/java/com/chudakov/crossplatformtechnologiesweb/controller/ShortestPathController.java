package com.chudakov.crossplatformtechnologiesweb.controller;

import com.chudakov.crossplatformtechnologiesweb.model.ShortestPathRequestDto;
import com.chudakov.crossplatformtechnologiesweb.model.PathDto;
import com.chudakov.crossplatformtechnologiesweb.service.ShortestPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortestPathController {

    private final ShortestPathService shortestPathService;

    @RequestMapping(
            value = "/api/path",
            produces = {"application/json"},
            method = RequestMethod.POST
    )
    public ResponseEntity<PathDto> computeShortestPath(@RequestBody ShortestPathRequestDto shortestPathRequestDto) {
        return ResponseEntity.ok(shortestPathService.computeShortestPath(shortestPathRequestDto));
    }
}
