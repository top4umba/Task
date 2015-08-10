package ru.tyanmt.task.cubes;

import ru.tyanmt.task.common.Cube;
import ru.tyanmt.task.common.Face;
import ru.tyanmt.task.solution.FaceCandidate;

import java.util.ArrayList;

/**
 * Created by mityan on 03.08.2015.
 */
public class BlueCube extends Cube {

    private final ArrayList<FaceCandidate> faceCandidates = new ArrayList<>();

    public BlueCube(){
        FaceCandidate.Builder faceBuilder = FaceCandidate.newBuilder();
        faceCandidates.add(faceBuilder.top(0b00100).right(0b00100).bottom(0b00100).left(0b00100).build());
        faceCandidates.add(faceBuilder.top(0b10101).right(0b11011).bottom(0b10101).left(0b11011).build());
        faceCandidates.add(faceBuilder.top(0b00100).right(0b01010).bottom(0b00100).left(0b00100).build());
        faceCandidates.add(faceBuilder.top(0b01010).right(0b00100).bottom(0b01011).left(0b11010).build());
        faceCandidates.add(faceBuilder.top(0b01010).right(0b01010).bottom(0b00101).left(0b11010).build());
        faceCandidates.add(faceBuilder.top(0b01010).right(0b01011).bottom(0b11011).left(0b10100).build());
    }
}
