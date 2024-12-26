import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;

public class MazeSolver {

    // initializer the size of each cell to be 60
    private static final int cellSize = 60;
    private static Cell[][] mazeGrid;

    public static void main(String[] args) {
        /// Using the default maze
        MinesOfMoria moria = new MinesOfMoria();

        System.out.println("Hardcoded Maze:");
        moria.printMaze();

        if (moria.findPath(0, 0)) {
            System.out.println("\nPath found:");
            moria.printMaze();
        } else {
            System.out.println("\nNo path found.");
        }

        // Generating a random 5 x 5 Maze
        Maze generateRandomMaze = new Maze(5, 5);
        generateRandomMaze.generateRandomMaze(0, 0);

        // Get the random maze grid
        mazeGrid = generateRandomMaze.getMaze();
        // Create the GUI frame
        JFrame frame = new JFrame("Maze GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        // Set the frame dimension based on the maze dimension and the margins
        frame.setSize(400, 400);
        // Create the maze Panel
        JPanel mazePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);

                for (int x = 0; x < mazeGrid.length; x++) {
                    for (int y = 0; y < mazeGrid[x].length; y++) {
                        if (mazeGrid[x][y] == Cell.WALL) {
                            // make the wall blacks
                            graphics.setColor(Color.BLACK);
                        } else if (mazeGrid[x][y] == Cell.PATH) {
                            // make the path white
                            graphics.setColor(Color.WHITE);
                        } else if (mazeGrid[x][y] == Cell.TRACK) {
                            // make the exit path green
                            graphics.setColor(Color.GREEN);
                        }
                        graphics.fillRect(y * cellSize, x * cellSize, cellSize, cellSize);
                        graphics.setColor(Color.LIGHT_GRAY);
                        graphics.drawRect(y * cellSize, x * cellSize, cellSize, cellSize);
                    }
                }
            }

        };

        // Create a button to generate a new maze
        JButton regenerateMazebutton = new JButton("Generate A New Maze");
        // Create a button to show the solution to the maze
        JButton solutionButton = new JButton("Maze Solution");

        regenerateMazebutton.addActionListener(e -> {
            // generate a new random maze
            generateRandomMaze.generateRandomMaze(0, 0);
            // update the current maze grid to the new maze generated
            mazeGrid = generateRandomMaze.getMaze();
            // Add colors to the new maze generated
            mazePanel.repaint();
            // Set the button to solve the maze to clikable
            solutionButton.setEnabled(true);
            // Set the button to regenerate a new maze to uncklikable
            regenerateMazebutton.setEnabled(false);
        });

        solutionButton.addActionListener(e -> {

            // solve the path to exit in the maze then print the solution
            MinesOfMoria solution = new MinesOfMoria(mazeGrid);

            if (solution.findPath(0, 0)) {
                System.out.println("\nPath found:");
                solution.printMaze();
            } else {
                System.out.println("\nNo path found.");
            }
            // Add colors to the new maze generated
            mazePanel.repaint();

            // Set the button to solve the maze to uncklikable
            solutionButton.setEnabled(false);
            // Set the button to regenerate a new maze to clikable
            regenerateMazebutton.setEnabled(true);

        });

        // Panel to hold both button to generate amd to solve the maze
        JPanel buttonPanel = new JPanel();
        // set the buttons horizontally then add them to the panel
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(regenerateMazebutton);
        buttonPanel.add(solutionButton);

        // add the panel and the button panel to the frame then make it visible
        frame.add(mazePanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);

    }

}
