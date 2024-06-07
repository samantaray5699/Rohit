package Data_driven_testing;

public class RandomAlphanumericGeneration {

	public static void main(String[] args) {
	
		int n=20;
		//Choose character from the string to generate random
		String alphanumeric="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
		//crete StringBuilder Size of alphanumeric
		StringBuilder sb= new  StringBuilder(n);
		for(int i=0;i<n;i++)
		{
			//generate random character f9rom alphanumeric 0 to 20
			int index=(int)(alphanumeric.length()*Math.random());
			//add character one by one to the builder
			sb.append(alphanumeric.charAt(index));
		}
		System.out.println(sb);
	}

}
