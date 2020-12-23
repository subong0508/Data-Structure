import java.util.*;

/*
* Since this file is not a part of submission,
* you can use any class/packages.
*/

public class Main
{
    public static void main(String[] args){
    	IMST G = new MST("./src/test/wgraph1.txt");
    	System.out.println(Arrays.toString(G.shortestPath(2, 3)));
    	System.out.println(G.findMST());
     //    for(int i=1;i<=6;i++){
	   	//     System.out.println("i: " + i);
	   	//     String filename = "./src/test/graph" + i + ".txt";
	    //     IGraphSort G = new GraphSort(filename);
	    //     System.out.println("cycle : " + G.cycle());
	    //     System.out.println(Arrays.toString(G.topologicalOrder()));
	    //     System.out.println();
	    // }
    }
}
