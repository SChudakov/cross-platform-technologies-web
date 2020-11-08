package com.chudakov.crossplatformtechnologiesweb.service;

import com.chudakov.BFSShortestPath;
import com.chudakov.Graph;
import com.chudakov.crossplatformtechnologiesweb.model.PathDto;
import com.chudakov.crossplatformtechnologiesweb.model.ShortestPathRequestDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShortestPathService {

    public PathDto computeShortestPath(ShortestPathRequestDto shortestPathRequestDto) {
        Graph graph = new Graph(shortestPathRequestDto.getGraph());
        int source = shortestPathRequestDto.getSource();
        int target = shortestPathRequestDto.getTarget();

        BFSShortestPath bfsShortestPath = new BFSShortestPath(graph);
        List<Integer> path = bfsShortestPath.getShortestPath(source, target);

        return PathDto.builder().vertices(path).build();
    }
}
