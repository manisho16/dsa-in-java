
import java.util.*;

public class heaps {
    /*there are 2 concpets we use here ehich includes
     * priority queues and heaps
     * priority queue sorts the datat in it in an ascending order or the 
     * way the user wants it to be made priority
     */

    /* while in case of heap we have max heap and min heap as the important 
     * key concepts
     * there are 3 properties it shoul follow
     * binary tree -- complate binary tree --- heap property should be followed
     *  in min heap-> child >= parent
     *  in max heap-> child <=parent 
     */

    public static void print(ArrayList<Integer> arr){
        for(int i=0;i<arr.size();i++){
            System.out.print(arr.get(i)+" ");
        }
    }

    public static void heapify(ArrayList<Integer> arr, int i, int size){
        //we have max as the aprent index
        //and left and right as its childs index
        int left=2*i+1;
        int right=2*i+2;
        int max=i;
        //for ascending order we do a max heap
        //i.e parent should be greater than the child
        if(left<size && arr.get(left)>arr.get(max)){
            max=left;
        }
        if(right<size && arr.get(right)>arr.get(max)){
            max=right;
        }

        //if max is not equal to initial value that mean swe have 
        //to correct the heap by exchanging the child and the parent
        if(max!=i){
            //swapping the parent and the child
            int temp=arr.get(i);
            arr.set(i,arr.get(max));
            arr.set(max,temp);
            //i heapify again for the current parent
            heapify(arr,max,size);
        }
    }

    public static void heapsort(ArrayList<Integer> arr){
        /*for ascending order we find the max heap
         then after heapify we swap the 1st and last digitin the array
         then we do heapify again for n-1 digits
        */

        //making the array in a max heap fashion
        for(int i=(arr.size()/2) - 1;i>=0;i--){
            heapify(arr,i,arr.size());
        }

        for(int i=arr.size()-1;i>=0;i--){
            //swapping 1st and last digits
            int temp=arr.get(0);
            arr.set(0,arr.get(i));
            arr.set(i,temp);

            //heapify for n-1 items
            heapify(arr,0,i);
        }
        System.out.println();
    }

    public static void add(ArrayList<Integer> arr, int data){
        //for adding we add the datat at the last index 
        arr.add(data);
        int child=arr.size()-1;
        while(child>0){
            int parent=(child-1)/2;

            //checking the condition
            if(arr.get(child)<arr.get(parent)){
                int temp=arr.get(child);
                arr.set(child,arr.get(parent));
                arr.set(parent,temp);

                //making the child as the parent
                child = parent;
            }
            else{
                break;
            }
        } 
        heapsort(arr);   
    }

    public static int delete(ArrayList<Integer> arr){
        int data=arr.get(0);

        //swapping the first and last no.s
        int temp=arr.get(0);
        arr.set(0,arr.get(arr.size()-1));
        arr.set(arr.size()-1,temp);
        
        //removing the element
        arr.remove(arr.size()-1);

        heapsort(arr);
        return data;

    }

    public static class point implements Comparable<point>{
        //we are implementing a comparable class to our point object
        int x,y,dist,index;

        public point(int x, int y, int dist,int index){
            this.x=x;
            this.y=y;
            this.dist=dist;
            this.index=index;
        }
        @Override
        public int compareTo(point p2){
            return this.dist-p2.dist;
        }
    }

    public static class index implements Comparable<index>{
        int loc,data;
        index(int loc, int data){
            this.loc=loc;
            this.data=data;
        }
        @Override
        public int compareTo(index i2){
            //for getting the largest element at the top
            return i2.data-this.data;
        }
    }

     public static void main(String args[]){
        //heaps are given in the form of arraylist
        /*for addition 
        we add the element at the last element and then fic the heap
        */
        ArrayList<point> arr=new ArrayList<>();
        // arr.add(1);
        // arr.add(6);
        // arr.add(8);
        // arr.add(2);
        // arr.add(10);
        // print(arr);
        // heapsort(arr);
        // print(arr);

        // add(arr,0);
        // print(arr);
        
        // delete(arr);
        // print(arr);
        // delete(arr);
        // print(arr);
        // add(arr,-1);
        // print(arr);
        // add(arr,11);
        // print(arr);

        //priority queue
        /* in the priority queue the dtaa is stored in order of priority */
        //nearby cars
        //we have to find the order in which the cars are nearest to the origin

        // int p[][]={{0,2},{0,1},{0,0},{0,7},{0,5}};
        // int k=2;
        // //here we make an object to store the points coordinate and the distance fom origin of each 
        // //thus we make a priority queue of objects
        // PriorityQueue<point> ps=new PriorityQueue<>();
        // for(int i=0;i<p.length;i++){
        //     //we find the distance and store them in their respective objects
        //     int dist=(p[i][0]*p[i][0])+(p[i][1]*p[i][1]);
        //     ps.add(new point(p[i][0],p[i][1],dist,i));
        // }
        // for(int i=0;i<p.length;i++){
        //     System.out.println("p "+ps.remove().index);
        // }
       
        //sliding window
        //given an array of elements have to find the max for each window slid
        int w[]={1,2,3,4,5,6,0,3,4,9};
        int k=4;

        for(int i=0;i<w.length;i++){
            System.out.print(w[i]+" ");
        }
        System.out.println();
        for(int i=1;i<k;i++){
            System.out.print(". ");
        }
        //we make a priority queue set to reverse to get hte max element for the k element s
        //then we add the next elemet and check if the index of max is within our window if yes then it is the max
        //else we remove the max and new max becomes our windows max
        PriorityQueue<index> pi=new PriorityQueue<>();
        
        //adding the first k elemets in the priority queue
        for(int i=0;i<k;i++){
            pi.add(new index(i,w[i]));
        }

        //making a new array to store the results
        int res[]=new int[w.length-k+1];
        //adding the largest elemtnt for thr the initial window
        res[0]=pi.peek().data;
        for(int i=k;i<w.length;i++){
            //checking if the index of max is not in range then we remove it
            while(pi.size()>0 && pi.peek().loc <= (i-k)  ){
                pi.remove();
            }
            //adding new after the next window
            pi.add(new index(i,w[i]));

            res[i-k+1]=pi.peek().data;
        }

        for(int i=0;i<res.length;i++){
            System.out.print(res[i]+" ");
        }

    }
}
