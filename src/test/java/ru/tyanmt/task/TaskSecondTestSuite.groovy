import org.junit.Test
import ru.tyanmt.task.common.Cube3D
import ru.tyanmt.task.common.CubeASCII
import ru.tyanmt.task.common.Face3D

import static org.hamcrest.Matchers.is
import static org.junit.Assert.assertThat

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
    public void shouldReturnCorrectTop(){
        //given
        int[][][] controlCube = [
                [[0,5,0,5,0],
                [5,5,5,5,5],
                [0,5,5,5,0],
                [5,5,5,5,5],
                [5,0,5,0,0],]
        ]
        Cube3D cube = new Cube3D();
        cube.
        //when
        Face3D face = cube.getFace(5);
        //then
        assertThat Arrays.deepEquals(face.face,controlFace), is(true)
    }
}