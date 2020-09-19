package testStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class AnaChar{
		private final char c;
		private final AnaChar parent;
		private final String substr ;
		public static final List<String> anagrams = new ArrayList<>();
		public static final ConcurrentHashMap<AnaChar, ConcurrentHashMap<String, Boolean>> nodePoints = new ConcurrentHashMap<>();
		
		static public void buildTree(String s) {
			new AnaChar(s, 0, new AnaChar());	
		}
		
		private AnaChar( ) {
			this.c = 0;
			this.parent = null;
			this.substr = "";
		
		}
				
		
		private AnaChar(String s, int index,AnaChar p ) {
			this.parent = p;
			this.c      = s.charAt(index);
			this.substr = this.parent==null?String.valueOf(this.c):this.parent.toString()+String.valueOf(this.c);
			
			if (index==0) {
				IntStream.range(1,s.length()).parallel().forEach(i->{new AnaChar(s,i,p);});
				//for (int i=1;i<s.length();i++) new AnaChar(s, i, p);
			}
			if (s.length() == 1){
				String word = this.toString();
				System.out.println(word);
				anagrams.add(word);
			}
			else {
				String right = s.substring(0,index)+s.substring(index+1);
				ConcurrentHashMap<String, Boolean> strings =  (nodePoints.computeIfAbsent(p, f->(new ConcurrentHashMap<String, Boolean>())));
				if (strings.putIfAbsent(String.valueOf(this.c)+right, true)==null) new AnaChar(right, 0, this);					
			}
								
		}		
		
		@Override
		public String toString() {return this.substr ;}
		

}
