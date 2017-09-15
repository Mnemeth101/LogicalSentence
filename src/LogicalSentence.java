
public class LogicalSentence {
	
	private PropositionConstant propC;
	//private Boolean simple;
	
	public LogicalSentence (PropositionConstant p) {
		propC = p;
	}
	
	public static void main(String[] args) {
		PropositionConstant a = new PropositionConstant("a");
		PropositionConstant b = new PropositionConstant("b");
		LogicalSentence l1 = new LogicalSentence(a);
		LogicalSentence l2 = new LogicalSentence(b);
		LogicalSentence l3 = new Negation(l1);
		LogicalSentence l4 = new Negation(l3);
		LogicalSentence l5 =  new Conjunction(l3, new Negation(l4));

		TruthAssignment ta1 = new TruthAssignment();
		ta1.put(b,true);
		ta1.put(a,false); 
		ta1.get(a);
		//System.out.println(l5.evaluate(ta1));
		System.out.println(legal("a&(b&(c<=>e)|d)|(f&j)"));
		System.out.println(findMatch("a(b)"));

		String[] pc = {"p"};
		//truthTable(pc);
		
		//LogicalSentence l6 = new Nand(l1, l2);
		
	}
	
	public Boolean evaluate (TruthAssignment t) {
		return t.get(propC);
	}
	
	public static Boolean legal (String s) {
		//Checks to make sure the tested string isn't empty.
		if (s.length() == 0) {
			return false;
		}
		
		//Checks if the string is a simple sentence (p or q in this case).
		else if (SimpleSentence(s)) {
			return true;
		}
		
		//Checks if the string begins with ~.
		else if (BeginsWithNot(s)) {
			s = RemoveNot(s);
			return legal(s);
		}
		
		else if (HasParen(s)) {
			int leftparen = s.indexOf("(");
			int rightparen = findMatch(s);
			String s1 = s.substring(0, leftparen) + s.substring(leftparen+1, rightparen);
			if (rightparen < s.length()) {
				s1 += s.substring(rightparen+1);
			}
			return legal (s1);
		}
		
		//Checks if an operator is in the string, and then runs legal() 
		//on the substrings before and after the operator.
		else if (HasOperator(s)) {
			Boolean opclear = false;
			for (int i = 0; i < s.length(); i++) {
				if (s.substring(i,i+1).equals("&")) {
					opclear = (legal(s.substring(0, i)) && legal(s.substring(i+1)));
					break;
				}
				if (s.substring(i,i+1).equals("|")) {
					opclear = (legal(s.substring(0, i)) && legal(s.substring(i+1)));
					break;
				}
				if (s.substring(i,i+1).equals("=")) {
					if (s.substring(i+1,i+2).equals(">")){
						opclear = (legal(s.substring(0, i)) && legal(s.substring(i+2)));
					}
					break;
				}
				if (s.substring(i,i+1).equals("<")) {
					if (s.substring(i+1,i+3).equals("=>")){
						opclear = (legal(s.substring(0, i)) && legal(s.substring(i+3)));
					}
					break;
				}
			}
			return opclear;
			
		}	
		
		else {
			return false;
		}
	
	}

	
	//functions for legal
	public static boolean SimpleSentence (String s1) {
		String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "j", "k", 
		                    "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", 
		                    "v", "w", "x", "y", "z"};
		Boolean retval = false;
		for (int i = 0; i < 25; i++) {
			if (s1.equals(letters[i])) {
				retval = true;
			}
		}
		return retval;
				
	}
	
	public static boolean BeginsWithNot (String s2) {
		return s2.substring(0, 1).equals("~");
		
	} 
	
	public static String RemoveNot(String s3) {
		return s3.substring(1);
	}
	
	public static boolean HasOperator(String s4) {

		Boolean has = false;
		for (int i = 0; i < s4.length(); i++) {
			if (s4.substring(i,i+1).equals("&")) {
				has = true;
			}
			if (s4.substring(i,i+1).equals("|")) {
				has = true;
			}
			if (s4.substring(i,i+1).equals("=")) {
				has = true;
			}
			if (s4.substring(i,i+1).equals("<")) {
				has = true;
			}
		}
		return has;
	}

	public static boolean HasParen(String s5) {
		Boolean hasleft = false;
		Boolean hasright = false;
		for (int i = 0; i < s5.length(); i++) {
			if (s5.substring(i,i+1).equals("(")) {
				hasleft = true;
			}
			if (s5.substring(i,i+1).equals(")")) {
				hasright = true;
			}
		}
		return (hasleft == true && hasright == true);
	}
	public static Integer findMatch (String s) {
		//allows for maneuvering around parenthesis
		Boolean change = false;
		int bk = 0;
		int index = -1;
		for (int i = 0; i < s.length(); i++) {
			if (bk == 0 && change == true) {
				index = i;
				break;
			}
			if (s.substring(i,i+1).equals("(")) {
				change = true;
				bk++;
			}
			if (s.substring(i,i+1).equals(")")) {
				bk--;
				if (bk == 0 && change == true) {
					index = i;
					break;
				}
			}
		}
		return index;
	}
}
