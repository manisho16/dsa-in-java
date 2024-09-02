
import java.util.*;

public class graph {

    static class pair implements Comparable<pair>{
        
        int node;
        int path;
        public pair(int node,int path){
            this.node=node;
            this.path=path;
        }

        @Override
        public int compareTo(pair p2){
            return this.path-p2.path;
        }//returns me an ascending order of class values comparing it to path
    }
    
    static class np implements Comparable<np>{

        int node;
        int edge;
        public np(int node,int edge){
            this.node=node;
            this.edge=edge;
        }

        @Override
        public int compareTo(np p2){
            return this.edge-p2.edge;
        }//returns me an ascending order of class values comparing it to path
    }

    static class edge {
        /*
         * graph consist of interlinkd vertices which are linked each other using edges
         * here we build a graph using adjacency list
         * where we make a arraylist of list which stores data of each edge
         */
        int src, dest;
        //  weight;

        edge(int src, int dest
        // int weight
        ) {
            this.src = src;
            this.dest = dest;
            // this.weight=weight;
        }
    }

    public static void build(ArrayList<edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();// initializing every cell with an array list
        }

        // now we add data of different edges
        // here we add the neighbouring edges og the current node
        graph[0].add(new edge(0,1));
        graph[0].add(new edge(0,3));
        graph[1].add(new edge(1, 2));
        graph[1].add(new edge(1, 3));
        graph[2].add(new edge(2, 0));
        graph[3].add(new edge(3, 4));
        graph[4].add(new edge(4, 5));
        // graph[5].add(new edge(5, 0));

            //dij
        // graph[0].add(new edge(0,1,2));
        // graph[0].add(new edge(0,2,4));
        // graph[1].add(new edge(1,3,7));
        // graph[1].add(new edge(1,2,1));
        // graph[2].add(new edge(2,4,3));
        // graph[3].add(new edge(3,5,1));
        // graph[4].add(new edge(4,3,2));
        // graph[4].add(new edge(4,5,5));
        
            //bell
        // graph[0].add(new edge(0,1,2));
        // graph[0].add(new edge(0,2,4));
        // graph[1].add(new edge(1,2,-4));
        // graph[2].add(new edge(2,3,2));
        // graph[3].add(new edge(3,4,4));
        // graph[4].add(new edge(4,1,-1));

            // prism
        // graph[0].add(new edge(0,1,10));
        // graph[0].add(new edge(0,2,15));
        // graph[0].add(new edge(0,3,30));
        // graph[1].add(new edge(1,3,40));
        // graph[1].add(new edge(1,0,10));
        // graph[2].add(new edge(2,1,15));
        // graph[2].add(new edge(2,3,50));
        // graph[3].add(new edge(3,0,30));
        // graph[3].add(new edge(3,1,40));
        // graph[3].add(new edge(3,2,50));
        // graph[4].add(new edge(4,1,-1));

    }

    public static void dfs(ArrayList<edge>[] graph, int curr, boolean visit[]) {
        // for dfs we make a new function
        // where we take starting node and for one of its chils we traver till we find a
        // node we have visited
        // we print out the node a dmark it as visited
        System.out.print(curr + " ");
        visit[curr] = true;

        // now we travers the child of the current node
        // we travers from 0 to the length of array list in current index of array
        for (int i = 0; i < graph[curr].size(); i++) {
            edge e = graph[curr].get(i);
            // we check if the neighbouring nodes of the current node are visited or not
            // if not, than we call dfs for that node
            if (!visit[e.dest]) {
                dfs(graph, e.dest, visit);
            }
        }
    }

    public static void bfs(ArrayList<edge>[] graph, int curr, boolean visit[]) {
        // here to store print element in bfs we make a queue and store current nodes
        // neighbours in the queue
        Queue<Integer> q = new LinkedList<>();
        q.add(curr);
        while (!q.isEmpty()) {
            curr = q.remove();
            // while my queue is not empty
            // icheck if my current node is visited or not
            // if it is not visited i print it and mark it as visited
            // then i add every neighbour of the current node
            // and continue the loop till i empty my queue
            if (!visit[curr]) {
                System.out.print(curr + " ");
                visit[curr] = true;
                for (int i = 0; i < graph[curr].size(); i++) {
                    q.add(graph[curr].get(i).dest);
                    // adding the neighbours within the queue
                }
            }
        }

    }

    public static boolean has_path(ArrayList<edge>[] graph, int src, int dest, boolean visit[]) {
        // if my src is equal to destination than i return true
        if (src == dest) {
            return true;
        }
        visit[src] = true;// marking current node as visited

        // here we use dfs to check neighbours of current node and for them we check
        // if they have a valid path till destination
        // i traverse through the neighbours of src to find if its neighbour has an path
        // to destination
        // also the neighbour should not be visited
        for (int i = 0; i < graph[src].size(); i++) {
            edge e = graph[src].get(i);
            if (!visit[e.dest] && has_path(graph, e.dest, dest, visit)) {
                System.out.print(e.dest + " ");
                return true;
            }
        }
        return false;
    }

    public static boolean cycleutil(ArrayList<edge>[] graph, int src, int parent, boolean visit[]) {
        visit[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            edge e = graph[src].get(i);
            // check for 3rd condition
            // we check if the neighbour is not visited
            if (!visit[e.dest]) {
                // then we check cycle for neighbour
                if (cycleutil(graph, e.dest, src, visit)) {
                    return true;
                }
            }
            // for 2nd condition
            else if (visit[e.dest] && e.dest != parent) {
                return true;
            }
        }
        return false;
    }

    public static boolean cycle(ArrayList<edge>[] graph, boolean visit[]) {
        // to find cycle in a undirected graph
        // we have a boolena array to store if the node is visited or not
        // we traverse for each node and check its neighbour for the following 3
        // conditions
        // 1-> visited and parent--- then we do nothing
        // 2-> visited but not parent --- then we return true as loop is present
        // 3-> not visited --- we call cycle for this node

        // i make the src node as visited
        for (int i = 0; i < graph.length; i++) {
            if (!visit[i]) {
                return cycleutil(graph, i, -1, visit);
            }
        }
        return false;
    }

    public static boolean bipart(ArrayList<edge>[] graph, int src, boolean visit[], int color[]) {
        // we use bfs algorithm for this code
        // here in bipart we have 3 condition we have to look out
        // 1-> neighbour have same color --- return false
        // 2-> neighbour have different color --- we do nothing
        // 3-> neighbour have no color --- we set color different to ours

        // we make a queue to add the nodes which have assigned a color/set
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == -1) { // i check if a node has no color, if they dont have it then i provide them a
                                  // default color
                // and then add them in the queue
                q.add(i);
                color[i] = 0;
            }
            while (!q.isEmpty()) {
                int curr = q.remove();// i make the current node as the 1st element of the queue
                // and check its neighbour is they have a set or not
                for (int k = 0; k < graph[curr].size(); k++) {
                    edge e = graph[curr].get(k);
                    if (color[e.dest] == -1) {// for condition 3
                        color[e.dest] = color[curr] == 1 ? 0 : 1;
                        q.add(e.dest);
                    } else if (color[curr] == color[e.dest]) { // for condition 1
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean bicycleutil(ArrayList<edge>[] graph, int src, boolean visit[], boolean stack[]) {
        visit[src] = true;
        stack[src] = true;
        for (int i = 0; i < graph[src].size(); i++) {
            edge e = graph[src].get(i);
            // at this point i have to do is see if certain conditions are being fullfilled
            // or not
            // i check if current's neighbour is present in the stack
            // if yes then we return true
            // else we check the bicyleutil function for current's neighbour given that it
            // has not been visited
            if (stack[e.dest]) {
                return true;
            } else if (!visit[e.dest] && bicycleutil(graph, e.dest, visit, stack)) {
                return true;
            }
        }
        // as i return back the previous stage of recurssion i remove the element from
        // stack
        stack[src] = false;
        return false;
    }

    public static boolean bicycle(ArrayList<edge>[] graph, boolean visit[]) {
        // here we use a modified
        boolean stack[] = new boolean[graph.length];// we make a visit array to store if a node is visited or not
        for (int i = 0; i < stack.length; i++) {
            stack[i] = false;
        }
        for (int i = 0; i < graph.length; i++) {
            if (!visit[i]) {
                return bicycleutil(graph, i, visit, stack);
            }
        }
        return false;
    }

    public static void topsortutil(ArrayList<edge>[] graph, boolean visited[], Stack<Integer> s, int curr) {
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            edge e = graph[curr].get(i);
            // now for my current node if its any neighbour is not visited i call the
            // topsortutil function for the neighbour
            // till i reach teh deadend
            // and if the deadend is reached then i add the node in the stack
            if (!visited[e.dest]) {
                topsortutil(graph, visited, s, e.dest);
            }
        }

        // in case of deadend i push the current in the stack
        s.push(curr);
    }

    public static void topsort(ArrayList<edge>[] graph) {
        // here i make a boolean array to store if the nodes are visited or not
        // and a stack to push the elements in the topological order
        boolean visited[] = new boolean[graph.length];
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                topsortutil(graph, visited, s, i);
            }
        }

        // i print the element of the stack
        while (!s.isEmpty()) {
            System.out.print(s.pop() + " ");
        }
    }

    public static void khans(ArrayList<edge>[] graph){
        //making an indegree array to store the inorders
        int indeg[]=new int[graph.length];
        //adding the indegrees to the array
        for(int i=0;i<graph.length;i++){
            for(int k=0;k<graph[i].size();k++){
                edge e=graph[i].get(k);
                indeg[e.dest]++;//updating the inorders, by increasing the values for the destinations
            }
        }
        //doing bfs at every node
        Queue<Integer> q= new LinkedList<>();
        for(int i=0;i<graph.length;i++){
            if(indeg[i]==0){//adding those nodes to the queue which have 0 indegrees
                q.add(i);
            }
        }
        
        while(!q.isEmpty()){
            //getting the first 0 inorder element and printing it 
            int curr=q.remove();
            System.out.print(curr+" ");

            //decreasing the inorder of currents neighbours
            for(int i=0;i<graph[curr].size();i++){
                edge e=graph[curr].get(i);
                indeg[e.dest]--;
                //adding those neighbours to the queue whose indegress are now 0
                if(indeg[e.dest]==0){
                    q.add(e.dest);
                }
            }
        }
    }

    public static void allpath(ArrayList<edge>[] graph, int src, int dest, String path){
        //here i pass the src, destination and path string that stores the path from src to destination
        //i take the current node and check its neighbour for the child adding th current node in the path string as i pass it to the recurssive function
        //if at the end of the path i get my current as the destination i add the surrent in the path string and print it else i retrive back
        if(src==dest){
            path+=src;
            System.out.println(path);
        }

        for(int i=0;i<graph[src].size();i++){
            edge e=graph[src].get(i);
            allpath(graph,e.dest,dest,path+src);
        }
    }

    public static void dig(ArrayList<edge>[] graph, int src){
        int dist[]=new int[graph.length];
        //initializing every value apart from src to be infinity
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        PriorityQueue<pair> pq= new PriorityQueue<>();
        boolean visited[]=new boolean[graph.length];
        pq.add(new pair(src,0));
        while(!pq.isEmpty()){
            pair curr=pq.remove();
            if(!visited[curr.node]){
                visited[curr.node]=true;

                //relaxation condition for current nodes childs
                for(int i=0;i<graph[curr.node].size();i++){
                    edge e=graph[curr.node].get(i);
                    int u=e.src;
                    int v=e.dest;
                    // int w=e.weight;

                    //condition
                    // if(dist[u]+w < dist[v]){
                        //i update the neighbours destination with the smaller sitance from src
                        // dist[v]=dist[u]+w;
                        // pq.add(new pair(v,dist[v]));
                    // }
                }
            }
        }

        for(int i=0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }


    }

    public static void bell(ArrayList<edge>[] graph, int src){
        int dist[]=new int[graph.length];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[src]=0;
        for(int i=0;i<graph.length-1;i++){//the outer loop that runs for n-1 times
            
            for(int in=0;in<graph.length;in++){//the inner loop for getting the edges
                for(int k=0;k<graph[in].size();k++){
                    edge e=graph[in].get(k);
                    int u=e.src;
                    int v=e.dest;
                    // int w=e.weight;

                    //along with my relaxation consition i also ensure the current nodes distance to not be infinite
                    //then only i would be able to check the condition, as without it the condition wont be ever valid
                    // if(dist[u]!=Integer.MAX_VALUE && dist[u] + w < dist[v]){
                        // dist[v]=w + dist[u];
                    // }
                }
            }
        }
        for(int i=0;i<dist.length;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i=0;i<dist.length;i++){
            System.out.print(dist[i]+" ");
        }
    }

    public static void prism(ArrayList<edge>[] graph, int src){
        boolean visited[]=new boolean[graph.length];
        PriorityQueue<np> p=new PriorityQueue<>();
        ArrayList<Integer> seq=new ArrayList<>();
        p.add(new np(src,0));
        int min=0;
        while(!p.isEmpty()){
            np curr=p.remove();
            if(!visited[curr.node]){
                visited[curr.node]=true;
                min+=curr.edge;
                seq.add(curr.node);
                for(int i=0;i<graph[curr.node].size();i++){
                    edge e=graph[curr.node].get(i);
                    // p.add(new np(e.dest,e.weight));
                    
                }
            }
        }
        for(int i=0;i<seq.size();i++){
            System.out.print(seq.get(i)+" ");
        }
        System.out.println();
        System.out.print(min);
    }

    public static int find(int parent[],int a){
        if(a==parent[a]){
            return a;
        }
        return find(parent,parent[a]);
    }

    public static void union(int rank[],int parent[],int a, int b){
        //while making union of 2 nodes i have to always check their parent
        //if both's parent have the same rank then any of them could be the parent, wherewe update the parent and increase the rank of new parent
        //while if they are not same then we make the larger rank one tha overall parent and increase its rank
        int pa=find(parent,a);
        int pb=find(parent,b);
        if(rank[pa]==rank[pb]){
            // say i make a as the parent
            parent[pb]=pa;
            rank[pa]++;
        }
        else if(rank[pa]>rank[pb]){
            parent[pb]=pa;
            rank[pa]++;
        }
        else{
            parent[pa]=pb;
            rank[pb]++;
        }
    }

    public static void connectedcities(int cities[][]){
        //this code is same as the prism algo
        //we use a class to store the node and edge
        //and a priority list for the smallest edge
        //and a visited array to store the the visited array
        PriorityQueue<np> pq=new PriorityQueue<>();
        boolean visited[]=new boolean[cities.length];
        int f=0;

        //adding the current node in the priorityqueue
        pq.add(new np(0,0));
        while(!pq.isEmpty()){
            np curr=pq.remove();
            //if current node is not visited i visit it
            //and then add its edge cost to the final cost
            //and then add the current's neighbours in the priority queue
            if(!visited[curr.node]){
                visited[curr.node]=true;
                f+=curr.edge;
                //i traverse the row of the current node
                for(int i=0;i<cities[curr.node].length;i++){
                    if(cities[curr.node][i]!=0){
                        pq.add(new np(i,cities[curr.node][i]));
                    }
                }
            }
        }
        System.out.println(f);
    }

    public static void change_pix(boolean visited[][],int pix[][],int r,int c,int n,int og){
        //our base cases are
        //if row<0 || col<0 || row>length || col>length || visited[r][c] ||or current node not equal to color we want
        if(r<0 || c<0 || r >= pix.length || c>=pix[0].length || visited[r][c] || pix[r][c]!=og){
            return;
        }
        else{
            pix[r][c]=n;
        }
        //left
        change_pix(visited, pix, r, c-1, n, og);
        //right
        change_pix(visited, pix, r, c+1, n, og);
        //up
        change_pix(visited, pix, r-1, c, n, og);
        //down
        change_pix(visited, pix, r+1, c, n, og);
    }

    public static void kosaraju(ArrayList<edge>[] graph, int v){
        //initialising a visited boolean array and a new stack
        boolean visited[]=new boolean[v];
        Stack<Integer> s= new Stack<>();
        
        // step 1 --- i do top sort on the given graph nodes
        for(int i=0;i<v;i++){
            if(!visited[i]){
                topsortutil(graph, visited, s, i);
            }
        }

        // step 2 --- new transpose graph
        ArrayList<edge>[] trans = new ArrayList[v];
        //initialising the transpose graph with array list 
        //and making the visited array false
        for(int i=0;i<v;i++){
            trans[i]=new ArrayList<>();
            visited[i]=false;
        }
        for(int i=0;i<v;i++){
            for(int k=0;k<graph[i].size();k++){
                edge e=graph[i].get(k);
                trans[e.dest].add(new edge(e.dest,e.src));
            }
        }

        // step 3 -- dping dfs on the transpose graph
        while(!s.isEmpty()){
            int curr=s.pop();
            if(!visited[curr]){
                dfs(trans, curr, visited);
                System.out.println();
            }

        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String args[]) {
        // making an array to store arraylists within it that stores edges info
        //grpah is made using array of arraylist
            int v = 6 ;// to store the no of vertices
            ArrayList<edge>[] graph = new ArrayList[v];
        // here we could add any no of vertices
            build(graph);

        // //now we could do various action with our grpah
        // System.out.println("dfs printout");
        // dfs(graph,0,new boolean[graph.length]);

        // System.out.println("\nbfs printout");
        // bfs(graph,0,new boolean[graph.length]);

            // to find path bw src and destination
            // System.out.println(has_path(graph,src,dest,visit));

            // cycle detection
        // we send sec and parent as parameter
        // parent initialyy is -1
        // System.out.println(cycle(graph,visit));

            // bipartite graph
        // these are the type of graph in which src and destination are with 2 differnt
        // sets for every 2 nodes
        // int color[]=new int[graph.length];//we make a visit array to store if a node
        // is visited or not
        // for(int i=0;i<visit.length;i++){
        // color[i]= -1;
        // }//here i have an array color that stores 3 set of colors
        // 0->red, 1->blue, -1->no color which is by defalut
            // System.out.println(bipart(graph,src,visit,color));

            // cycle in a directed graph
        // System.out.println(bicycle(graph, visit));

            // topological sort
        // 1 using dfs
            // topsort(graph);
        // 2 using khans algorithm
        // here i use bfs
        // and an array that stores the inorder or outorder or every node
        // we add the nodes with lowest inorder in the queue
        // i rmeove an print the thop value and reduce their childs inorder with -1
        // and add those neighbours whose inorder is 0 now
        // and i reoest this till my queue is empty
            // khans(graph);

            //all path from src to target
        // allpath(graph,5,1,new String());

            //dijkistras algorithm
        //this algo is used to find the shortest path b/w the src and destination
        //here i take a class named as pair thath stores the data of node and path it traveled
        //i have a priority queue that provide me the pair for lowest path
        //i have an array named distance that stores the min distance from src to current node 
        //i also have a boolen array named visit that stored if a node is visited or not
        /*  the algo works as i insert the 1st pair of src to the priority queue than untill the pq gets empty
            i check if my current node is visited or not, if not i visit it and check the relaxation condition of current node and its neighbour
            and add the neighbour in the priority queue
        */
            // dig(graph,0);

            //bellmanford algo
        //this is smae sa dijkstras algo 
        //but here we are able to handle negative weights, that simulates the transaction 
        //limitation is thath it dosent work for negative cycyle graphs
        //here we find out theshortest distance for from source for every node, but here we dont have a compulsion to choose the shotest path first
        //but we run the loop for n-1 times, thus i dont require a visited array
            // bell(graph,0);

            //prism algo
        //this algo is used to find the minimum spanning tree for a given undirected graph
        //here we consider 2 sets, one is non mst and other is mst one
        //we have priority queue of pair calss that store the neighbour node and its edge weight  
        //here as we add one node in the mst, we check min neighbour edges in the priority queue
        //and add the one with the min edge weight in the mst, adding its weight in the total cost
        //here we have a visited array to store the visited nodes
            // prism(graph,0);

            //connectiong cities
        //this problem is a type of prism algo
        //we are given a 2d arrya b/w 2 nodes of cities
        //we have to find the min cost for joining all the nodes
            // int cities[][]={{0,1,2,3,4},{-1,0,5,0,7},{-1,-1,0,6,0},{-1,-1,-1,0,0},{-1,-1,-1,-1,0}};
            // connectedcities(cities); 

            //union and find
        //it is a concept of sets
        //here we have 2 array ,parent and rank array
        //parent stores the parent for a given indexed node
        //while rank helps us find that, which node is the abovet he parent child relationship
        //these 2 funcitons of find and union are seperate and done individually
        //while union joins 2 nodes and give us an parent child node resultant
        //while find returns us the super parent of the current node
            // int n=4;//we provide the no of nodes we require
            // int parent[]=new int[n];
            // int rank[]=new int[n];
            // for(int i=0;i<n;i++){
            //     parent[i]=i;
            // }
            // Arrays.fill(rank,0);//initila value of rank of every node is 0, as they all are at same level
            // union(rank,parent,0,1);
            // union(rank,parent,0,3);
            // union(rank,parent,0,2);
            // System.out.println(find(parent, 3));

            //kruskals algo
        //this algo is an optimised version of prism algo,her we take the use of find and union functions
        //in this algo we sort the edges with respwct to their edge cost
        //and then union them, while checking then the 2 nodes being in union must have different parents
        //as if 2 nodes have same parent that means we could make it a loop
        //we have to join all node with dfferent parents
        //we have the input as the arraylist of edges
           
            //floodfill algo
        //given a 2d array that contains differnet color pixels
        ////we have to change all the current selected colors that touches or selected index, but stop for 0's
            // int pix[][]={{5,1,1},{1,1,0},{1,0,1},{3,4,1}};
            // //we make a booelan 2d array of same size as pix to check if ww have visited the curent position
            // boolean visited[][]=new boolean[pix.length][pix[0].length];
            // change_pix(visited,pix,1,1,3,pix[1][1]);
            // for(int i=0;i<pix.length;i++){
            //     for(int k=0;k<pix[0].length;k++){
            //         System.out.print(pix[i][k]+" ");
            //     }
            //     System.out.println();
            // }

            //strongly connected components // kosaraju algo
        //these are the grpah in which from one node we could reach every other node of the graph
        //this is a dfs based algorithm
        //  1-> we do topologival sorting and make a stack
        //  2-> we make a new transpose grpah of the orignal graph // changing the directions of edges src and dest
        //  3-> we do the dfs on the transpose graph 
        kosaraju(graph,v);
    }
}