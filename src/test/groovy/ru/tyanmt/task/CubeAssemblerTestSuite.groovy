package ru.tyanmt.task

import org.junit.Test
import ru.tyanmt.task.common.Cube
import ru.tyanmt.task.common.CubeASCII
import ru.tyanmt.task.common.Face
import ru.tyanmt.task.common.FaceMergeValidator
import ru.tyanmt.task.solution.CubeAssembler

import static org.hamcrest.Matchers.equalTo
import static org.junit.Assert.*
import static ru.tyanmt.task.common.FacePosition.*

public class CubeAssemblerTestSuite {


    @Test
    public void shouldReadSecondFace() {
        //given
        int[][] controlFace = [
                [2, 0, 2, 0, 2],
                [2, 2, 2, 2, 2],
                [0, 2, 2, 2, 0],
                [2, 2, 2, 2, 2],
                [2, 0, 2, 0, 2],
        ]
        CubeASCII cube = new CubeASCII("blueCube.txt");
        //when
        Face face = cube.readFace 2;
        //then
        assertTrue Arrays.deepEquals(face.matrix, controlFace)
    }

    @Test
    public void shouldReadFifthFace() {
        //given
        int[][] controlFace = [
                [0, 5, 0, 5, 0],
                [5, 5, 5, 5, 5],
                [0, 5, 5, 5, 0],
                [5, 5, 5, 5, 5],
                [5, 0, 5, 0, 0],
        ]
        CubeASCII cube = new CubeASCII("blueCube.txt");
        //when
        Face face = cube.readFace 5;
        //then
        assertTrue Arrays.deepEquals(face.matrix, controlFace)
    }

    @Test
    public void shouldMergeFacesWhenAllSidesAreFilled() {
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
    public void shouldMergeFacesWhenOnlyVertexIsNotFilledAndNeighborFaceIsEmpty() {
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
    public void shouldNotMergeFacesWhenMatricesIntersect() {
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
        assertFalse canBePlaced
    }

    @Test
    public void shouldNotMergeFacesWhenNotFilledPointsExist() {
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
        assertFalse canBePlaced
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
                [0, 1, 0, 1, 0],
                [1, 1, 1, 1, 1],
                [1, 1, 1, 1, 0],
                [0, 1, 1, 1, 1],
                [0, 0, 1, 0, 1]
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
        Face rotatedFace = new Face(face).rotateClockwise()
        //then
        assertTrue Arrays.deepEquals(controlFace, rotatedFace.matrix)
    }

    @Test
    public void shouldReturnTopFace() {
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
    public void shouldPutOnTopFace() {
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
        boolean faceWasPut = cube.tryPutFaceOn(TOP, new Face(face))
        //then
        assertTrue faceWasPut
        assertTrue Arrays.deepEquals(controlFace, cube.getFace(TOP).matrix)
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
        int bottomFace = BOTTOM.getPoint(2, 2, cube.cube)
        int frontFace = FRONT.getPoint(2, 2, cube.cube)
        int topFace = TOP.getPoint(2, 2, cube.cube)
        int rightFace = RIGHT.getPoint(2, 2, cube.cube)
        int rearFace = REAR.getPoint(2, 2, cube.cube)
        int leftFace = LEFT.getPoint(2, 2, cube.cube)
        //then
        assertThat bottomFace, equalTo(1)
        assertThat frontFace, equalTo(2)
        assertThat topFace, equalTo(3)
        assertThat rightFace, equalTo(4)
        assertThat rearFace, equalTo(5)
        assertThat leftFace, equalTo(6)
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

        def controlString = "               \n" +
                "               \n" +
                "  o    o    o  \n" +
                "               \n" +
                "               \n" +
                "          \n" +
                "          \n" +
                "       o  \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "       o  \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "          \n" +
                "       o  \n" +
                "          \n" +
                "          \n"
        //when
        //then
        assertThat cube.toString(), equalTo(controlString)

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
                        [1, 1, 1, 0, 0],
                        [0, 1, 1, 1, 1],
                        [0, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0]
                ],
                [
                        [0, 0, 0, 1, 0],
                        [1, 1, 1, 1, 0],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 0, 0, 1]
                ],
                [
                        [1, 1, 0, 0, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 1],
                        [1, 1, 1, 1, 0],
                        [0, 0, 0, 1, 0]
                ],
                [
                        [1, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0],
                        [0, 1, 1, 1, 0],
                        [0, 1, 1, 1, 1],
                        [1, 1, 1, 0, 0]
                ],
                [
                        [0, 1, 1, 1, 1],
                        [0, 1, 1, 1, 1],
                        [0, 1, 1, 1, 0],
                        [1, 1, 1, 1, 0],
                        [0, 0, 1, 1, 1]
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
        CubeASCII blueCube = new CubeASCII("blueCube.txt");
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
