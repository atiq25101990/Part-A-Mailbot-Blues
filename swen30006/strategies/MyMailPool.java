package strategies;


import java.util.Collections;
import java.util.Stack;

import automail.MailItem;
import automail.PriorityMailItem;

public class MyMailPool  implements IMailPool{

	private Stack<MailItem> nonPriorityPool;
	private Stack<MailItem> priorityPool;
	
	public MyMailPool(){
		// Start empty
		nonPriorityPool = new Stack<MailItem>();
		priorityPool = new Stack<MailItem>();
	}
	
	//returns the pool size of priority mail items
	public int getPriorityPoolSize(){
		// This is easy
		return priorityPool.size();
	}
	
	//returns the pool size of non priority mail items
	public int getNonPriorityPoolSize() {
		// So is this
		return nonPriorityPool.size();
	}

	//Adding mail items to the pool
	public void addToPool(MailItem mailItem) {
		// Check whether it has a priority or not
		if(mailItem instanceof PriorityMailItem){
			// Add to priority items
			
			//Sorting the priority mail in ascending order of priority level of the mail item 
			//to be delivered so that from last element which is having
			//the highest value would get popped out as the collection is stack(LIFO)
			sortPriorityPool(priorityPool);
			//Collections.sort(priorityPool, comparePriority(tpriorityPool, tpriorityPool));
			priorityPool.push(mailItem);
			

		}
		else{
			// Add to nonpriority items
			
			//Sorting the non priority mail in descending order of floor of the mail item 
			//to be delivered so that from last element which is having
			//the lowest floor value would get popped out as the collection is stack(LIFO)
			descSelectionSort(nonPriorityPool);
			nonPriorityPool.add(mailItem);
		}
	}
	
	public MailItem getNonPriorityMail(){
		if(getNonPriorityPoolSize() > 0){
			
			//
			return nonPriorityPool.pop();
		}
		else{
			return null;
		}
	}
	
	public MailItem getHighestPriorityMail(){
		if(getPriorityPoolSize() > 0){
			
			//Sorting the priority mail in ascending order of priorities so that 
			//last element which is having the highest priority would get popped out
			sortPriorityPool(priorityPool);
			System.out.println("Priority pool after softing "+priorityPool);
			return priorityPool.pop();
		}
		else{
			return null;
		}
		
	}
	


	public MailItem getBestMail(int FloorFrom, int FloorTo) {
		
		
		System.out.println("Floor from "+FloorFrom);
		System.out.println("Floor to "+FloorTo);
		
		return null;
	}
	
	//sorting non priority pool in descending order so that lowest floor 
	//mail item would get popped out.
	//[Sorting is done using selection sort in descending order]
	public Stack<MailItem> descSelectionSort(Stack<MailItem> nonPriorityPool2){
        
        for (int i = 0; i < nonPriorityPool2.size() - 1; i++)  
        {  
            int max = i;  
            for (int j = i + 1; j < nonPriorityPool2.size(); j++){  
            	//Comparing to get max floor size
                if (nonPriorityPool2.get(j).getDestFloor() > 
                		nonPriorityPool2.get(max).getDestFloor()){  
                    max = j;//searching for max   
                }  
            }  
            Collections.swap(nonPriorityPool2, i, max);
        }  
    	
    	return nonPriorityPool2;
    }
    
    //sorting priority pool in ascending order so that highest priority
    //mail item would get popped out [Sorting is done using 
    //[Sorting is done using selection sort in ascending order]
	private Stack<MailItem> sortPriorityPool(Stack<MailItem> priorityPool2) {
		// TODO Auto-generated method stub
        for (int i = 0; i < priorityPool2.size() - 1; i++)  
        {  
            int min = i;  
            for (int j = i + 1; j < priorityPool2.size(); j++){  
            		//Comparing to get the minimum priority level
                	if(((PriorityMailItem) priorityPool2.get(j)).getPriorityLevel() 
                		< ((PriorityMailItem) priorityPool2.get(min)).getPriorityLevel()){  
                    min = j;//searching for min   
                }  
            }  
            Collections.swap(priorityPool2, i, min);
        }  
    	
    	return priorityPool2;
		
	}
	
}
