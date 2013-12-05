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
                String path_overlap="";
		File[] files=new File(root+File.separator+"Result").listFiles();
		String[]pathGraph=new String[files.length-1];
		int ind=0;
		for(File file : files){
			if(file.getName().equals("knowledge_overlap.txt"))
                            path_overlap=file.getAbsolutePath();
			if(file.getName().startsWith("R")){
                            pathGraph[ind++]=file.getAbsolutePath();
                    }
		}
		String overlap;
		try {
                    readerJ = new BufferedReader(new FileReader(path_overlap));
                    overlap=readerJ.readLine();
                    String subject1=overlap;
                    overlap=readerJ.readLine();
                    String subject2=overlap;
                    System.out.println("____ANALYSIS____:\n1Subject:"+subject1+"\n2Subject:"+subject2);
                    overlap=readerJ.readLine();
                    System.out.println(overlap);
                    overlap=readerJ.readLine();
                    ind=0;
                    n=reqList.size();
                    for(int i=0;i<n;i++) {
                        if(overlap!=null){
                            overlap=readerJ.readLine();
                            while(overlap.isEmpty())
                                overlap=readerJ.readLine();
                            reqList.get(i).fill(pathGraph[ind++],pathGraph[ind++],Float.parseFloat(overlap));
                            overlap=readerJ.readLine();
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
        public void clearVal(){
            for(int i=0; i<reqList.size();i++)
                reqList.get(i).clearVal();
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
