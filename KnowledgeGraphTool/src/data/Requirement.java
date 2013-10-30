package data;

public class Requirement {
	private double val;
	private String pathGraphD1;
	private String pathGraphD2;
	private String all;
	public Requirement(String req,String pathD1,String pathD2, double val){
		all=req;
		this.val=val;
		pathGraphD1=pathD1;
		pathGraphD2=pathD2;
	}
	public void setVal(double val){
		this.val=val;
	}
	public double getVal(){
		return val;
	}
	public void setPath(String pathd1,String pathd2){
		pathGraphD1=pathd1;
		pathGraphD2=pathd2;
	}
	public String getPathD1(){
		return pathGraphD1;
	}
	public String getPathD2(){
		return pathGraphD2;
	}
	public void setReq(String req){
		all=req;
	}
	public String getReq(){
		return all;
	}
}
