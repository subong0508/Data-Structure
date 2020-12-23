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

public final class Graph implements IGraph {
	private int[][] adjMat = new int[0][0];
	private int n; // size of vertices
	private int m; // size of edges

    public Graph(String filename) {
        /*
         * Constructor
         * This function is an initializer for this class.
         */
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

    @Override
    public void insertVertex() {
		int[][] tmp = new int[n+1][n+1];
    	for(int u=0;u<n;u++){
    		for(int v=0;v<n;v++){
    			tmp[u][v] = adjMat[u][v];
    		}
    	}
    	
    	adjMat = tmp;
    	n++;
    }

    @Override
    public void deleteVertex(int n) {
    	if(0<=n && n<this.n){
    		delete(n);
    	} else{
    		System.out.println("Nothing is done.");
    	}
    }

    private void delete(int u){
    	int deleteNum = 0;
    	for(int v=0;v<n;v++){
            // way from u to v
    		if(adjMat[u][v]==1){
    			deleteNum++;
    		}

            // way from v to u
            if(adjMat[v][u]==1){
                deleteNum++;
            }
    	}

    	int[][] tmp = new int[n-1][n-1];
    	for(int i=0;i<n;i++){
    		for(int j=0;j<n;j++){
    			if(i==u || j==u){
    				continue;
    			} else if(i<u && j<u){
    				tmp[i][j] = adjMat[i][j];
    			} else if(i<u && j>u){
    				tmp[i][j-1] = adjMat[i][j];
    			} else if(i>u && j<u){
    				tmp[i-1][j] = adjMat[i][j];
    			} else{ // i>u && j>u
    				tmp[i-1][j-1] = adjMat[i][j];
    			}
    		}
    	}

    	adjMat = tmp;
    	n--;
    	m -= deleteNum;
    }

    @Override
    public void insertEdge(int u, int v) {
    	if((0<=u && u<n) && (0<=v && v<n) && adjMat[u][v]==0){
    		adjMat[u][v] = 1;
    		m++;
    	} else{
    		System.out.println("Nothing is done.");
    	}
    }

    @Override
    public void deleteEdge(int u, int v) {
    	if((0<=u && u<n) && (0<=v && v<n) && adjMat[u][v]==1){
    		adjMat[u][v] = 0;
    		m--;
    	} else{
    		System.out.println("Nothing is done.");
    	}
    }

    @Override
    public int[][] matrix() {
        return adjMat;
    }

    @Override
    public int size() {
        return n;
    }
}