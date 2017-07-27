import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileControl {
	/*
	 * This method is to make file to save all features.
	 * */
	public void makeFile() throws IOException
	{
		Merge merge= new Merge();
		BufferedReader infile=null;
		FileInputStream[] input={new FileInputStream("HKIB-20000_001.txt"),new FileInputStream("HKIB-20000_002.txt"),new FileInputStream("HKIB-20000_003.txt"),new FileInputStream("HKIB-20000_004.txt")};
		InputStreamReader[] reader={new InputStreamReader(input[0]),new InputStreamReader(input[1]),new InputStreamReader(input[2]),new InputStreamReader(input[3])};
		BufferedWriter outfile=new BufferedWriter(new FileWriter("out.txt"));
		BufferedWriter outfile1=new BufferedWriter(new FileWriter("AllFeature.txt"));
		for(int i=0;i<4;i++){
			merge.mergeText(infile,outfile,outfile1,reader[i]);
		}
		
	}
	
}
