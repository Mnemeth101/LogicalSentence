
public class Negation extends LogicalSentence{

	private LogicalSentence sent;

	
	public Negation(LogicalSentence l1) {
		this.sent = l1;
	}


	public Boolean evaluate (TruthAssignment t) {
		boolean retval = !sent.evaluate(t);
		return retval;
	}

}
