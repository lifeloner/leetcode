package leetcode;

public class NumMatrix {
	private int[][] res;

	public NumMatrix(int[][] matrix) {
		res = matrix;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (i - 1 >= 0) {
					res[i][j] += res[i - 1][j];
				}
				if (j - 1 >= 0) {
					res[i][j] += res[i][j - 1];
				}
				if (i >= 1 && j >= 1) {
					res[i][j] = res[i][j] - res[i - 1][j - 1];
				}
			}
		}
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		int sum = res[row2][col2];
		if (col1 - 1 >= 0) {
			sum -= res[row2][col1 - 1];
		}
		if (row1 - 1 >= 0) {
			sum -= res[row1 - 1][col2];
		}
		if (col1 - 1 >= 0 && row1 - 1 >= 0) {
			sum += res[row1 - 1][col1 - 1];
		}
		return sum;
	}

	public static void main(String[] args) {
		int[][] array = { { 3, 0, 1, 4, 2 }, { 5, 6, 3, 2, 1 }, { 1, 2, 0, 1, 5 }, { 4, 1, 0, 1, 7 },
				{ 1, 0, 3, 0, 5 } };
		NumMatrix matrix = new NumMatrix(array);
		System.out.println(matrix.sumRegion(2, 1, 4, 3));
	}
}
