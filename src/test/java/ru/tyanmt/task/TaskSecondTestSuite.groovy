import org.junit.Test
import ru.tyanmt.task.common.Cube
import ru.tyanmt.task.common.CubeASCII
import ru.tyanmt.task.common.Face
import ru.tyanmt.task.solution.CubeAssembler
import ru.tyanmt.task.util.Printer

import static org.hamcrest.Matchers.equalTo
import static org.hamcrest.Matchers.is
import static org.junit.Assert.assertThat
import static ru.tyanmt.task.common.FaceMapper.getPointFromFace
import static ru.tyanmt.task.common.FaceMergeValidator.isAppropriateFace
import static ru.tyanmt.task.common.FaceMergeValidator.isVerticesAccessible
import static ru.tyanmt.task.common.FacePosition.*

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
        assertThat Arrays.deepEquals(face.matrix, controlFace), is(true)
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
        assertThat Arrays.deepEquals(face.matrix, controlFace), is(true)
    }

    @Test
    public void shouldConnectMatrixWhenAllSidesAreFilled() {
        //given
        int[][] faceCandidateMatrix = [
                [0, 1, 0, 1, 0],
                [1, 1, 1, 1, 1],
                [0, 1, 1, 1, 0],
                [1, 1, 1, 1, 1],
                [1, 0, 1, 0, 0]
        ]
        int[][] faceCubeMatrix = [
                [2, 0, 5, 0, 5],
                [0, 0, 0, 0, 0],
                [2, 0, 0, 0, 4],
                [0, 0, 0, 0, 0],
                [0, 3, 0, 3, 3]
        ]
        int[][] neighborFaces = [
                [0, 5, 5, 5, 0],
                [2, 0, 0, 0, 4],
                [2, 0, 0, 0, 4],
                [2, 0, 0, 0, 4],
                [0, 3, 3, 3, 0]
        ]
        Face cubeFace = new Face(faceCubeMatrix);
        copyNeighborFaces(neighborFaces, cubeFace)
        //when
        boolean canBePlaced = isAppropriateFace(new Face(faceCandidateMatrix), cubeFace);
        //then
        assertThat canBePlaced, is(true)
    }

    @Test
    public void shouldConnectMatricesWhenOnlyVertexIsNotFilledAndAdjacentFaceIsEmpty() {
        //given
        int[][] faceCandidateMatrix = [
                [0, 0, 0, 0, 0],
                [1, 1, 1, 1, 0],
                [0, 1, 1, 1, 0],
                [1, 1, 1, 1, 0],
                [1, 0, 0, 0, 0],
        ]
        int[][] faceCubeMatrix = [
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [2, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
        ]

        int[][] neighborFaces = [
                [0, 0, 0, 0, 0],
                [2, 0, 0, 0, 0],
                [2, 0, 0, 0, 0],
                [2, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
        ]
        Face cubeFace = new Face(faceCubeMatrix);
        copyNeighborFaces(neighborFaces, cubeFace)
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
        int[][] neighborFaces = [
                [0, 2, 2, 2, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
        ]
        Face cubeFace = new Face(faceCubeMatrix);
        copyNeighborFaces(neighborFaces, cubeFace)
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
                [0, 1, 1, 1, 1],
                [0, 1, 1, 1, 0],
                [1, 1, 1, 1, 1],
                [1, 0, 1, 0, 0],
        ]
        int[][] faceCubeMatrix = [
                [2, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [2, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
        ]
        int[][] neighborFaces = [
                [0, 0, 0, 0, 0],
                [2, 0, 0, 0, 0],
                [2, 0, 0, 0, 0],
                [2, 0, 0, 0, 0],
                [0, 0, 0, 0, 0],
        ]
        Face cubeFace = new Face(faceCubeMatrix);
        copyNeighborFaces(neighborFaces, cubeFace)
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
        Face flippedFace = new Face(face).flip();
        //then
        assertThat Arrays.deepEquals(controlFace, flippedFace.matrix), is(true)
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
        Face roatedFace = new Face(face).rotateClockwise()
        //then
        assertThat Arrays.deepEquals(controlFace, roatedFace.matrix), is(true)
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
        int[][] neighborFaces = [
                [0, 2, 2, 2, 0],
                [1, 0, 0, 0, 3],
                [1, 0, 0, 0, 3],
                [1, 0, 0, 0, 3],
                [0, 0, 0, 0, 0]
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
                topFace
        ]
        //when
        Cube cube = new Cube();
        initCube(cube, cubeArray)
        Face face = cube.getFace(TOP);
        //then
        assertThat Arrays.deepEquals(face.matrix, topFace), is(true)
        assertThat Arrays.deepEquals(face.neighborFaces, neighborFaces), is(true)
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
        Cube cube = new Cube();
        initCube(cube, cubeArray)
        //when
        cube.putFaceOn(TOP, new Face(face))
        Face topFace = cube.getFace(TOP)
        //then
        assertThat Arrays.deepEquals(controlFace, topFace.matrix), is(true)
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

        Cube cube = new Cube();
        initCube(cube, cubeArray)
        //when
        int faceOne = getPointFromFace(BOTTOM,2,2,cube.cube)
        int faceTwo = getPointFromFace(FRONT,2,2,cube.cube)
        int faceThree = getPointFromFace(TOP,2,2,cube.cube)
        int faceFour = getPointFromFace(RIGHT,2,2,cube.cube)
        int faceFive = getPointFromFace(REAR,2,2,cube.cube)
        int faceSix = getPointFromFace(LEFT,2,2,cube.cube)
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

        Cube cube = new Cube();
        initCube(cube, cubeArray)
        //when
        int faceOne = getPointFromFace(BOTTOM,2,2,cube.cube)
        int faceTwo = getPointFromFace(FRONT,2,2,cube.cube)
        int faceThree = getPointFromFace(TOP,2,2,cube.cube)
        int faceFour = getPointFromFace(RIGHT,2,2,cube.cube)
        int faceFive = getPointFromFace(REAR,2,2,cube.cube)
        int faceSix = getPointFromFace(LEFT,2,2,cube.cube)
        //then
        //TODO Print cube
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
                        [0, 1, 1, 1, 1],
                ],
                [
                        [0, 1, 1, 1, 1],
                        [0, 1, 1, 1, 1],
                        [0, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0],
                        [0, 0, 1, 1, 1],
                ],
                [
                        [0, 0, 0, 1, 0],
                        [1, 1, 1, 1, 0],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 0, 0, 1],
                ],
                [
                        [0, 1, 0, 0, 0],
                        [0, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 0, 0, 1, 1],
                ],
                [
                        [1, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0],
                        [0, 1, 1, 1, 0],
                        [0, 1, 1, 1, 1],
                        [1, 1, 1, 0, 0],
                ],
                [
                        [1, 1, 1, 0, 0],
                        [0, 1, 1, 1, 1],
                        [0, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0],
                ],
                [
                        [1, 0, 0, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [0, 1, 1, 1, 1],
                        [0, 1, 0, 0, 0],
                ],
                [
                        [1, 1, 0, 0, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 0],
                        [0, 0, 0, 1, 0],
                ]
        ]

        //when
        List<Face> options = new Face(face).getRotateOptions();
        //then
        options.eachWithIndex {opt, i ->
            assertThat Arrays.deepEquals(opt.matrix, faces[i]), is(true)
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

    private void copyNeighborFaces(int[][] neighborFaces, cubeFace) {
        (0..<5).each { i -> cubeFace.neighborFaces[i] = Arrays.copyOf(neighborFaces[i], 5) }
    }

    private void initCube(Cube cube, int[][][] cubeArray) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                cube.cube[x][y] = Arrays.copyOf(cubeArray[x][y], 5);
            }
        }
    }
}
