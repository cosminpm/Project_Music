package umu.tds.test;

import java.util.*;

public class TestPlay {

	int matrixElementsSum(int[][] matrix) {
		int result = 0;
		Set<Integer> colRem = new HashSet<Integer>();
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[r].length; c++) {
				if (colRem.contains(c)) {
					c++;
				} else {
					System.out.println(matrix[r][c]);
					result += matrix[r][c];
					if (matrix[r][c] == 0) {
						colRem.add(c);
					}
				}

			}
		}
		return result;
	}
}