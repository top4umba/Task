package ru.tyanmt.task.solution;

import ru.tyanmt.task.common.Cube;
import ru.tyanmt.task.common.CubeASCII;
import ru.tyanmt.task.common.Face;

import java.util.ArrayList;
import java.util.List;

import static ru.tyanmt.task.common.FaceHandler.getRotateOptions;


/**
 * Created by mityan on 03.08.2015.
 */
public class CubeAssembler {


    List<Cube> solutions = new ArrayList<>();

    public void assembleCube(CubeASCII flatCube) {
        addFace(new Cube(), 1, flatCube.getFaces());
    }

    public List<Cube> getSolutions() {
        return solutions;
    }

    private void addFace(Cube cube, int faceNumber, List<Face> faces) {
        if (faces.size()==6){
            cube.putFaceOn(faceNumber, faces.remove(0));
            addFace(cube, faceNumber+1,faces);
        }else{
            for (Face face : faces) {
                Cube cubeCandidate = new Cube(cube.getCube());
                final List<Face> remainingFaces = new ArrayList<>(faces);
                remainingFaces.remove(face);
                List<Face> rotateOptions = getRotateOptions(face);
                for (Face faceOption : rotateOptions) {
                    if (cubeCandidate.putFaceOn(faceNumber, faceOption)) {
                        if (remainingFaces.isEmpty()) {
                            solutions.add(cubeCandidate);
                        } else {
                            addFace(cubeCandidate, faceNumber + 1, remainingFaces);
                        }
                    }
                }
            }
        }

    }


}
