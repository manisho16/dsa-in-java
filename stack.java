import java.util.*;
// import java.math.*;
public class stack {

    // static class Stack
    //{
    //     static ArrayList<Integer> list= new ArrayList<>();
    //     public boolean isempty(){
    //         return list.size()==0;
    //     }
    //     public void push(int data){
    //         list.add(data);
    //     }
    //     public int pop(){
    //         if(isempty()){
    //             return -1;
    //         }
    //         int top=list.get(list.size()-1);
    //         list.remove(list.size()-1);
    //         return top;
    //     }
    //     public int peek(){
    //         if(isempty()){
    //             return -1;
    //         }
    //         return list.get(list.size()-1);
    //     }
    // }

    public static void bottom_push(Stack<Integer> s, int data){
        if(s.isEmpty()){
            s.push(data);
            return;
        }
        int top=s.pop();
        bottom_push(s,data);
        s.push(top);
    }

    public static String reverse_str(String str){
        Stack<Character> st=new Stack<>();
        int i=0;
        while(i<str.length()){
            st.push(str.charAt(i));
            i++;
        }

        StringBuilder res=new StringBuilder();
        while(!st.isEmpty()){
            char top=st.pop();
            res.append(top);
        }
        return res.toString();
    }
    
    public static void reverse(Stack<Integer> s){
        if(s.isEmpty()){
            return;
        }
        int top=s.pop();
        reverse(s);
        bottom_push(s, top);
    } 

    public static void print(Stack<Integer> s){
        while(!s.isEmpty()){
            System.out.println(s.pop());
        }
    }

    public static void maxspan(int stock[], int span[]){
        Stack<Integer> s = new Stack<>();
        span[0]=1;
        s.push(0);
        for(int i=1;i<stock.length;i++){
            int currval=stock[i];
            while(!s.isEmpty() && currval>=stock[s.peek()]){
                s.pop();
            }
            if(s.isEmpty()){
                span[i]=i+1;
            }
            else{
                int prevhigh=s.peek(); //make prevhigh as the top element
                span[i]=i-prevhigh; // we get the difference of the highs
            }
            s.push(i);
        }
    }

    public static void next_greater(int arr[], int right_greater[]){
        Stack<Integer> s=new Stack<>();
        s.push(arr.length-1);
        for(int i=arr.length-1;i>=0;i--){
            //empty all the elements which are smaller than teh current element
            while(!s.isEmpty() && arr[s.peek()]<=arr[i]){
                s.pop();
            }
            if(s.isEmpty()){
                right_greater[i]=-1;
            }
            else{
                right_greater[i]=arr[s.peek()];
            }
            s.push(i);
        }
        for(int i=0;i<right_greater.length;i++){
            System.out.print(right_greater[i]+" ");
        }
    }

    public static boolean paren(String str){
        Stack<Character> s=new Stack<>();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='(' || str.charAt(i)=='[' || str.charAt(i)=='{' ){
                s.push(str.charAt(i));
                System.out.println(s.peek());
            }
            else{
                char x=str.charAt(i);
                System.out.println(x);
                switch(x){
                    case ')':
                        if(s.peek()=='('){
                            System.out.println(s.pop());
                        }
                        else{
                            return false;
                        }
                        break;

                    case ']':
                        if(s.peek()=='['){
                            System.out.println(s.pop());
                        }
                        else{
                            return false;
                        }
                        break;

                    case '}':
                        if(s.peek()=='{'){
                            System.out.println(s.pop());
                        }
                        else{
                            return false;
                        }
                        break;
                }
            }
        }
        return true;
    }
    
    public static boolean paren_dupli(String str){
        Stack<Character> s=new Stack<>();
        for(int i=0;i<str.length();i++){
            //pushing every element other than the opening brackets
            if(str.charAt(i)!=')'){
                s.push(str.charAt(i));
            }
            else{//counting the digits b/w the brackets
                int count=0;
                while(s.peek()!='('){
                    count++;
                    s.pop();
                }
                if(count<1){
                    return false;
                }
            }
        }
        return true;
    }

    public static int max_area(int hist[]){
        int leftsmall[]=new int[hist.length];
        int rightsmall[]=new int[hist.length];
        Stack<Integer> sl=new Stack<>();sl.push(0);
        Stack<Integer> sr=new Stack<>();sr.push(hist.length-1);

        //for right smallest
        for(int i=hist.length-1;i>=0;i--){
            while(!sr.isEmpty() && hist[sr.peek()]>=hist[i]){
                sr.pop();
            }
            if(sr.isEmpty()){
                rightsmall[i]=hist.length;
            }
            else{
                rightsmall[i]=sr.peek();
            }
            sr.push(i);
        }

        //for left smallest
        for(int i=0;i<hist.length;i++){
            while(!sl.isEmpty() && hist[sl.peek()]>=hist[i]){
                sl.pop();
            }
            if(sl.isEmpty()){
                leftsmall[i]=-1;
            }
            else{
                leftsmall[i]=sl.peek();
            }
            sl.push(i);
        }
        int start,end,height,area;
        int res=0;
        
        for(int i=0;i<hist.length;i++){
            
            start=leftsmall[i];
            end=rightsmall[i];
            height=hist[i];
            area=height*(end-start-1);
            res=Math.max(area,res);
        }
        return res;

    }

    public static void main(String args[]){
        // Stack s1= new Stack();
        //  Stack<Integer> s= new Stack<>(); // java collection framework
        //  s.push(3);
        //  s.push(2);
        //  s.push(1);
        //  bottom_push(s,0);
        //  System.out.println("reverse.");
        //  reverse(s);

        //  print(s);

        // String str="123456";
        // String result=reverse_str(str);
        // System.out.println(result);

        // int stock[]={100,80,60,70,60,85,100};
        // int span[]=new int[stock.length];
        // maxspan(stock,span);
        // for(int i=0;i<span.length;i++){
        //     System.out.println(span[i]);
        // }

        // int arr[]={6,8,0,1,3};
        // int right_greater[]=new int[arr.length];
        // next_greater(arr,right_greater);

        // String str="({[]})";
        // System.out.println(paren(str));

        // String str="((a+b)+1)";
        // System.out.println(paren_dupli(str));

        //finding the max area rectangle in histogram
        /* 1.for each element in the array find the right lowest and left lowest hight's index
         * 2. height is the lowest among the left and right height
         * 3. width is the difference of the indexes- i-j-1;
         * 4. then we find the area; */
        int hist[]={2,1,5,6,2,3};
        System.out.println(max_area(hist));

    }
}
