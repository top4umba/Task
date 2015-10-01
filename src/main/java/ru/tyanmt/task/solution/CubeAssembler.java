package ru.tyanmt.task.solution;

import ru.tyanmt.task.common.Cube;
import ru.tyanmt.task.common.CubeASCII;
import ru.tyanmt.task.common.Face;
import ru.tyanmt.task.common.FacePosition;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mityan on 03.08.2015.
 */
public class CubeAssembler {
    List<Cube> solutions = new ArrayList<>();

    public List<Cube> getSolutions() {
        return solutions;
    }

    public void assembleCube(CubeASCII flatCube) {
        Cube cube = new Cube();
        List<Face> faces = flatCube.getFaces();
        cube.putFaceOn(FacePosition.BOTTOM, faces.remove(0));
        addFaceTo(cube, FacePosition.FRONT, faces);
    }

    private void addFaceTo(Cube cube, FacePosition facePosition, List<Face> faces) {
        faces.stream().forEach(face -> {
            Cube cubeCandidate = new Cube(cube);
            final List<Face> remainingFaces = new ArrayList<>(faces);
            remainingFaces.remove(face);
            face.getRotateOptions().stream()
                    .filter(faceOption -> cubeCandidate.putFaceOn(facePosition, faceOption))
                    .forEach(faceOption -> {
                        if (facePosition.hasNext()) {
                            addFaceTo(cubeCandidate, facePosition.next(), remainingFaces);
                        } else {
                            solutions.add(cubeCandidate);
                        }
                    });
        });
    }

}
