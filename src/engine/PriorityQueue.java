package engine;

import java.util.ArrayList;

import model.world.Champion;

public class PriorityQueue {
 
   private Comparable[] elements;
   private int nItems;
   private int maxSize;

   public PriorityQueue(int size){
      maxSize = size;
      elements = new Comparable[maxSize];
      nItems=0;
   }
    
   public void insert(Comparable item) {
      
      int i;
      for (i = nItems - 1;i >= 0 && item.compareTo(elements[i]) > 0;i--)
         elements[i + 1] = elements[i];

      elements[i + 1] = item;
      nItems++;
   }
    
   public Comparable remove() {
      nItems--;
      return elements[nItems];
   }
   
   public void remove(Comparable item) {
	   ArrayList<Comparable> tmp=new ArrayList<Comparable>();
	   int x= nItems-1;
	   for(int i=x;i>=0;i--) {
		   if(elements[i]!=item)
			   tmp.add(remove());
		   else {
			   remove();
		   }
	   }
	   for(int i=0;i<tmp.size();i++)
		   this.insert(tmp.get(i));
   }
    
   public boolean isEmpty() {
      return (nItems == 0);
   }
    
   public boolean isFull() {
      return (nItems == maxSize);
   }
  
   public Comparable peekMin() {
      return elements[nItems-1];
   }
     
   public int size() {
      return nItems;
   }
   
   
   public String toString() {
	   
	   PriorityQueue pq= new PriorityQueue(6);
	   String s="";
	   
	   while(!this.isEmpty()) {
		   Champion champ= (Champion) remove();
		   pq.insert(champ);
		   s=s+champ.getName()+"-";
	   }
	   while(!pq.isEmpty()) {
		   this.insert(pq.remove());
	   }
	   return s;
		
	}
   
   
   
   
   
   
   
}
