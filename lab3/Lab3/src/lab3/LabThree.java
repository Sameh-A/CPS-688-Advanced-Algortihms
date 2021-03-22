//////////////////////////////////////////////////
/*
Lab 3 - CPS688 - W21
Name: Sameh  Ahmed
St ID: 500907041
*/
//////////////////////////////////////////////////

package lab3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LabThree{
	public static void main(String[] args) {
		
		
		System.out.print("\n--- Q1 -------\n\n");
		/*
		 * For each amount given in the array "amounts",
		 * using the currency denominations: 1c, 5c, 10c, 25c, $1, 
		 * pay amount to customer using fewest numbers of coins.
		 */
		int[] amounts = {0,34,37,97,100};
		for (int m=0; m<5; m++) {
			System.out.print("Amount = "+amounts[m]+" cents.-------\n");
			System.out.print("Payout:\n");
			payout_in_coins (amounts[m]);
		}
		///////////////////////////////////////////////////////////
		System.out.print("\n--- Q2 -------\n");
		// Input for Q2 will be read from a file "input_Q2.txt".
		// There can be more than one input graphs.
		// For a set of n vertices, set {1,2,3,...,n} will be used as the set of vertices
		// First row of the input graph has 2 positive integers separated by a space.
		// These two values are the number of vertices and the number of edges, respectively.
		// Second row of the input graph has one integer value indicating the starting vertex for Prim’s algorithm.
		// Every subsequent row contains 3 positive integers separated by a space.
		// Among  these 3 values, the first two values are the end-points of an edge in the graph.
		// The third value is the weight of the edge in the graph.
		
		try { 
			FileReader freader = new FileReader(new File("input_Q2.txt"));
			BufferedReader readInput = new BufferedReader(freader);
			String row;
			while ((row = readInput.readLine()) != null) {
				System.out.print("\nInput Graph:\n");
				String[] graph_parameters = row.split(" ",2);
				int num_vertices = Integer.parseInt(graph_parameters[0]);
				int num_edges = Integer.parseInt(graph_parameters[1]);
				int starting_vertex = Integer.parseInt(readInput.readLine().split(" ",1)[0]);
				int[][] graph = new int [num_edges][3];
				System.out.print("There are "+num_vertices+" vertices and "+num_edges+" edges.\n");
				System.out.print("Edge   Weight\n");
				for (int i=0; i<num_edges; i++) {
					row = readInput.readLine();
					String[] triples = row.split(" ",3);
					for (int j=0; j<3; j++)
						graph[i][j] = Integer.parseInt(triples[j]);
					System.out.print("("+graph[i][0]+","+graph[i][1]+")   "+graph[i][2]+"\n");
				}
				System.out.print("\nMST by Prim's algorithm:\n");
				// Call method to find MST using Prim's algorithm
				// Method should print the edges and their weights in the MST
				// Method should return the total weight of the MST
				int total_weight_Prim = mst_Prim (num_vertices, num_edges, graph, starting_vertex);
				System.out.print("The total weight of the spanning tree is "+total_weight_Prim+".\n");
				
				
				System.out.print("\nMST by Kruskal's algorithm:\n");
				// Call method to find MST using Kruskal's algorithm
				// Method should print the edges and their weights in the MST
				// Method should return the total weight of the MST
				int total_weight_Kruskal = mst_Kruskal (num_vertices, num_edges, graph);
				System.out.print("The total weight of the spanning tree is "+total_weight_Kruskal+".\n");
				
				readInput.readLine();
			} 
			freader.close();
			readInput.close();
		}
		catch (IOException e) {  
	            e.printStackTrace();  
	    }	
	}
	
//////////////////////////////////////////////////
/*
Write your solution below where it is indicated.
*/
//////////////////////////////////////////////////
	
	//For the integer value "amount",
	// using the currency denominations: 1c, 5c, 10c, 25c, $1, 
	// methods pay amount to customer using fewest numbers of coins.
	public static void payout_in_coins (int amount) {    
            int quarter= 0, dime =0, nickel = 0, dollar = 0, penny =0;
            while(amount >= 100){
            amount = amount- 100;
            dollar ++;
           }
            while(amount >= 25){
            amount = amount- 25;
            quarter ++;
           }
            while(amount >= 10){
            amount = amount - 10;
            dime ++;
           }
            while(amount >= 5){
            amount = amount - 5;
            nickel ++;
           }
            while(amount >= 1){
            amount = amount- 1;
            penny ++;
           }
            
		System.out.print(dollar+" coins of "+1+" dollar.\n");
		System.out.print(quarter+" coins of "+25+" cents.\n");
		System.out.print(dime+" coins of "+10+" cents.\n");
		System.out.print(nickel+" coins of "+5+" cents.\n");
		System.out.print(penny+" coins of "+1+" cents.\n");	
	}
	
	// Method to find MST using Prim's algorithm
	// Method should print the edges and their weights in the MST
	// Method should return the total weight of the MST
          public static int mst_Prim (int num_vertices, int num_edges, int[][] graph, int starting_vertex) {
                int infinity = Integer.MAX_VALUE;
                int[] nearestNode = {infinity, infinity, infinity, infinity, infinity, infinity};
                nearestNode[starting_vertex] = 0;
		int total_weight=0;
                ArrayList<int[]> primInfo = new ArrayList<int[]>();
                int[][] AdjMatrix = new int[num_vertices+1][num_vertices+1];
                for(int  i = 0; i < AdjMatrix.length; i++){
                    int y = graph[i][0];
                    int z = graph[i][1];
                    int x = graph[i][2];
                    AdjMatrix[y][z] = x;
                    if(y != z){
                        AdjMatrix[z][y] = x;
                    }
                }
               for(int i = 0; i < AdjMatrix.length; i++){
                   for(int j = 0; j < AdjMatrix[i].length; j++){
                   if(AdjMatrix[i][j] == 0){
                    AdjMatrix[i][j] = infinity;
                   }                        
                   }
                   }      
               
                int startWeight = AdjMatrix[starting_vertex][1];
                int startNode = starting_vertex;
                for(int i = 1; i < AdjMatrix[starting_vertex].length; i++){
                   for(int j = i; j < AdjMatrix[starting_vertex].length; j++){
                    if(AdjMatrix[starting_vertex][i] < startWeight){
                    startWeight = AdjMatrix[starting_vertex][i];
                    startNode = i;
                   }
                   }
                   }
                
                int[] arr = {1, startNode, startWeight};
                
                primInfo.add(arr);
                nearestNode[startNode] = 0;
                
                for(int i = 1; i <  nearestNode.length; i++){
                   if(nearestNode[i] != 0){
                   if(AdjMatrix[i][starting_vertex] < AdjMatrix[i][startNode]){
                   nearestNode[i] = starting_vertex;
                   }
                   else{
                   nearestNode[i] = startNode;
                 }
                 }
                    
                 }
                int lowNode;
                int x = 0;
                for(int i = 1; i < num_vertices-1; i++){
                   lowNode = infinity;
                   for(int j = 1; j <= num_vertices; j++){
                   if(nearestNode[j] != 0 && AdjMatrix[j][nearestNode[j]] < lowNode){
                   x = j;
                   lowNode = AdjMatrix[j][nearestNode[j]];
                    }
                    }       
                    int[] edge = {x, nearestNode[x], lowNode};
                    primInfo.add(edge);
                    nearestNode[x] = 0;
                    for(int q = 1; q <= num_vertices; q++){
                        if(nearestNode[q] != 0 && AdjMatrix[q][x] < AdjMatrix[q][nearestNode[q]]){
                        nearestNode[q] = x;
                        }                    
                        }             
                        }
                System.out.println("There are " + num_vertices + " vertices and " + primInfo.size() + " edges");
                System.out.println("Edge" +"       Weight");
                for(int i = 0; i < primInfo.size(); i++){
                    total_weight += primInfo.get(i)[2];
                    int a = primInfo.get(i)[0];
                    int b = primInfo.get(i)[1];
                    if(b<a)
                    System.out.println("(" + b + "," + a +")"+ "        " + primInfo.get(i)[2]);
                    else if(b>a){
                    System.out.println("(" + a + "," + b + ")"+"        " + primInfo.get(i)[2]);
                } }
             
		return total_weight;       
                
                }

  
	
	// Call method to find MST using Kruskal's algorithm
	// Method should print the edges and their weights in the MST
	// Method should return the total weight of the MST
	public static int mst_Kruskal (int num_vertices, int num_edges, int[][] graph) {
            int infinity = Integer.MAX_VALUE;
            int[] referenceNode = new int[num_vertices];
            int edgeNum = 1;
            int total_weight = 0; 
            
            int[][] AdjMatrix = new int[num_vertices+1][num_vertices+1];
                for(int  i = 0; i < AdjMatrix.length; i++){
                    int y = graph[i][0]-1;
                    int z = graph[i][1]-1;
                    int x = graph[i][2];
                    AdjMatrix[y][z] = x;
                    if(y != z){
                        AdjMatrix[z][y] = x;
                    }
                    }
                
            for(int i = 0; i < AdjMatrix.length; i++){
                   for(int j = 0; j < AdjMatrix[i].length; j++){
                       if(AdjMatrix[i][j] == 0){
                          AdjMatrix[i][j] = infinity;
                    }                        
                    }
                    }      
            for (int i = 0; i < num_vertices; i++) 
            referenceNode[i] = i; 
            System.out.println("There are " +num_vertices+ " Verticies and  " +(num_vertices-1)+" edges");
            System.out.println("Edge      Weight ");
            for(int x =0; x<num_vertices; x++ ){
                if(edgeNum< num_vertices){
                int weight = infinity;
                int u = 0;
                int v = 0;
                int uParent = 0;
                int vParent = 0;
                for (int i = 0; i < num_vertices; i++){ 
                for (int j = 0; j < num_vertices; j++){ 
                uParent = find(i,referenceNode);
                        vParent = find(j,referenceNode);
                        if (uParent != vParent){ 
                            if(AdjMatrix[i][j] < weight){ 
                            weight = AdjMatrix[i][j]; 
                            u = i; 
                            v = j; 
                } 
                }
                } 
                } 
        cycle(u, v, referenceNode); 
        System.out.println("("+(u+1)+","+(v+1)+")"+ "        "+ weight);
        edgeNum = edgeNum + 1;
        total_weight +=weight; 
        } 
        }
        return total_weight;
	}
        
   
        static int find(int node,int[] reference){ 
        for (int j =0; j<reference.length;j++)      
        if (reference[node] != node) 
            node = reference[node]; 
        return node; 
         } 

        static void cycle(int set1, int set2, int[] reference) 
         { 
        int u = find(set1,reference); 
        int v = find(set2, reference); 
        reference[u] = v; 
         } 

         }