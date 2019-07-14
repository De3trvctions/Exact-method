
/**
 *
 * @author Ramitaa Loganathan
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


final class Graph {
    
   //------------------------------------------------------------------------------------------------------
   // Initialization of Variables
   //------------------------------------------------------------------------------------------------------
    private int[][] edgesTable ;                                         // EdgesTable   
    private static ArrayList<Node> nodeList = new ArrayList<>();         // Node List 
    private int graphSize;                                               // Total number of nodes
    private int cliqueSize;                                              // K-value (Number of nodes in a clique
    
    //------------------------------------------------------------------------------------------------------
    // Initiazation of Constructor
    //------------------------------------------------------------------------------------------------------
    Graph()
    {
        
    }
    
    Graph (int graphSize, int cliqueSize)
    {
       this.edgesTable = null;
       this.nodeList.clear();
       this.graphSize = graphSize;
       this.cliqueSize = cliqueSize;
       createEdges();
       assignEdgesToNodes();
    } 
    
    Graph (int graphSize, int cliqueSize, int[][] edgesTable, ArrayList<Node> nodeList)
    {
       this.graphSize = graphSize;
       this.cliqueSize = cliqueSize;
       this.edgesTable = edgesTable;
       Graph.nodeList = nodeList;
    } 
       
   
    //------------------------------------------------------------------------------------------------------
    // Initiazation of Getters and Setters
    //------------------------------------------------------------------------------------------------------
    
    //Initialization of Getters or Accessors
    public int[][] getEdgesTable() { return edgesTable; }
    public int getGraphSize() { return graphSize; }
    public int getCliqueSize() { return cliqueSize; }
    public ArrayList getNodeList() { return nodeList; }

    //Initialization of Setters or Mutators
    public void setEdgesTable(int[][] edgesTable) { this.edgesTable = edgesTable; }
    public void setGraphSize(int graphSize) { this.graphSize = graphSize; }
    public void setCliqueSize(int cliqueSize) { this.cliqueSize = cliqueSize; }
    public void setNodeList(ArrayList<Node> nodeList) { Graph.nodeList = nodeList; }

    
    //------------------------------------------------------------------------------------------------------
    // Methods
    //------------------------------------------------------------------------------------------------------
    
    /**
     * This createNodeEdges() method creates the edges tables (which node is connected to which node)
     */
    public void createEdges()
    {
        // The random number of edges is generated
        int noOfEdges = generateRandomNoOfEdges();
        edgesTable = new int[noOfEdges][2];
        
        for(int r = 0; r < noOfEdges; r++)
        {
            boolean addedEdge = false;
            
            while (addedEdge != true)
            {
                boolean validEdge = true;
                
                // A random edge is generated 
                int edge[] = generateRandomEdge();

                // The edge is then checked whether it preexists in the table
                for(int[] checkrow : edgesTable)
                {
                   
                    if (Arrays.equals(checkrow, edge))
                    {
                        validEdge = false;
                        break;
                    }
                } 

                // The edge is added only if the edge is unique and not duplicate
                if (validEdge == true)
                {
                    edgesTable[r] = edge;
                    addedEdge = true;
                }
                
                
            }
            
        }
        
        // The table is then sorted first according to the first column, and then by the second
        sortByColumn(edgesTable);
    }
    
    /**
     * This assignEdgesToNodes() method creates the each node of the graph with the nodeID and its edges
     */
    private void assignEdgesToNodes() 
    {
        for (int i = 0; i < graphSize; i++)
        {
            nodeList.add(new Node(i));
        }
        
        for (int[] row : edgesTable)
        {
            nodeList.get(row[0]).addEdge(row[1]);
            nodeList.get(row[1]).addEdge(row[0]);
        }
        
    }
    
    /**
     * This sortByColumn() method sorts the table according to the first column, then by the second
     * @param arr
     * @return edgeTable
     */
    public void sortByColumn(int arr[][]) 
    { 
        Arrays.sort(arr, (final int[] int1, final int[] int2) -> {
            // Sorts by the first column
            if (int1[0] > int2[0]) 
                return 1;
            
            // If both values in the first column is similar, the second column is checked
            else if (int1[0] == int2[0])
            {
                if(int1[1] > int2[1])
                    return 1;
                
                else
                    return -1;
            }
            
            else
                return -1; 
        });
    } 
    
    /**
     * This generateRandomNoOfEdges() method generates a logical random number of edges
     * It ensures that half the number of nodes at least have 1 edge
     * It ensures that the maximum number of edges is not exceeded
     * @return randomNum
     */
    public int generateRandomNoOfEdges()
    {
        Random rand = new Random();
        
        int minEdges = graphSize / 2;
        int maxEdges = (graphSize * (graphSize - 1)) / 2;
        
        int randomNum =  rand.nextInt((maxEdges - minEdges) + 1) + minEdges;

        int edge = 120;
        return edge;
        //return randomNum;

    }
    
    /**
     * This generateRandomEdge() method generates a logical edge
     * It ensures that the edges are connecting two different nodes
     * It ensures that the first node is of less value than the second node to eliminate duplicate edges later
     * @return edgeTable
     */
    public int[] generateRandomEdge()
    {
        Random rand = new Random();
        
        int min = 0;
        int max = graphSize - 1;
        
        int randomNum1 =  rand.nextInt((max - min) + 1) + min;
        
        int randomNum2 =  rand.nextInt((max - min) + 1) + min;
        
        while (randomNum1 == randomNum2)
        {
            randomNum2 =  rand.nextInt((max - min) + 1) + min;
        }
        
        int edge[] = new int[2];
        
        if (randomNum1 < randomNum2)
        {
            edge[0] = randomNum1;
            edge[1] = randomNum2;
        }
            
        else
        {
            edge[0] = randomNum2;
            edge[1] = randomNum1;
        }
                    
        return edge;
    }
    
    /**
     * This printNodeDetails() method is to print the details of each node
     */
    public void printNodeDetails()
    {
        nodeList.forEach((n) -> {
            System.out.print(n.toString());
        });
    }
    
    
    /**
     * This toString() method returns the output in String representation
     * @return output (graph details) in a fixed format
     */
    @Override
    public String toString()
    {
        String output = String.format("\nGraph size   : %d%n"
                                    + "Clique size  : %d%n"
                                    + "No of Edges  : %d%n"
                                    + "\nEdges table%n", graphSize, cliqueSize, edgesTable.length);
        
        int i = 1;
        
        StringBuilder sb = new StringBuilder();
        String space = System.lineSeparator();
        
        for(int[] row: edgesTable)
        {
            if (i < 10)
                sb.append("Edge ").append(i).append("  : ").append(Arrays.toString(row)).append(space);
            else
                sb.append("Edge ").append(i).append(" : ").append(Arrays.toString(row)).append(space);
            
            i++;
        }
        
        output += sb.toString();
        
        return output; 
    }
    
    //------------------------------------------------------------------------------------------------------
    
}

