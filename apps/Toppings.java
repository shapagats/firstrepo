public class Toppings{
	private String TopName;
	private String extraTop;

	public Toppings(String TopName, String extraTop){
		this.TopName = TopName;
		this.extraTop = extraTop;
	}
     
    public String getTopName(){
    	return TopName;
    }

    public String getExtraTop(){
    	return extraTop;
    }
    
    public void setTopName(String TopName){
    	this.TopName = TopName;
    }
    public String toString(){
    	return "Topname "+ TopName + "extraTop "+ extraTop;
    }
}