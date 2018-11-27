package meena;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map.Entry;

public class ToCompare implements Comparator<Entry>{

	//Entry<String, Integer> min = Collections.min(map.entrySet(), new Comparator<Entry<String, Integer>>() {
	    
	//@Override
	//public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
	  //      return entry1.getValue().compareTo(entry2.getValue());
	    //}

	
	@Override
	public int compare(Entry entry1, Entry entry2) {
		// TODO Auto-generated method stub
		return ((Integer) entry1.getValue()).compareTo((Integer)entry2.getValue());
	//	return 0;
	}
}