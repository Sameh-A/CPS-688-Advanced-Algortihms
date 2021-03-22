
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


//////////////////////////////////////////////////
/*
Lab 2 - CPS688 - W21
*/
//////////////////////////////////////////////////


public class lab2{
	public static void main(String[] args) {
	
		// Input for Q1 is an undirected graph "graph1".
		// Input graph has "n1" vertices.
		// The set of vertices = {0,1,...,(n1-1)}
		System.out.print("--- Q1 -------\n");
		int n1=9; // set of vertices = {0,1,2,3,4,5,6,7,8}
		int[][] graph1 = {{0,0},{0,1},{1,2},{1,2},{2,5},{2,5},{2,5},{3,3},{4,5},{5,6},{5,7},{6,7}}; 
		question1(n1,graph1);
		
		// Input for Q2 is an acyclic directed graph "graph2".
		// Input graph has "n2" vertices.
	    // The set of vertices = {0,1,...,(n2-1)}
		// Your search should start at vertex "start_node1".
		System.out.print("--- Q2 -------\n");
		int n2=8; 
		int start_node1 =0; // set of vertices = {0,1,2,3,4,5,6,7}
		int[][] graph2 = {{2,3},{3,6},{4,0},{4,7},{5,0},{5,2},{6,1},{7,1}}; 
		question2(start_node1,n2,graph2);
		
		// Input for Q3 is an undirected graph "graph3".
		// Input graph has "n3" vertices.
	    // The set of vertices = {0,1,...,(n3-1)}
		// Your search should start at vertex "start_node2".
		System.out.print("--- Q3 -------\n");
		int n3=9; 
		int start_node2=0; // set of vertices = {0,1,2,3,4,5,6,7,8}
		int[][] graph3 = {{0,1},{0,5},{0,7},{1,2},{2,3},{2,7},{2,8},{3,4},{4,6},{4,8},{5,6},{6,8}}; 
		question3(start_node2,n3,graph3);
		
	}
	
//////////////////////////////////////////////////

	// Solution for Question 1.
	
	static void question1 (int n, int[][] g) {
        System.out.println("Edge Enpoint Function: ");
        
       
	
        for(int i =0; i<g.length;i++){
            System.out.print( (i+1) + "   {" );
        
        for(int x = 0;x < g[x].length; x++){
            System.out.print(g[i][x]+",");
        }
        System.out.print("}\n");
        }
	 
         int[][] adjacencyMatrix = new int[n][n];
         for(int k = 0; k < g.length; k++ ){
         int y = g[k][0];
         int z = g[k][1];
       
         adjacencyMatrix[y][z]= adjacencyMatrix[y][z]+1;
         //since undirected must account for both ways
         if(y != z){
         adjacencyMatrix[z][y]= adjacencyMatrix[z][y]+1;

         }
         else if (y==z){
         
         adjacencyMatrix[y][z] =adjacencyMatrix[y][z]+1;
         }
         
       
         }
         
         

        System.out.println("Adjacency Matrix A : ");
        System.out.print("     ");
        for (int i =0; i<n;i++){
     
        System.out.print("("+ i + ")");
        
        }
         System.out.println("");
         
        for(int j = 0; j<adjacencyMatrix.length; j++){
         System.out.print( "("+ j + ")   ");
            for(int k = 0; k<adjacencyMatrix[j].length; k++){
           
        System.out.print(adjacencyMatrix[j][k]+ "  ");

        }
         System.out.println("");
        }
        int sum[] = new int[n];
       
        for(int j = 0; j<adjacencyMatrix.length; j++){
         
        for(int k = 0; k<adjacencyMatrix[j].length; k++){
         
         
         sum[j] = sum[j] + adjacencyMatrix[j][k];
         
        }
         
        }
        int sumTotal = 0;
        System.out.println("Degrees of all verticies:");
        System.out.print("(" );
        for(int i=0; i< sum.length;i++){
        sumTotal += sum[i];
        System.out.print(sum[i] + ",");
                }
        System.out.print(")" + "\n");
        System.out.println("Total Degree of the Graph:");
        System.out.println(sumTotal);
        
        System.out.println("Set of isolated vertices:" );
        System.out.print("");

         
       
               
               
       System.out.print("{");
       for(int i = 0; i < adjacencyMatrix.length; i++){
       int iterator = 0;
       for(int j = 0; j < adjacencyMatrix[i].length; j++){
       if(adjacencyMatrix[i][j] != 0){
       if(i!= j)
       iterator++;
           }
          }
        if(iterator == 0){
         System.out.print(i + ",");
           }
           }
         System.out.print("}");
         System.out.println("");

        
         int checkLoop=0;
         for(int x = 0; x<adjacencyMatrix.length;x++){
         if(adjacencyMatrix[x][x]!=0){
         
         checkLoop++;
         }
         }
         System.out.println("Is the input graph simple  : ");
         int checkPar= 0;
         for(int i = 0; i<adjacencyMatrix.length;i++){
         for(int j = 0; j<adjacencyMatrix[i].length;j++){
         if(adjacencyMatrix[i][j]>1){
         checkPar++;
         }  
         }
         }
         if(checkPar>0||checkLoop>0){
         System.out.println("NO");
         }
         else{
         System.out.println("YES");
         
         }          
         } 
        
         
     
        
        
	// Solution for Question 2.
static void question2 (int m, int n, int[][] g) {

         // Write your code here
                Stack<Integer> hold = new Stack<Integer>();
                ArrayList<ArrayList<Integer>> adjacentList = new ArrayList<>(n);
                for(int i =0; i<g.length; i++){
                adjacentList.add(new ArrayList<>());
                }
                for(int i=0; i<g.length; i++){
                int y = g[i][0];
                int z = g[i][1];     
                adjacentList.get(y).add(z);   
                }
                int visited[] = new int[n];
                Arrays.fill(visited, 0);

                for(int tracker = 0; tracker <g.length; tracker++){
                if (visited[tracker] == 0){
                dfs(adjacentList,  visited,tracker,hold);
                }
                }
                 System.out.print("(");
                 while(!hold.isEmpty()){
                 System.out.print(hold.pop()+" ");
                }



                System.out.print(")\n\n\n");
           

               }

                 static void dfs(ArrayList<ArrayList<Integer>> list,  int hold[], int tracker, Stack<Integer> holder){
                 hold[tracker] = 1;
                 int i;
                 Iterator<Integer> iterations = list.get(tracker).iterator(); 
                 while(iterations.hasNext()){
                 i = iterations.next();
                 if(hold[i] == 0)
                    dfs(list,hold,i,holder);

                 }
                 holder.push(tracker);

                 }

        
	
	static void question3 (int m, int n, int[][] g) {
		
                 int[][] adjacencyMatrix = new int[n][n];
                 for(int k = 0; k < g.length; k++ ){
                 int y = g[k][0];
                 int z = g[k][1];
       
                 adjacencyMatrix[y][z]= adjacencyMatrix[y][z]+1;
                 //since undirected must account for both ways
                 if(y != z){
                 adjacencyMatrix[z][y]= adjacencyMatrix[z][y]+1;
                 }
                 else if (y==z){
         
                 adjacencyMatrix[y][z] =adjacencyMatrix[y][z]+1;
                  }
         
                  }
                   
                 System.out.println("");
            
       
                 System.out.print("\n");
   
                 System.out.print("(");
                 bfs(m,n,adjacencyMatrix);
                 

        
        }  

           
          
        
static void bfs(int m, int n,int[][] adjacencyMatrix){
                Queue<Integer> holdQueue = new LinkedList<>();
                Queue<Integer> travOrder = new LinkedList<>();
                
                
                boolean[] vis = new boolean[n];
                
                
                travOrder.add(m);
                vis[m] = true;
                holdQueue.add(m);
      
                while(!holdQueue.isEmpty()){
                int temp = holdQueue.remove();
                for(int j = 0; j < n; j++){
                if(adjacencyMatrix[temp][j] != 0 && !vis[j]){
                travOrder.add(j);
                holdQueue.add(j);

                vis[j] = true;
                        }
                    }
                    
                }
                int sizeOfQueue = travOrder.size();
                for(int i = 0; i < sizeOfQueue; i++){
                    System.out.print(travOrder.remove() + " ");
                    
                    
                }
                System.out.print(")");
                }
        
}
	


