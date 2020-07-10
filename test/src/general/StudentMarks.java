package general;


import java.util.HashMap;
import java.util.Map;


public class StudentMarks {

	
	static public class StudScore implements Comparable<StudScore>
	{
		static private final HashMap<String, StudScore> m = new HashMap<>();
		
		public final String name;
		private double  count = 0;
		private double  score = 0;
		
		private StudScore(String name) {
			this.name = name;
		}
		
		public static void processRecord(String[] s){
			StudScore rec = m.computeIfAbsent(s[0], f->(new StudScore(s[0])));
			rec.count++;
			rec.score+=Math.floor(Double.valueOf(s[1]));			
		}
		
		private int average() {
			return (int)Math.floor(score/count);
		}
		
		@Override
		public int compareTo(StudScore o) { 
			int comp = Integer.compare(this.average(), o.average());
			return comp==0?-this.name.compareTo(o.name):comp;
		}
		
		@Override
		public String toString() {return name+String.format(" %d", this.average());}
		
		public static StudScore betterOf(StudScore one, StudScore two) {
			if (two == null) return one;
			if (one == null) return two;
			if (one.compareTo(two)> 0) return one;
			else return two;
		}
		
		static public StudScore star() {
			StudScore star=null; 	
			for (Map.Entry<String, StudScore> rec:m.entrySet()) {
				star = StudScore.betterOf(rec.getValue(), star);
			}
			return star;	
		} 
		
	}
	

	static public void maxScore(String[][] students) {
		for (String[] s:students) StudScore.processRecord(s);		
		System.out.println(StudScore.star());
	}
	
	public static void main(String[] args) {
		String[][] students = {{"Bob","87"}, {"Mike", "35"},{"Bob", "52"}, {"Jason","35"}, {"Mike", "55"}, {"Jessica", "49"}};
		maxScore(students);
	}


}
