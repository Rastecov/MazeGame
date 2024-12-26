import java.util.Random;

public class Maze {

    // random object instance to generate directions and paths
    private static final Random randomMaze = new Random();
    // create rows and columns
    private int rows, colums;
    // Array for the maze
    private Cell[][] maze;

    // Initialize constructor with a maze
    public Maze(int rows, int colums) {
        this.rows = rows;
        this.colums = colums;
        // Create the maze grid
        this.maze = new Cell[rows][colums];
        // Initialize the grid with walls
        initializeMazeWithWalls();
    }

    // Fill the maze with Walls
    private void initializeMazeWithWalls() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++) {
                // set each cells in the grid to a wall, which is also are unvisited walls
                maze[i][j] = Cell.WALL;
            }
        }
    }

    // Generate a random maze
    public void generateRandomMaze(int x, int y) {
        // initialize the new cells  in the maze with walls
        initializeMazeWithWalls();
        // Create maze structure by making a Space around the walls recursively
        makeMazeStructure(x, y);

    }

    // Recursive Method to make up  a maze structure
    private void makeMazeStructure(int x, int y) {
        // mark the current cell as a part of the path
        maze[x][y] = Cell.PATH;

        // define possible direction, move one cell to the right, downward, to the left, upward to create a path around the walls
        int[][] directions = {
                { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }
        };

        // shuffle the direction array using the fisher yates algorithm to randomize the direction to create a unique maze everytime
        shuffleArray(directions);

        // try to make possible path in each shuffled direction
        for (int[] dir : directions) {

            // Calculate the next cell to visit two cell away from the current cell in the
            // shuffled direction
            // Move two cell from the current row index in the shuffled direction
            int newX = x + dir[0] * 2;
            // Move two cell from the current column index in the shuffled direction
            int newY = y + dir[1] * 2;

            // Check if the new cell is within bounds, is unvisited and is still a wall to determine if it can be made into a path
            if (newX >= 0 && newY >= 0 && newX < rows && newY < colums && maze[newX][newY] == Cell.WALL) {

                // Mark the cell between the current cell and the next cell as a path to create
                // the maze struture
                maze[x + dir[0]][y + dir[1]] = Cell.PATH;
                // Recursively make new spaces starting from the next cell
                makeMazeStructure(newX, newY);

            }
        }

    }

    // This shuffleArray was found on stack overflow, the Fisher yates used to shuffle the direction randomly :
    // Source: https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array
    private void shuffleArray(int[][] array) {
        for (int i = array.length - 1; i > 0; i--) {
            // Generate a random index between 0 and i
            int j = randomMaze.nextInt(i + 1);
            // Swap element at i with j
            int[] temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // Get the maze grid
    public Cell[][] getMaze() {
        return maze;
    }

    // Method to display the maze
    public void printMaze() {
        for (Cell[] row : maze) {
            for (Cell cell : row) {
                // print the value of each cell in the console 0 for wall, 1 for path and 2 for track
                System.out.print(cell.getCellValue() + " ");
            }
            // Move to the next row after printing all cells in the current row
            System.out.println();
        }
    }

}
