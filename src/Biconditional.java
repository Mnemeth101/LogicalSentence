
public class Biconditional extends LogicalSentence{
	
	private LogicalSentence left;
	private LogicalSentence right;

	public Biconditional(LogicalSentence left, LogicalSentence right) {
		this.left = left;
		this.right = right;
		// TODO Auto-generated constructor stub
	}
	
	public Boolean evaluate (TruthAssignment t) {
		boolean retval = !left.evaluate(t) || !right.evaluate(t);
		return retval;
	}
	
}
