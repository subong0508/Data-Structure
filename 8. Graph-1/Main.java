import java.util.*;

/*
* Since this file is not a part of submission,
* you can use any class/packages.
*/

public class Main
{
    public static void main(String[] args)
    {
        // IGraph G = new Graph("./src/test/graph1.txt");
        // printMatrix(G.matrix());
        // System.out.println("size: " + G.size());
        // G.insertVertex();
        // G.insertVertex();
        // G.insertEdge(0, 1);
        // printMatrix(G.matrix());
        // System.out.println("size: " + G.size());
        // G.deleteEdge(0, 2);
        // G.insertEdge(100, 100);
        // System.out.println("size: " + G.size());

        ISCC G = new SCC("./src/test/graph3.txt");
        System.out.println(G.findSCC());
        System.out.println(G.connectivity());
    }

    public static void printMatrix(int[][] mat){
        System.out.println("Printing Adjacency Matrix");
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat.length;j++){
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }
}
