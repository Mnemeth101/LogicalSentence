import java.awt.List;
import java.util.ArrayList;

public class TruthAssignment {
	
	private ArrayList<PropositionConstant> propA = new ArrayList<PropositionConstant>();
	private ArrayList<Boolean> truthval = new ArrayList<Boolean>();
	
	public void put (PropositionConstant a, Boolean b) {
		propA.add(a);
		truthval.add(b);
	}
	
	public Boolean get(PropositionConstant a) {
		int index = propA.indexOf(a);
		return truthval.get(index);
	}
}