Problem Statement


You are given a 2D array of characters, and a target string. Return whether or not the word target word exists in the matrix. Unlike a standard word search, the word must be either going left-to-right, or top-to-bottom in the matrix.

Example:

[['F', 'A', 'C', 'I'],
 ['O', 'B', 'Q', 'P'],
 ['A', 'N', 'O', 'B'],
 ['M', 'A', 'S', 'S']]

Given this matrix, and the target word FOAM, you should return true, as it can be found going up-to-down in the first columnIndex.





Solution :

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