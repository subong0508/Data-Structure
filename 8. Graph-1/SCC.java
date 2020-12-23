/*
* Name: 정재은
* Student ID#: 2017122063
*/

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
/*
* Do NOT use any external packages/classes.
* If you (un)intentionally use them we did not provide,
* you will get 0.
* Also do NOT use auto-import function on IDEs.
* If the import statements change, you will also get 0.
*/

public final class SCC implements ISCC {
    private Graph g;
    private int[][] adjMat;
    private int n;

    public SCC(String filename) {
        g = new Graph(filename);
        adjMat = g.matrix();
        n = g.size();
    }

    private boolean isValid(){
        return n!=0;
    }

    @Override
    public boolean path(int u, int v) {
        if(!isValid())
            return false;

        boolean[] visited = new boolean[n];
        // DFS
        if((0<=u && u<n) && (0<=v && v<n)){
            return pathDFS(u, v, visited);
        } 

        return false;
    }

    private boolean pathDFS(int u, int v, boolean[] visited){
        visited[u] = true;
        if(adjMat[u][v]==1){
            return true;
        }

        for(int i=0;i<n;i++){
            if(!visited[i] && adjMat[u][i]==1 && pathDFS(i, v, visited)){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean connectivity() {
        if(!isValid()){
            return false;
        }

        boolean[] visited = new boolean[n];
        visit(0, visited);
        for(int i=0;i<n;i++){
            if(!visited[i]){
                return false;
            }
        }

        // init
        visited = new boolean[n];

        visitT(0, visited);
        for(int i=0;i<n;i++){
            if(!visited[i]){
                return false;
            }
        }

        // no mismatch found
        return true;
    }

    private void visit(int u, boolean[] visited){
        // mark
        visited[u] = true;

        // DFS
        for(int v=0;v<n;v++){
            if(!visited[v] && adjMat[u][v]==1){
                visit(v, visited);
            }
        }
    }

    private void visitT(int u, boolean[] visited){
        // mark
        visited[u] = true;

        // DFS
        for(int v=0;v<n;v++){
            if(!visited[v] && adjMat[v][u]==1){
                visitT(v, visited);
            }
        }
    }

    @Override
    public List<List<Integer>> findSCC() {
        if(!isValid()){
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> lst = new ArrayList<>();
        ArrayList<Integer> stack = new ArrayList<>();

        // DFS... 
        boolean[] visited = new boolean[n];
        for(int u=0;u<n;u++){
            if(!visited[u]){
                DFS(u, stack, visited);
            }
        }

        // init
        visited = new boolean[n];
        while(!stack.isEmpty()){
            int u = stack.get(stack.size()-1);
            ArrayList<Integer> scc = new ArrayList<>();
            reverseDFS(u, stack, scc, visited);
            lst.add(scc);
        }

        // internal sort
        for(int i=0;i<lst.size();i++){
            List<Integer> tmp = sort(lst.get(i));
            lst.set(i, tmp);
        }

        // external sort: bucket sort
        List<List<Integer>> sorted = new ArrayList<>();
        int arr[] = new int[n];
        // init
        for(int i=0;i<n;i++){
            arr[i] = -1;
        }

        for(int i=0;i<lst.size();i++){
            int tmp = lst.get(i).get(0);
            arr[tmp] = i;
        }

        for(int i=0;i<n;i++){
            if(arr[i]!=-1){
                sorted.add(lst.get(arr[i]));
            }
        }

        return sorted;
    }

    private void DFS(int u, ArrayList<Integer> stack, boolean[] visited){
        // no way to u
        visited[u] = true;

        for(int v=0;v<n;v++){
            if(!visited[v] && adjMat[u][v]==1){
                DFS(v, stack, visited);
                break;
            }
        }

        stack.add(u);
    }

    private void reverseDFS(int u, ArrayList<Integer> stack, ArrayList<Integer> scc,
        boolean[] visited){
        // no way to u
        visited[u] = true;

        for(int v=0;v<n;v++){
            if(!visited[v] && adjMat[v][u]==1){
                reverseDFS(v, stack, scc, visited);
                break;
            }
        }

        scc.add(stack.remove(stack.size()-1));
    }

    private List<Integer> sort(List<Integer> scc){
        // bucket sort
        ArrayList<Integer> sorted = new ArrayList<>();
        int[] arr = new int[n];
        // init: indicate empty
        for(int i=0;i<n;i++){
            arr[i] = -1;
        }

        for(int i=0;i<scc.size();i++){
            int tmp = scc.get(i);
            arr[tmp] = tmp;
        }

        for(int i=0;i<n;i++){
            if(arr[i]!=-1){
                sorted.add(arr[i]);
            }
        }

        return sorted;
    }
}
