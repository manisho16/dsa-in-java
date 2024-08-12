// import java.util.*;
public class trie{
    //these are k-noded trees
    //unlike a binary which have only 2 child nodes, it may have 'k' no of child nodes
    //it is used to store characters efficiently
    
    public static class node{
        node[] child=new node[26]; //making anode array of 26 indexes to store every character
        boolean eow=false; // to get if it is end of word or not

        public node(){
            for(int i=0;i<26;i++){
                child[i]=null;//initialising every index with null
            }
        }
    }

    //a node class for prefic question
    public static class prenode{
        prenode child[]=new prenode[26];
        boolean eow=false;
        int freq;

        prenode(){
            for(int i=0;i<26;i++){
                child[i]=null;
            }
            freq=1;
        }
    }

    public static node root = new node();//making a root node

    public static void insert(String word){
        //make a new node current to store the root node and future nodes
        node curr=root;
        //running a loop for each character
        for(int i=0;i<word.length();i++){
            //having a 'ci' for character index
            int ci=word.charAt(i)-'a';
            //checking if the child node at character index is null or not
            //it child node is null i make a new node and make it the current node
            if(curr.child[ci]==null){
                curr.child[ci]=new node();  
            }
            curr=curr.child[ci];
        }
        //at last i make the last charcters node's end of word as true
        curr.eow=true;
        System.out.println("inserted "+word);
    }

    public static boolean valid(String word){
        node curr=root;
        for(int i=0;i<word.length();i++){
            int ci=word.charAt(i)-'a';
            //if check if child node at current character's index is null or not
            //if it is null then i return false
            if(curr.child[ci]==null){
                return false;
            }
            curr=curr.child[ci];
        }
        //if we have reached at this line it means we have traversed the last character of the word
        //now we check if the last charcters node is end of word or not
        return curr.eow==true;
    }

    public static prenode preroot=new prenode();

    public static void preinsert(String word){
        prenode curr=preroot;
        for(int i=0;i<word.length();i++){
            int ci=word.charAt(i)-'a';
            if(curr.child[ci]==null){
                curr.child[ci]=new prenode();  
            }
            else{
                curr.child[ci].freq++;
            }
            curr=curr.child[ci];
        }
        curr.eow=true;
        System.out.println("inserted "+word);   
    }

    public static String presearch(String word){
        String res="";
        prenode curr=preroot;
        for(int i=0;i<word.length();i++){
            int ci=word.charAt(i)-'a';
            if(curr.child[ci].freq!=1){
                res+=word.charAt(i);
                curr=curr.child[ci];
            }
            else{
                res+=word.charAt(i);
                break;
            }
        }
        return res;
    }

    public static void prefix(String arr[]){
        for(int i=0;i<arr.length;i++){
            preinsert(arr[i]);
        }
        String ans[]=new String[arr.length];
        for(int i=0;i<arr.length;i++){
            String res=presearch(arr[i]);
            ans[i]=res;
        }
        for(int i=0;i<ans.length;i++){
            System.out.print(ans[i]+" ");
        }
    }

    public static boolean wb(String key){
        if(key.length()==0){
            return true;
        }
        //here i have taken i=1 as in java.substring method the ending index is not included
        //thus we take an incremented value to get our desired ending character
        for(int i=1;i<=key.length();i++){
            //i check if both my substring is present in the trie
            if(valid(key.substring(0, i)) && wb(key.substring(i))){
                return true;
            } 
        }
        return false;
    }

    public static boolean starts(String key){
        node curr=root;
        for(int i=0;i<key.length();i++){
            int ci=key.charAt(i)-'a';
            if(curr.child[ci]==null){
                return false;
            }
            curr=curr.child[ci];
        }
        return true;
    }

    public static int count(node root){
        if(root==null){
            return 0;
        }
        int res=0;
        //icheck every alphabet array to find if it is null nor not
        //if it is not null then i call count for that node 
        //to find the nodes that child node consist
        for(int i=0;i<26;i++){
            if(root.child[i]!=null){
                res+=count(root.child[i]);
            }
        }
        return res+1;
    }

    public static void main(String args[]){
        // String dict[]={"i","am","a","recommended","person","chap","not"};
        
        // for(int i=0;i<dict.length;i++){
        //     insert(dict[i]);
        // }

        // for(int i=0;i<dict.length;i++){
        //     System.out.print(dict[i]+" "+valid(dict[i]));
        //     System.out.println();
        // }

        //prefix problem
            // prefix(dict);

        //wordbreak problem
            // String key="iamrecommended";
            // System.out.println(wb(key));
        
        //starts with
            // String key="per";
            // System.out.println("key status "+ starts(key));
        
        //unique substring
            String key="ababa";
            //for this problem 
            //1. we find out all the suffixexs of the given key
            //2. we store them within a trie ds as it stores all the unique prefexes of a given word
            //thus it is also called prefix tree
            //3. then we find out all the nodes in the teir, to get the no of unique substrings
            for(int i=0;i<key.length();i++){
                insert(key.substring(i));
                //inserting all the suffixes in the tier
            }
            //calling a count function to give us the no of nodes in the tier
            System.out.println(count(root));
    }
}