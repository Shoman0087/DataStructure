package application;

public class Equation {
	
	String LHE;
	String RHE;
	LinkedList LHEPostfix = new LinkedList();;
	LinkedList RHEPostfix = new LinkedList();;;
	boolean LHEValidity;
	boolean RHEValidity;
	Double LHEValue;
	Double RHEValue;
	String LHEPostfixString;
	String RHEPostfixString;
	boolean equal;
	String lValid;
	String rValid;
	public Double getLHEValue() {
		return LHEValue;
	}
	public void setLHEValue(double lHEValue) {
		LHEValue = lHEValue;
	}
	
	public void setREHValue(double rEHValue) {
		RHEValue = rEHValue;
	}
	public Equation(String lHE, String rHE, LinkedList lHEPostfix, LinkedList rHEPostfix, boolean lHEValidity,
			boolean rHEValidity) {
		super();
		LHE = lHE;
		RHE = rHE;
		LHEPostfix.setHead(lHEPostfix.getHead());
		LHEPostfixString = LHEPostfix.toString();
		RHEPostfix.setHead(rHEPostfix.getHead());
		RHEPostfixString = RHEPostfix.toString();
		LHEValidity = lHEValidity;
		RHEValidity = rHEValidity;
		
	}

	public Equation() {
		// TODO Auto-generated constructor stub
	}
	
	public Double getRHEValue() {
		return RHEValue;
	}

	public void setRHEValue(double rHEValue) {
		RHEValue = rHEValue;
	}
	public String getLHEPostfixString() {
		return LHEPostfixString;
	}
	public void setLHEPostfixString(String lHEPostfixString) {
		LHEPostfixString = lHEPostfixString;
	}
	public String getRHEPostfixString() {
		return RHEPostfixString;
	}
	public void setRHEPostfixString(String rHEPostfixString) {
		RHEPostfixString = rHEPostfixString;
	}
	public String getLHE() {
		return LHE;
	}
	public void setLHE(String lHE) {
		LHE = lHE;
	}
	public String getRHE() {
		return RHE;
	}
	public void setRHE(String rHE) {
		RHE = rHE;
	}
	public LinkedList getLHEPostfix() {
		return LHEPostfix;
	}
	public void setLHEPostfix(LinkedList lHEPostfix) {
		LHEPostfix.setHead(lHEPostfix.getHead());
	}
	public LinkedList getRHEPostfix() {
		return RHEPostfix;
	}
	public void setRHEPostfix(LinkedList rHEPostfix) {
		RHEPostfix.setHead(rHEPostfix.getHead());
	}
	public boolean isLHEValidity() {
		return LHEValidity;
	}
	public void setLHEValidity(boolean lHEValidity) {
		LHEValidity = lHEValidity;
	}
	public boolean isRHEValidity() {
		return RHEValidity;
	}
	public void setRHEValidity(boolean rHEValidity) {
		RHEValidity = rHEValidity;
	}
	public boolean isEqual() {
		if (LHEValidity && RHEValidity && LHEValue != null && RHEValue != null) 
			return LHEValue.equals(RHEValue);
		return false;
		
	}
	

	public String getLValid() {
		if (isLHEValidity())
			lValid = "Valid";
		else
			lValid = "InValid";
		return lValid;
	}
	public void setlValid(String lValid) {
		this.lValid = lValid;
	}
	public String getRValid() {
		if (isRHEValidity())
			rValid = "Valid";
		else
			rValid = "InValid";
		return rValid;
	}
	public void setrValid(String rValid) {
		this.rValid = rValid;
	}
	@Override
	public String toString() {
		System.out.println(LHEPostfix.toString());
		return "Equation [LHE=" + LHE + ", RHE=" + RHE + ", LHEPostfix=" + LHEPostfixString + ", RHEPostfix=" + RHEPostfixString
				+ ", LHEValidity=" + LHEValidity + ", RHEValidity=" + RHEValidity + ", LEHValue=" + LHEValue + ", REHValue=" + RHEValue + "]";
	}
	
	

}
