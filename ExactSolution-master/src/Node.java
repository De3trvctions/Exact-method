
/**
 *
 * @author Ramitaa Loganathan
 */

import java.util.ArrayList;

class Node implements Cloneable{
    
   //------------------------------------------------------------------------------------------------------
   // Initialization of Variables
   //------------------------------------------------------------------------------------------------------
    private int nodeID;                               // Node number
    private ArrayList edgeList = new ArrayList<>();   // Node List 
    
    
    //------------------------------------------------------------------------------------------------------
    // Initiazation of Constructor
    //------------------------------------------------------------------------------------------------------
    Node(int nodeID)
    {
        this.nodeID = nodeID;
    }
    
    Node (int nodeID, ArrayList edgeList)
    {
       this.nodeID = nodeID;
       this.edgeList = edgeList;
    }
    
    public Object clone()throws CloneNotSupportedException{  
        return super.clone();  
    } 
  
    //------------------------------------------------------------------------------------------------------
    // Initiazation of Getters and Setters
    //------------------------------------------------------------------------------------------------------
    
    //Initialization of Getters or Accessors
    public int getNodeID() { return nodeID; }
    public ArrayList getEdgeList() { return edgeList; }
    public int getEdgeCount() { return edgeList.size(); }
    
    //Initialization of Setters or Mutators
    public void setNodeID(int nodeID) { this.nodeID = nodeID; }
    public void setEdges(ArrayList edgeList) { this.edgeList = edgeList; }

    
    //------------------------------------------------------------------------------------------------------
    // Methods
    //------------------------------------------------------------------------------------------------------
    
    /**
     * This addEdge() method adds an edge to the node's ArrayList
     * @param edge
     */
    public void addEdge(int edge) { edgeList.add(edge);}
    public void removeEdge(int edge) { edgeList.remove(edge); }
    
    /**
     * This toString() method returns the output in String representation
     * @return output (nodeID, edges) in a fixed format
     */
    @Override
    public String toString()
    {
        String output = "";
        StringBuilder sb = new StringBuilder();
        String space = System.lineSeparator();
        
        if (nodeID < 10)
            sb.append("Node ").append(nodeID).append("  : ").append(edgeList.toString()).append(space);
        else
            sb.append("Node ").append(nodeID).append(" : ").append(edgeList.toString()).append(space);

        
        output += sb.toString();
        
        return output;
        
    }
    
   
    //------------------------------------------------------------------------------------------------------
}
