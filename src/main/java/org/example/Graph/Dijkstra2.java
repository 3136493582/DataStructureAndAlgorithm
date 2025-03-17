package org.example.Graph;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


/**
 * 1. 创建一个优先级队列，放入所有顶点（队列大小会达到边的数量）
 * 2. 为每个顶点分配一个临时距离值
 *    * 对于我们的初始顶点，将其设置为零
 *    * 对于所有其他顶点，将其设置为无穷大。
 * 3. 每次选择最小临时距离的未访问顶点，作为新的当前顶点
 * 4. 对于当前顶点，遍历其所有未访问的邻居，并更新它们的临时距离为更小，若距离更新需加入队列
 *    * 例如，1->6 的距离是 14，而1->3->6 的距离是11。这时将距离更新为 11
 *    * 否则，将保留上次距离值
 * 5. 当前顶点的邻居处理完成后，把它从队列中删除
 */
public class Dijkstra2 {
    public static void main(String[] args) {
//        正常情况
//        Vertex v1 = new Vertex("v1");
//        Vertex v2 = new Vertex("v2");
//        Vertex v3 = new Vertex("v3");
//        Vertex v4 = new Vertex("v4");
//        Vertex v5 = new Vertex("v5");
//        Vertex v6 = new Vertex("v6");
//
//        v1.edges = List.of(new Edge(v3, 9), new Edge(v2, 7), new Edge(v6, 14));
//        v2.edges = List.of(new Edge(v4, 15));
//        v3.edges = List.of(new Edge(v4, 11), new Edge(v6, 2));
//        v4.edges = List.of(new Edge(v5, 6));
//        v5.edges = List.of();
//        v6.edges = List.of(new Edge(v5, 9));
//
//        List<Vertex> graph = List.of(v1, v2, v3, v4, v5, v6);

        // 负边情况
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v2, 2), new Edge(v3, 1));
        v2.edges = List.of(new Edge(v3, -2));
        v3.edges = List.of(new Edge(v4, 1));
        v4.edges = List.of();
        List<Vertex> graph = List.of(v1, v2, v3, v4);
        dijkstra2(graph,v1);
    }

    private static void dijkstra2(List<Vertex> graph, Vertex source) {
        //以顶点的dist距离为比较优先级创建优先级队列
        PriorityQueue<Vertex> queue= new PriorityQueue<>(Comparator.comparingInt(v->v.dist));
        source.dist=0;
        //java中的优先级队列默认为小项堆，重新添加一遍会=建堆排序，直接添加不会
        for(Vertex v: graph){
            queue.offer(v);
        }

        while(!queue.isEmpty()){
            System.out.println(queue);
            //1、选取当前顶点
            Vertex curr = queue.peek();
            //2、更新当前顶点邻居距离
            if(!curr.visited){
                updateNeighboursDist2(curr,queue);
                curr.visited=true;
            }
            //3、移除当前点
            queue.poll();
        }

        for(Vertex v: graph){
            System.out.println(v.name+":"+v.dist);
        }
    }

    private static void updateNeighboursDist2(Vertex curr, PriorityQueue<Vertex> queue) {
        for(Edge e: curr.edges){
            Vertex linked=e.linked;
            if(!linked.visited){
                int dist=curr.dist+e.weight;
                if(dist<linked.dist){
                    linked.pre=curr;
                    linked.dist=dist;
                    queue.offer(linked);
                }
            }
        }
    }
}
