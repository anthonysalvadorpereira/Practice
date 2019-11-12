package com.anthony.programming.challenge.one;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

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
    public void shouldThrowExceptionOnInvalidDataToMatrixConstructor() {
        Assertions.assertThrows(IllegalArgumentException.class,()->new Matrix(-1,0,new ArrayList<>()));
        Assertions.assertThrows(IllegalArgumentException.class,()->new Matrix(1,-1,new ArrayList<>()));
        Assertions.assertThrows(IllegalArgumentException.class,()->new Matrix(0,0,new ArrayList<>()));
        Assertions.assertThrows(IllegalArgumentException.class,()->new Matrix(1,1,new ArrayList<>()));
        Assertions.assertThrows(IllegalArgumentException.class,()->new Matrix(2,1,Arrays.asList('A','B', 'B')));
    }

    @Test
    public void shouldBuildMatrixOnValidData() {
        assertThat(matrix, notNullValue());
    }

    @Test
    public void shouldReturnFalseIfPositionIsInvalid() {
        assertThat(matrix.isValidPosition(new Position(0,4)), is(false));
    }

    @Test
    public void shouldReturnTrueIfPositionIsValidAndAtTheStart() {
        assertThat(matrix.isValidPosition(new Position(0,0)), is(true));
    }

    @Test
    public void shouldReturnTrueIfPositionIsValidAndAtTheEnd() {
        assertThat(matrix.isValidPosition(new Position(3,3)), is(true));
    }

    @Test
    public void shouldReturnTrueIfPositionIsValidAndAtTheEndOfTheFirstRow() {
        assertThat(matrix.isValidPosition(new Position(0,3)), is(true));
    }

    @Test
    public void shouldFindNextInRowFromStartPositionWhenPresent() {
        assertThat(matrix.findNextInRowFromPosition('F',new Position(0,0)), is(new Position(0,0)));
    }

    @Test
    public void shouldReturnNullWhenCharacterNotPresentInRow() {
        assertThat(matrix.findNextInRowFromPosition('F',new Position(0,1)), nullValue());
    }

    @Test
    public void shouldThrowExceptionWhenInvalidPositionIsPassed() {
        Assertions.assertThrows(IllegalArgumentException.class,()->matrix.findNextInRowFromPosition('F',new Position(4,4)));
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
    public void shouldReturnTrueWhenNextPositionPresent() {
        assertThat(matrix.hasNext(new Position(0,2)), is(true));
    }

    @Test
    public void shouldReturnTrueWhenRowsFollowCurrentEndOfRow() {
        assertThat(matrix.hasNext(new Position(0,3)), is(true));
    }

    @Test
    public void shouldReturnFalseWhenAtTheEndOfMatrix() {
        assertThat(matrix.hasNext(new Position(3,3)), is(false));
    }

    @Test
    public void shouldThrowExceptionOnInvalidPositionWhenCheckingIfNextPositionPresent() {
        Assertions.assertThrows(IllegalArgumentException.class, ()->matrix.hasNext(new Position(0,5)));
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
    public void shouldReturnTrueOnCompareOfIdenticalArrays() {
        char[] source = {'F','O','A','M'};
        char[] target = {'F','O','A','M'};
        assertThat(matrix.compare(source, target),is(true));
    }

    @Test
    public void shouldReturnFalseOnCompareOfNonIdenticalArrays() {
        char[] source = {'F','O','A'};
        char[] target = {'F','O','A','M'};
        assertThat(matrix.compare(source, target),is(false));
    }

    @Test
    public void shouldFindSequenceWhenPresentVertically() {
        char[] source = {'F','O','A','M'};
        assertThat(matrix.isTargetSequence(source, new Position(0,0)), is(true));
    }

    @Test
    public void shouldFindSequenceWhenPresentHorizontally() {
        char[] source = {'F','A','C','I'};
        assertThat(matrix.isTargetSequence(source, new Position(0,0)), is(true));
    }

    @Test
    public void shouldFindSequenceWhenPresentVerticallyWhenTargetIsASubset() {
        char[] source = {'O','A','M'};
        assertThat(matrix.isTargetSequence(source, new Position(1,0)), is(true));
    }

    @Test
    public void shouldFindSequenceWhenPresentHorizontallyWhenTargetIsASubset() {
        char[] source = {'A','C','I'};
        assertThat(matrix.isTargetSequence(source, new Position(0,1)), is(true));
    }

    @Test
    public void shouldNotFindSequenceWhenPresentVertically() {
        char[] source = {'R','O','A','M'};
        assertThat(matrix.isTargetSequence(source, new Position(0,0)), is(false));
    }

    @Test
    public void shouldNotFindSequenceWhenPresentHorizontally() {
        char[] source = {'F','A','D','I'};
        assertThat(matrix.isTargetSequence(source, new Position(0,0)), is(false));
    }

    @Test
    void shouldFindTargetWhenPresentInMatrixVertically() {
        char[] target = {'F','O','A','M'};
        assertThat(matrix.findSequenceInMatrix(target), is(true));
    }

    @Test
    void shouldFindTargetWhenPresentInMatrixHorizontally() {
        char[] target = {'N', 'O', 'B'};
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
        assertThat(matrix.findSequenceInMatrix(target), is(false));
    }

}