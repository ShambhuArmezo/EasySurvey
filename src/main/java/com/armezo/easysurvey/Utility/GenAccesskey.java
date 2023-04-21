package com.armezo.easysurvey.Utility;

public class GenAccesskey {
	
	public static String generateAccesskey( int key_length ) 
	{
		
			
        String accesskey="";
	    
	    String chars = "ABCDEFGHJKMNPQRSTUVWXYZ23456789";
		String pass="";
		int i=0;
		boolean flag=true;
			
			
				 i++;
				 for(int x=0; x < key_length; x++ )
				 {
					  int  j = (int)Math.floor(Math.random() * 31);
					  pass += chars.charAt(j);
				 }
				
				
					
					accesskey=pass; 
					
			
	  return accesskey;
	}
	
	
	public static String replaceNull(String param ) 
	{
		String result="";
		if(param == null)
		{
			result=""	;
		}else
		{
			result= param	;
		}
					
			
	  return result;
	}

}
