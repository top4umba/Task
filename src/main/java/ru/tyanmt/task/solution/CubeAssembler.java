package ru.tyanmt.task.solution;

import ru.tyanmt.task.common.Cube;
import ru.tyanmt.task.common.CubeASCII;
import ru.tyanmt.task.common.Face;

import java.util.ArrayList;
import java.util.List;

import static ru.tyanmt.task.common.FaceHandler.flipFace;
import static ru.tyanmt.task.common.FaceHandler.getRotateOptions;
import static ru.tyanmt.task.common.FaceHandler.rotateClockwise;


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

    private void addFace(Cube cube, int faceNumber, List<Face> faces){
        for (Face face : faces) {
            List<Face> remainingFaces = new ArrayList<>(faces);
            remainingFaces.remove(face);
            List<Face> rotateOptions = getRotateOptions(face);
            for (Face faceOption : rotateOptions) {
                if (cube.putFaceOn(faceNumber,faceOption)){
                    if (remainingFaces.isEmpty()){
                        solutions.add(cube);
                    }else{
                        addFace(new Cube(cube.getCube()), ++faceNumber, remainingFaces);
                    }
                }
            }
        }
    }





}
