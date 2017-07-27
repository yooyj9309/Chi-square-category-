import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class ChiSquare {

	private double[] textNum = { 344, 5023, 539, 479, 2362, 3637, 3309, 281 };
	
	private HashMap<String, Double> map1 = new HashMap<String, Double>();
	private HashMap<String, Double> map2 = new HashMap<String, Double>();
	private HashMap<String, Double> map3 = new HashMap<String, Double>();
	private HashMap<String, Double> map4 = new HashMap<String, Double>();
	private HashMap<String, Double> map5 = new HashMap<String, Double>();
	private HashMap<String, Double> map6 = new HashMap<String, Double>();
	private HashMap<String, Double> map7 = new HashMap<String, Double>();
	private HashMap<String, Double> map8 = new HashMap<String, Double>();

	private HashMap<String, Double> map = new HashMap<String, Double>();
	
	private HashMap<String,Double> no5Map=new HashMap<String,Double>();
	private HashMap<Double,Integer> resultMap=new HashMap<Double,Integer>();
	
	private HashMap<String,Integer > indexMap = new HashMap<String,Integer>();
	private ArrayList<Map<String, Double>> myMap = new ArrayList<Map<String, Double>>();
	String[] array = null;
	private static int i=0;
	/*
	 * This method is to get a chisquare value using formula.
	 * */
	public static double Chisquare(double a, double b, double c, double d) {
		double result = 0;
		result = (15978 * (a * d - c * b) * (a * d - c * b)) / ((a + c) * (b + d) * (a + b) * (c + d));
		return result;
	}
	
	//Sorting method (descending order)
	public static List sortByValue(final Map map){
        List<String> list = new ArrayList();
        list.addAll(map.keySet());

        Collections.sort(list,new Comparator(){
             
            public int compare(Object o1,Object o2){
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                 
                return ((Comparable) v1).compareTo(v2);
            }
             
        });
        return list;
    }
	
	//Sorting method (ascending order)
	public static List sortByValue1(final Map map){
        List<String> list = new ArrayList();
        list.addAll(map.keySet());

        Collections.sort(list,new Comparator(){
             
            public int compare(Object o1,Object o2){
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                 
                return ((Comparable) v1).compareTo(v2);
            }
             
        });
        Collections.reverse(list); 
        return list;
    }
	
	/*
	 * This method is to make a training file.
	 * And get index of chisquare.
	 * */
	public void makeChi() throws IOException {
		BufferedWriter result = new BufferedWriter(new FileWriter("result.txt"));
		myMap.add(map1);
		myMap.add(map2);
		myMap.add(map3);
		myMap.add(map4);
		myMap.add(map5);
		myMap.add(map6);
		myMap.add(map7);
		myMap.add(map8);
		double[] cal = new double[8];
		double[] chi = new double[8];
		
		for (int p = 0; p < 8; p++) {
			for (String key : myMap.get(p).keySet()) {
				double sum = 0;
				for (int k = 0; k < 8; k++) {
					if (myMap.get(k).containsKey(key))
						cal[k] = myMap.get(k).get(key);
					else
						cal[k] = 0;
					sum += cal[k];
				}
				for (int k = 0; k < 8; k++) {
					chi[k] = Chisquare(cal[k], textNum[k] - cal[k], sum - cal[k], (15978 - cal[k]) - (sum - cal[k]));
				}
				Arrays.sort(chi);
				if(!map.containsKey(key))
				{
					map.put(key, chi[7]);
				}
			}
		}
		Iterator iterator=sortByValue1(map).iterator();
		int indexNum=1;
		while(iterator.hasNext())
		{
			String temp=(String)iterator.next();
			result.write(temp+" : "+map.get(temp));
			indexMap.put(temp,indexNum );
			indexNum++;
			result.newLine();
			result.flush();
		}
	}
	
	/*
	 * For making test file, remove the redundancy words.
	 * */
	public void makeNo5() throws IOException
	{
		Merge merge= new Merge();
		BufferedReader infile=null;
		FileInputStream input=new FileInputStream("HKIB-20000_005.txt");
		InputStreamReader reader=new InputStreamReader(input);
		BufferedWriter outfile=new BufferedWriter(new FileWriter("text5.txt"));
		BufferedWriter outfile1=new BufferedWriter(new FileWriter("text5Features.txt"));
		merge.mergeText(infile,outfile,outfile1,reader);
		
	}
	
	/*
	 *  For making test file, make text file split with category.
	 * */
	public void make5Split() throws IOException {
		FileControl text = new FileControl();
		text.makeFile();
		String[] str;
		BufferedReader infile = null;
		FileInputStream input = new FileInputStream("text5.txt");
		InputStreamReader reader = new InputStreamReader(input);
		BufferedWriter outfile1 = new BufferedWriter(new FileWriter("result5.txt"));
		BufferedWriter temp = null;
		Boolean ctrl = false;
		try {
			infile = new BufferedReader(reader);
			String s;
			String split="";
			temp=outfile1;	
			while ((s = infile.readLine()) != null) {		
				switch (s) {
					case "*건강과 의학*":				
						if(!split.equals("")){
							temp.newLine();
							temp.write("1 : ");
							temp.newLine();
							temp.flush();
						TreeSet tset = new TreeSet();
					     String[] tokens = split.split(" ");
					     for(int i = 0; i < tokens.length; i++) {
					           tset.add(tokens[i]);
					     }  
					     Iterator itor = tset.iterator();
					     while(itor.hasNext()) {
					    	 String feature=""+itor.next()+"";
					    	 if(map.containsKey(feature)){
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					    	  no5Map.put(feature,map.get(feature));
					    	 }
					     }
						}
					     split="";
						temp.newLine();
						break;
					case "*경제*":
						temp.newLine();
						temp.write("2 : ");
						temp.newLine();
						temp.flush();
						TreeSet tset2 = new TreeSet();
					     String[] tokens2 = split.split(" ");
					     for(int i = 0; i < tokens2.length; i++) {
					           tset2.add(tokens2[i]);
					     }  
					     Iterator itor2 = tset2.iterator();
					     while(itor2.hasNext()) {
					    	 String feature=""+itor2.next()+"";
					    	 if(map.containsKey(feature)){
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					    	  no5Map.put(feature,map.get(feature));
					          }
					     }
					     split="";
						temp.newLine();
						break;
					case "*과학*":
						temp.newLine();
						temp.write("3 : ");
						temp.newLine();
						temp.flush();
						TreeSet tset3 = new TreeSet();
					     String[] tokens3 = split.split(" ");
					     for(int i = 0; i < tokens3.length; i++) {
					           tset3.add(tokens3[i]);
					     }  
					     Iterator itor3 = tset3.iterator();
					     while(itor3.hasNext()) {
					    	 String feature=""+itor3.next()+"";
					    	 if(map.containsKey(feature)){
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					    	  no5Map.put(feature,map.get(feature));
					    	 }
					     }
					     split="";
						temp.newLine();
						break;
					case "*교육*":
						temp.newLine();
						temp.write("4 : ");
						temp.newLine();
						temp.flush();
						TreeSet tset4 = new TreeSet();
					     String[] tokens4 = split.split(" ");
					     for(int i = 0; i < tokens4.length; i++) {
					           tset4.add(tokens4[i]);
					     }  
					     Iterator itor4 = tset4.iterator();
					     while(itor4.hasNext()) {
					    	 String feature=""+itor4.next()+"";
					    	 if(map.containsKey(feature)){
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					    	no5Map.put(feature,map.get(feature));
					    	 }
					     }
					     split="";
						temp.newLine();
						break;
					case "*문화와 종교*":
						temp.newLine();
						temp.write("5 : ");
						temp.newLine();
						temp.flush();
						TreeSet tset5 = new TreeSet();
					     String[] tokens5 = split.split(" ");
					     for(int i = 0; i < tokens5.length; i++) {
					           tset5.add(tokens5[i]);
					     }  
					     Iterator itor5 = tset5.iterator();
					     while(itor5.hasNext()) {
					    	 String feature=""+itor5.next()+"";
					    	 if(map.containsKey(feature)){
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					    	no5Map.put(feature,map.get(feature));
					    	 }
					     }
					     split="";
						temp.newLine();
						break;
					case "*사회*":
						temp.newLine();
						temp.write("6 : ");
						temp.newLine();
						temp.flush();
						TreeSet tset6 = new TreeSet();
					     String[] tokens6 = split.split(" ");
					     for(int i = 0; i < tokens6.length; i++) {
					           tset6.add(tokens6[i]);
					     }  
					     Iterator itor6 = tset6.iterator();
					     while(itor6.hasNext()) {
					    	 String feature=""+itor6.next()+"";
					    	 if(map.containsKey(feature)){
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					    	no5Map.put(feature,map.get(feature));
					    	 }
					     }
					     split="";
						temp.newLine();
						break;
					case "*산업*":
						temp.newLine();
						temp.write("7 : ");
						temp.newLine();
						temp.flush();
						TreeSet tset7 = new TreeSet();
					     String[] tokens7 = split.split(" ");
					     for(int i = 0; i < tokens7.length; i++) {
					           tset7.add(tokens7[i]);
					     }  
					     Iterator itor7 = tset7.iterator();
					     while(itor7.hasNext()) {
					    	 String feature=""+itor7.next()+"";
					    	 if(map.containsKey(feature)){
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					    	no5Map.put(feature,map.get(feature));
					    	 }
					     }
					     split="";
						temp.newLine();
						break;
					case "*여가생활*":
						temp.newLine();
						temp.write("8 : ");
						temp.newLine();
						temp.flush();
						TreeSet tset8 = new TreeSet();
					     String[] tokens8 = split.split(" ");
					     for(int i = 0; i < tokens8.length; i++) {
					           tset8.add(tokens8[i]);
					     }  
					     Iterator itor8 = tset8.iterator();
					     while(itor8.hasNext()) {
					    	 String feature=""+itor8.next()+"";
					    	 if(map.containsKey(feature)){
					          temp.write(feature.trim());
					          temp.newLine();
					          temp.flush();
					    	 no5Map.put(feature,map.get(feature));
					    	 }
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
			System.out.println("num : "+ i);
			Iterator iterator=sortByValue1(no5Map).iterator();
		} catch (FileNotFoundException e) {
			System.out.println("file error");
		}
	}
	/*
	 * For read to svm, sort chisquare value(descending) and index(ascending).
	 * And make test file.
	 * */
	public void sortNo5Array() throws IOException{
		BufferedReader infile = null;
		FileInputStream input1 = new FileInputStream("result5.txt");		
		InputStreamReader reader1 = new InputStreamReader(input1);
		BufferedWriter train = new BufferedWriter(new FileWriter("train1.txt"));
		resultMap.clear();
		try {
			infile = new BufferedReader(reader1);
			String s="";
			while ((s = infile.readLine()) != null) {	
					if(s.equals("1 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("1 ");
						
					}
					else if(s.equals("2 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("2 ");
						
					}
					else if(s.equals("3 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("3 ");
						
					}
					else if(s.equals("4 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("4 ");
						
					}
					else if(s.equals("5 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("5 ");
						
					}
					else if(s.equals("6 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("6 ");
						
					}
					else if(s.equals("7 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("7 ");
						
					}
					else if(s.equals("8 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("8 ");
						
					}
					if(!s.equals("")&&map.containsKey(s)&&indexMap.containsKey(s))
					{
						resultMap.put(no5Map.get(s),indexMap.get(s));		
					}
				}			
			infile.close();
			resultMap.clear();
		}catch (FileNotFoundException e) {
			System.out.println("file error");
		}
	}
	
	/*
	 * 
	 * For read to svm, sort chisquare value(descending) and index(ascending).
	 * And make training file.
	 * */
	public void sortArray() throws IOException
	{
		BufferedReader infile = null;
		BufferedReader infile1=null;
		BufferedReader infile2=null;
		BufferedReader infile3=null;
		BufferedReader infile4=null;
		BufferedReader infile5=null;
		BufferedReader infile6=null;
		BufferedReader infile7=null;
		
		FileInputStream input1 = new FileInputStream("out1.txt");
		FileInputStream input2 = new FileInputStream("out2.txt");
		FileInputStream input3 = new FileInputStream("out3.txt");
		FileInputStream input4 = new FileInputStream("out4.txt");
		FileInputStream input5 = new FileInputStream("out5.txt");
		FileInputStream input6 = new FileInputStream("out6.txt");
		FileInputStream input7 = new FileInputStream("out7.txt");
		FileInputStream input8 = new FileInputStream("out8.txt");
		
		InputStreamReader reader1 = new InputStreamReader(input1);
		InputStreamReader reader2 = new InputStreamReader(input2);
		InputStreamReader reader3 = new InputStreamReader(input3);
		InputStreamReader reader4 = new InputStreamReader(input4);
		InputStreamReader reader5 = new InputStreamReader(input5);
		InputStreamReader reader6 = new InputStreamReader(input6);
		InputStreamReader reader7 = new InputStreamReader(input7);
		InputStreamReader reader8 = new InputStreamReader(input8);
		
		
		BufferedWriter train = new BufferedWriter(new FileWriter("train.txt"));

		try {
			infile = new BufferedReader(reader1);
			String s="";
			while ((s = infile.readLine()) != null) {	
					if(s.equals("1 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("1 ");
						
					}
					if(!s.equals("")&&map.containsKey(s)&&indexMap.containsKey(s))
					{
						resultMap.put(map.get(s),indexMap.get(s));		
					}
				}			
			infile.close();
			resultMap.clear();
			
			infile1 = new BufferedReader(reader2);
			s="";
			while ((s = infile1.readLine()) != null) {	
					if(s.equals("2 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("2 ");
					}
					if(!s.equals("")&&map.containsKey(s)&&indexMap.containsKey(s))
					{
						resultMap.put(map.get(s),indexMap.get(s));			
					}
				}			
			infile1.close();
			resultMap.clear();
			
			infile2 = new BufferedReader(reader3);
			s="";
			while ((s = infile2.readLine()) != null) {	
					if(s.equals("3 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("3 ");
					}
					if(!s.equals("")&&map.containsKey(s)&&indexMap.containsKey(s))
					{
						resultMap.put(map.get(s),indexMap.get(s));		
					}
				}			
			infile2.close();
			resultMap.clear();
			
			infile3 = new BufferedReader(reader4);
			s="";
			while ((s = infile3.readLine()) != null) {	
					if(s.equals("4 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("4 ");
					}
					if(!s.equals("")&&map.containsKey(s)&&indexMap.containsKey(s))
					{
						resultMap.put(map.get(s),indexMap.get(s));		
					}
				}			
			infile3.close();
			resultMap.clear();
			
			infile4 = new BufferedReader(reader5);
			s="";
			while ((s = infile4.readLine()) != null) {	
					if(s.equals("5 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("5 ");
					}
					if(!s.equals("")&&map.containsKey(s)&&indexMap.containsKey(s))
					{
						resultMap.put(map.get(s),indexMap.get(s));	
					}
				}			
			infile4.close();
			resultMap.clear();
			
			infile5 = new BufferedReader(reader6);
			s="";
			while ((s = infile5.readLine()) != null) {	
					if(s.equals("6 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("6 ");
					}
					if(!s.equals("")&&map.containsKey(s)&&indexMap.containsKey(s))
					{
						resultMap.put(map.get(s),indexMap.get(s));		
					}
				}			
			infile5.close();
			resultMap.clear();
			
			infile6 = new BufferedReader(reader7);
			s="";
			while ((s = infile6.readLine()) != null) {	
					if(s.equals("7 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("7 ");
					}
					if(!s.equals("")&&map.containsKey(s)&&indexMap.containsKey(s))
					{
						resultMap.put(map.get(s),indexMap.get(s));	
					}
				}			
			infile6.close();
			resultMap.clear();
			
			infile7 = new BufferedReader(reader8);
			s="";
			while ((s = infile7.readLine()) != null) {	
					if(s.equals("8 : "))
					{	
						if(!resultMap.isEmpty())
						{
							Iterator iterator1=sortByValue(resultMap).iterator();
							while(iterator1.hasNext())
							{
								Double temp=(Double)iterator1.next();
								train.write(resultMap.get(temp)+":"+temp+" ");
								train.flush();
							}
							resultMap.clear();
						}
						train.newLine();
						train.write("8 ");
					}
					if(!s.equals("")&&map.containsKey(s)&&indexMap.containsKey(s))
					{
						resultMap.put(map.get(s),indexMap.get(s));		
					}
				}			
			infile7.close();
			resultMap.clear();
		} catch (FileNotFoundException e) {
			System.out.println("file error");
		}
		
	}
	/*
	 * This method is to get chi-square value for eacha word.
	 * So get chi-square's a,b,c,d
	 * a= the number of word in a category
	 * b= the number of word not involving category
	 * c= the number of word in not category
	 * d= the number of word in not category not involving category
	 * */
	public void makeHash() throws IOException {
		BufferedReader infile = null;
		BufferedReader infile2 = null;
		BufferedReader infile3 = null;
		BufferedReader infile4 = null;
		BufferedReader infile5 = null;
		BufferedReader infile6 = null;
		BufferedReader infile7 = null;
		BufferedReader infile8 = null;

		FileInputStream feature = new FileInputStream("out1.txt");
		FileInputStream feature1 = new FileInputStream("out2.txt");
		FileInputStream feature2 = new FileInputStream("out3.txt");
		FileInputStream feature3 = new FileInputStream("out4.txt");
		FileInputStream feature4 = new FileInputStream("out5.txt");
		FileInputStream feature5 = new FileInputStream("out6.txt");
		FileInputStream feature6 = new FileInputStream("out7.txt");
		FileInputStream feature7 = new FileInputStream("out8.txt");

		InputStreamReader freader = new InputStreamReader(feature);
		InputStreamReader freader1 = new InputStreamReader(feature1);
		InputStreamReader freader2 = new InputStreamReader(feature2);
		InputStreamReader freader3 = new InputStreamReader(feature3);
		InputStreamReader freader4 = new InputStreamReader(feature4);
		InputStreamReader freader5 = new InputStreamReader(feature5);
		InputStreamReader freader6 = new InputStreamReader(feature6);
		InputStreamReader freader7 = new InputStreamReader(feature7);

		double num = 1;
		try {
			infile = new BufferedReader(freader);
			String s = "";
			while ((s = infile.readLine()) != null) {
				if (!s.equals("") && !s.contains("1 : ")) {
					if (map1.containsKey(s))
						map1.put(s, map1.get(s) + 1);
					else
						map1.put(s, num);
				}
			}
			infile.close();

			infile2 = new BufferedReader(freader1);
			while ((s = infile2.readLine()) != null) {
				if (!s.equals("") && !s.contains("2 : ")) {
					if (map2.containsKey(s))
						map2.put(s, map2.get(s) + 1);
					else
						map2.put(s, num);
				}
			}
			infile2.close();

			infile3 = new BufferedReader(freader2);
			while ((s = infile3.readLine()) != null) {
				if (!s.equals("") && !s.contains("3 : ")) {
					if (map3.containsKey(s))
						map3.put(s, map3.get(s) + 1);
					else
						map3.put(s, num);
				}
			}
			infile3.close();

			infile4 = new BufferedReader(freader3);
			while ((s = infile4.readLine()) != null) {
				if (!s.equals("") && !s.contains("4 : ")) {
					if (map4.containsKey(s))
						map4.put(s, map4.get(s) + 1);
					else
						map4.put(s, num);
				}
			}
			infile4.close();

			infile5 = new BufferedReader(freader4);
			while ((s = infile5.readLine()) != null) {
				if (!s.equals("") && !s.contains("5 : ")) {
					if (map5.containsKey(s))
						map5.put(s, map5.get(s) + 1);
					else
						map5.put(s, num);
				}
			}
			infile5.close();

			infile6 = new BufferedReader(freader5);
			while ((s = infile6.readLine()) != null) {
				if (!s.equals("") && !s.contains("6 : ")) {
					if (map6.containsKey(s))
						map6.put(s, map6.get(s) + 1);
					else
						map6.put(s, num);
				}
			}
			infile6.close();

			infile7 = new BufferedReader(freader6);
			while ((s = infile7.readLine()) != null) {
				if (!s.equals("") && !s.contains("7 : ")) {
					if (map7.containsKey(s))
						map7.put(s, map7.get(s) + 1);
					else
						map7.put(s, num);
				}
			}
			infile6.close();

			infile8 = new BufferedReader(freader7);
			while ((s = infile8.readLine()) != null) {
				if (!s.equals("") && !s.contains("8 : ")) {
					if (map8.containsKey(s))
						map8.put(s, map8.get(s) + 1);
					else
						map8.put(s, num);
				}
			}
			infile8.close();
		} catch (FileNotFoundException e) {
			System.out.println("file error");
		}

	}
}
