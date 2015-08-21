import org.junit.Ignore
import org.junit.Test
import ru.tyanmt.task.common.Cube
import ru.tyanmt.task.common.CubeASCII
import ru.tyanmt.task.common.Face
import ru.tyanmt.task.solution.CubeAssembler
import ru.tyanmt.task.util.Printer

import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.is
import static org.junit.Assert.assertThat
import static ru.tyanmt.task.common.FaceHandler.*
import static ru.tyanmt.task.common.FaceMapper.getPointFromFace

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
        Face face = new Cube(cube).getFace(3);
        //then
        assertThat Arrays.deepEquals(face.face, topFace), is(true)
        assertThat Arrays.deepEquals(face.adjacentFaces, adjacentFaces), is(true)
    }

    @Test
    public void shouldPlaceOnTop() {
        //given
        //TODO Fixed test with proper faces
        int[][] face = [
                [3, 0, 0, 3, 3],
                [3, 3, 3, 3, 0],
                [0, 3, 3, 3, 0],
                [3, 3, 3, 3, 0],
                [3, 0, 3, 0, 3]
        ]
        int[][][] cubeArray = [
                [[0, 2, 0, 2, 0],
                 [6, 0, 0, 0, 0],
                 [0, 0, 0, 0, 4],
                 [6, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0]],

                [[2, 2, 2, 2, 2],
                 [6, 0, 0, 0, 4],
                 [6, 0, 0, 0, 4],
                 [6, 0, 0, 0, 4],
                 [6, 0, 0, 0, 4]],

                [[6, 2, 2, 2, 4],
                 [6, 0, 0, 0, 4],
                 [6, 0, 0, 0, 4],
                 [6, 0, 0, 0, 4],
                 [6, 0, 0, 0, 4]],

                [[2, 2, 2, 2, 2],
                 [6, 0, 0, 0, 4],
                 [6, 0, 0, 0, 4],
                 [6, 0, 0, 0, 4],
                 [6, 0, 0, 0, 4]],

                [[0, 2, 2, 0, 0],
                 [0, 0, 0, 0, 4],
                 [6, 0, 0, 0, 4],
                 [0, 0, 0, 0, 4],
                 [0, 0, 0, 0, 0]]
        ]
        int[][] controlFace = [
                [3, 2, 2, 3, 3],
                [3, 3, 3, 3, 4],
                [6, 3, 3, 3, 4],
                [3, 3, 3, 3, 4],
                [3, 0, 3, 0, 3]
        ]
        Cube cube = new Cube(cubeArray)
        //when
        cube.putFaceOn(3, new Face(face))
        Face topFace = cube.getFace(3)
        //then
        assertThat Arrays.deepEquals(controlFace, topFace.face), is(true)
    }


    @Test
    public void shouldGetProperSide() {
        //given
        int[][][] cubeArray = [
                [[0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 1, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0]],

                [[0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0]],

                [[0, 0, 2, 0, 0],
                 [0, 0, 0, 0, 0],
                 [6, 0, 0, 0, 4],
                 [0, 0, 0, 0, 0],
                 [0, 0, 5, 0, 0]],

                [[0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0]],

                [[0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 3, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0]]
        ]
        Cube cube = new Cube(cubeArray)
        //when
        int faceOne = getPointFromFace(1,2,2,cube.cube)
        int faceTwo = getPointFromFace(2,2,2,cube.cube)
        int faceThree = getPointFromFace(3,2,2,cube.cube)
        int faceFour = getPointFromFace(4,2,2,cube.cube)
        int faceFive = getPointFromFace(5,2,2,cube.cube)
        int faceSix = getPointFromFace(6,2,2,cube.cube)
        //then
        assertThat faceOne, equalTo(1)
        assertThat faceTwo, equalTo(2)
        assertThat faceThree, equalTo(3)
        assertThat faceFour, equalTo(4)
        assertThat faceFive, equalTo(5)
        assertThat faceSix, equalTo(6)
    }



    @Test
    public void shouldPrintCube() {
        //given
        int[][][] cubeArray = [
                [[0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 1, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0]],

                [[0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0]],

                [[0, 0, 2, 0, 0],
                 [0, 0, 0, 0, 0],
                 [4, 0, 0, 0, 6],
                 [0, 0, 0, 0, 0],
                 [0, 0, 5, 0, 0]],

                [[0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0]],

                [[0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 3, 0, 0],
                 [0, 0, 0, 0, 0],
                 [0, 0, 0, 0, 0]]
        ]
        Cube cube = new Cube(cubeArray)
        //when
        int faceOne = getPointFromFace(1,2,2,cube.cube)
        int faceTwo = getPointFromFace(2,2,2,cube.cube)
        int faceThree = getPointFromFace(3,2,2,cube.cube)
        int faceFour = getPointFromFace(4,2,2,cube.cube)
        int faceFive = getPointFromFace(5,2,2,cube.cube)
        int faceSix = getPointFromFace(6,2,2,cube.cube)
        //then
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
        println assembler.solutions.size()
        if (!assembler.solutions.empty) {
            assembler.solutions.each {
                println ""
                println "-----------------------"
                println ""
                Printer.print it
            }
        }
    }
}
