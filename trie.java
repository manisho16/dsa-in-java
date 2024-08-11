import java.util.*;
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

    public static void main(String args[]){
        String dict[]={"set","see","tes","tee","andy"};
        
        for(int i=0;i<dict.length;i++){
            insert(dict[i]);
        }
        for(int i=0;i<dict.length;i++){
            System.out.print(dict[i]+" "+valid(dict[i]));
            System.out.println();
        }
        
    }
}