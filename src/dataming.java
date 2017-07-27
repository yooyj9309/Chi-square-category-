import java.io.IOException;

public class dataming {

	public static void main(String[] args) throws IOException {		
		Merge merge = new Merge();
		merge.makeSplit();
		merge.makeSplitFeature();
		ChiSquare cs=new ChiSquare();
		cs.makeHash();
		cs.makeChi();
		cs.sortArray();
		cs.makeNo5();
		cs.make5Split();
		cs.sortNo5Array();
	}
}
