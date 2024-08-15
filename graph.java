import java.lang.reflect.Array;
import java.util.*;
public class graph{

    /*graph consist of interlinkd vertices which are linked each other using edges
     * here we build a graph using adjacency list
     * where we make a arraylist of list which stores data of each edge
     */
    static class edge{
        int src, dest, weight;
        edge(int src, int dest, int weight){
            this.src=src;
            this.dest=dest;
            this.weight=weight;
        }
    }

    public static void build(ArrayList<edge>[] graph){
        for(int i=0;i<graph.length;i++){
            graph[i]=new ArrayList<>();//initializing every cell with an array list
        }

        //now we add data of different edges
        //here we add the neighbouring edges og the current node
        // graph[0].add(new edge(0,1,5));

        // graph[1].add(new edge(1,0,5));
        // graph[1].add(new edge(1,2,1));
        // graph[1].add(new edge(1,3,3));

        // graph[2].add(new edge(2,1,1));
        // graph[2].add(new edge(2,4,1));
        // graph[2].add(new edge(2,5,2));

        // graph[3].add(new edge(3,1,1));
        // graph[3].add(new edge(3,4,3));

        // graph[4].add(new edge(4,2,2));
        // graph[4].add(new edge(4,3,2));

        // graph[5].add(new edge(5,2,2));

        graph[0].add(new edge(0,1,5));

        graph[1].add(new edge(1,2,5));

        graph[2].add(new edge(2,3,1));

        // graph[3].add(new edge(3,2,1));

        graph[4].add(new edge(4,3,2));

        graph[5].add(new edge(5,4,2));

    
    }

    //for dfs we make anew function 
    //where we take starting node and for one of its chils we traver till we find a node we have visited
    public static void dfs(ArrayList<edge>[] graph,int curr, boolean visit[] ){
        //we print out the node a dmark it as visited
        System.out.print(curr+" ");
        visit[curr]=true;

        //now we travers the child of the current node
        //we travers from 0 to the length of array list in current index of array
        for(int i=0;i<graph[curr].size();i++){
            edge e=graph[curr].get(i);
            //we check if the neighbouring nodes of the current node are visited or not 
            //if not, than we call dfs for that node
            if(!visit[e.dest]){
                dfs(graph,e.dest,visit);
            }
        }
    }

    public static void bfs(ArrayList<edge>[] graph,int curr, boolean visit[]){
        //here to store print element in bfs we make a queue and store current nodes neighbours in the queue
        Queue<Integer> q= new LinkedList<>();
        q.add(curr);
        while(!q.isEmpty()){
            curr=q.remove();
            //while my queue is not empty
            //icheck if my current node is visited or not
            //if it is not visited i print it and mark it as visited
            //then i add every neighbour of the current node
            //and continue the loop till i empty my queue
            if(!visit[curr]){
                System.out.print(curr+" ");
                visit[curr]=true;
                for(int i=0;i<graph[curr].size();i++){
                    q.add(graph[curr].get(i).dest);
                    //adding the neighbours within the queue
                }
            }
        }

    }

    public static boolean has_path(ArrayList<edge>[] graph, int src, int dest, boolean visit[]){
        //if my src is equal to destination than i return true
        if(src==dest){
            return true;
        }
        visit[src]=true;//marking current node as visited

        //here we use dfs to check neighbours of current node and for them we check 
        //if they have a valid path till destination
        //i traverse through the neighbours of src to find if its neighbour has an path to destination
        //also the neighbour should not be visited
        for(int i=0;i<graph[src].size();i++){
            edge e=graph[src].get(i);
            if( !visit[e.dest]  && has_path(graph, e.dest, dest, visit)){
                System.out.print(e.dest+" ");
                return true;
            }
        }
        return false;
    }

    public static boolean cycleutil(ArrayList<edge>[] graph, int src, int parent,boolean visit[]){
        visit[src]=true;
        for(int i=0;i<graph[src].size();i++){
            edge e=graph[src].get(i);
            //check for 3rd condition
            //we check if the neighbour is not visited
            if(!visit[e.dest]){
                //then we check cycle for neighbour
                if(cycleutil(graph,e.dest,src,visit)){
                    return true;
                }
            }
            //for 2nd condition
            else if(visit[e.dest] && e.dest!=parent){
                return true;
            }
        }
        return false;
    }

    public static boolean cycle(ArrayList<edge>[] graph,boolean visit[]){
        //to find cycle in a undirected graph
        //we have a boolena array to store if the node is visited or not
        //we traverse for each node and check its neighbour for the following 3 conditions
        //  1-> visited and parent--- then we do nothing
        //  2-> visited but not parent --- then we return true as loop is present
        //  3-> not visited --- we call cycle for this node
        
        // i make the src node as visited
        for(int i=0;i<graph.length;i++){
            if(!visit[i]){
                return cycleutil(graph, i, -1, visit);
            }
        }
        return false;
    }

    public static boolean bipart(ArrayList<edge>[] graph, int src, boolean visit[], int color[]){
        //we use bfs algorithm for this code
        //here in bipart we have 3 condition we have to look out
        //  1-> neighbour have same color --- return false
        //  2-> neighbour have different color --- we do nothing
        //  3-> neighbour have no color --- we set color different to ours

        //we make a queue to add the nodes which have assigned a color/set
        Queue<Integer> q= new LinkedList<>();
        for(int i=0;i<graph.length;i++){
            if(color[i]==-1){ // i check if a node has no color, if they dont have it then i provide them a default color
                //and then add them in the queue
                q.add(i);
                color[i]=0;
            }
            while(!q.isEmpty()){
                int curr=q.remove();//i make the current node as the 1st element of the queue
                //and check its neighbour is they have a set or not
                for(int k=0;k<graph[curr].size();k++){
                    edge e=graph[curr].get(k);
                    if(color[e.dest]==-1){//for condition 3 
                        color[e.dest]=color[curr]==1?0:1;
                        q.add(e.dest);
                    }
                    else if(color[curr]==color[e.dest]){ //for condition 1
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean bicycleutil(ArrayList<edge>[] graph, int src, boolean visit[], boolean stack[]){
        visit[src]=true;
        stack[src]=true;
        for(int i=0;i<graph[src].size();i++){
            edge e=graph[src].get(i);
            //what at this point i have to do is see if certain conditions are being fullfilled or not
            //i check if current's neighbour is present in the stack
            //if yes then we return true
            //else we check the bicyle frunction for curretn's neighbour given that it has not been visited
            if(stack[e.dest]){
                return true;
            }
            else if(!visit[e.dest] && bicycleutil(graph, e.dest,visit,stack)){
                return true;
            }
        }
        //as i return back the previous stage of recurssion i remove the element from stack
        stack[src]=false;
        return false;
    }

    public static boolean bicycle(ArrayList<edge>[] graph,boolean visit[]){
        //here we use a modified
        boolean stack[]=new boolean[graph.length];//we make a visit array to store if a node is visited or not
        for(int i=0;i<stack.length;i++){
            stack[i]=false;
        }
        for(int i=0;i<graph.length;i++){
            if(!visit[i]){
                return bicycleutil(graph,i,visit,stack);
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public static void main(String args[]){
        //making an array to store arraylists within it that stores edges info
        int v=6;//to store the no of vertices
        ArrayList<edge>[] graph= new ArrayList[v];
        // here we could add any no of vertices
        build(graph);
        
        // //now we could do various action with our grpah
            // System.out.println("dfs printout");
            // dfs(graph,0,new boolean[graph.length]);

            // System.out.println("\nbfs printout");
            // bfs(graph,0,new boolean[graph.length]);

        
        //to find if theier is apth bw src and destination
            int src=0;
            // int dest=5;
            boolean visit[]=new boolean[graph.length];//we make a visit array to store if a node is visited or not
            for(int i=0;i<visit.length;i++){
                visit[i]=false;
            }
            
            // System.out.println(has_path(graph,src,dest,visit));

        //cycle detection
            //we send sec and parent as parameter
            //parent initialyy is -1
            // System.out.println(cycle(graph,visit));

        //bipartite graph
            //these are the type of graph in which src and destination are with 2 differnt sets for every 2 nodes
            // int color[]=new int[graph.length];//we make a visit array to store if a node is visited or not
            // for(int i=0;i<visit.length;i++){
            //     color[i]= -1;
            // }//here i have an array color that stores 3 set of colors
            //0->red,   1->blue,    -1->no color which is by defalut 
            // System.out.println(bipart(graph,src,visit,color));
        
        //cycle in a directed graph
            System.out.println(bicycle(graph, visit));
    }
}