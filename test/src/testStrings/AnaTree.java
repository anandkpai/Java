package testStrings;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AnaTree {
	
	static private final Set<String>  signatures = Collections.synchronizedSet(new HashSet<String>());	
	static private final List<String> anagrams   = Collections.synchronizedList(new ArrayList<String>());
	static private AtomicInteger      count      = new AtomicInteger(0);  

	
	private static void buildAnaTree(String leftStr, String rightStr, int index ) {
		String signature = leftStr+":"+rightStr+":"+rightStr.toCharArray()[index];
		if (signatures.add(signature)) {
			if (rightStr.length() == 1) {
				if (count.incrementAndGet()%1000000==0) System.out.printf("%d million anagrams computed\n",count.intValue()/1000000);
				anagrams.add(leftStr+rightStr);
				}
			else {
				String newLeft  = leftStr + rightStr.toCharArray()[index];
				String newRight = rightStr.substring(0, index) + rightStr.substring(index+1);
				buildAnaTree(newLeft, newRight, 0);
				if (index == 0) 
					IntStream.range(1,rightStr.length())
//						.parallel()
							.forEach(i->buildAnaTree(leftStr, rightStr, i));					
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		AnaTree.signatures.clear();
		AnaTree.anagrams.clear();		
		String anastring ="abcdefghij"; 
		AnaTree.buildAnaTree("", anastring, 0);
		DecimalFormat df = new DecimalFormat("#,##0");
		long reftime = System.currentTimeMillis()-start;
		
		System.out.printf(df.format(anagrams.size())+" Anagrams computed for string '"
									+anastring+"' of length %d in %d seconds\n", anastring.length(),reftime/1000);
		

	}		
}
