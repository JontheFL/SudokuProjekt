import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// Make sure that the imports are correct.
import solver.MySudokuSolver;
import solver.SudokuSolver;     //wtf menas?

public class TestSolver {
	private SudokuSolver solver;
	
	/**
	 * Runs before each test and creates a new instance of the solver class.
	 * */
	@BeforeEach public void setUp() {
		// Make sure you use the correct class of your solver.
		this.solver = new MySudokuSolver();
	}
	
	/**
	 * Runs after each test and destorys the instance of the solver class.
	 */
	@AfterEach public void tearDown() {
		this.solver = null;
	}
	
	/**
	 * Tests that the setGrid method doesn't throw 
	 * an exception when everything is correct.
	 * */
	@Test public void testSetGridNotThrows() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
			
		assertDoesNotThrow(
			() -> solver.setGrid(board),
			"Solver::setGrid shouldn't throw an exception if everything is in range [1..9]."
		);
	}
	
	/**
	 * Tests that the setGrid method does throw 
	 * an exception when something is wrong.
	 * */
	@Test public void testSetGridThrows() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 10 },
			{ 0, 0, 0, 0, 0, 0, 0, 4, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 124, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, -3, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
			
		assertThrows(
			IllegalArgumentException.class, () -> solver.setGrid(board),
			"Solver::setGrid should thrown an exception if any number is out of bounds."
		);
			
		
		assertThrows(
			IllegalArgumentException.class, () -> solver.setGrid(new int[8][9]),
			"Solver::setGrid should thrown an exception if wrong dimension."
		);
			
		assertThrows(
			IllegalArgumentException.class, () -> solver.setGrid(new int[9][10]),
			"Solver::setGrid should thrown an exception if wrong dimension."
		);
	}
	
	/**
	 * Tests that solve doesn't modify the matrix that is
	 * provided to setGrid.
	 * */
	@Test public void testNoExternalModificationSet() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		
		int[][] board2 = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		
		solver.setGrid(board);
		solver.solve();
		assertTrue(
			Arrays.deepEquals(board, board2),
			"Solver::setGrid should copy the elements of the board and not the references to the arrays. " +
			"After Solver:solve the provided board should stay the same."
		);
	}
	
	/**
	 * Test that setGrid and getGrid does a deep copy.
	 * */
	@Test public void testSetAndGetGrid() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		
		solver.setGrid(board);
		int[][] board2 = solver.getGrid();
		
		assertNotSame(board, board2, "Solver::getGrid and Solver::setGrid should do a deep copy of the matrix.");
		for (int i = 0; i < board.length; i++) {
			assertNotSame(
				board[i], board2[i],
				"Solver:getGrid and Solver:setGrid shoud do a deep copy of the matrix, meaning the internal arrays should also be copied."
			);
		}
	}
	
	/**
	 * Makes sure that consecutive calls to getGrid yields different copies of the board.
	 * */
	@Test public void getGridConsecutiveCalls() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		
		solver.setGrid(board);
		int[][] board2 = solver.getGrid();
		assertNotSame(board, board2, "Solver::getGrid and Solver::setGrid should do a deep copy of the matrix.");
		for (int i = 0; i < board.length; i++) {
			assertNotSame(
				board[i], board2[i],
				"Solver:getGrid and Solver:setGrid shoud do a deep copy of the matrix, meaning the internal arrays should also be copied."
			);
		}
		
		int[][] board3 = solver.getGrid();
		assertNotSame(board2, board3, "Solver::getGrid consecutive calls to getGrid should be copies and not the same copy.");
		for (int i = 0; i < board.length; i++) {
			assertNotSame(
				board2[i], board3[i],
				"Solver:getGrid consecutive calls should be deepcopies of the matrix."
			);
		}
	}
	
	/**
	 * Tests that the copies of the arrays from getGrid and setGrid 
	 * are correct.
	 * */
	@Test public void testCorrectCopies() {
		int[][] board = new int[][] {
			{ 0, 0, 8, 0, 0, 9, 0, 6, 2 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 5 },
			{ 1, 0, 2, 5, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 2, 1, 0, 0, 9, 0 },
			{ 0, 5, 0, 0, 0, 0, 6, 0, 0 },
			{ 6, 0, 0, 0, 0, 0, 0, 2, 8 },
			{ 4, 1, 0, 6, 0, 8, 0, 0, 0 },
			{ 8, 6, 0, 0, 3, 0, 1, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 4, 0, 0 }
		};
		
		solver.setGrid(board);
		assertTrue(Arrays.deepEquals(
			board, solver.getGrid()), "Solver::getGrid and Solver::setGrid should copy the elements."
		);
	}

	//testar lösa lösbart sudoku
	@Test public void testSolveSolvablePuzzle() {
		int[][] board = new int[][] {
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};

	//testar lösa olösbart suduko
	solver.setGrid(board);
	assertTrue(solver.solve(), "Solver::solve returnerar true om lösbart");
	}

	@Test public void testSolveUnsolvablePuzzle() {
		int[][] board = new int[][] {
			{ 5, 3, 0, 0, 7, 0, 0, 0, 0 },
			{ 6, 0, 0, 1, 9, 5, 0, 0, 0 },
			{ 0, 9, 8, 0, 0, 0, 0, 6, 0 },
			{ 8, 0, 0, 0, 6, 0, 0, 0, 3 },
			{ 4, 0, 0, 8, 0, 3, 0, 0, 1 },
			{ 7, 0, 0, 0, 2, 0, 0, 0, 6 },
			{ 0, 6, 0, 0, 0, 0, 2, 8, 0 },
			{ 0, 0, 0, 4, 1, 9, 0, 0, 5 },
			{ 0, 0, 0, 0, 8, 0, 0, 7, 5 } //två femmor i slutet
		};
		solver.setGrid(board);
		assertFalse(solver.solve(), "Solver::solve ska returnera false");
	}

	//testa att rensa 1 ruta
	@Test public void testClear() {
		int[][] board = new int[][] {
			{5, 0, 0, 0, 0, 0, 0, 0, 0},
			{5, 0, 0, 0, 0, 0, 0, 0, 0},
			{5, 0, 0, 0, 0, 0, 0, 0, 0},
			{5, 0, 0, 0, 0, 0, 0, 0, 0},
			{5, 0, 0, 0, 0, 0, 0, 0, 0},
			{5, 0, 0, 0, 0, 0, 0, 0, 0},
			{5, 0, 0, 0, 0, 0, 0, 0, 0},
			{5, 0, 0, 0, 0, 0, 0, 0, 0},
			{5, 0, 0, 0, 0, 0, 0, 0, 0}
		};
		solver.setGrid(board);
		solver.clear(0,0);
		assertEquals(0, solver.get(0, 0), "Solver::clear should set row,col to 0");
	}

	//testa rensa allt!
	@Test public void testClearAll() {
		int[][] board = new int[][] {
			{5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5},
			{5, 5, 5, 5, 5, 5, 5, 5, 5},
		};
		solver.setGrid(board);
		solver.clearAll(); //rensa allt i brädet solver
		int[][] rensatBräde = solver.getGrid();
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				assertEquals(0, rensatBräde[row][col], "Solver::clearAll har rensat allt");
			}
		}
	}

	//testa om en bra placering blir bra
	@Test public void testIsValid() {
		int [][] board = new int [][] {
			{ 0, 1, 2, 3, 4, 5, 6, 7, 8 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		solver.setGrid(board);
		assertTrue(solver.isValid(0,0), "Solver::isValid funkar");
		solver.set(0,0,1); //lägg till ogiltig siffra
		assertFalse(solver.isValid(0,0), "Solver::isValid ska returnera false");
	}
}