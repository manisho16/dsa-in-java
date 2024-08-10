import java.util.*;
public class binary_tree {
    
    static class Node{
        int data;
        Node left,right;

        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }

    static int indx=-1;

    public static Node Build_tree(int arr[]){
        //we traverse the elements of the array
        indx++;
        if(indx>=arr.length || arr[indx]==-1){
            //if found -1 that means we found a null node
            return null;
        }
        //if not we make a new node and call the same for its left and right
        Node newnode=new Node(arr[indx]);
        newnode.left=Build_tree(arr);
        newnode.right=Build_tree(arr);

        //at last we return the current node
        return newnode;
    }

    public static void preorder(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.data+" ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void inorder(Node root){
        if(root==null){
            return;
        }
        inorder(root.left);
        System.out.print(root.data+" ");
        inorder(root.right);
    }

    public static void postorder(Node root){
        if(root==null){
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data+" ");
    }

    public static void levelorder(Node root){
        Queue<Node> q= new LinkedList<>();
        q.add(root);
        q.add(null);
        while(!q.isEmpty()){
            Node current=q.remove();//take the initial root inserted in the queue
            if(current==null){
                System.out.println();//we print the next level
                if(q.isEmpty()){
                    break;// it means that the queue is empty of every element
                }
                else{
                    q.add(null);//we add null in queue to say that next line is inserted
                }
            }
            else{
                System.out.print(current.data+" ");//we print the data in the queue
                if(current.left!=null){
                    q.add(current.left);
                }
                if(current.right!=null){
                    q.add(current.right);
                }
            }
        }   
    }

    public static int height(Node root){
        if(root==null){
            return 0;
        }
        int left=height(root.left);
        int right=height(root.right);
        return Math.max(left,right)+1;
    }

    public static int count_nodes(Node root){
        if(root==null){
            return 0;
        }
        int left=count_nodes(root.left);
        int right=count_nodes(root.right);
        return (left+right+1);
    }

    public static int diameter(Node root){
        if(root==null){
            return 0;
        }
        int ld=diameter(root.left);//get the diameter for left tree
        int rd=diameter(root.right);//getting the diameter for right tree

        //getting the roght and left height to find diameter through self
        int rh=height(root.right);
        int lh=height(root.left);
        int self=rh+lh+1;
        return Math.max(Math.max(ld,rd),self);
    }

    public static boolean isidentical(Node root, Node subroot){
        //we check the structure of both the trees
        if(root==null && subroot==null){
            return true;
        }
        else if(root==null || subroot==null || root.data!=subroot.data){
            return false;
        }

        if(!isidentical(root.left, subroot.left)){
            return false;
        }
        if(!isidentical(root.right, subroot.right)){
            return false;
        }
        return true;
    }

    public static boolean sub_tree(Node root, Node subroot){
        //firstly we check for the root , if it is available or not
        //if yes then we chaeck if the subtree is identical or not

        if(root==null){ // if we dont have a tree we return false
            return false;
        }
        if(root.data==subroot.data){
            if(isidentical(root,subroot)){
                return true;
            }
            else{
                return false;
            }
        }

        //if the current node is not equal to subroot then we check the right and left nodes of the main node
        boolean left = sub_tree(root.left,subroot);
        boolean right = sub_tree(root.right,subroot);

        //if any of them shows true we return true
        return left||right;

    }

    static class node_info{
        int key;
        Node node;

        node_info(Node node, int key){
            this.node=node;
            this.key=key;
        }
    }

    public static void top(Node root){
        int min,max;
        min=max=0;

        //we have made an info class that store the node and its order/key

        //making a hashmap to store the topvalue and node and check it with time
        HashMap<Integer,Node> m=new HashMap<>();

        // a queue to do the level order traversal and store the info of every node
        Queue<node_info> q= new LinkedList<>();
        q.add(new node_info(root,0));
        q.add(null);
        while(!q.isEmpty()){
            node_info current=q.remove();
            if(current==null){
                if(q.isEmpty()){
                    break;
                }
                else{
                    q.add(null);
                }
            }
            else{
                //we have added the current's order int he hashmap with its node 
                if(!m.containsKey(current.key)){
                    m.put(current.key,current.node);
                }

                /*we check for its right and left node if they are not null 
                we add them in the queue and change their order/key accordingly
                also we change max and min value comapring them with order/key of the current node */
                if(current.node.left!=null){
                    q.add(new node_info(current.node.left,current.key-1));
                    min=Math.min(min,current.key-1);
                }
                if(current.node.right!=null){
                    q.add(new node_info(current.node.right,current.key+1));
                    max=Math.max(max,current.key+1);
                }
            }
        }
        for(int i=min;i<=max;i++){
            if(m.get(i)!=null){

                System.out.print(m.get(i).data+" ");
            }
        }

    }

    public static boolean getpath(Node root, int n, ArrayList<Node> arr){
        if(root==null){
            return false;
        }
        //we add the node in the array
        arr.add(root);
        if(root.data==n){
            return true;
        }
        
        //we check the left and right for the node
        boolean left=getpath(root.left,n,arr);
        boolean right=getpath(root.right,n,arr);

        if(left||right){
            return true;
        }

        //if we are unable to find the digit from this node then we remove it from the arryalist and return false
        arr.remove(arr.size()-1);
        return false;
    }

    public static int ancestor(Node root,int a,int b){
        /*the logic here is to find=the path from root to the given nodes
        and store them in different arrays
        then we iterate with each element of the array while they are same
        and return the node after which tey are not same whuch becomes their last common ancestor
        */
        ArrayList<Node> pa=new ArrayList<>();
        ArrayList<Node> pb=new ArrayList<>();

        //we check and find seperate path for both the nodes
        getpath(root,a,pa);
        getpath(root,b,pb);

        //we iterate both the array till we get our last common digit
        int i=0;
        for(; i<pa.size() && i<pb.size();i++){
            //we break our function if element of array are not equal
            if(pa.get(i)!=pb.get(i)){
                break;
            }
        }
        return pa.get(i-1).data;
    }

    public static int k_ancestor(Node root, int n, int a){
        //we return -1 if we have found null
        if(root==null){
            return -1;
        }
        /*we return 0 if we have found the data */
        if(root.data==n){
            return 0;
        }

        //if we havent found the node then we chek the left or right node for the required node
        int left=k_ancestor(root.left,n,a);
        int right=k_ancestor(root.right,n,a);

        //if bothe left and right gives us -1 then we return -1 means this node dosent counts and skip it
        if(left==-1 && right ==-1){
            return -1;
        }

        //else we find the max height from the left or right nodes
        int max=Math.max(left,right);
        //we check if we have satisfied the kth root and return of it is true
        if(max+1==a){
            System.out.println("ancestor of node is "+root.data);
        }
        //we have to return the max height of the required node from the current node
        return max+1;
    }

    public static void main(String args[]){
        //binary_tree building
        int arr[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,7,-1};
        Node root=Build_tree(arr);
        indx=-1;
        System.out.println("root data : "+root.data);
        
        //traversals
            // preorder(root);
            // System.out.println();
            // inorder(root);
            // System.out.println();
            // postorder(root);
            // System.out.println();
                levelorder(root);
        
        //height of a tree
            //System.out.println("height of tree id -> "+height(root));

        //count no of nodes
            // System.out.println("no of nodes in the tree are -> "+count_nodes(root));
            // System.out.println("no of edges in the tree are -> "+ (count_nodes(root)-1));

        //diameter of a tree
            // System.out.println("diameter of the tree is -> "+ diameter(root));

        int arr2[]={3,-1,6,7,-1};
        Node subroot=Build_tree(arr2);
        indx=-1;
        System.out.println("subroot data : "+subroot.data);
        //subtree of another tree
            // System.out.println("is it subtree : "+sub_tree(root,subroot));

        //top view of the tree
            // top(root);

        //lowest common ancestor
            // System.out.println("the lowest common ancestor is : "+ancestor(root,7,6));
        
        //kth ancestor
            k_ancestor(root,6,1);

    }
}
