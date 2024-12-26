// enum class to represent each cell in the maze 
public enum Cell {
    WALL(0), PATH(1), TRACK(2);

    private final int cellValue;

    // Initialize the Cell constructor with a value for each cell type with 0
    // representing the walls, 1 the valid path and 2 the solution path
    Cell(int cellValue) {
        this.cellValue = cellValue;
    }

    // Get the cell value
    public int getCellValue() {
        return cellValue;
    }

}
