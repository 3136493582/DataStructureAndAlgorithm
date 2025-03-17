package org.example.Graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 最短路径法
 */
public class Dijkstra {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges = List.of(new Edge(v3, 9), new Edge(v2, 7), new Edge(v6, 14));
        v2.edges = List.of(new Edge(v4, 15));
        v3.edges = List.of(new Edge(v4, 11), new Edge(v6, 2));
        v4.edges = List.of(new Edge(v5, 6));
        v5.edges = List.of();
        v6.edges = List.of(new Edge(v5, 9));

        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6);

        dijkstra(graph,v1);
    }

    private static void dijkstra(List<Vertex> graph, Vertex source) {
        ArrayList<Vertex> list = new ArrayList<>(graph);

        source.dist=0;

        while(!list.isEmpty()) {
            //选取当前顶点
            Vertex current = chooseCurrentVertex(list);
            //更新当前顶点邻居距离
            updateNeighboursDist(current,list);
            //移除当前顶点
            list.remove(current);
        }

        for(Vertex v : graph) {
            System.out.println(v.name+":"+v.dist);
        }
    }

    private static Vertex chooseCurrentVertex(ArrayList<Vertex> list) {
        Vertex min=list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if(list.get(i).dist < min.dist) {
                min = list.get(i);
            }
        }
        return min;
    }
    private static void updateNeighboursDist(Vertex current, ArrayList<Vertex> list) {
        for(Edge edge : current.edges) {
            Vertex v=edge.linked;
            if(list.contains(v)) {
                int dist=current.dist+edge.weight;
                if(dist<v.dist) {
                    v.dist=dist;
                }
            }
        }
    }
}
