// 


public class linkedlist{ 
    public static class node{
        int data;
        node next;
        public node(int data){
            this.data=data;
            this.next=null;
        }
    };

    public static node head;
    public static node tail;

    public void addfirst(int data){
        node newnode=new node(data);
        if(head==null){
            head=tail=newnode;
            return;
        }
        newnode.next=head;
        head=newnode;
    }

    public void addlast(int data){
        node newnode=new node(data);
        tail.next=newnode;
        tail=newnode;
    }

    public void print_node(){
        if(head==null){
            System.out.println("empty");
        }
        node temp=head;
        while(temp!=null){
            System.out.print("->"+temp.data);
            temp=temp.next;
        }
        System.out.println();
    }

    public static void initialize(int data){
        node newnode=new node(data);
        head=tail=newnode;
    }

    public static boolean detect_cycle(){
        node slow=head;
        node fast=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast){
                return true;
            }
        }
        return false;
    }

    public static void remove_cycle(){
        node slow=head;
        node fast=head;
        while(fast!=null && fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(slow==fast){
                System.out.println("loop is present");
                break;
            }
        }
        slow=head;
        int count=1;
        while(slow!=fast){
            slow=slow.next;
            fast=fast.next;
            count++;
        }
        System.out.println("loop intersection at position : "+count);
        while(fast.next!=slow){
            fast=fast.next;
        }
        fast.next=null;
        System.out.println("loop removed !!");


    }
    
    public static node middle(node head){
        node slow=head;
        // node fast=head; //-->for pallindrome
        node fast=head.next; //-->for division
        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    public static node reverse(node middle){
        node next;
        node curr=middle;
        node prev=null;
        while(curr!=null){
            next=curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }
    
    public boolean is_pallindrome(){
        node middle=middle(head);
        node prev=reverse(middle);
        node right_side=prev;
        node left_side=head;
        while(right_side!=null){
            System.out.println(left_side.data+" "+right_side.data);
            if(right_side.data!=left_side.data){
                return false;
            }
            right_side=right_side.next;
            left_side=left_side.next;
        }
        return true;
    }
    
    public static node merge_sort(node head){
        while(head==null || head.next==null){
            return head;
        }
        node middle=middle(head);
        node right_head=middle.next;
        middle.next=null;
        node left_head=head;
        node new_left=merge_sort(left_head);
        node new_right=merge_sort(right_head);
        return merge(new_left,new_right);
        
    }

    public static node merge(node left,node right){
        node merge_ll=new node(-1);
        node temp=merge_ll;
        while(left!=null && right!=null){
            if(left.data<right.data){
                temp.next=left;
                temp=temp.next;
                left=left.next;
            }
            else{
                temp.next=right;
                temp=temp.next;
                right=right.next;
            }
        }
        while(left!=null){
            temp.next=left;
            temp=temp.next;
            left=left.next;
        }
        while(right!=null){
            temp.next=left;
            temp=temp.next;
            right=right.next;
        }
        return merge_ll.next;
    }

    public static void zigzag(node head){
        node middle=middle(head);
        node rev=reverse(middle);
        node first=head;
        node sec=rev;
        node nextl=head;
        node nextr=sec;
        while(first!=null && sec!=null){
            nextl=first.next;
            first.next=sec;

            nextr=sec.next;
            sec.next=nextl;

            first=nextl;
            sec=nextr;
        }
    }

    public static void main(String args[]){
        //add and remove data
        {
        // linkedlist ll= new linkedlist();
        // ll.initialize(4);
        // ll.addfirst(9);
        // ll.addlast(8);
        // ll.addlast(6);
        // ll.addlast(7);
        // ll.addlast(5);
        // ll.print_node();
        }
        
        //cycle in linked list
        {
        // linkedlist l2=new linkedlist();
        // head= new node(2);
        // head.next=new node(4);
        // head.next.next=new node(5);
        // head.next.next.next=new node(8);
        // head.next.next.next.next=new node(7);
        // head.next.next.next.next.next=new node(0);
        // head.next.next.next.next.next.next=head.next.next.next;
        // System.out.println(l2.detect_cycle());
        // l2.remove_cycle();
        // System.out.println(l2.detect_cycle());
        // l2.print_node();
        }

        //pallindrome
        {
            // linkedlist l3=new linkedlist();
            // l3.initialize(1);
            // l3.addlast(2);
            // l3.addlast(6);
            // l3.addlast(7);
            // l3.addlast(1);
            // l3.print_node();
            // System.out.println(l3.is_pallindrome());
        }

        //merge sort
        {
            // linkedlist l4=new linkedlist();
            // l4.initialize(0);
            // l4.addlast(1);
            // l4.addlast(8);
            // l4.addlast(6);
            // l4.addlast(4);
            // l4.addlast(3);
            // l4.print_node();
            // System.out.println(head.data);
            // l4.head=merge_sort(head);
            // l4.print_node();
            
        }
    
        //zigzag
        {
            linkedlist l5 = new linkedlist();
            // l5.initialize(1);
            l5.addlast(2);
            l5.addlast(3);
            l5.addlast(4);
            l5.addlast(5);
            l5.addlast(6);
            l5.print_node();
            // l5.zigzag(head);
            l5.print_node();
        }
    }
}