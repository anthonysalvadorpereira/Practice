package com.anthony.programming.challenge.one;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MatrixTest {

    private Matrix matrix;

    @BeforeEach
    void setUp() {
    matrix = new Matrix(4,4, Arrays.asList('F', 'A', 'C', 'I',
                                                              'O', 'B', 'Q', 'P',
                                                              'A', 'N', 'O', 'B',
                                                              'M', 'A', 'S', 'S'));
    }

    @AfterEach
    void tearDown() {
        matrix = null;
    }

    @Test
    void shouldReturnEntireRowOnHorizontalTraversalFromFirstElement() {
        char[] result = {'F', 'A', 'C', 'I'};
        assertThat(matrix.horizontalTraversal(new Position(0,0), 4), is(result));
    }

    @Test
    void shouldReturnRowSubsetOnHorizontalTraversalFromElementOtherThanFirst() {
        char[] result = {'A', 'C', 'I'};
        assertThat(matrix.horizontalTraversal(new Position(0,1),3), is(result));
    }

    @Test
    void shouldReturnRowSubsetUptoLengthOnHorizontalTraversalFromElementOtherThanFirst() {
        char[] result = {'A', 'C'};
        assertThat(matrix.horizontalTraversal(new Position(0,1),2), is(result));
    }

    @Test
    void shouldReturnRowSubsetUptoEndOnHorizontalTraversalFromElementOtherThanFirstWhenLengthIsGreaterThanRemainderRow() {
        char[] result = {'A', 'C', 'I'};
        assertThat(matrix.horizontalTraversal(new Position(0,1),4), is(result));
    }

    @Test
    void shouldReturnLastElementOnHorizontalTraversalFromLastElementInRow() {
        char[] result = {'I'};
        assertThat(matrix.horizontalTraversal(new Position(0,3),4), is(result));
    }

    @Test
    void shouldThrowExceptionOnHorizontalTraversalWhenColumnExceeded() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> matrix.horizontalTraversal(new Position(0,5),4));
    }

    @Test
    void shouldThrowExceptionOnHorizontalTraversalWhenRowExceeded() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> matrix.horizontalTraversal(new Position(5,0),4));
    }

    @Test
    void shouldThrowExceptionOnHorizontalTraversalWhenRowAndColumnExceeded() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> matrix.horizontalTraversal(new Position(5,5),4));
    }

    @Test
    void shouldThrowExceptionOnHorizontalTraversalWhenNullValueIsPassed() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> matrix.horizontalTraversal(null, 4));
    }

    @Test
    void shouldReturnEntireColumnOnVerticalTraversalFromFirstElement() {
        char[] result = {'F', 'O', 'A', 'M'};
        assertThat(matrix.verticalTraversal(new Position(0,0), 4), is(result));
    }

    @Test
    void shouldReturnColumnSubsetOnVerticalTraversalFromElementOtherThanFirst() {
        char[] result = {'O', 'A', 'M'};
        assertThat(matrix.verticalTraversal(new Position(1,0), 3), is(result));
    }

    @Test
    void shouldReturnColumnSubsetUptoLengthOnVerticalTraversalFromElementOtherThanFirst() {
        char[] result = {'A', 'B'};
        assertThat(matrix.verticalTraversal(new Position(0,1),2), is(result));
    }

    @Test
    void shouldReturnColumnSubsetUptoEndOnVerticalTraversalFromElementOtherThanFirstWhenLengthIsGreaterThanRemainderColumn() {
        char[] result = {'B', 'N', 'A'};
        assertThat(matrix.verticalTraversal(new Position(1,1),4), is(result));
    }

    @Test
    void shouldReturnLastElementOnVerticalTraversalFromLastElementInColumn() {
        char[] result = {'S'};
        assertThat(matrix.verticalTraversal(new Position(3,3),4), is(result));
    }

    @Test
    void shouldThrowExceptionOnVerticalTraversalWhenColumnExceeded() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> matrix.verticalTraversal(new Position(0,5), 4));
    }

    @Test
    void shouldThrowExceptionOnVerticalTraversalWhenRowExceeded() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> matrix.verticalTraversal(new Position(5,0), 4));
    }

    @Test
    void shouldThrowExceptionOnVerticalTraversalWhenRowAndColumnExceeded() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> matrix.verticalTraversal(new Position(5,5), 4));
    }

    @Test
    void shouldThrowExceptionOnVerticalTraversalWhenNullValueIsPassed() {
        Assertions.assertThrows(IllegalArgumentException.class, ()-> matrix.verticalTraversal(null, 4));
    }

    @Test
    public void shouldFetchNextPositionGivenAValidPosition() {
        assertThat(matrix.nextPosition(new Position(0,0)), Matchers.is(new Position(0,1)));
    }

    @Test
    public void shouldFetchNextRowPositionGivenEndOfRow() {
        assertThat(matrix.nextPosition(new Position(0,3)), Matchers.is(new Position(1,0)));
    }

    @Test
    void shouldFindTargetWhenPresentInMatrix() {
        char[] target = {'F','O','A','M'};
        assertThat(matrix.findSequenceInMatrix(target), is(true));
    }

    @Test
    void shouldFindTargetWhenPresentInMatrixAndIsASubSet() {
        char[] target = {'O','A','M'};
        assertThat(matrix.findSequenceInMatrix(target), is(true));
    }

    @Test
    void shouldNotFindTargetWhenNotPresentInMatrix() {
        char[] target = {'F','O','B','M'};
        assertThat(matrix.findSequenceInMatrix(target), is(true));
    }

}