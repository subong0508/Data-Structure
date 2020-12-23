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

public final class MST implements IMST {
	private int[][] adjMat = new int[0][0];
    private int n;
    private int m;
    static final int MINUS_INF = Integer.MIN_VALUE;
    static final int PLUS_INF = Integer.MAX_VALUE;

    public MST(String filename) {
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
        			int w = Integer.parseInt(tmp[2]);
        			adjMat[u][v] = w;
        			adjMat[v][u] = w;
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
    public int[] shortestPath(int u, int v) {
        if(u==v){
            int[] path = {u};
            return path;
        } else if((u<0) || (u>=n) || (v<0) || (v>=n)){
            return new int[0];
        }

    	int[] distances = new int[n];
    	distances[u] = 0;
    	boolean[] visited = new boolean[n];
    	visited[u] = true;
    	// for tracking
    	int[] prev = new int[n];
    	for(int i=0;i<n;i++){
    		int w = adjMat[u][i];
    		if(w!=0){
    			distances[i] = w;
    			prev[i] = u;
    		} else{
    			distances[i] = PLUS_INF;
    		}
    	}
    	// no tracking
    	prev[u] = -1;

    	for(int i=1;i<n;i++){
    		int idx = -1;
    		int min = PLUS_INF;

    		for(int j=0;j<n;j++){
    			if(!visited[j] && distances[j]!=PLUS_INF){
                    if(distances[j]<min){
        				idx = j;
        				min = distances[j];
                    }
    			}
    		}

    		// all visited.. should never occur (already visited at the previous step)
    		if(idx==-1){
    			continue;
    		}

    		visited[idx] = true;

    		for(int j=0;j<n;j++){
    			int w = adjMat[idx][j];
    			if(!visited[j] && w!=0 && w!=PLUS_INF){
    				if((distances[idx]+w)<distances[j]){
	    				prev[j] = idx;
	    				distances[j] = distances[idx]+w;
	    			}
    			}
    		}
    	}

    	int[] tmp = new int[n];
    	int cnt = 0;
    	// init
    	for(int i=0;i<n;i++){
    		tmp[i] = -1;
    	}

    	int idx = prev[v];
    	while(idx!=u){
    		tmp[cnt++] = idx;
    		idx = prev[idx];
    	}

    	int[] path = new int[cnt+2];
        path[0] = u;
    	for(int i=0;i<cnt;i++){
    		path[cnt-i] = tmp[i];
    	}
    	path[cnt+1] = v;

    	return path;
    }


    @Override
    public int findMST() {
        int cnt = 0;
        int sum = 0;
    	Node[] clusters = new Node[n];
    	// define cluster
    	for(int i=0;i<n;i++){
    		clusters[i] = new Node(i);
    	}
    	
        Edge[] queue = new Edge[m];
        for(int u=0;u<n;u++){
            for(int v=0;v<u;v++){
                int k = adjMat[u][v];
                if(k!=0){
                    queue[cnt++] = new Edge(k, u, v);
                }
            }
        }

        // selection sort
        for(int i=0;i<m-1;i++){
            int min = i;
            for(int j=i+1;j<m;j++){
                if(queue[j].key<queue[min].key){
                    min = j;
                }

                Edge tmp = queue[i];
                queue[i] = queue[min];
                queue[min] = tmp;
            }
        }

        cnt = 0;
    	while(cnt<(n-1)){
            Edge e = queue[cnt];
            int u = e.u;
            int v = e.v;

            int uCluster = getCluster(u, clusters);
            int vCluster = getCluster(v, clusters);

            if(uCluster!=vCluster){
        		sum += e.key;
        		cnt++;
        		merge(uCluster, vCluster, clusters);
            }
    	}

        return sum;
    }

    private int getCluster(int u, Node[] clusters){
    	for(int i=0;i<n;i++){
    		Node node = clusters[i];
    		while(node!=null && node.data!=u){
    			node = node.next;
    		}

    		if(node!=null && node.data==u){
    			return i;
    		}
    	}

    	return -1;
    }

    private void merge(int u, int v, Node[] clusters){
    	Node node = clusters[u];
    	while(node.next!=null){
    		node = node.next;
    	}

    	node.next = clusters[v];
    	clusters[v] = null;
    }
}

class Node {
	int data;
	Node next;

	public Node(int data, Node next){
		this.data = data;
		this.next = next;
	}

	public Node(int data){
		this(data, null);
	}
}


class Edge {
    int key;
    int u, v;

    public Edge(int key, int u, int v){
        this.key = key;
        this.u = u;
        this.v = v;
    }
}