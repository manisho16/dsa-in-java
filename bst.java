import java.util.*;

class bst{

    public static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    };

    public static void inorder(Node root){
        if(root==null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }

    public static Node insert(Node root,int data){
        if(root==null){
            root = new Node(data);
            return root;
        }

        if(data<root.data){
            root.left=insert(root.left,data);
        }
        else{
            root.right=insert(root.right,data);
        }
        return root;
    }

    public static boolean search(Node root,int data){
        if(root==null){
            return false;
        }
        if(root.data==data){
            return true;
        }
        if(data<root.data){
            return search(root.left, data);
        }
        else{
            return search(root.right, data);
        }
    }

    public static void inrange(Node root, int a, int b){
        if(root==null){
            return;
        }
        if(root.data<=b && root.data>=a){
            inrange(root.left,a,b);
            System.out.print(root.data+" ");
            inrange(root.right,a,b);
        }
        else if(root.data<a){
            inrange(root.right,a,b);
        }
        else{
            inrange(root.left,a,b);
        }
    }

    public static Node findSuccessor(Node root){
        if(root==null){
            return null;
        }
        Node toreturn=root;;
        while(root!=null){
            root=root.left;
        }
        return toreturn;
    }

    public static Node delete(Node root, int data){
        if(root==null){
            return null;
        }
        if(data<root.data){
            root.left=delete(root.left, data);
        }
        else if(data>root.data){
            root.right=delete(root.right, data);
        }
        // when we have found the value we check for its children
        else{
            //for single child
            if(root.left==null){
                return root.right;
            }
            if(root.right==null){
                return root.left;
            }

            //fordouble childs;
            Node successor=findSuccessor(root.right);
            root.data=successor.data;
            //here for the right sub tree i find and delete the successiove node
            root.right=delete(root.right,successor.data);
        }

        return root;

    }

    public static void leavepath(Node root, ArrayList<Integer> path){
        //to find the path from root to leaves
        if(root==null){
            return;
        }
        //firstly i add the data in the arraylist
        path.add(root.data);
        //chek if it is leave node or not
        
        if(root.left==null && root.right==null){
            print(path);
        }
        //i traverse to the left subtree till the leave gets
        leavepath(root.left,path);
        leavepath(root.right,path);
    
        path.remove(path.size()-1);
    }

        public static void print(ArrayList<Integer> path){
            for(int i=0;i<path.size();i++){
                System.out.print(path.get(i)+" ");
            }
            System.out.println();
        }

    public static boolean validate(Node root,Node min, Node max){
        /*with each traversal to subroot i have condition that 
        the root.data should follow, initially the max and min==null
        if i got to left tree tehn max is the root.data and min remains null
        i have return false if at any node the data gets>max;
        same for right tree return false if the root.data<min;
        */
        if(root==null){
            return true; 
        }
        if(min!=null && root.data <= min.data){
            return false;
        }
        if(max!=null && root.data >= max.data){
            return false;
        }
        boolean left=validate(root.left,min,root);
        boolean right=validate(root.right,root,max);
        return left && right;

    }

    public static Node mirror(Node root){
        if(root==null){
            return null;
        }
        Node left=mirror(root.left);
        Node right=mirror(root.right);

        //exchanging theh nodes
        root.left=right;
        root.right=left;
        return root;
    }

    public static class info{
        boolean isbst;
        int size;
        int min;
        int max;

        info(boolean isbst, int size, int min, int max){
            this.isbst=isbst;
            this.size=size;
            this.min=min;
            this.max=max;
        }
    }

    public static int maxbst=0;

    public static info largestbst(Node root){
        //if my root has reached to null that means it is a valid bst in lowest stage
        //thus its isze is 0 nad natural max and min are set
        if(root==null){
            return new info(true, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);    
        } 

        //i traverse to left and right nodes
        info left=largestbst((root.left));
        info right=largestbst((root.right));

        //i make a local size value to store the value of left + right tree along with self node
        int size=left.size + right.size +1;

        //now for local nodes i find their min and max by comapring the node data and 
        //respective min and max data of the left and right subtrees
        int min=Math.min(root.data,Math.min(left.min,right.min));
        int max=Math.max(root.data,Math.max(left.max,right.max));

        //for the currretn root i make an info class storing the repective info values if certain conditions are met
        /*the condition is i check if left and right bst are true
            if they are true then i check along 2 other conditions
            if either my left node is null or the root data is greater than left trees max
            and either the right node is null or root data is less than the min of right
            if it satisfies the condition then its a valid root of the bst 
         */
        if (left.isbst && right.isbst && (root.left == null || root.data > left.max) && (root.right == null || root.data < right.min)) {
            maxbst = Math.max(size, maxbst);
            return new info(true, size, min, max);
        }
        return new info(false,size,min,max);
    }

    public static void main(String args[]){
        int arr[]={2,5,6,4,7,9,0};
        Node root=null;
        for(int i=0;i<arr.length;i++){
            root=insert(root,arr[i]);
        }
        inorder(root);
        // System.out.println(search(root,3));
        // inrange(root,6,8);
        System.out.println();
        // ArrayList<Integer> path=new ArrayList<>();
        // leavepath(root,path);

        // Node min=null;
        // Node max=null;
        // System.out.println(validate(root,min,max));
        // root=mirror(root);

        largestbst(root);
        System.out.println(maxbst);
    }
}