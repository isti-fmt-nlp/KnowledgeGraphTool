package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Requirements {
	private ArrayList<Requirement> reqList=new ArrayList<Requirement>();
	public Requirements(){
		
	}
        public int loadReqs(String root){
            int nreq=0;
            reqList.clear();
            BufferedReader readerL;
            String pathReq=new File(root+File.separator+"Requirements").listFiles()[0].getAbsolutePath();
            String line;
            try {
                readerL = new BufferedReader(new FileReader(pathReq));
                try {
                    line = readerL.readLine();
                    while(line!=null) {
                        if(!line.isEmpty()){
                            reqList.add(new Requirement(line));
                            nreq++;
                        }
                        line = readerL.readLine();
                    }
                    readerL.close();
                }catch (IOException ex) {} 
            }catch (FileNotFoundException ex) {
            }
            return nreq;
        }
        public void loadAnalysis(String root){
		BufferedReader readerJ;
		int n;
                String pathjac="";
		File[] files=new File(root+File.separator+"Result").listFiles();
		String[]pathGraph=new String[files.length-1];
		int ind=0;
		for(File file : files){
			if(file.getName().equals("jaccard.txt"))
				pathjac=file.getAbsolutePath();
			if(file.getName().startsWith("R")){
				pathGraph[ind++]=file.getAbsolutePath();
                    }
		}
		String jac;
		try {
                    readerJ = new BufferedReader(new FileReader(pathjac));
                    jac=readerJ.readLine();
                    ind=0;
                    n=reqList.size();
                    for(int i=0;i<n;i++) {
                        if(jac!=null){
                        jac=readerJ.readLine();
                        reqList.get(i).fill(pathGraph[ind++],pathGraph[ind++],Float.parseFloat(jac));
                        jac=readerJ.readLine();
                        }
                    }
                    readerJ.close();
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
