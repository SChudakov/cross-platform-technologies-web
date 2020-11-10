package com.chudakov.crossplatformtechnologiesweb.service;

import com.chudakov.AStarShortestPath;
import com.chudakov.BFSShortestPath;
import com.chudakov.DijkstraShortestPath;
import com.chudakov.Graph;
import com.chudakov.ShortestPathAlgorithm;
import com.chudakov.crossplatformtechnologiesweb.model.PathDto;
import com.chudakov.crossplatformtechnologiesweb.model.ShortestPathRequestDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class ShortestPathService {

    private static Map<String, Function<Graph, ShortestPathAlgorithm>> nameToAlgorithmMap;

    public ShortestPathService() {
        nameToAlgorithmMap = new HashMap<>();
        nameToAlgorithmMap.put("BFS", BFSShortestPath::new);
        nameToAlgorithmMap.put("Dijkstra", DijkstraShortestPath::new);
        nameToAlgorithmMap.put("AStar", graph -> new AStarShortestPath(graph, (i1, i2) -> 0));
    }

    public PathDto computeShortestPath(ShortestPathRequestDto shortestPathRequestDto) {
        Graph graph = new Graph(shortestPathRequestDto.getGraph());
        int source = shortestPathRequestDto.getSource();
        int target = shortestPathRequestDto.getTarget();

        String algorithmName = shortestPathRequestDto.getAlgorithm();
        if (!nameToAlgorithmMap.containsKey(algorithmName)) {
            throw new IllegalArgumentException("Unknown algorithm name: " + algorithmName);
        }
        ShortestPathAlgorithm algorithm = nameToAlgorithmMap.get(algorithmName).apply(graph);
        List<Integer> path = algorithm.getShortestPath(source, target);

        return PathDto.builder().vertices(path).build();
    }
}
