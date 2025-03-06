import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List; 


public class SudokuGUI extends JFrame {
private SudokuSolver solver;
private JTextField[][] grid;
private JButton solveButton;
private JButton clearButton;
private List<int[]> felList;

    public SudokuGUI(SudokuSolver solver) {
        this.solver = solver;
        this.felList = new ArrayList<>(); //lista som kan lagra fel karaktärer
        createGUI();
    }

    private void createGUI(){

        setTitle("SudokuSolver123");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        this.grid = new JTextField[9][9];
        JPanel panel = new JPanel(new GridLayout(9, 9));

        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){

                grid[row][col] = new JTextField();
                grid[row][col].setHorizontalAlignment(JTextField.CENTER);
                grid[row][col].setFont(new Font("Arial", Font.BOLD, 20));

                if ((row / 3 + col / 3) % 2 == 0) {
                    grid[row][col].setBackground(Color.LIGHT_GRAY); //färga vissa
                }
                
                panel.add(grid[row][col]);
                
            }
        }
        
        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        solveButton = new JButton("Solve");
        clearButton = new JButton("Clear");
        buttonPanel.add(solveButton);
        buttonPanel.add(clearButton);
        solveButton.addActionListener(e -> solve());
        clearButton.addActionListener(e -> clearGrid());
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void solve(){
        int[][] matrix = new int[9][9];
        felList.clear();

        try {
            for (int row = 0; row < 9; row++){
                for (int col = 0; col < 9; col++){
                    String text = grid[row][col].getText();
                    if (text.isEmpty()){
                        matrix[row][col] = 0;
                    } else {
                        try {
                            matrix[row][col] = Integer.parseInt(text);
                            if (matrix[row][col] < 1 || matrix[row][col] > 9) {
                                felList.add(new int[]{row, col});
                            }
                        } catch (NumberFormatException e) {
                            felList.add(new int[]{row, col});
                        }
                    }
                }
            }
            if (!felList.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Felaktig inmatning, måste vara mellan 1-9");
                for (int[] pos : felList) {
                    int r = pos[0];
                    int c = pos[1];
                    blinkblink(r, c);
                    clearcell(r, c);
                }
                return;
            }

            //Här borde vi kolla om det är en lösning eller inte
            
            solver.setGrid(matrix);
            
            if (CheckIfSolvable(matrix) && solver.solve()){
                int[][] solution = solver.getGrid();
                for (int row = 0; row < 9; row++){
                    for (int col = 0; col < 9; col++){
                        grid[row][col].setText(solution[row][col] == 0 ? "" : Integer.toString(solution[row][col]));
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Det finns ingen lösning");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Felaktig inmatning, endast siffror tillåtna");
            clearGrid();
        }
    }
    private boolean CheckIfSolvable(int[][] matrix){
        for (int row = 0; row < 9; row++){
            for (int col = 0; col < 9; col++){
                if (matrix[row][col] != 0){
                    if (!solver.isValid(row, col)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void clearGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setText("");
            }
        }
        solver.clearAll();
    }

    
    private void clearcell(int row, int col){
        grid[row][col].setText(""); 
    }

    private void blinkblink(int row, int col){
        Timer timer = new Timer(500, new ActionListener() {
            private boolean isPink = true;

            @Override
            public void actionPerformed(ActionEvent e){
                grid[row][col].setBackground(isPink ? Color.PINK : Color.LIGHT_GRAY);
                isPink = !isPink;
            }
        });

        timer.setRepeats(true);
        timer.start();

        new Timer(2000, e -> {
            timer.stop(); // stoppar timern efter 2 sekunder
            if ((row / 3 + col / 3) % 2 == 0) {
                grid[row][col].setBackground(Color.LIGHT_GRAY);
            } else {
                grid[row][col].setBackground(Color.WHITE);
            }
        }).start();
    }

}