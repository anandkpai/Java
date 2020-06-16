package gen;

import java.util.ArrayList;
import java.util.Comparator;

public class MaxSet {

	static public class Score implements Comparable<Score>{

		public String name;
		public int score;
		
		
		public Score(String n, String s) {
			this.name  = n;
			this.score = Integer.valueOf(s);
		}
		
		@Override
		public int compareTo(Score o) {return Integer.compare(this.score, o.score);}
		
		@Override
		public String toString() {return this.name+String.format("%3d", this.score);}

		static Comparator<Score> compareScores = new Comparator<Score>() {
			@Override
			public int compare(Score s1, Score s2) {return s1.compareTo(s2);}
		};
		
		static Comparator<Score> compareDesc = new Comparator<Score>() {
			@Override
			public int compare(Score s1, Score s2) {return -s1.compareTo(s2);}
		};

		static Comparator<Score> compareNames = new Comparator<Score>() {
			@Override
			public int compare(Score s1, Score s2) {
				return s1.name.compareTo(s2.name);
			}
		};
	}
	
	
	
	public static void main(String[] args) {

		// {"Bob","87"}, {"Mike", "35"},{"Bob", "52"}, {"Jason","35"}, {"Mike", "55"}, {"Jessica", "99"}
		
		ArrayList<Score> scores = new ArrayList<Score>(5);
		scores.add(new Score("Bob","87"));
		scores.add(new Score("Mike","35"));
		scores.add(new Score("Bob","52"));
		scores.add(new Score("Jason","35"));
		scores.add(new Score("Mike", "55"));
		scores.add(new Score("Jessica","99"));
		
		Score maxScore = new Score("Dummy","0");
		for (Score score:scores) maxScore = score.compareTo(maxScore)>0?score:maxScore;
		System.out.println(maxScore);
		
		scores.sort(Score.compareScores);
		System.out.println(scores);
		
		scores.sort(Score.compareDesc);
		System.out.println(scores);
		
		scores.sort(Score.compareNames);
		System.out.println(scores);		
		
//		String[] stringsArray   = {"This", "that", "and", "the", "other"};
//		ArrayList<String> strings =new  ArrayList<String> (stringsArray);
	}

}
