import java.util.*;

public class greedy {
    
    public static void activity(){
        int start[]={1,3,0,5,8,5};
        int end[]={2,4,6,7,9,9};

        //a 2d array to store the data stored in ascending order with respect to end time
        int activity[][]= new int [start.length][3];
        for(int i=0;i<start.length;i++){
            activity[i][0]=i;
            activity[i][1]=start[i];
            activity[i][2]=end[i];
        }
        Arrays.sort(activity,Comparator.comparingDouble(o->o[2]));

        int lastend=activity[0][2];// the initial end
        int count=1;
        for(int i=1;i<start.length;i++){
            if(activity[i][1]>lastend){
                lastend=activity[i][2];
                count++;
            }
        }
        System.out.println(count);
    }

    public static void knapsack(){
        int val[]={60,100,120};
        int wei[]={10,20,30};
        int weight=50;

        //storing the index and ratio withina 2d array
        double ratio[][]=new double[val.length][2];
        for(int i=0;i<val.length;i++){
            ratio[i][0]=i;
            ratio[i][1]=val[i]/(double)wei[i];
        }
        

        Arrays.sort(ratio,Comparator.comparingDouble(o->o[1]));
        for(int i=0;i<val.length;i++){
            System.out.println(ratio[i][1]);
        }
        int cap=weight;
        double result=0;
        for(int i=val.length-1;i>=0;i--){
            int y=(int)ratio[i][0];
            if(wei[y]<=cap){
                cap-=wei[y];
                result+=val[y];
            }
            else{
                result+=(cap * ratio[i][1]);
                cap=0;
            }
        }
        System.out.println(result);
    }

    public static void coins(){
        int currency[]={1,2,5,10,20,50,100,200,500,2000};
        Arrays.sort(currency);
        ArrayList<Integer> count=new ArrayList<>();
        int amount=590;
        int cap=amount;
        int required=0;
        for(int i=currency.length-1;i>=0;i--){
            while(currency[i]<=cap){
                cap-=currency[i];
                System.out.println("left: "+cap);
                count.add(currency[i]);
                required++;
            }
        }
        System.out.println("notes requires = "+required);
        for(int i=0;i<count.size();i++){
            System.out.println(count.get(i));
        }
    }

    public static void coco(){
        
        Integer costver[]={2,1,3,1,4};
        Integer costhor[]={4,1,2};
        Arrays.sort(costver,Comparator.reverseOrder());
        Arrays.sort(costhor,Comparator.reverseOrder());

        int i=0,j=0;
        int hp=1,vp=1;
        int cost=0;
        while(i<costver.length && j<costhor.length){
            if(costver[i]>costhor[j]){
                cost += hp*costver[i];
                vp++;i++;
            }
            else{
                cost+=vp*costhor[j];
                hp++;j++;
            }
        }
        while(i<costver.length){
            cost += hp*costver[i];
            vp++;i++;
        }
        while(j<costhor.length){
            cost+=vp*costhor[j];
            hp++;j++;
        }
        System.out.println(cost);
    }

    public static void main(String Args[]){
        //activity selection
            //activity();

        //fractional knapsack
            //knapsack();

        //indina coins
            //coins();

        //chocolate
            coco();
    }
}
