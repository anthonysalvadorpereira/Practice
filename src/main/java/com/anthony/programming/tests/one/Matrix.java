package com.anthony.programming.tests.one;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Matrix {

    private char[][] problemSpace;
    private int rows;
    private int cols;

    public Matrix(int num_rows, int num_cols, List<Character> data) {
        if (num_rows < 1 || num_cols < 1 || Objects.isNull(data) || (!Objects.isNull(data) && data.isEmpty()) ||
                (num_cols * num_rows != data.size())) {
            throw new IllegalArgumentException("Invalid array config");
        }

        this.rows = num_rows;
        this.cols = num_cols;
        this.problemSpace = new char[num_rows][num_cols];

        ListIterator<Character> iterator = data.listIterator();

        for (int i = 0; i < num_rows; i++) {
                for (int j = 0; j < num_cols; j++) {
                    if(iterator.hasNext())
                    this.problemSpace[i][j] = iterator.next();
                }
        }
    }

    Position findNextInRowFromPosition(char searchChar, Position position) {
        if(!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid Position");
        }

        Position charPosition = null;
        for (int j = position.getColumn(); j < cols; j++) {
                if(this.problemSpace[position.getRow()][j] == searchChar) {
                    charPosition = new Position(position.getRow(),j);
                    break;
                }
        }

        return charPosition;
    }

    boolean isTargetSequence(char[] target, Position position) {
        if(Objects.isNull(target) || Objects.isNull(position)){
            throw new IllegalArgumentException("Invalid Input ");
        }
        boolean found = false;

        if(compare(target, horizontalTraversal(position, target.length)) ||
                compare(target, verticalTraversal(position, target.length)) ) {
            found = true;
        }
        return found;
    }

    char[] horizontalTraversal(Position position, int length) {
        if(!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid Position");
        }
        int targetLength = length < cols - position.getColumn() ? length : cols - position.getColumn();
        char[] subSet = new char[targetLength];
        int currentIndex = position.getColumn();
        int i = 0;
        while (currentIndex < cols && i < length) {
            subSet[i] = problemSpace[position.getRow()][currentIndex];
            i++;
            currentIndex++;
        }
        return subSet;
    }

    char[] verticalTraversal(Position position, int length) {
        if(!isValidPosition(position)) {
            throw new IllegalArgumentException("Invalid Position");
        }
        int targetLength = length < rows - position.getRow() ? length : rows - position.getRow();
        char[] subSet = new char[targetLength];
        int currentIndex = position.getRow();
        int i = 0;
        while (currentIndex < rows && i < length) {
            subSet[i] = problemSpace[currentIndex][position.getColumn()];
            i++;
            currentIndex++;
        }
        return subSet;
    }

    boolean compare (char[] source, char[] dest) {
        return 0 == Arrays.compare(source, dest);
    }

    boolean isValidPosition(Position position) {
        boolean result = true;
        if (Objects.isNull(position) || position.getRow() > rows ||
                position.getColumn() > cols || position.getRow() < 0 || position.getColumn() < 0) {
            result = false;
        }
        return result;
    }

    boolean hasNext( Position position) {
        boolean result = true;
        if (position.getColumn() == (cols - 1) && position.getRow() == (rows - 1)) {
            result = false;
        }
        return result;
    }

    Position nextPosition(Position position) {
        if(Objects.isNull(position) || !isValidPosition(position)) {
            throw new IllegalArgumentException("No or Invalid Position Specified");
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

    public boolean findSequenceInMatrix( char[] sequence) {
        return this.findSequenceFromPosition(sequence, new Position(0,0));
    }

    private boolean findSequenceFromPosition(char[] sequence, Position position) {
        Position next = this.findNextInRowFromPosition(sequence[0], position);

        if(isTargetSequence(sequence, position)) {
            return true;
        } else if(Objects.isNull(next) && hasNext(new Position(position.getRow(), (cols-1)))) {
            findSequenceFromPosition(sequence, nextPosition(new Position(position.getRow(), (cols-1))));
        } else if(!Objects.isNull(next) && hasNext(next)) {
            findSequenceFromPosition(sequence, nextPosition(next));
        }
        return false;
    }

}