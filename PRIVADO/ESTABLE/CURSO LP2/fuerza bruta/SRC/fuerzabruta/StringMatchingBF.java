package fuerzabruta;
import java.util.*;

/**
 * String matching brute force.
 */
public class StringMatchingBF {

	public int comparisons;

	public List<Integer> search(String text, String pattern) {
		char[] y = text.toCharArray();
		char[] x = pattern.toCharArray();
		int n = y.length;
		int m = x.length;
		comparisons = 0;
		List<Integer> indexes = new ArrayList<Integer>();

		/* Searching */
		int j = 0;
		for (int i = 0, l = n - m + 1; i < l; i++) {
			for (j = 0; j < m && x[j] == y[i+j]; j++) {
				comparisons++;
			}
			if (j >= m) {
				indexes.add(i);
			}
		}

		return indexes;
	}

	public static void main(String args[]) {

		String text = "gcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagaggcagagaggcatcgcagagagcagagagtatacagtacggcagagag";
		String pattern = "gcagagagtatacagtacggcagagaggcagag";

		StringMatchingBF bf = new StringMatchingBF();
		
		// Expected: [20, 67, 114, 161, 208, 255, 302, 349, 396, 443, 490, 537]
		System.out.printf("%s%n",bf.search(text, pattern));


	}

}