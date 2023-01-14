import java.util.StringTokenizer;
class Must 
{
	public static void main(String a[])
	{	
		String s = "Hello Friends, How are you ? ";
		StringTokenizer st = new StringTokenizer(s);
		while(st.hasMoreTokens())
		{
			String val = st.nextToken();
			System.out.println(val.toUpperCase());		
		}
		
	
	}
}	
