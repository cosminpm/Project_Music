import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class test {

	int len;
	// Problema 1
	int matrixElementsSum(int[][] matrix) {
		int result = 0;
		Set<Integer> colRem = new HashSet<Integer>();
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[r].length; c++) {
				System.out.println(matrix[r][c]);
                if (!colRem.contains(c)) {
					result += matrix[r][c];
					if (matrix[r][c] == 0) {
						colRem.add(c);
					}
				}

			}
		}
		return result;
	}
	
	// Problema 2
	String[] allLongestStrings(String[] inputArray) {
		List<String> r = new LinkedList<String>();
		len = 0;
		for (String string : inputArray) {
			if (string.length() >= len) {
				len = string.length();
				r.add(string);
			}
		}
		
		r.stream()
		.filter(s ->( s.length() < len)).collect(Collectors.toList());
		
		String[] result = new String[r.size()];
		for (int i = 0; i < r.size(); i++) {
			result[i] = r.get(i);
		}
		
		return result;
	}
	
	// Problema conjunto de caracteres
	
	int commonCharacterCount2(String s1, String s2) {
	    Set<Character> aux = new HashSet<Character>();
	    Set<Character> aux2 = new HashSet<Character>();		
		for(int i = 0; i < s1.length(); i++) {
			aux.add(s1.charAt(i));
		}
		for(int i = 0; i < s2.length(); i++) {
			aux2.add(s2.charAt(i));
		}
		System.out.println("1");
		for (Character character : aux) {
			System.out.println(character);
		}
		System.out.println("2");
		for (Character character : aux2) {
			System.out.println(character);
		}
		aux.retainAll(aux2);
		return aux.size();
	}
	
	boolean isLucky(int n) {
		String aux = Integer.toString(n);
		System.out.println(aux);
		int r1 = 0;
		int r2 = 0;

		for (int i = 0; i < aux.length(); i++) {
			if (aux.length()/2 > i)
			r1 += Character.getNumericValue(aux.charAt(i));
			else {
				r1 -= Character.getNumericValue(aux.charAt(i));
			}
		}

		if(r1 == r2) 
			return true;
		else 
			return false;
	}

}
