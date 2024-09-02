import java.util.*;

public class dp {
    //dynamic programming is similar to recurssion 
    //nut it is a more optimised version of it
    //here we use 2 main methods for optimisation
    // 1-> memoization -- here with recurssion we also pass an array to store the result of current iteration
    // 2-> tabulation -- here we use loops to fill up our array values from lowest values to the final result
    
    public static int climbing_rec(int n){
        if(n==1 || n==0){
            return 1;
        }
        if(n<0){
            return 0;
        }
        return climbing_rec(n-1)+climbing_rec(n-2);
    }

    public static int climbing_memo(int ways[],int n){
        if(n==0){
            return 1;
        }
        if(n<0){
            return 0;
        }
        if(ways[n]!=-1){
            return ways[n];
        }
        ways[n]=climbing_memo(ways,n-1)+climbing_memo(ways,n-2);
        return ways[n];
    }

    public static void main(String args[]){
            
        //climbing stairs
    //find the no of ways i could reach nth stair given i could climb 1,2 steps
    //for climbing nth stair given 1,2 steps i have, it is the sum of steps required to reach n-1th step and n-2th step
    // thus for nth->i return (n-1)+(n-2)
        int n=4;
        // System.out.println(climbing_rec(n));
    //for memoization i make an array and pass it with the function
        // int ways[]=new int[n+1];
        // Arrays.fill(ways,-1);
        // System.out.println(climbing_memo(ways,n));
        // for(int i=0;i<ways.length;i++){
        //     System.out.print(ways[i]+" ");
        // }
    //for tabulation i use the array, initializing the initial known value and building the array till the end iteratively
        // int ways[]=new int[n+1];
        // ways[0]=1;
        // for(int i=1;i<ways.length;i++){
        //     //for the initial value of i=1
        //     if(i==1){
        //         ways[i]=ways[i-1];
        //     }else{
        //         ways[i]=ways[i-1]+ways[i-2];
        //     }
        // }
        // for(int i=0;i<ways.length;i++){
        //     System.out.print(ways[i]+" ");
        // }
    
        //0-1 knapsack
    //here i have 3 types of knapsack problems
    // 1-> fractional -- i use ratios, praportions
    // 2-> 0-1 -- here i either take or leave, one item only once
    // 3-> unbounded -- here i could take an item any no of time
    
    
    }
}
