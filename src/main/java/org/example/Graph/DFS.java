package org.example.Graph;

import java.util.List;
import java.util.Stack;

/**
 * 深度优先搜索
 */
public class DFS {
    //递归
    public static void dfs1(Vertex vertex) {
        vertex.visited=true;
        System.out.println(vertex.name);
        for(Edge edge:vertex.edges){
            if(!edge.linked.visited){
                dfs1(edge.linked);
            }
        }
    }

    //非递归
    public static void dfs2(Vertex vertex) {
        Stack<Vertex> vertices = new Stack<>();
        vertices.push(vertex);
        while(!vertices.isEmpty()){
            Vertex popped = vertices.pop();//pop移除栈顶的原素并返回
            System.out.println(popped.name);
            popped.visited=true;
            for(Edge edge:popped.edges){
                if(!edge.linked.visited){
                    vertices.push(edge.linked);
                }
            }
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

//        dfs1(v1);
        dfs2(v1);
    }
}
