package org.example.Graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Floydwarshell {
    public static void main(String[] args) {
        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        v1.edges = List.of(new Edge(v3, -2));
        v2.edges = List.of(new Edge(v1, 4), new Edge(v3, 3));
        v3.edges = List.of(new Edge(v4, 2));
        v4.edges = List.of(new Edge(v2, -1));
        List<Vertex> graph = List.of(v1, v2, v3, v4);

                /*
                直接连通
                v1  v2  v3  v4
            v1  0   ∞   -2  ∞
            v2  4   0   3   ∞
            v3  ∞   ∞   0   2
            v4  ∞   -1  ∞   0

                k=0 借助v1到达其它顶点
                v1  v2  v3  v4
            v1  0   ∞   -2  ∞
            v2  4   0   2   ∞
            v3  ∞   ∞   0   2
            v4  ∞   -1  ∞   0

                k=1 借助v2到达其它顶点
                v1  v2  v3  v4
            v1  0   ∞   -2  ∞
            v2  4   0   2   ∞
            v3  ∞   ∞   0   2
            v4  3   -1  1   0

                k=2 借助v3到达其它顶点
                v1  v2  v3  v4
            v1  0   ∞   -2  0
            v2  4   0   2   4
            v3  ∞   ∞   0   2
            v4  3   -1  1   0

                k=3 借助v4到达其它顶点
                v1  v2  v3  v4
            v1  0   -1   -2  0
            v2  4   0   2   4
            v3  5   1   0   2
            v4  3   -1  1   0
         */
        
        floydwarshell(graph);
    }

    private static void floydwarshell(List<Vertex> graph) {
        int size = graph.size();
        int[][] dist=new int[size][size];
        Vertex[][] pre=new Vertex[size][size];
        //1、初始化
        for (int i = 0; i < size; i++) {
            Vertex v = graph.get(i);//获取顶点
            //收集每条边链接的顶点和边的权重
            Map<Vertex, Integer> map = v.edges.stream().collect(Collectors.toMap(edge -> edge.linked, edge -> edge.weight));
            for (int j = 0; j < size; j++) {
                Vertex u=graph.get(j);//获取顶点
                if(u==v){//如果行和列都是顶点本身
                    dist[i][j]=0;
                }else {
                    dist[i][j]=map.getOrDefault(u,Integer.MAX_VALUE);
                    pre[j][i]=map.get(u)!=null?v:null;
                }
            }
        }

        print(pre);
        // 2）看能否借路到达其它顶点
        /*
            v2->v1          v1->v?
            dist[1][0]   +   dist[0][0]
            dist[1][0]   +   dist[0][1]
            dist[1][0]   +   dist[0][2]
            dist[1][0]   +   dist[0][3]
         */
        for(int k=0;k<size;k++){
            for (int i = 0; i <size; i++) {
                for (int j = 0; j <size; j++) {
//                    dist[i][j]+dist[k][j]//i行的顶点，借助k顶点，到达j列顶点
//                    dist[i][j]             //i行顶点，直接到达j列顶点
                    if(dist[i][k]!=Integer.MAX_VALUE && dist[k][j]!=Integer.MAX_VALUE&&dist[i][k]+dist[k][j]<dist[i][j]){
                        dist[i][j]=dist[i][k]+dist[k][j];
                        pre[i][j]=pre[i][k];
                    }
                }
            }

        }
        print(dist);
//        print(pre);
    }

    private static void path(Vertex[][] pre, List<Vertex> graph,int i,int j) {
        LinkedList<String> stack = new LinkedList<>();
        System.out.println("["+graph.get(i).name+","+graph.get(j).name+"]");
        stack.push(graph.get(j).name);
        while (i!=j){
            Vertex p = pre[i][j];
            stack.push(p.name);
            j=graph.indexOf(p);
        }
        System.out.println(stack);
    }

    private static void print(int[][] pre) {
        System.out.println("------------------------------------");
        for(int[] row:pre){
            System.out.println(
                    Arrays.stream(row).boxed().map(x->x==Integer.MAX_VALUE?"∞":String.valueOf(x))
                            .map(s -> String.format("%2s",s))
                            .collect(Collectors.joining(",","[","]"))
            );
        }
    }

    private static void print(Vertex[][] pre) {
        System.out.println("------------------------------------");
        for(Vertex[] row:pre){
            System.out.println(Arrays.stream(row).map(v->v==null?"null":v.name)
                    .map(s->String.format("%5s",s))
                    .collect(Collectors.joining(",","[","]"))
            );
        }
    }
}
