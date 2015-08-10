import org.junit.Test
import ru.tyanmt.task.common.Cube3D
import ru.tyanmt.task.common.CubeASCII
import ru.tyanmt.task.common.Face3D

import static org.hamcrest.Matchers.is
import static org.junit.Assert.assertThat
import static ru.tyanmt.task.common.FaceHandler.isAppropriateFace

public class TaskSecondTestSuite {


    @Test
    public void shouldReturnCorrectSecondFace(){
        //given
        int[][] controlFace = [
                [2,0,2,0,2],
                [2,2,2,2,2],
                [0,2,2,2,0],
                [2,2,2,2,2],
                [2,0,2,0,2],
        ]
        CubeASCII cube = new CubeASCII();
        //when
        Face3D face = cube.getFace(2);
        //then
        assertThat Arrays.deepEquals(face.face,controlFace), is(true)
    }

    @Test
    public void shouldReturnCorrectFifthFace(){
        //given
        int[][] controlFace = [
                [0,5,0,5,0],
                [5,5,5,5,5],
                [0,5,5,5,0],
                [5,5,5,5,5],
                [5,0,5,0,0],
        ]
        CubeASCII cube = new CubeASCII();
        //when
        Face3D face = cube.getFace(5);
        //then
        assertThat Arrays.deepEquals(face.face,controlFace), is(true)
    }


    @Test
    public void shouldConnectMatrixWhenAllSidesAreFilled(){
    //given
    int[][] faceCandidateMatrix = [
            [0,1,0,1,0],
            [1,1,1,1,1],
            [0,1,1,1,0],
            [1,1,1,1,1],
            [1,0,1,0,0],
    ]
    int[][] faceCubeMatrix = [
            [2,0,5,0,5],
            [0,0,0,0,0],
            [2,0,0,0,4],
            [0,0,0,0,0],
            [0,3,0,3,3],
    ]
    //when
    boolean canBePlaced = isAppropriateFace(new Face3D(faceCandidateMatrix), new Face3D(faceCubeMatrix));
    //then
    assertThat canBePlaced, is(true)
    }

    @Test
    public void shouldNotConnectMatrixWhenMatricesIntersected(){
        //given
        int[][] faceCandidateMatrix = [
                [0,1,0,1,0],
                [1,1,1,1,1],
                [0,1,1,1,0],
                [1,1,1,1,1],
                [1,0,1,0,0],
        ]
        int[][] faceCubeMatrix = [
                [2,2,2,2,2],
                [0,0,0,0,0],
                [0,0,0,0,0],
                [0,0,0,0,0],
                [0,0,0,0,0],
        ]
        //when
        boolean canBePlaced = isAppropriateFace(new Face3D(faceCandidateMatrix), new Face3D(faceCubeMatrix));
        //then
        assertThat canBePlaced, is(false)
    }

    @Test
    public void shouldNotConnectMatrixWhenMatricesDontFillFace(){
        //given
        int[][] faceCandidateMatrix = [
                [0,1,0,1,0],
                [1,1,1,1,1],
                [0,1,1,1,0],
                [1,1,1,1,1],
                [1,0,1,0,0],
        ]
        int[][] faceCubeMatrix = [
                [2,0,0,0,0],
                [0,0,0,0,0],
                [0,0,0,0,0],
                [0,0,0,0,0],
                [0,0,0,0,0],
        ]
        //when
        boolean canBePlaced = isAppropriateFace(new Face3D(faceCandidateMatrix), new Face3D(faceCubeMatrix));
        //then
        assertThat canBePlaced, is(false)
    }

    //TODO Tests for Face Handler
}