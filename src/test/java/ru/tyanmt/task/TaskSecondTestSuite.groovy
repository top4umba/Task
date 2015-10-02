import org.hamcrest.collection.IsIterableContainingInOrder
import org.junit.Test
import ru.tyanmt.task.common.Cube
import ru.tyanmt.task.common.CubeASCII
import ru.tyanmt.task.common.Face
import ru.tyanmt.task.common.FaceMergeValidator
import ru.tyanmt.task.solution.CubeAssembler
import ru.tyanmt.task.util.Printer

import static org.hamcrest.Matchers.equalTo
import static org.junit.Assert.assertThat
import static org.junit.Assert.assertTrue
import static ru.tyanmt.task.common.FaceMapper.getPointFromFace
import static ru.tyanmt.task.common.FacePosition.*

public class TaskSecondTestSuite {


    @Test
    public void shouldEncodeSecondFace() {
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
        assertTrue Arrays.deepEquals(face.matrix, controlFace)
    }

    @Test
    public void shouldEncodeFifthFace() {
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
        assertTrue Arrays.deepEquals(face.matrix, controlFace)
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
        boolean canBePlaced = new FaceMergeValidator(new Face(faceCandidateMatrix), cubeFace).validate();
        //then
        assertTrue canBePlaced
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
        boolean canBePlaced = new FaceMergeValidator(new Face(faceCandidateMatrix), cubeFace).validate();
        //then
        assertTrue canBePlaced
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
        boolean canBePlaced = new FaceMergeValidator(new Face(faceCandidateMatrix), cubeFace).validate();
        //then
        assertTrue !canBePlaced
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
        boolean canBePlaced = new FaceMergeValidator(new Face(faceCandidateMatrix), cubeFace).validate();
        //then
        assertTrue !canBePlaced
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
        assertTrue Arrays.deepEquals(controlFace, flippedFace.matrix)
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
        assertTrue Arrays.deepEquals(controlFace, roatedFace.matrix)
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
        assertTrue Arrays.deepEquals(face.matrix, topFace)
        assertTrue Arrays.deepEquals(face.neighborFaces, neighborFaces)
    }

    @Test
    public void shouldPlaceOnTop() {
        //given
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
        cube.tryPutFaceOn(TOP, new Face(face))
        Face topFace = cube.getFace(TOP)
        //then
        assertTrue Arrays.deepEquals(controlFace, topFace.matrix)
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
        int faceOne = getPointFromFace(BOTTOM, 2, 2, cube.cube)
        int faceTwo = getPointFromFace(FRONT, 2, 2, cube.cube)
        int faceThree = getPointFromFace(TOP, 2, 2, cube.cube)
        int faceFour = getPointFromFace(RIGHT, 2, 2, cube.cube)
        int faceFive = getPointFromFace(REAR, 2, 2, cube.cube)
        int faceSix = getPointFromFace(LEFT, 2, 2, cube.cube)
        //then
        assertThat faceOne, equalTo(1)
        assertThat faceTwo, equalTo(2)
        assertThat faceThree, equalTo(3)
        assertThat faceFour, equalTo(4)
        assertThat faceFive, equalTo(5)
        assertThat faceSix, equalTo(6)
    }


    @Test
    public void shouldEncodeCube() {
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
        //then
        def controlList = ["               ",
                           "               ",
                           "  o    o    o  ",
                           "               ",
                           "               ",
                           "          ",
                           "          ",
                           "       o  ",
                           "          ",
                           "          ",
                           "          ",
                           "          ",
                           "       o  ",
                           "          ",
                           "          ",
                           "          ",
                           "          ",
                           "       o  ",
                           "          ",
                           "          "] as String[]
        assertThat Printer.encodeFaces(cube), IsIterableContainingInOrder.contains(controlList)

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
        options.eachWithIndex { opt, i ->
            assertTrue Arrays.deepEquals(opt.matrix, faces[i])
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
        assertThat assembler.solutions.size(), equalTo(3)
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
