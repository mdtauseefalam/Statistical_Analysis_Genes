package MyGeneAnalysis;
import java.io.*;
import java.util.Objects;
import java.util.*;
import java.lang.*;
class ShrTTest
{
	//To compute Shrinkage T value of each row
	static Double[] t_kStar_value(Double[][] arr,Double[][] brr)
    	{
        Double tk[]=new Double[arr.length];                        
        for(int i=0; i<arr.length; i++)
        {
            Double Xi=average(arr[i]);                 //Xmeank1
            Double Yi=average(brr[i]);                 //Xmeank2
            tk[i]=(Math.abs(Xi-Yi));                              //Xmeank1-Xmeank2
            Double vk1=VstarK(i,arr);                     //V*k1/n1 
            Double vk2=VstarK(i,brr);                     //V*k2/n2
            tk[i]=tk[i]/(Math.sqrt(vk1+vk2));           //(Xmeank1-Xmeank2)/sqrt(v*k1/n1+v*k2/n2)
        }
        return tk;
   	 }
	//Calculating for Vk*/n=(lambda*)*Vmedian+(1-(lambda*))*Vk
	static Double VstarK(int k, Double M[][])
	{             
        Double vk=0.0;
        Double n=Double.valueOf(M[k].length);
        vk=Y(k,M)*Median(k,M);      		 //Y*Vmedian
        vk+=(1-Y(k,M))*Vk(k, M);            //Y*Vmedian+(1-Y)*Vk
        return vk/n;
    	}
	 //Calculating lambda*=Min[1,{Summation upto k(Variance(Vk))/Summation upto k(Vk-Vmedian)^2}]
    static Double Y(int k, Double M[][])                   
    {
        Double y=0.0;
        Double numerator=0.0;
        Double denominator=0.0;
        for(int i=0; i<M.length; i++)
        {
            numerator+=Variance(k,M);
            Double x=(Vk(i, M)-Median(k, M));   //Vk-Vmedian
            denominator+=(x*x);
        }   
        y=numerator/denominator;				//Summation upto k(Variance(Vk))/Summation upto k(Vk-Vmedian)^2
        return (min(1.0, y));                //Min[1,{Summation upto k(Variance(Vk))/Summation upto k(Vk-Vmedian)^2}]
    }
	
	//Calculation of Variance(Vk) = (n/(n-1)^3 * Summation(Wik-W`k)^2)
    static Double Variance(int k,Double M[][])
    {
        Double variance=0.0;
		Double[][] temp=Wik(M);							//Wik;
		for(int i=0; i<M[k].length; i++)
        {
			Double wik=(temp[k][i]-Wk(k,M));				//Wik-W`k
            variance+=(wik*wik);						//Summation(Wik-W`k)^2
        }
        Double n=Double.valueOf(M[k].length);
        variance = (variance*n)/((n-1)*(n-1)*(n-1));	//(n/(n-1)^3 * Summation(Wik-W`k)^2)
        return variance;
    }
	
	//Calculating Vk=(n*Wk)/(n-1)
    static Double Vk(int i,Double M[][])
	{
        Double n=Double.valueOf(M[i].length);
        return (n/(n-1)*Wk(i,M));				//(n*Wk)/(n-1)
    }
	
	//Calculation of Wk = ((1/n )* Summation((Xik-Xmeank)^2))
    static Double Wk(int k,Double M[][])
    {
        Double XI=0.0;
        for(int i=0; i<M[k].length; i++)
        {
            Double t=(M[k][i]-average(M[k]));			//(Xik-Xmeank)^2
            XI+=(t*t);									//Summation((Xik-Xmeank)^2)
        }
        return XI/M[k].length;
    }
	//Calculating Wik=(Xik-Xmeank)^2
	static Double[][] Wik(Double[][] Arr)
	{
		Double[][] temp=new Double[Arr.length][Arr[0].length];
		for(int j=0;j<Arr.length;j++)
			for(int i=0; i<Arr[j].length; i++)
			{
				temp[j][i]=(Arr[j][i]-average(Arr[i]))*(Arr[j][i]-average(Arr[i]));			//(Xik-Xmeank)^2
			}
		return temp;
	}
	
	//Calculating Median
    static Double Median(int k,Double m[][])
    {
        Double median=0.0;
        median=m[k][0];
        Double row[]=m[k];
        Arrays.sort(row);
        median=row[(1+row.length)/2];            
        return median;
    }
	
	//Calculating Average
	static Double average(Double[] arr)
	{
		Double s=0.0;
		int len=arr.length;
		for(int i=0;i<len;i++)
			s+=arr[i];
		return (s/len);
	}
	//For minimum of two Values
	static Double min(Double a, Double b)
    {
        Double x=(a>b)?(b):(a);
        return x;
    }
}
