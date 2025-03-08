package org.example.Graph;

/**
 * 图的边 类
 */
public class Edge {
    Vertex linked;
    int weight;//权重 代表从源顶点到目标顶点的距离、费用、时间或其他度量

    public Edge(Vertex linked){
        this(linked,1);
    }

    public Edge(Vertex linked, int weight){
        this.linked = linked;
        this.weight = weight;
    }
}
