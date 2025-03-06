public class MAIN {
    public static void main(String[] args) {
        SudokuSolver solver = new MySudokuSolver();
        SudokuGUI gui = new SudokuGUI(solver);
        gui.setVisible(true);
    }
}
