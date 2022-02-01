package MyGeneAnalysis;
import java.io.*;
import java.util.*;
import java.lang.*;
public class TTest_modT
{
	private String FileName1;
	private String FileName2;
	private FileWriter fw;			
	private BufferedWriter bw;			//object for writting in file
	private File file1;
	private File file2;
	private File file;
	private Scanner scr1,scr2; 			//objects to read from a file
	TTest_modT(String s1,String s2)
	{
		FileName1=s1;
		FileName2=s2;
	}
	//Calculate t_value and write in output file 1row at a time
	public void compute(String s)
	{
		file1=new File(FileName1);
		file2=new File(FileName2);
		file = new File(s);
		try{
				if (!file.exists()) {
								file.createNewFile();
						}
				
				fw = new FileWriter(file.getAbsoluteFile(),true);
						bw = new BufferedWriter(fw);
				}
		catch (IOException e) {

			e.printStackTrace();

		}
		
		try
		{
				scr1=new Scanner(file1);
				scr2=new Scanner(file2);
				scr1.next();
				scr2.next();
				
				while(scr1.hasNext() && scr2.hasNext())
				{
					String data1=scr1.next();
					String data2=scr2.next();
					String[] g=data1.split(",");
					double[] geneFile1=convertToDouble(g);
					double[] geneFile2=convertToDouble(data2.split(","));		//Converting string array data to double type for computation
						bw.write(g[0]);													//Writting to file
						bw.write(" ,");
						bw.write(Double.toString(TTest.t_value(geneFile1,geneFile2)));    //calling funtion to calculate t_value
						bw.write("\n");
						
					System.out.println("T value of "+g[0]+" is= "+TTest.t_value(geneFile1,geneFile2));   ////Writing to console window
				}
		} 
		catch (IOException e) {

			e.printStackTrace();

		}
		finally 
		{
			try 
			{
				if (bw != null)
						bw.close();
				if (fw != null)
						fw.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	//Calculate t_value then compute and write in output file 1 row at a time
	public void compute(String s,double n)
	{
		file1=new File(FileName1);
		file2=new File(FileName2);
		file = new File(s);
		try{
				if (!file.exists()) {
								file.createNewFile();
						}
				
				fw = new FileWriter(file.getAbsoluteFile(),true);
						bw = new BufferedWriter(fw);
				}
		catch (IOException e) {

			e.printStackTrace();

		}
		
		try
		{
				scr1=new Scanner(file1);
				scr2=new Scanner(file2);
				scr1.next();
				scr2.next();
				
				while(scr1.hasNext() && scr2.hasNext())
				{
					String data1=scr1.next();
					String data2=scr2.next();
					String[] g=data1.split(",");
					double[] geneFile1=convertToDouble(g);
					double[] geneFile2=convertToDouble(data2.split(","));        //Converting string array data to double type for computation
					double tvalue=TTest.t_value(geneFile1,geneFile2);			//calling funtion to calculate t_value
					if(tvalue<=n)
					{
						bw.write(g[0]);											//Writting to file
						bw.write("  ,");
						bw.write(Double.toString(tvalue));
						bw.write("\n");
						
					System.out.println("T value of "+g[0]+" is= "+tvalue);		//Writing to console window
					}
				}
		} 
		catch (IOException e) {

			e.printStackTrace();

		}
		finally 
		{
			try 
			{
				if (bw != null)
						bw.close();
				if (fw != null)
						fw.close();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	//Convert string type array to double type
	double[] convertToDouble(String[] str)
    {
		double[] temp=new double[str.length];
		for(int i=1,j=0;i<str.length;i++,j++)
			temp[j]=Double.parseDouble(str[i]);
		return temp;
    }
}
