
public class Nand extends LogicalSentence {
	
	private LogicalSentence left;
	private LogicalSentence right;

	public Nand (LogicalSentence left, LogicalSentence right) {
		this.left = left;
		this.right = right;
	}

	public Boolean evaluate (TruthAssignment t) {
		boolean retval = left.evaluate(t) && right.evaluate(t);
		return retval;
	}
}
