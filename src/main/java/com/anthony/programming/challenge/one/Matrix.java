package com.anthony.programming.challenge.one;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Matrix {

    private char[][] dataMatrix;
    private int rows;
    private int cols;

    /**
     * Construct Matrix by specifying rows, columns and passing data
     * @param num_rows
     * @param num_cols
     * @param data
     */
    public Matrix(int num_rows, int num_cols, List<Character> data) {
        if (num_rows < 1 || num_cols < 1 || Objects.isNull(data) || (!Objects.isNull(data) && data.isEmpty()) ||
                (num_cols * num_rows != data.size())) {
            throw new IllegalArgumentException("Invalid array config");
        }

        this.rows = num_rows;
        this.cols = num_cols;
        this.dataMatrix = new char[num_rows][num_cols];

        ListIterator<Character> iterator = data.listIterator();

        for (int i = 0; i < num_rows; i++) {
                for (int j = 0; j < num_cols; j++) {
                    if(iterator.hasNext())
                    this.dataMatrix[i][j] = iterator.next();
                }
        }
    }

    /**
     * Check is any given position is valid as per current matrix
     * @param position
     * @return
     */
    boolean isValidPosition(Position position) {
        boolean result = true;
        if (Objects.isNull(position) || position.getRow() > (rows-1) ||
                position.getColumn() > (cols-1) || position.getRow() < 0 || position.getColumn() < 0) {
            result = false;
        }
        return result;
    }

    /**
     * Find Next Position in Row where searchChar is found
     *
     * @param searchChar
     * @param position
     * @return
     */
    Position findNextInRowFromPosition(char searchChar, Position position) {
        if(!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid Position");
        }

        Position charPosition = null;
        for (int j = position.getColumn(); j < cols; j++) {
                if(this.dataMatrix[position.getRow()][j] == searchChar) {
                    charPosition = new Position(position.getRow(),j);
                    break;
                }
        }

        return charPosition;
    }

    /**
     * Return horizontal array of a given length from a given position
     * If length is greater than available array then just return available array
     *
     * @param position
     * @param length
     * @return
     */
    char[] horizontalTraversal(Position position, int length) {
        if(!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid Position");
        }
        int targetLength = length < cols - position.getColumn() ? length : cols - position.getColumn();
        char[] subSet = new char[targetLength];
        int currentIndex = position.getColumn();
        int i = 0;
        while (currentIndex < cols && i < length) {
            subSet[i] = dataMatrix[position.getRow()][currentIndex];
            i++;
            currentIndex++;
        }
        return subSet;
    }

    /**
     * Return vertical array of a given length from a given position
     * If length is greater than available array then just return available array
     *
     * @param position
     * @param length
     * @return
     */
    char[] verticalTraversal(Position position, int length) {
        if(!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid Position");
        }
        int targetLength = length < rows - position.getRow() ? length : rows - position.getRow();
        char[] subSet = new char[targetLength];
        int currentIndex = position.getRow();
        int i = 0;
        while (currentIndex < rows && i < length) {
            subSet[i] = dataMatrix[currentIndex][position.getColumn()];
            i++;
            currentIndex++;
        }
        return subSet;
    }

    /**
     * check if a next element in the matrix exists
     *
     * @param position
     * @return
     */
    boolean hasNext( Position position) {
        if(!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid Position Specified");
        }

        boolean result = true;
        if (position.getColumn() == (cols - 1) && position.getRow() == (rows - 1)) {
            result = false;
        }
        return result;
    }

    /**
     * Returns next position in the Traversal order
     *
     * @throws IllegalArgumentException on Invalid Starting Position
     * @throws IllegalArgumentException on End Of Matrix
     * @param position
     * @return
     */
    Position nextPosition(Position position) {
        if(!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid Position Specified");
        }

        Position next;

        if(position.getColumn() < (cols-1)) {
            next = new Position(position.getRow(), position.getColumn()+1);
        } else if( position.getRow() < (rows - 1)) {
            next = new Position(position.getRow() + 1, 0);
        } else {
            throw new IllegalArgumentException("Matrix Ended");
        }
        return next;
    }

    /**
     * Compare if two arrays are equal
     *
     * @param source array
     * @param dest array
     * @return
     */
    boolean compare (char[] source, char[] dest) {
        return 0 == Arrays.compare(source, dest);
    }

    /**
     * Returns true if target sequence present vertically or horizontally from current location
     *
     * @param target
     * @param position
     * @return
     */
    boolean isTargetSequence(char[] target, Position position) {
        if(Objects.isNull(target) || Objects.isNull(position)){
            throw new IllegalArgumentException("Invalid Input ");
        }
        boolean found = false;

        //TODO: Check if remaining length less than sequence length and skip row/column

        if(compare(target, horizontalTraversal(position, target.length)) ||
                compare(target, verticalTraversal(position, target.length)) ) {
            found = true;
        }
        return found;
    }

    /**
     * Find Sequence in matrix
     *
     * @param sequence
     * @return
     */
    public boolean findSequenceInMatrix( char[] sequence) {
        return this.findSequenceFromPosition(sequence, new Position(0,0), false);
    }

    /**
     * Recursive call to find a given sequence in the matrix
     *
     * @param sequence
     * @param startPosition
     * @param isFound
     * @return
     */
    boolean findSequenceFromPosition(char[] sequence, Position startPosition, boolean isFound) {
        boolean result = isFound;

        //TODO: Check if remaining length less than sequence length and skip row
        Position next = this.findNextInRowFromPosition(sequence[0], startPosition);

        if(!Objects.isNull(next) && isTargetSequence(sequence, next)) {
            result = true;
        } else if(!result && Objects.isNull(next) && hasNext(new Position(startPosition.getRow(), (cols-1)))) {
            result = findSequenceFromPosition(sequence, nextPosition(new Position(startPosition.getRow(), (cols-1))), result);
        } else if(!result && !Objects.isNull(next) && hasNext(next)) {
            result = findSequenceFromPosition(sequence, nextPosition(next), result);
        }
        return result;
    }
}