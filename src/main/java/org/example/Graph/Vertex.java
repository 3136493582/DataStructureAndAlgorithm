package org.example.Graph;

import java.util.List;

/**
 * 图的顶点类
 */
public class Vertex {
    String name;
    List<Edge> edges;

    //拓扑排序相关
    int inDegree;//入度 度是指与该顶点相邻的边的数量
    int status;//0 未访问，1 访问中，2，访问过

    //dfs ,bfs相关
    boolean visited;

    //求解最短距离相关
    private static  final  int INF=Integer.MAX_VALUE;
    int dist=INF;
    Vertex pre=null;

    public Vertex(String name){
        this.name=name;
    }
}
