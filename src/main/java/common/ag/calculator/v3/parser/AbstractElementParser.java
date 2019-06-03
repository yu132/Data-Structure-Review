package common.ag.calculator.v3.parser;

public abstract class AbstractElementParser implements ElementParser {
	
	@Override
	public boolean parse(ParserShared shared) {
		if (checkBeforeParse(shared) && parseHelper(shared)) {
			setSharedAfterParse(shared);
			checkAfterParse(shared);
			return true;
		}
		return false;
	}
	
	protected abstract boolean checkBeforeParse(ParserShared shared);
	
	protected abstract boolean parseHelper(ParserShared shared);
	
	protected void checkAfterParse(ParserShared shared) {};
	
	protected abstract void setSharedAfterParse(ParserShared shared);
	
}
