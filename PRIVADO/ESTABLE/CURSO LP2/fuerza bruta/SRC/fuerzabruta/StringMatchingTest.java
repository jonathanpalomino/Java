package fuerzabruta;
public class StringMatchingTest {

	public static void main(String[] args) {

		final int TESTS = 100000;		
		final int MAX_TEXT_LENTGH = 1024;
		final int MIN_TEXT_LENTGH = 256;
		final int MAX_PATTERN_LENGTH = 15;
		final int MIN_PATTERN_LENGTH = 5;
		
		long bmTotalMillis = 0;
		long bfTotalMillis = 0;
		long t1 = 0;
		
		int bmTotalComparisons = 0;
		int bfTotalComparisons = 0;
		
		StringMatchingBM bm = new StringMatchingBM();
		StringMatchingBF bf = new StringMatchingBF();
		
		for(int test = 100; test <= TESTS; test *= 10) {
			for (int i = 0; i<test; i++) {
				
				StringBuffer sb = new StringBuffer();
				int n = (int)(Math.random()*((MAX_TEXT_LENTGH-MIN_TEXT_LENTGH)))+MIN_TEXT_LENTGH;
				int m = (int)(Math.random()*((MAX_PATTERN_LENGTH-MIN_PATTERN_LENGTH)))+MIN_PATTERN_LENGTH;
				int im = (int)(Math.random()*(n-m));
				
				for (int j = 0; j<n; j++) {
					sb.append(((char)(Math.random()*('z'-'a')))+'a');
				}
				String text = sb.toString();			
				String pattern = text.substring(im,m+im);
				
				t1 = System.currentTimeMillis();
				bm.search(text, pattern);
				bmTotalMillis += System.currentTimeMillis()-t1;
				bmTotalComparisons += bm.comparisons;
				
				t1 = System.currentTimeMillis();
				bf.search(text, pattern);
				bfTotalMillis += System.currentTimeMillis()-t1;
				bfTotalComparisons += bf.comparisons;
				
			}
			
			/* Statistics */
			System.out.printf("%d tests --> BM: %d millis (%d comparisons)%n", test, bmTotalMillis, bmTotalComparisons);
			System.out.printf("%d tests --> BF: %d millis (%d comparisons)%n%n", test, bfTotalMillis, bfTotalComparisons);
		}
	}

}
