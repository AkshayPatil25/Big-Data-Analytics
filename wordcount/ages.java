// Extract numbers/digits from paragraph

import java.util.StringTokenizer;
class ages 
{
	static boolean isNumber(String s)
	{
		for(int i=0;i<s.length();i++)
		{
			char ch = s.charAt(i);
			if (ch < '0' || ch > '9')
			   return false;
		}
		return true;
	}
		
	public static void main(String a[])
	{	
		String s="They are 21 and 22 years old.";
		StringTokenizer st = new StringTokenizer(s);
		while(st.hasMoreTokens())
		{
			String t = st.nextToken();
			if(isNumber(t))
			System.out.println(t);
		}
	}
}
