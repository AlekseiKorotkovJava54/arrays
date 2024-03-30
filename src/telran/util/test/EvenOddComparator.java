package telran.util.test;

import java.util.Comparator;

public class EvenOddComparator implements Comparator<Integer> {

	@Override
	public int compare(Integer o1, Integer o2) {
		int resComp=1;
		boolean isNumEven1 = o1%2 == 0;
		boolean isNumEven2 = o2%2 == 0;
		if(isNumEven1 && isNumEven2) resComp = o1.compareTo(o2);
		else if(!isNumEven1 && !isNumEven2) resComp = o2.compareTo(o1);
		else if(isNumEven1) resComp = -1;
		return resComp;
	}
}