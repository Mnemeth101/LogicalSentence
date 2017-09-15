
public class Implicator extends LogicalSentence{
	private LogicalSentence left;
	private LogicalSentence right;

	public Implicator(LogicalSentence a, LogicalSentence b) {
		this.left = a;
		this.right = b;
	}
	
	public Boolean evaluate (TruthAssignment t) {
		boolean retval = false;
		if (left.evaluate(t) == true) {
			retval = !right.evaluate(t);
		}
		return retval;
	}
	
}
