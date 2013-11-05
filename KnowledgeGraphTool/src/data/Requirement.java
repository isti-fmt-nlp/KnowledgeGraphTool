package data;

public class Requirement {
	private float val;
	private String pathGraphD1;
	private String pathGraphD2;
	private String req;
        public Requirement(String req){
		this.req=req;
                val=-1;
	}
	public Requirement(String req,String pathD1,String pathD2, float val){
		this.req=req;
		this.val=val;
		pathGraphD1=pathD1;
		pathGraphD2=pathD2;
	}
        public void fill(String pathD1,String pathD2, float val){
		this.val=val;
		pathGraphD1=pathD1;
		pathGraphD2=pathD2;
	}
	public void setVal(float val){
		this.val=val;
	}
	public float getVal(){
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
		this.req=req;
	}
	public String getReq(){
		return req;
	}
}
