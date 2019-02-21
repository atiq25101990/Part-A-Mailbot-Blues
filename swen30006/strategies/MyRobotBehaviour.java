package strategies;

import automail.Clock;
import automail.PriorityMailItem;
import automail.StorageTube;
import exceptions.TubeFullException;

public class MyRobotBehaviour  implements IRobotBehaviour {
	
	private static final int MAX_TAKE = 4;
	private boolean newPriority; // Used if we are notified that a priority item has arrived. 
		
	public MyRobotBehaviour() {
		newPriority = false;
	}

	@Override
	public boolean fillStorageTube(IMailPool mailPool, StorageTube tube) {
		// Priority items are important;
		// if there are some, grab one and go, otherwise take as many items as we can and go
		
		//getting current size of tube
		int tubeSize = tube.getSize();
		try{
			// Start afresh
			newPriority = false;
			//if the mail item is added to the priority pool then decrementing the 
			//tube size
			while(!tube.isEmpty()) {
				mailPool.addToPool(tube.pop());
				System.out.println("Tube Size: " +tubeSize);
				--tubeSize;
			}
			
			//If the mail item is delivered the decrementing the 
			//the tube size for both priority and non priority pool
			
			// Check for a top priority item
			if (tubeSize < MAX_TAKE && mailPool.getPriorityPoolSize() > 0) {
				// Add priority mail item
				tube.addItem(mailPool.getHighestPriorityMail());
				// Go deliver that item
				System.out.println("Tube Size: " +tubeSize);
				++tubeSize;
				return true;
			}
			else{
				// Get as many nonpriority items as available or as fit
				while(tubeSize < MAX_TAKE && mailPool.getNonPriorityPoolSize() > 0) {
					tube.addItem(mailPool.getNonPriorityMail());
					System.out.println("Tube Size: " +tubeSize);
					++tubeSize;
				}
				return (tubeSize > 0);
			}
		}
		catch(TubeFullException e){
			e.printStackTrace();
		}
		//Initializing tube size to 0 after it reaches a limit of 4
		if(tubeSize == 4){
			tubeSize=0;
		}
		return false;
	}
	
	@Override
    public void priorityArrival(int priority) {
    	// Record that a new one has arrived
    	newPriority = true;
    	System.out.println("T: "+Clock.Time()+" | Priority arrived");
    }
 
	@Override
	public boolean returnToMailRoom(StorageTube tube) {
		// Only return if we don't have a priority item and a new one came in
		if (tube.getSize() > 0) {
			Boolean priority = (tube.peek() instanceof PriorityMailItem);
			return !priority && newPriority;
		}
		else {
			return false;
		}
	}
	
	

}
