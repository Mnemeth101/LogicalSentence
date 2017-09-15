
public class Conjunction extends LogicalSentence{

	private LogicalSentence left;
	private LogicalSentence right;

	public Conjunction (LogicalSentence a, LogicalSentence b) {
		this.left = a;
		this.right = b;
	}
	
	public Boolean evaluate (TruthAssignment t) {
		boolean retval = !left.evaluate(t) && !right.evaluate(t);
		return retval;
	}
}
