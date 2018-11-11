package login;

import java.util.Random;

public class Enc {
	
	protected String encrypt(int n) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
      String i="."+n+".";
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        salt.insert(5,i);
        String saltStr = salt.toString();
        return saltStr;
    }
	public int decrypt(String s)
	{
		String d[]=s.split("\\.");
	     int num=Integer.valueOf(d[1]);
	     return num;
	}
	public static void main(String args[])
	{
		Enc e=new Enc();
		String s=e.encrypt(8);
		int num=e.decrypt(s);
		System.out.println(s);
		System.out.println(num);
	}
}
