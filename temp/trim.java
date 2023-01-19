import java.util.StringTokenizer;
class trim
{
	/*public static void main(String args[])
	{
		String s = "53877 20010101 -9.000  -82.61   35.49 -9999.0 -9999.0 -9999.0 -9999.0 -9999.0 -9999.00 R -9999.0 -9999.0    -3.7 -9999.0 -9999.0 -9999.0 -99.000 -99.000 -99.000 -99.000 -99.000 -9999.0 -9999.0 -9999.0 -9999.0 -9999.0";
		String sub = s.substring(38,46);
		System.out.println(sub.trim());
	}*/
	
	
	public static void main(String args[])
	{
		String s = "53877 20010101 -9.000  -82.61   35.49 -9999.0 -9999.0 -9999.0 -9999.0 -9999.0 -9999.00 R -9999.0 -9999.0    -3.7 -9999.0 -9999.0 -9999.0 -99.000 -99.000 -99.000 -99.000 -99.000 -9999.0 -9999.0 -9999.0 -9999.0 -9999.0";
		String sub = s.substring(38,46);
		System.out.println(sub.trim());
		String st[] = s.split("\\s+");
		System.out.println(st[5]);
		System.out.println(st[1].substring(0,4));
	}
}	
	
