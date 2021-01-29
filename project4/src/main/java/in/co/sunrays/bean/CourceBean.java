package in.co.sunrays.bean;

public class CourceBean extends BaseBean{

	private String Courcename;
	private String CourceDescription;
	
	
	
	public String getCourcename() {
		return Courcename;
	}

	public void setCourcename(String courcename) {
		Courcename = courcename;
	}

	public String getCourceDescription() {
		return CourceDescription;
	}

	public void setCourceDescription(String courceDescription) {
		CourceDescription = courceDescription;
	}

	
	
	
	
	
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return Courcename;
	}

}
