import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class Merge {
	
	public void mergeText(BufferedReader infile, BufferedWriter outfile, BufferedWriter outfile1, InputStreamReader reader) throws IOException {
		Boolean ctrl = false;
		try {
			infile = new BufferedReader(reader);
			String s;
			String text = "";
			while ((s = infile.readLine()) != null) {
				if (s.equals("                    ") || s.equals(""))
					continue;
				if (s.contains("#CAT'03")) {
					String catalog = s.substring(10, s.length());
					catalog = catalog.substring(0, catalog.indexOf("/"));
					outfile.newLine();
					outfile.write("*"+ catalog + "*");
					outfile.flush();
					outfile.newLine();
					continue;
				}
				if (ctrl) {
					text = s.replace("(", " ").replace("=", " ").replace("*", " ").replace(".", " ").replace(",", " ").replace(")", " ").replace("<", " ")
							.replace("[", " ").replace("]", " ").replace("\n"," ").replace("?", " ").replace("!"," ").replace(">", " ");
					String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
					text=text.replaceAll(match, "");
				}
				if (s.contains("#TEXT"))
					ctrl = true;
				if (s.contains("<KW>") || s.contains("@DOCUMENT")) {
					text = "";
					ctrl = false;
				}
				if (!text.equals("")){
					outfile.write(text);
					outfile.flush();
					outfile1.write(text);
					outfile1.flush();		
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("file error");
		}
	}
	public void makeSplitFeature() throws IOException
	{
		int k=1;
		BufferedReader infile = null;
		FileInputStream input = new FileInputStream("AllFeature.txt");
		InputStreamReader reader = new InputStreamReader(input);
		BufferedWriter outfile1 = new BufferedWriter(new FileWriter("feature.txt"));
		String text=new String();
		TreeSet tset = new TreeSet();
		String[] tokens=null;
		try {
			infile = new BufferedReader(reader);
			String s="";
			while ((s = infile.readLine()) != null) {		
				tokens = s.split(" ");
			     for(int i = 0; i < tokens.length; i++) {
			           tset.add(tokens[i]);
			     }  
			     Iterator itor = tset.iterator();
			     while(itor.hasNext()) {
			    	 String feature=""+itor.next()+"";
			          outfile1.write(feature.trim());
			          outfile1.newLine();
			          outfile1.flush();
			          k++;
			     }
				}
		} catch (FileNotFoundException e) {
			System.out.println("file error");
		}
	}
	
	public void makeSplit() throws IOException {
		FileControl text = new FileControl();
		text.makeFile();
		String[] str;
		BufferedReader infile = null;
		FileInputStream input = new FileInputStream("out.txt");
		InputStreamReader reader = new InputStreamReader(input);
		BufferedWriter outfile1 = new BufferedWriter(new FileWriter("out1.txt"));
		BufferedWriter outfile2 = new BufferedWriter(new FileWriter("out2.txt"));
		BufferedWriter outfile3 = new BufferedWriter(new FileWriter("out3.txt"));
		BufferedWriter outfile4 = new BufferedWriter(new FileWriter("out4.txt"));
		BufferedWriter outfile5 = new BufferedWriter(new FileWriter("out5.txt"));
		BufferedWriter outfile6 = new BufferedWriter(new FileWriter("out6.txt"));
		BufferedWriter outfile7 = new BufferedWriter(new FileWriter("out7.txt"));
		BufferedWriter outfile8 = new BufferedWriter(new FileWriter("out8.txt"));
		BufferedWriter temp = null;
		Boolean ctrl = false;
		try {
			infile = new BufferedReader(reader);
			String s;
			String split="";
			while ((s = infile.readLine()) != null) {		
				switch (s) {
					case "*건강과 의학*":
						temp=outfile1;		
						if(!split.equals("")){
							temp.newLine();
							temp.write("1 : ");
							temp.flush();
						TreeSet tset = new TreeSet();
					     String[] tokens = split.split(" ");
					     for(int i = 0; i < tokens.length; i++) {
					           tset.add(tokens[i]);
					     }  
					     Iterator itor = tset.iterator();
					     while(itor.hasNext()) {
					    	 String feature=""+itor.next()+"";
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					     }
						}
					     split="";
						temp.newLine();
						break;
					case "*경제*":
						temp=outfile2;
						temp.newLine();
						temp.write("2 : ");
						temp.flush();
						TreeSet tset2 = new TreeSet();
					     String[] tokens2 = split.split(" ");
					     for(int i = 0; i < tokens2.length; i++) {
					           tset2.add(tokens2[i]);
					     }  
					     Iterator itor2 = tset2.iterator();
					     while(itor2.hasNext()) {
					    	 String feature=""+itor2.next()+"";
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					     }
					     split="";
						temp.newLine();
						break;
					case "*과학*":
						temp=outfile3;
						temp.newLine();
						temp.write("3 : ");
						temp.flush();
						TreeSet tset3 = new TreeSet();
					     String[] tokens3 = split.split(" ");
					     for(int i = 0; i < tokens3.length; i++) {
					           tset3.add(tokens3[i]);
					     }  
					     Iterator itor3 = tset3.iterator();
					     while(itor3.hasNext()) {
					    	 String feature=""+itor3.next()+"";
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					     }
					     split="";
						temp.newLine();
						break;
					case "*교육*":
						temp=outfile4;
						temp.newLine();
						temp.write("4 : ");
						temp.flush();
						TreeSet tset4 = new TreeSet();
					     String[] tokens4 = split.split(" ");
					     for(int i = 0; i < tokens4.length; i++) {
					           tset4.add(tokens4[i]);
					     }  
					     Iterator itor4 = tset4.iterator();
					     while(itor4.hasNext()) {
					    	 String feature=""+itor4.next()+"";
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					     }
					     split="";
						temp.newLine();
						break;
					case "*문화와 종교*":
						temp=outfile5;
						temp.newLine();
						temp.write("5 : ");
						temp.flush();
						TreeSet tset5 = new TreeSet();
					     String[] tokens5 = split.split(" ");
					     for(int i = 0; i < tokens5.length; i++) {
					           tset5.add(tokens5[i]);
					     }  
					     Iterator itor5 = tset5.iterator();
					     while(itor5.hasNext()) {
					    	 String feature=""+itor5.next()+"";
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					     }
					     split="";
						temp.newLine();
						break;
					case "*사회*":
						temp=outfile6;
						temp.newLine();
						temp.write("6 : ");
						temp.flush();
						TreeSet tset6 = new TreeSet();
					     String[] tokens6 = split.split(" ");
					     for(int i = 0; i < tokens6.length; i++) {
					           tset6.add(tokens6[i]);
					     }  
					     Iterator itor6 = tset6.iterator();
					     while(itor6.hasNext()) {
					    	 String feature=""+itor6.next()+"";
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					     }
					     split="";
						temp.newLine();
						break;
					case "*산업*":
						temp=outfile7;
						temp.newLine();
						temp.write("7 : ");
						temp.flush();
						TreeSet tset7 = new TreeSet();
					     String[] tokens7 = split.split(" ");
					     for(int i = 0; i < tokens7.length; i++) {
					           tset7.add(tokens7[i]);
					     }  
					     Iterator itor7 = tset7.iterator();
					     while(itor7.hasNext()) {
					    	 String feature=""+itor7.next()+"";
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					     }
					     split="";
						temp.newLine();
						break;
					case "*여가생활*":
						temp=outfile8;
						temp.newLine();
						temp.write("8 : ");
						temp.flush();
						TreeSet tset8 = new TreeSet();
					     String[] tokens8 = split.split(" ");
					     for(int i = 0; i < tokens8.length; i++) {
					           tset8.add(tokens8[i]);
					     }  
					     Iterator itor8 = tset8.iterator();
					     while(itor8.hasNext()) {
					    	 String feature=""+itor8.next()+"";
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					     }
					     split="";
						temp.newLine();
						break;
					default:
						if(!s.equals("")){
							split=s+split;
						}
						break;
					}
				}		
		} catch (FileNotFoundException e) {
			System.out.println("file error");
		}
	}
}
