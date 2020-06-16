package palindromes;

public class Test {

	static public class Deque {

		char[] q;
		int index;
		
		public Deque(int n) {
			this.q=new char[n];
			this.index = 0;
		}
		
		public void add(char c) {
			this.q[index++]=c;
		}
		
		public char remove() {
			index--;
			char t =this.q[index];
			this.q[index] = '0';
			return t;
		}
		
		public boolean isEmpty() {
			return index==0?true:false;
		}
	}
	
	
	public static boolean close(char c, char t) {
		if ((c=='(') && (t==')')) return true;
		if ((c=='{') && (t=='}')) return true;
		if ((c=='[') && (t==']')) return true;
		return false;
	}
	
	public static String isBalanced(String s) {
		Deque q = new Deque(s.length());
		char[] chars = s.toCharArray();
		char c,t;
		for (int i=0;i<chars.length;i++) {
			c = chars[i];
			if ((c=='{')||(c=='(')||(c=='[')) q.add(c);
			else {
				if (q.isEmpty()) return "NO";
				t = q.remove();
				if (!close(t,c)) return "NO";
			}				
		}
		return q.isEmpty()?"YES":"NO";
	}
	
	public static void main(String[] args) {
		System.out.println(isBalanced("{[()]}"));
	}

		
}
