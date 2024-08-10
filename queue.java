import java.util.*;
public class queue {

    public static void non_rep(String str){
        int freq[]=new int [26];
        Queue<Character> q=new LinkedList<>();
        for(int i=0;i<str.length();i++){
            char x=str.charAt(i);
            q.add(x); 
            freq[x-'a']+=1;
            while(!q.isEmpty() && freq[q.peek()-'a']>1){
                q.remove();
            }
            if(q.isEmpty()){
                System.out.print("-1 ");
            }
            else{
                System.out.print(q.peek()+" ");
            }
        }
    }
    public static void main(String args[]){
        String str="ababacdbc";
        non_rep(str);

    }
}
