package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Requirements {
	private ArrayList<Requirement> reqList=new ArrayList<Requirement>();
	public Requirements(){
		
	}
	public void loadReqs(String root){
		BufferedReader readerL;
		BufferedReader readerJ;
		String pathReq=new File(root+File.separator+"Requisiti").listFiles()[0].getAbsolutePath();
		String pathjac="";
		File[] files=new File(root+File.separator+"Risultati").listFiles();
		String[]pathGraph=new String[files.length-1];
		int ind=0;
		for(File file : files){
			if(file.getName().equals("jaccard.txt"))
				pathjac=file.getAbsolutePath();
			if(file.getName().startsWith("R")){
				pathGraph[ind++]=file.getAbsolutePath();
				}
			
		}
		String line;
		String jac;
		try {
			readerL = new BufferedReader(new FileReader(pathReq));
			readerJ = new BufferedReader(new FileReader(pathjac));
			line = readerL.readLine();
			jac=readerJ.readLine();
			ind=0;
			while(line!=null && jac!=null) {
				jac=readerJ.readLine();
				reqList.add(new Requirement(line,pathGraph[ind++],pathGraph[ind++],Double.parseDouble(jac)));
				jac=readerJ.readLine();
				line = readerL.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void clearReq(){
		reqList.clear();
	}
	
	public Requirement getReq(int n){
		if(n>=reqList.size())
			return null;
		return reqList.get(n);
	}
	public int  getSize(){
		return reqList.size();
	}
	
	
}
