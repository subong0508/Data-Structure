/*
* Name: 정재은
* Student ID#: 2017122063
*/

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
/*
* Do NOT use any external packages/classes.
* If you (un)intentionally use them we did not provide,
* you will get 0.
* Also do NOT use auto-import function on IDEs.
* If the import statements change, you will also get 0.
*/

public final class GraphSort implements IGraphSort {
    private int[][] adjMat = new int[0][0];
	private int n; // size of vertices
	private int m; // size of edges
	private int tmp;

    public GraphSort(String filename) {
        try{
        	File file = new File(filename);
        	FileReader filereader = new FileReader(file);
        	BufferedReader buffer = new BufferedReader(filereader);
        	String line = null;
        	boolean start = true;

        	int cnt = 0;
        	while(true){
                line = buffer.readLine();

                if(line==null){
                    break;
                }

        		String[] tmp = line.split(" ");
        		if(start){
        			n = Integer.parseInt(tmp[0]);
        			m = Integer.parseInt(tmp[1]);
        			adjMat = new int[n][n];
        			start = false;
        		} else{
        			int u = Integer.parseInt(tmp[0]);
        			int v = Integer.parseInt(tmp[1]);
        			adjMat[u][v] = 1;
        			cnt++;
        		}

        		if(cnt>=m){
        			break;
        		}
        	}

        	filereader.close();
        	buffer.close();
        } catch(FileNotFoundException e){
        	System.out.println(e.getMessage());
        } catch(IOException e){
        	System.out.println(e.getMessage());
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    private boolean isValid(){
        return n!=0;
    }

    @Override
    public boolean cycle() {
        if(!isValid()){
            return false;
        }

        boolean[] visited = new boolean[n];
        boolean[] stack = new boolean[n];
        for(int u=0;u<n;u++){
            if(cycleDFS(u, visited, stack)){
                return true;
            }
        }

        // no cycle found
        return false;
    }

    private boolean cycleDFS(int u, boolean[] visited, boolean[] stack){
		if(stack[u]){ 
            return true;
        } else if(visited[u]){
            return false;
        }

        visited[u] = true;
        stack[u] = true;
        for(int v=0;v<n;v++){
            if(adjMat[u][v]==1){
                if(cycleDFS(v, visited, stack)){
            	   return true;
                }
            }
        }
        stack[u] = false;

        return false;
    }

    @Override
    public int[] topologicalOrder() {
        if(!isValid()){
            return new int[0];
        }

        // init
        tmp = 0;
        int[] ordered = new int[n];
        boolean[] visited = new boolean[n];
        for(int u=0;u<n;u++){
        	if(!visited[u]){
            	topologicalDFS(u, visited, ordered);
            }
        }

        return ordered;
    }

    private void topologicalDFS(int u, boolean[] visited, int[] ordered){
        visited[u] = true;
        for(int v=0;v<n;v++){
            if(adjMat[u][v]==1 && !visited[v]){
                topologicalDFS(v, visited, ordered);
            }
        }

        tmp++;
        ordered[n-tmp] = u;
        System.out.printf("ordered[%d]=%d%n", n-tmp, u);
    }
}

