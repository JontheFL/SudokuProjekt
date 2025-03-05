
public class MySudokuSolver implements SudokuSolver {
    private int[][] matrix = new int[9][9];

    public MySudokuSolver(){
        this.matrix = matrix;
    }

    /**
	 * Solves the sudoku.
	 * 
	 * @return true if the sudoku is solveable
	 */
	public boolean solve(){
        return false;
    }

	/**
	 * Puts digit in the box row, col. The digit 0 represents an empty box.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IndexOutOfBoundsException if row or col is outside the range[0..8]
	 * @throws IllegalArgumentException if digit is outside the range [0..9]
	 */
	public void set(int row, int col, int digit){
        if (row < 0 || row > 8 || col < 0 || col > 8){
            throw new IndexOutOfBoundsException("Fel!");
        }

        if (digit < 0 || digit > 9){
            throw new IllegalArgumentException("Dumbom, fel värde toker!");
        }

        matrix[row][col] = digit;
    }

	/**
	 * Returns the digit in the box row, col.
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IndexOutOfBoundsException if row or col is outside the range[0..8]
	 * @return the digit in box row, col or 0 if the box i empty
	 */
	public int get(int row, int col){
        if (row < 0 || row > 8 || col < 0 || col > 8){
            throw new IndexOutOfBoundsException("Fel!");
        }

        return matrix[row][col];
    }

	/**
	 * Clears the digit in the box row, col.
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IndexOutOfBoundsException if row or col is outside the range[0..8]
	 */
	public void clear(int row, int col){
        if (row < 0 || row > 8 || col < 0 || col > 8){
            throw new IndexOutOfBoundsException("Fel!");
        }
        matrix[row][col] = 0;
    }

	/**
	 * Clears all digits in the sudoku.
	 */
	public void clearAll(){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                matrix[i][j] = 0;
            }
        }
    }

	/**
	 * Checks that the digit in the box row, col follows the sudoku rules.
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IndexOutOfBoundsException if row or col is outside the range [0..8]
	 * @return true if the box is empty or if the digit in the box row, col follows 
	 * the sudoku rules.
	 */
	public boolean isValid(int row, int col){
        if (row < 0 || row > 8 || col < 0 || col > 8){
            throw new IndexOutOfBoundsException("Fel!");
        }
		int siffra = matrix[row][col];
		if (siffra == 0) {
			return true; //tom ruta
		}
		for (int i = 0; i < 9; i++) {
			if (i != row && matrix[row][i] == siffra) { //checka rad
				return false;
			}
			if (i != col && matrix[i][col] == siffra) { //checka kolumn
				return false;
			}
		}
		//nu jävlar kollar vi 3x3

		int row3 = (row / 3) * 3;
		int col3 = (col / 3) * 3;
		for (int i = row3; i < row3+3; i++) {
			for (int j = col3; j < col3+3; j++) {
				if (i != row && j != col && matrix[i][j] == siffra) {
					return false;
				}
			}
		}
		return true; //alla tester funkar
    }

	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 * 
	 * @return true if all filled in digits follows the the sudoku rules
	 */
	public boolean isAllValid(){
        return isValid(9,9);
    }

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	public void setGrid(int[][] m){
        if (m.length != 9 || m[0].length != 9){
            throw new IllegalArgumentException("Måste vara 9x9");
        }

        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                int value = m[i][j];

                if (value < 0 || value > 9){
                    throw new IllegalArgumentException("värdet måste vara mellan 0-9");
                }
                matrix[i][j] = value;
            }
        }
    }

	/**
	 * Returns a matrix with all digits in the sudoku grid.
	 * 
	 * @return a matix with all digits in the sudoku grid
	 */
	public int[][] getGrid(){
        return matrix; //walla kebab
    }
}
