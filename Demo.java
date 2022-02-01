import MyGeneAnalysis.TTest_modT;
import MyGeneAnalysis.ShrinkageTTest_shrT;
class Demo
{
	public static void main(String arg[])
	{
		TTest_modT t=new TTest_modT("/home/tauseef/Documents/normal.csv","/home/tauseef/Documents/tumor.csv");
		ShrinkageTTest_shrT s=new ShrinkageTTest_shrT("/home/tauseef/Documents/normal.csv","/home/tauseef/Documents/tumor.csv");
		t.compute("/home/tauseef/Documents/outputmodT.csv");
		t.compute("/home/tauseef/Documents/outputmodT1.csv",0.5);
		s.compute("/home/tauseef/Documents/outputshrT.csv");
		s.compute("/home/tauseef/Documents/outputshrT1.csv",0.5);
	}
}
