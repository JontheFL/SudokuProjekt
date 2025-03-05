
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

        if (digit < 1 || digit > 9){
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

        if (matrix[row][col] == 0){
            return 0;
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
        return false;
    }

	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 * 
	 * @return true if all filled in digits follows the the sudoku rules
	 */
	public boolean isAllValid(){
        return false;
    }

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	public void setGrid(int[][] m){
        return;
    }

	/**
	 * Returns a matix with all digits in the sudoku grid.
	 * 
	 * @return a matix with all digits in the sudoku grid
	 */
	public int[][] getGrid(){
        return null;
    }
}
