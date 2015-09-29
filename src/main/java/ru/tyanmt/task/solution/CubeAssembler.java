package ru.tyanmt.task.solution;

import ru.tyanmt.task.common.Cube;
import ru.tyanmt.task.common.CubeASCII;
import ru.tyanmt.task.common.Face;

import java.util.ArrayList;
import java.util.List;

import static ru.tyanmt.task.common.MatrixUtil.flipMatrix;
import static ru.tyanmt.task.common.MatrixUtil.rotateMatrixClockwise;


/**
 * Created by mityan on 03.08.2015.
 */
public class CubeAssembler {


    List<Cube> solutions = new ArrayList<>();

    public void assembleCube(CubeASCII flatCube) {
        Cube cube = new Cube();
        List<Face> faces = flatCube.getFaces();
        cube.putFaceOn(1, faces.remove(0));
        addFaceTo(cube, 2, faces);
    }

    public List<Cube> getSolutions() {
        return solutions;
    }

    private void addFaceTo(Cube cube, int faceNumber, List<Face> faces) {
        for (Face face : faces) {
            Cube cubeCandidate = new Cube(cube);
            final List<Face> remainingFaces = new ArrayList<>(faces);
            remainingFaces.remove(face);
            List<Face> rotateOptions = getRotateOptions(face);
            for (Face faceOption : rotateOptions) {
                if (cubeCandidate.putFaceOn(faceNumber, faceOption)) {
                    if (remainingFaces.isEmpty()) {
                        solutions.add(cubeCandidate);
                    } else {
                        addFaceTo(cubeCandidate, faceNumber + 1, remainingFaces);
                    }
                }
            }
        }
    }

    private static List<Face> getRotateOptions(Face face) {
        int[][] faceOption = face.getMatrix();
        List<Face> result = new ArrayList<>();
        result.add(face);
        for (int i = 0; i < 7; i++) {
            faceOption = i == 3 ? flipMatrix(faceOption) : rotateMatrixClockwise(faceOption);
            result.add(new Face(faceOption));
        }
        return result;
    }

}
