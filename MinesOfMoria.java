public class MinesOfMoria {
    // Array for the maze
    private Cell[][] maze;

    //create rows and column
    private int rows, columns;

    // Constructor that initioalize the random maze generated
    public MinesOfMoria(Cell[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.columns = maze[0].length;
    }

    // Default constructor with defined maze
    public MinesOfMoria() {
        this(new Cell[][] {
                { Cell.PATH, Cell.PATH, Cell.WALL, Cell.WALL, Cell.PATH },
                { Cell.WALL, Cell.PATH, Cell.PATH, Cell.PATH, Cell.WALL },
                { Cell.PATH, Cell.WALL, Cell.WALL, Cell.PATH, Cell.PATH },
                { Cell.WALL, Cell.PATH, Cell.WALL, Cell.PATH, Cell.PATH },
                { Cell.PATH, Cell.PATH, Cell.WALL, Cell.WALL, Cell.PATH },
        });
    }

    // Recursive method to find the path from (x, y) to the exit
    // Returns true if an exit was found
    public boolean findPath(int x, int y) {
        // Out of bounds or not a valid path
        if (x < 0 || y < 0 || x >= rows || y >= columns || maze[x][y] != Cell.PATH) {
            return false;
        }

        // Return true if the exit is found
        if (x == rows - 1 && y == columns - 1) {
            maze[x][y] = Cell.TRACK;
            return true;
        }

        // Exit is not found yet but the current cell is part of the path to the
        // solution mark it
        maze[x][y] = Cell.TRACK;

        // try to move down, righ, up or left paths from using recursion
        if (findPath(x + 1, y) || findPath(x, y + 1) || findPath(x - 1, y) || findPath(x, y - 1)) {
            return true;
        }

        // Backtracking, change the cell to path as there is no valid path that leads to
        // the exit from this cell
        maze[x][y] = Cell.PATH;
        return false;

    }
 // Get the maze grid
 public Cell[][] getMaze() {
    return maze;
}

// Method to display the maze
public void printMaze() {
    for (Cell[] row : maze) {
        for (Cell cell : row) {
            // print the value of each cell in the console
            System.out.print(cell.getCellValue() + " ");
        }
        // Move to the next row
        System.out.println();
    }
}
}