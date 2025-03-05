public interface SudokuSolver {
	/**
	 * Solves the sudoku.
	 * 
	 * @return true if the sudoku is solveable
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col. The digit 0 represents an empty box.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IndexOutOfBoundsException if row or col is outside the range[0..8]
	 * @throws IllegalArgumentException if digit is outside the range [0..9]
	 */
	void set(int row, int col, int digit);

	/**
	 * Returns the digit in the box row, col.
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IndexOutOfBoundsException if row or col is outside the range[0..8]
	 * @return the digit in box row, col or 0 if the box i empty
	 */
	int get(int row, int col);

	/**
	 * Clears the digit in the box row, col.
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IndexOutOfBoundsException if row or col is outside the range[0..8]
	 */
	void clear(int row, int col);

	/**
	 * Clears all digits in the sudoku.
	 */
	void clearAll();

	/**
	 * Checks that the digit in the box row, col follows the sudoku rules.
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IndexOutOfBoundsException if row or col is outside the range [0..8]
	 * @return true if the box is empty or if the digit in the box row, col follows 
	 * the sudoku rules.
	 */
	boolean isValid(int row, int col);

	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 * 
	 * @return true if all filled in digits follows the the sudoku rules
	 */
	boolean isAllValid();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setGrid(int[][] m);

	/**
	 * Returns a matix with all digits in the sudoku grid.
	 * 
	 * @return a matix with all digits in the sudoku grid
	 */
	int[][] getGrid();
}