package MyGeneAnalysis;
import java.io.*;
import java.util.Objects;
import java.util.*;
import java.lang.*;
public class ShrinkageTTest_shrT
{
	private String FileName1;
	private String FileName2;
	private Double normalgenes[][];
	private Double tumorgenes[][];
	private FileWriter fw;
	private BufferedWriter bw;
	ShrinkageTTest_shrT(String s1,String s2)
	{
		FileName1=s1;
		FileName2=s2;
	}
	//Compute tvalue and write in file
	public void compute(String s)
	{
		
		File file = new File(s);
		try{
			if (!file.exists()) {
				file.createNewFile();
			}
				
			 fw = new FileWriter(file);
			 bw = new BufferedWriter(fw);
		}
		catch (IOException e) {

			e.printStackTrace();

		}
		try
		{
			normalgenes=readValues(FileName1);				//Converting File Data in two d Array
			tumorgenes=readValues(FileName2);
			Double[] t_value=ShrTTest.t_kStar_value(normalgenes,tumorgenes); //Calling function t_kStar_value
			for(int i=0;i<t_value.length;i++)
			{
				bw.write("g");
				bw.write(Double.toString(i+1));
				bw.write(",");
				bw.write(Double.toString(t_value[i]));
				bw.write("\n");
				System.out.println("Value of t for g"+(i+1)+" is= "+t_value[i]);
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
	//Compute tvalue then compare and write in file
	public void compute(String s,double n)
	{
		
		File file = new File(s);
		try{
				if (!file.exists()) {
								file.createNewFile();
						}
				
				fw = new FileWriter(file);
						bw = new BufferedWriter(fw);
				}
		catch (IOException e) {

			e.printStackTrace();

		}
		try
		{
			normalgenes=readValues(FileName1);
			tumorgenes=readValues(FileName2);
			Double[] t_value=ShrTTest.t_kStar_value(normalgenes,tumorgenes); //Calling function t_kStar_value
			for(int i=0;i<t_value.length;i++)
			{   
				if(t_value[i]<=n){
					bw.write("g");
					bw.write(Double.toString(i+1));
					bw.write(",");
					bw.write(Double.toString(t_value[i]));
					bw.write("\n");
					System.out.println("Value of t for g"+(i+1)+" is= "+t_value[i]);
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
  
	//Get A 2d matrix of Double type for a File
   private Double[][] readValues(String f1) throws IOException
    {
        int i,j=0;
        FileReader fr=new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        String line,row[];
        String head=br.readLine();
        row=head.split(",");                                                //To Get Number of Columns
        Double matrix[][]=new Double[countRows(f1)-1][row.length-1];	//matrix is 2d Double array to store data from file
        while((line=br.readLine())!=null){
            row=line.split(",");
            i=1;
            while(i<row.length)
            {
                matrix[j][i-1]=Double.parseDouble(row[i]);
                i++;
            }
            j++;
        }
        return matrix;
    }
    private int countRows(String s)
	{
		int count=0;
		File f=new File(s);
		try
		{
			Scanner sc=new Scanner(f);
			while(sc.hasNextLine()){
				count++;
				sc.nextLine();
			}
		}
		catch (IOException ex) 
		{
				ex.printStackTrace();
		}
		return count;
	}
	
}
