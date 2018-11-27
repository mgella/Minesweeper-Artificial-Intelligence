package meena;
import java.util.Map.Entry;

import meena.Action.ACTION;
import java.util.*;
public class MyAI extends AI {
	
	@SuppressWarnings("unchecked")
	ArrayList visited = new ArrayList();
	HashMap probabilities = new HashMap();
	int maxPermissableIndex = 5;
	int minPermissableIndex = -1;
	
	
	
	
int globalX ,globalY;
	public MyAI(int rowDimension, int colDimension, int totalMines, int startX, int startY) {
	
		globalX = startX;
		globalY = startY;
		String coords = "";
		coords+=globalX;
		coords+=",";
		coords+=globalY;
		
		int samplex = startX+1;
		int sampley = startY+1;
		visited.add(coords);
		System.out.println("x "+samplex+" y "+sampley);
	}
	
	
	public void learn(int x,int y,int uncovered)
	{
		String key = x+","+y;
		if(uncovered == 0)
		{
			
			if(!probabilities.containsKey(key))
			{
				System.out.println("New neighbour with prob 0 "+(int)(x+1)+","+(int)(y+1));
				probabilities.put(key, 0);
			}
		}
		else
		{
			if(probabilities.containsKey(key))
			{
				
				int prob = (int) probabilities.get(key);
				System.out.println("Increasing prob for   "+(int)(x+1)+","+(int)(y+1)+" to"+(prob+1) );
				probabilities.put(key, ++prob);
				
			}
			else
			{
				System.out.println("New neighbour with prob 1  "+(int)(x+1)+","+(int)(y+1));
				probabilities.put(key, 1);
			}
		}
	}

	
	public int getMaxOnes(int x1,int x2,int y1,int y2)
	{
		int count=0;
		System.out.println("#############Tie Breaker");
		
		return count;
		
	}
	
	public void callLearnForNeighbours(int x,int y,int covered)
	{
		System.out.println("Estimating neighbours for "+(x+1)+" , "+(y+1));
		countIt++;
		if(x+1>minPermissableIndex&&x+1<maxPermissableIndex&&y>minPermissableIndex&&y<maxPermissableIndex)
		learn(x+1,y,covered);
		if(x-1>minPermissableIndex&&x-1<maxPermissableIndex&&y>minPermissableIndex&&y<maxPermissableIndex)
		learn(x-1,y,covered);
		if(x>minPermissableIndex&&y-1<maxPermissableIndex&&x<maxPermissableIndex&&y-1>minPermissableIndex)
		learn(x,y-1,covered);
		if(x>minPermissableIndex&&y+1<maxPermissableIndex&&x<maxPermissableIndex&&y+1>minPermissableIndex)
		learn(x,y+1,covered);
		if(x+1>minPermissableIndex&&x+1<maxPermissableIndex&&y+1>minPermissableIndex&&y+1<maxPermissableIndex)
		learn(x+1,y+1,covered);
		if(x-1>minPermissableIndex&&x-1<maxPermissableIndex&&y+1>minPermissableIndex&&y+1<maxPermissableIndex)
		learn(x-1,y+1,covered);
		if(x-1>minPermissableIndex&&x-1<maxPermissableIndex&&y-1>minPermissableIndex&&y-1<maxPermissableIndex)
		learn(x-1,y-1,covered);
		if(x+1>minPermissableIndex&&x+1<maxPermissableIndex&&y-1>minPermissableIndex&&y-1<maxPermissableIndex)
		learn(x+1,y-1,covered);
	}
	int countIt = 0;
	
	public Action getAction(int number) {
		System.out.println("count is "+countIt);
		
		callLearnForNeighbours(globalX,globalY,number);
        
		HashMap unVisitedProbabilities = new HashMap();
		Iterator itr = probabilities.entrySet().iterator();
		while(itr.hasNext())
		{
			Map.Entry pair = (Map.Entry)itr.next();
			if(!visited.contains(pair.getKey()))
					{
				unVisitedProbabilities.put(pair.getKey(), pair.getValue());
					}
	     
		}
		
		System.out.println("hello");
		 System.out.println("###############status of unvisited probabilities");
		    Iterator it = unVisitedProbabilities.entrySet().iterator();
			while(it.hasNext())
			{
				Map.Entry pair = (Map.Entry)it.next();
				if(!visited.contains(pair.getKey()))
						{
					String st = (String)pair.getKey();
					String[] cot = st.split(",");
					int x1 = Integer.parseInt(cot[0]);
					int y1 = Integer.parseInt(cot[1]);
					x1++;
					y1++;
					System.out.println(x1+","+y1+" prob "+pair.getValue());
						}
		   
			}
			
			
		
			String keyToVisit="";
		LinkedHashMap mp=(LinkedHashMap) sortHashMapByValues(unVisitedProbabilities);
		
		Iterator iterIt = mp.entrySet().iterator();
		if(iterIt.hasNext())
		{
			keyToVisit = (String) ((Entry) iterIt.next()).getKey();
		}
		
		String[] co = keyToVisit.split(",");
		int x = Integer.parseInt(co[0]);
		int y = Integer.parseInt(co[1]);
	    visited.add(keyToVisit);
	    globalX = x;
	    globalY = y;
		System.out.println("about to uncover "+(x+1)+", "+(y+1));
		return new Action(ACTION.UNCOVER,x+1,y+1);
	}
	
	public HashMap<String,Integer> sortHashMapByValues(
	        HashMap<String,Integer> passedMap) {
	    List<String> mapKeys = new ArrayList<>(passedMap.keySet());
	    List<Integer> mapValues = new ArrayList<>(passedMap.values());
	    Collections.sort(mapValues);
	    Collections.sort(mapKeys);

	    LinkedHashMap<String,Integer> sortedMap =
	        new LinkedHashMap<>();

	    Iterator<Integer> valueIt = mapValues.iterator();
	    while (valueIt.hasNext()) {
	        Integer val = valueIt.next();
	        Iterator<String> keyIt = mapKeys.iterator();

	        while (keyIt.hasNext()) {
	            String key = keyIt.next();
	            Integer comp1 = passedMap.get(key);
	            Integer comp2 = val;

	            if (comp1.equals(comp2)) {
	                keyIt.remove();
	                sortedMap.put(key, val);
	                break;
	            }
	        }
	    }
	    return sortedMap;
	}
	
}
