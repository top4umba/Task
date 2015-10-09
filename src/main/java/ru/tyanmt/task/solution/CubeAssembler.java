package ru.tyanmt.task.solution;

import ru.tyanmt.task.common.Cube;
import ru.tyanmt.task.common.CubeASCII;
import ru.tyanmt.task.common.Face;
import ru.tyanmt.task.common.FacePosition;

import java.util.ArrayList;
import java.util.List;


public class CubeAssembler {
    private final List<Cube> solutions = new ArrayList<>();

    public List<Cube> getSolutions() {
        return solutions;
    }

    public void assembleCube(CubeASCII flatCube) {
        Cube cube = new Cube();
        List<Face> faces = flatCube.getFaces();
        cube.tryPutFaceOn(FacePosition.BOTTOM, faces.remove(0));
        addFaceTo(cube, FacePosition.FRONT, faces);
    }

    private void addFaceTo(Cube cube, FacePosition facePosition, List<Face> faces) {
        faces.stream().forEach(face -> {
            Cube cubeCandidate = new Cube(cube);
            List<Face> remainingFaces = new ArrayList<>(faces);
            remainingFaces.remove(face);
            face.getRotateOptions().stream()
                    .filter(faceOption -> cubeCandidate.tryPutFaceOn(facePosition, faceOption))
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
