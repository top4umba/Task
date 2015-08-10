package ru.tyanmt.task.solution;

import java.util.ArrayList;

/**
 * Created by mityan on 03.08.2015.
 */
public class UnfoldedCube {

    public UnfoldedCube() {
        faces.stream().map(e -> e = new FaceCandidate());
    }

    private final ArrayList<FaceCandidate> faces = new ArrayList<>();

    public ArrayList<FaceCandidate> getFaces() {
        return faces;
    }

}
