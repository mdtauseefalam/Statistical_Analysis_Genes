package MyGeneAnalysis;
import java.lang.Math;
class TTest
{
	//To Calculate Xmean=1/n*(summation(Xi))
	static double average(double[] arr)
	{
		double s=0.0;
		int len=arr.length;
		for(int i=0;i<len;i++)
			s+=arr[i];
		return (s/len);
	}
	//To Calculate Standard Deviation:= SD=1/n*(Summation((Xi-Xmean)^2))
	static double stdevtn(double[] arr)
	{
		double avgarr=average(arr);
		int len=arr.length;
		double s=0.0;
		for(int i=0;i<len;i++)
			s+=((arr[i]-avgarr)*(arr[i]-avgarr));
		return (s/len);
	}
	//To Calculate Standard error:= Sx1-x2=sqrt(SD1/n1+SD2/n2)
	static double stnderror(double[] a,double[] b)
	{
		int n1=a.length;
		int n2=b.length;
		double sderr=Math.sqrt(((stdevtn(a)/n1)+(stdevtn(b)/n2)));
		return sderr;
	}
	//To Calculate t value:= t=(X1mean-X2mean)/Sx1-x2
	static double t_value(double[] a,double[] b)
	{
		double avgA=average(a);
		double avgB=average(b);
		double sderr=stnderror(a,b);
		return (Math.abs(avgA-avgB)/sderr);
	}
}
