package org.example.Graph;

import java.util.LinkedList;
import java.util.List;

/**
 * 广度优先
 */
public class BFS {
    //只要将DFS非递归方法中的栈替换为队列即是广度优先
    public static void fds(Vertex vertex){
        LinkedList<Vertex> vertices = new LinkedList<>();
        vertex.visited=true;
        vertices.offer(vertex);
        while(!vertices.isEmpty()){
            Vertex poll = vertices.poll();
            System.out.println(poll.name);
            for(Edge edge : poll.edges){
                if(!edge.linked.visited){
                    edge.linked.visited=true;
                    vertices.offer(edge.linked);
                }
            }
        }
    }

    public static void fds2(Vertex vertex){
        vertex.visited=true;
        System.out.println(vertex.name);
        for(Edge edge : vertex.edges){

        }
    }

    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");
        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");

        v1.edges= List.of(new Edge(v3), new Edge(v2), new Edge(v6));
        v2.edges= List.of(new Edge(v4));
        v3.edges= List.of(new Edge(v4), new Edge(v6));
        v4.edges= List.of(new Edge(v5));
        v5.edges= List.of();
        v6.edges= List.of(new Edge(v5));

        fds(v1);
    }
}
