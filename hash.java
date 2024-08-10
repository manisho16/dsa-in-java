import java.util.*;
//hashmaps uses the key and value pairs as their data storing technique
//impimentation of hashmaps are done using arrays of linkedlist  
public class hash<K,V> 
{
        
    //as we know  that hashmap is an linkedlist of linkedlist
    //thus we make 2 static values as size of bigger array bucket--> N
    //and size of innner linked list stored in it--> n
    private int n;
    private static final int N=4;
    public LinkedList<node> bucket[];

        public class node{
        K key;
        V val;
            public node(K key, V val){
                this.key=key;
                this.val=val;
            }
        }

        //constructor for our hashmap class
        @SuppressWarnings("unchecked")
        //removes any unchecked warnings
        public hash(){
            this.bucket=new LinkedList[N];//making an array of datatype linkedlist
            
            //initialisong every element of array with an empty linkedlist
            for(int i=0;i<bucket.length;i++){
                bucket[i]=new LinkedList<>();
            }
        }


    //making a hashfunction
    private int hashfunction(K key) {
        //we get a unique hash code using the functinoality of hashcode()
        int hash=key.hashCode();
        return (Math.abs(hash)) % N;// it provide a value with the range 0-N
    }

    private int searchll(K key, int bi){
        LinkedList<node> ll=bucket[bi];
        //i make a node of linkedlist that have the value of linkedlist present at bi
        int di=0;
        for(int i=0;i<ll.size();i++){
            node data=ll.get(i);
            if(data.key == key){
                return di;
            }
            di++;
        }
        return -1;
    }

    //function to get the data from teh hashmap
    public V get( K key){
        int bi=hashfunction(key);
        int di=searchll(key,bi);
        //if data index is not -1 then we get the value of the key
        if(di!=-1){
            return bucket[bi].get(di).val;
        }
        return null;
    }


    //making a put function to enter data in the hashmap
    public void put(K key, V val){
        //we get the bucket index for our data
        int bi=hashfunction(key);

        //now i get the data index from teh given bucket index
        int di=searchll(key,bi);

        //if the key is not present then we add a new node woth our data
        if(di==-1){
            bucket[bi].add(new node(key,val));
            n++;
        }
        //else if it is present we update it
        else{
            node n=bucket[bi].get(di);
            n.val=val;
        }

    }

    //make an array list as a keyset to store all teh value present in hashmap
    public ArrayList<K> keyset(){
        ArrayList<K> keys=new ArrayList<>();
        for(int i=0;i<N;i++){
            LinkedList<node> ll=bucket[i];
            for(node n:ll){
                keys.add(n.key);
            }
        }
        return keys;
    }

    public static void main(String args[]){
        hash<String, Integer> hp=new hash<>();//making a new hashmap object
        hp.put("india",1);
        hp.put("usa",4);
        hp.put("china",3);


        System.out.println(hp.keyset());
    }
}

