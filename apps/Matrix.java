import java.util.Arrays;

public class Matrix<T extends Number> {
    private final int rows;
    private final int cols;
    private final T[][] data;

    public Matrix(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
    this.data = (T[][]) new Number[rows][cols];
}

    public void populate(T[][] elements) {
        if (elements.length != rows || elements[0].length != cols) {
            throw new IllegalArgumentException("Input dimensions must match matrix dimensions.");
        }
        for (int i = 0; i < rows; i++) {
            if (elements[i].length != cols) {
                throw new IllegalArgumentException("Input dimensions must match matrix dimensions.");
            }
            System.arraycopy(elements[i], 0, data[i], 0, cols);
        }
    }

    public T get(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Invalid row or column index.");
        }
        return data[row][col];
    }

    public void set(int row, int col, T value) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IndexOutOfBoundsException("Invalid row or column index.");
        }
        data[row][col] = value;
    }


    @SuppressWarnings("unchecked")
    public Matrix<T> add(Matrix<T> other) {
        if (other.rows != rows || other.cols != cols) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition.");
        }
        Matrix<T> result = new Matrix<>(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double sum = data[i][j].doubleValue() + other.data[i][j].doubleValue();
                if (data[i][j] instanceof Integer) {
                    result.data[i][j] = (T) Integer.valueOf((int) sum);
                } else if (data[i][j] instanceof Double) {
                    result.data[i][j] = (T) Double.valueOf(sum);
                }
            }
        }
        return result;
    }
     
    @SuppressWarnings("unchecked") 
    public Matrix<T> subtract(Matrix<T> other) {
        if (other.rows != rows || other.cols != cols) {
            throw new IllegalArgumentException("Matrix dimensions must match for subtraction.");
        }
        Matrix<T> result = new Matrix<>(rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double diff = data[i][j].doubleValue() - other.data[i][j].doubleValue();
                if (data[i][j] instanceof Integer) {
                    result.data[i][j] = (T) Integer.valueOf((int) diff);
                } else if (data[i][j] instanceof Double) {
                    result.data[i][j] = (T) Double.valueOf(diff);
                }
            }
        }
        return result;
    }
    
    @SuppressWarnings("unchecked")
    public Matrix<T> multiply(Matrix<T> other) {
        if (cols != other.rows) {
            throw new IllegalArgumentException("Invalid dimensions for matrix multiplication.");
        }
        Matrix<T> result = new Matrix<>(rows, other.cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < other.cols; j++) {
                double sum = 0;
                for (int k = 0; k < cols; k++) {
                    sum += data[i][k].doubleValue() * other.data[k][j].doubleValue();
                }
                if (data[i][0] instanceof Integer) {
                    result.data[i][j] = (T) Integer.valueOf((int) sum);
                } else if (data[i][0] instanceof Double) {
                    result.data[i][j] = (T) Double.valueOf(sum);
                }
            }
        }
        return result;
    }

    public Matrix<T> transpose() {
        Matrix<T> result = new Matrix<>(cols, rows);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                result.data[i][j] = data[j][i];
            }
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append(Arrays.toString(data[i])).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Matrix<Integer> intMatrix = new Matrix<>(2, 3);
        Integer[][] intData = {{1, 2, 3}, {4, 5, 6}};
        intMatrix.populate(intData);

        Matrix<Double> doubleMatrix = new Matrix<>(2, 2);
        Double[][] doubleData = {{1.5, 2.5}, {3.5, 4.5}};
        doubleMatrix.populate(doubleData);

        System.out.println("Integer Matrix:");
        System.out.println(intMatrix);

        System.out.println("Double Matrix:");
        System.out.println(doubleMatrix);

        Matrix<Integer> intMatrixTranspose = intMatrix.transpose();
        System.out.println("Integer Matrix Transpose:");
        System.out.println(intMatrixTranspose);

        Matrix<Double> doubleMatrixTranspose = doubleMatrix.transpose();
        System.out.println("Double Matrix Transpose:");
        System.out.println(doubleMatrixTranspose);

        Matrix<Integer> intMatrixAddition = intMatrix.add(intMatrix);
        System.out.println("Integer Matrix Addition:");
        System.out.println(intMatrixAddition);

        Matrix<Double> doubleMatrixMultiplication = doubleMatrix.multiply(doubleMatrix);
        System.out.println("Double Matrix Multiplication:");
        System.out.println(doubleMatrixMultiplication);
    }
}
