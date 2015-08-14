import org.junit.Ignore
import org.junit.Test
import ru.tyanmt.task.common.Cube
import ru.tyanmt.task.common.CubeASCII
import ru.tyanmt.task.common.Face
import ru.tyanmt.task.solution.CubeAssembler

import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.is
import static org.junit.Assert.assertThat
import static ru.tyanmt.task.common.FaceHandler.*

public class TaskSecondTestSuite {


    @Test
    public void shouldReturnCorrectSecondFace() {
        //given
        int[][] controlFace = [
                [2, 0, 2, 0, 2],
                [2, 2, 2, 2, 2],
                [0, 2, 2, 2, 0],
                [2, 2, 2, 2, 2],
                [2, 0, 2, 0, 2],
        ]
        CubeASCII cube = new CubeASCII();
        //when
        Face face = cube.getFace(2);
        //then
        assertThat Arrays.deepEquals(face.face, controlFace), is(true)
    }

    @Test
    public void shouldReturnCorrectFifthFace() {
        //given
        int[][] controlFace = [
                [0, 5, 0, 5, 0],
                [5, 5, 5, 5, 5],
                [0, 5, 5, 5, 0],
                [5, 5, 5, 5, 5],
                [5, 0, 5, 0, 0],
        ]
        CubeASCII cube = new CubeASCII();
        //when
        Face face = cube.getFace(5);
        //then
        assertThat Arrays.deepEquals(face.face, controlFace), is(true)
    }

    @Test
    public void shouldConnectMatrixWhenAllSidesAreFilled() {
        //given
        int[][] faceCandidateMatrix = [
                [0, 1, 0, 1, 0],
                [1, 1, 1, 1, 1],
                [0, 1, 1, 1, 0],
                [1, 1, 1, 1, 1],
                [1, 0, 1, 0, 0],
        ]
        int[][] faceCubeMatrix = [
                [2, 0, 5, 0, 5],
                [0, 0, 0, 0, 0],
                [2, 0, 0, 0, 4],
                [0, 0, 0, 0, 0],
                [0, 3, 0, 3, 3],
        ]
        Face cubeFace = new Face(faceCubeMatrix);
        int[][] adjacentEdges = [
                [0, 5, 0],
                [2, 0, 4],
                [0, 3, 0]
        ]
        cubeFace.setAdjacentFaces(adjacentEdges);
        //when
        boolean canBePlaced = isAppropriateFace(new Face(faceCandidateMatrix), cubeFace);
        //then
        assertThat canBePlaced, is(true)
    }

    @Test
    public void shouldConnectMatricesWhenOnlyVertexIsNotFilledAndAdjacentFaceIsEmpty() {
        //given
        int[][] faceCandidateMatrix = [
                [0, 1, 0, 1, 0],
                [1, 1, 1, 1, 1],
                [0, 1, 1, 1, 0],
                [1, 1, 1, 1, 1],
                [1, 0, 1, 0, 0],
        ]
        int[][] faceCubeMatrix = [
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [2, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
        ]
        Face cubeFace = new Face(faceCubeMatrix);
        int[][] adjacentEdges = [
                [0, 0, 0],
                [2, 0, 0],
                [0, 0, 0]
        ]
        cubeFace.setAdjacentFaces(adjacentEdges);
        //when
        boolean canBePlaced = isAppropriateFace(new Face(faceCandidateMatrix), cubeFace);
        //then
        assertThat canBePlaced, is(true)
    }

    @Test
    public void shouldNotConnectMatrixWhenMatricesIntersected() {
        //given
        int[][] faceCandidateMatrix = [
                [0, 1, 0, 1, 0],
                [1, 1, 1, 1, 1],
                [0, 1, 1, 1, 0],
                [1, 1, 1, 1, 1],
                [1, 0, 1, 0, 0],
        ]
        int[][] faceCubeMatrix = [
                [2, 2, 2, 2, 2],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
        ]
        Face cubeFace = new Face(faceCubeMatrix);
        int[][] adjacentEdges = [
                [0, 2, 0],
                [0, 0, 0],
                [0, 0, 0]
        ]
        cubeFace.setAdjacentFaces(adjacentEdges);
        //when
        boolean canBePlaced = isAppropriateFace(new Face(faceCandidateMatrix), cubeFace);
        //then
        assertThat canBePlaced, is(false)
    }

    @Test
    public void shouldNotConnectMatrixWhenMatricesDontFillFace() {
        //given
        int[][] faceCandidateMatrix = [
                [0, 1, 0, 1, 0],
                [1, 1, 1, 1, 1],
                [0, 1, 1, 1, 0],
                [1, 1, 1, 1, 1],
                [1, 0, 1, 0, 0],
        ]
        int[][] faceCubeMatrix = [
                [2, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
        ]
        Face cubeFace = new Face(faceCubeMatrix);
        int[][] adjacentEdges = [
                [0, 0, 0],
                [2, 0, 0],
                [0, 0, 0]
        ]
        cubeFace.setAdjacentFaces(adjacentEdges);
        //when
        boolean canBePlaced = isAppropriateFace(new Face(faceCandidateMatrix), cubeFace);
        //then
        assertThat canBePlaced, is(false)
    }

    @Test
    public void shouldFlipFace() {
        //given
        int[][] face = [
                [0, 1, 0, 1, 0],
                [1, 1, 1, 1, 1],
                [0, 1, 1, 1, 1],
                [1, 1, 1, 1, 0],
                [1, 0, 1, 0, 0]
        ]
        int[][] controlFace = [
                [1, 0, 1, 0, 0],
                [1, 1, 1, 1, 0],
                [0, 1, 1, 1, 1],
                [1, 1, 1, 1, 1],
                [0, 1, 0, 1, 0]
        ]
        //when
        int[][] flippedFace = flipFace(new Face(face));
        //then
        assertThat Arrays.deepEquals(controlFace, flippedFace), is(true)
    }

    @Test
    public void shouldRotateClockwise() {
        //given
        int[][] face = [
                [0, 1, 0, 1, 0],
                [1, 1, 1, 1, 1],
                [0, 1, 1, 1, 1],
                [1, 1, 1, 1, 0],
                [1, 0, 1, 0, 0]
        ]
        int[][] controlFace = [
                [1, 1, 0, 1, 0],
                [0, 1, 1, 1, 1],
                [1, 1, 1, 1, 0],
                [0, 1, 1, 1, 1],
                [0, 0, 1, 1, 0]
        ]
        //when
        int[][] rotatedFace = rotateClockwise(new Face(face));
        //then
        assertThat Arrays.deepEquals(controlFace, rotatedFace), is(true)
    }

    @Test
    public void shouldReturnFalseWhenVerticesAreNotAccessible() {
        //given
        int[][] face = [
                [0, 2, 0, 2, 0],
                [1, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [1, 0, 0, 0, 0],
                [1, 0, 0, 0, 0]
        ]
        //when
        boolean isVerticesAccessible = isVerticesAccessible(new Face(face));
        //then
        assertThat isVerticesAccessible, is(false)
    }

    @Test
    public void shouldReturnTrueWhenVerticesAreAccessible() {
        //given
        int[][] face = [
                [0, 0, 0, 2, 1],
                [1, 0, 0, 0, 3],
                [0, 0, 0, 0, 0],
                [1, 0, 0, 0, 0],
                [1, 0, 4, 0, 0]
        ]
        //when
        boolean isVerticesAccesible = isVerticesAccessible(new Face(face));
        //then
        assertThat isVerticesAccesible, is(true)
    }


    @Test
    public void shouldReturnTop() {
        //given
        int[][] topFace = [
                [0, 2, 2, 0, 0],
                [0, 0, 0, 0, 3],
                [1, 0, 0, 0, 3],
                [1, 0, 0, 0, 3],
                [0, 0, 0, 0, 0]
        ]
        int[][] adjacentFaces = [
                [0, 2, 0],
                [1, 0, 3],
                [0, 0, 0]
        ]
        int[][][] cube = [
                [[0, 2, 0, 2, 0],
                 [1, 0, 0, 0, 0],
                 [0, 0, 0, 0, 3],
                 [1, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0]],

                [[2, 2, 2, 2, 2],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3]],

                [[1, 2, 2, 2, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3]],

                [[2, 2, 2, 2, 2],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3]],
                topFace
        ]
        //when
        Face face = new Cube(cube).getFace(2);
        //then
        assertThat Arrays.deepEquals(face.face, topFace), is(true)
        assertThat Arrays.deepEquals(face.adjacentFaces, adjacentFaces), is(true)
    }

    @Test
    public void shouldPlaceOnTop() {
        //given
        int[][] face = [
                [4, 0, 0, 4, 4],
                [4, 4, 4, 4, 0],
                [0, 4, 4, 4, 0],
                [4, 4, 4, 4, 0],
                [4, 0, 4, 0, 4]
        ]
        int[][][] cubeArray = [
                [[0, 2, 0, 2, 0],
                 [1, 0, 0, 0, 0],
                 [0, 0, 0, 0, 3],
                 [1, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0]],

                [[2, 2, 2, 2, 2],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3]],

                [[1, 2, 2, 2, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3]],

                [[2, 2, 2, 2, 2],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3]],

                [[0, 2, 2, 0, 0],
                 [0, 0, 0, 0, 3],
                 [1, 0, 0, 0, 3],
                 [0, 0, 0, 0, 3],
                 [0, 0, 0, 0, 0]]
        ]
        int[][] controlFace = [
                [4, 2, 2, 4, 4],
                [4, 4, 4, 4, 3],
                [1, 4, 4, 4, 3],
                [4, 4, 4, 4, 3],
                [4, 0, 4, 0, 4]
        ]
        Cube cube = new Cube(cubeArray)
        //when
        cube.putFaceOn(2, new Face(face))
        Face topFace = cube.getFace(2)
        //then
        assertThat Arrays.deepEquals(controlFace, topFace.face), is(true)
    }

    @Test
    public void shouldRotateFace() {
        //given
        int[][] face = [
                [0, 0, 1, 1, 1],
                [1, 1, 1, 1, 0],
                [0, 1, 1, 1, 0],
                [0, 1, 1, 1, 1],
                [0, 1, 1, 1, 1]
        ]
        int[][][] faces = [
                [
                        [0, 0, 1, 1, 1],
                        [1, 1, 1, 1, 0],
                        [0, 1, 1, 1, 0],
                        [0, 1, 1, 1, 1],
                        [0, 1, 1, 1, 1]
                ],
                [
                        [0, 0, 0, 1, 0],
                        [1, 1, 1, 1, 0],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 0, 0, 1]
                ],
                [
                        [1, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0],
                        [0, 1, 1, 1, 0],
                        [0, 1, 1, 1, 1],
                        [1, 1, 1, 0, 0]
                ],
                [
                        [1, 0, 0, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [0, 1, 1, 1, 1],
                        [0, 1, 0, 0, 0]
                ],
                [
                        [0, 1, 0, 0, 0],
                        [0, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 0, 0, 1, 1]
                ],
                [
                        [1, 1, 1, 0, 0],
                        [0, 1, 1, 1, 1],
                        [0, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0]
                ],
                [
                        [1, 1, 0, 0, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 0],
                        [0, 0, 0, 1, 0]
                ],
                [
                        [0, 1, 1, 1, 1],
                        [0, 1, 1, 1, 1],
                        [0, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0],
                        [0, 0, 1, 1, 1]
                ],

        ]

        //when
        List<Face> options = getRotateOptions(new Face(face));
        //then
        options.eachWithIndex {opt, i ->
            assertThat Arrays.deepEquals(opt.face, faces[i]), is(true)
        }
    }

    @Test
    public void shouldAssembleCube() {
        //given
        CubeASCII blueCube = new CubeASCII();
        CubeAssembler assembler = new CubeAssembler();
        //when
        assembler.assembleCube(blueCube);
        //then
        if (!assembler.solutions.empty) {
            Cube cube = assembler.solutions.first();
            6.times { i ->
                cube.getFace(i + 1).face.each { println it }
                println ""
            }
        }
    }
}
