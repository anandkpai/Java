package testStrings;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.concurrent.CompletableFuture;

public class AnaTree {
	
	static private final Set<String>  signatures = Collections.synchronizedSet(new HashSet<String>());	
	static private final List<String> anagrams   = Collections.synchronizedList(new ArrayList<String>());
//	static private final List<CompletableFuture<Boolean>> completes = new ArrayList<>();
//	static private AtomicInteger      count      = new AtomicInteger(0);  
	

	
	private static void  buildAnaTree(String leftStr, String rightStr, int index ) {
		String signature = leftStr+":"+rightStr+":"+rightStr.toCharArray()[index];
		if (signatures.add(signature)) {
			if (rightStr.length() == 1) {
//				count.incrementAndGet();
//				if (count.incrementAndGet()%1000000==0) System.out.printf("%d million anagrams computed\n",count.intValue()/1000000);
				anagrams.add(leftStr+rightStr);
				}
			else {
				String newLeft  = leftStr + rightStr.toCharArray()[index];
				String newRight = rightStr.substring(0, index) + rightStr.substring(index+1);
				buildAnaTree(newLeft, newRight, 0);
				if (index == 0) 
					IntStream.range(1,rightStr.length())
						.parallel()
							.forEach(i->CompletableFuture.runAsync(() -> buildAnaTree(leftStr, rightStr, i)));					
//					for (int i=1;i<rightStr.length();i++) {
//						final int j = i;
//						CompletableFuture<Boolean> future = 
//						CompletableFuture.supplyAsync(() -> buildAnaTree(leftStr, rightStr, j));
//						completes.add(future);						
//					}
			}
		}
//		return true;
	}
	
	
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		DecimalFormat df = new DecimalFormat("#,##0");
		
		AnaTree.signatures.clear();
		AnaTree.anagrams.clear();	
//		AnaTree.completes.clear();
		String anastring ="abcdefghijk"; 
		AnaTree.buildAnaTree("", anastring, 0);
		int tracker = 0;
		long elapsedTime;
		String filler = "                                                                            ";
		while (tracker != anagrams.size()) {
			tracker         = anagrams.size();
			elapsedTime     = System.currentTimeMillis()-start;
			String countstr = df.format(anagrams.size());
			countstr        = filler.substring(0,12-countstr.length()) + countstr;
			System.out.printf(countstr+" Anagrams computed for string '"
					+anastring+"' of length %d in %3d seconds\n", anastring.length(),elapsedTime/1000);			
			try {Thread.sleep(1000);} catch (InterruptedException e) {};
		}
			
//		CompletableFuture<?>[] futuresArray = new CompletableFuture<?>[completes.size()];
//		CompletableFuture.allOf(completes.toArray(futuresArray));
//		for (CompletableFuture<Boolean> f:completes) assert f.isDone();


//		elapsedTime = System.currentTimeMillis()-start;		
//		System.out.printf(df.format(anagrams.size())+" Anagrams computed for string '"
//									+anastring+"' of length %d in %d seconds\n", anastring.length(),elapsedTime/1000);
		System.out.println("done");

	}		
}
