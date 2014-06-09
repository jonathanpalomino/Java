package prueba;
/**
 *
 * @author JONATHAN
 */
import java.util.*;
 
public class LZWv1 {
    /** Compress a string to a list of output symbols. */
    public static List<Integer> compress(String uncompressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<String,Integer> dictionary = new HashMap<String,Integer>();
        for (int i = 0; i < 256; i++)
            dictionary.put("" + (char)i, i);
 
        String w = "";
        List<Integer> result = new ArrayList<Integer>();
        for (char c : uncompressed.toCharArray()) {
            String wc = w + c;
            if (dictionary.containsKey(wc))
                w = wc;
            else {
                result.add(dictionary.get(w));
                // Add wc to the dictionary.
                dictionary.put(wc, dictSize++);
                w = "" + c;
            }
        }
 
        // Output the code for w.
        if (!w.equals(""))
            result.add(dictionary.get(w));
        return result;
    }
 
    /** Decompress a list of output ks to a string. */
    public static String decompress(List<Integer> compressed) {
        // Build the dictionary.
        int dictSize = 256;
        Map<Integer,String> dictionary = new HashMap<Integer,String>();
        for (int i = 0; i < 256; i++)
            dictionary.put(i, "" + (char)i);
 
        String w = "" + (char)(int)compressed.remove(0);
        String result = w;
        for (int k : compressed) {
            String entry;
            if (dictionary.containsKey(k))
                entry = dictionary.get(k);
            else if (k == dictSize)
                entry = w + w.charAt(0);
            else
                throw new IllegalArgumentException("Bad compressed k: " + k);
 
            result += entry;
 
            // Add w+entry[0] to the dictionary.
            dictionary.put(dictSize++, w + entry.charAt(0));
 
            w = entry;
        }
        return result;
    }
 
    public static void main(String[] args) {
        List<Integer> compressed = compress("1F8B080000000000000BCD53BB0DC23010BDE0540C80C40E2C01951B33421AD734740C404FEF25B2013B44A245A2638048B4F87EB1E3044149F17497F3BDFBD82F00D50AFE031E60B1619853B40100AE31EE04E718B392EB124F39DF30E4DB0CD8EBC5BD089D00FD388F59F237717B892367CB713A0F294F79EA9B36ABED8B7D6F3297CB66ECC7F3AA0F77069EA3A5FEE57E8ECF9547B53A8929F611355BBACB46721BAEAB75CC735C97BE43F289E7D92A97E6D3B792BA94AB9C5EFC5678729FFA9E937DEAEC7E1ED11EE5CC6631EC75493105E5AD33AD200E32D32E81DE2ACC8376EEA6F152B7AA19D440099C6BD0460EADE73FEB15FF815C0FA58E7FD2BF9DEFA175ABFE0D3B8D2DE600040000");
        //System.out.println(compressed);
        for (int k=0 ;k< compressed.size();k++) {
            char c = (char)compressed.get(k).intValue();
            System.out.print(c);
            //System.out.println(compressed.get(k) +" --- "+c);
        }
        System.out.println("");
        String decompressed = decompress(compressed);
        System.out.println(decompressed);
    }
}
