package ru.tyanmt.task.common;

import java.util.ArrayList;

/**
 * Created by mityan on 03.08.2015.
 */
public class Cube {

    protected final ArrayList<Face> faces = new ArrayList<>();

    public Cube() {
        faces.add(Face.newBuilder().withNumber(0).topConnectedTo(5).rightConnectedTo(1).bottomConnectedTo(3).leftConnectedTo(4).build());
        faces.add(Face.newBuilder().withNumber(1).topConnectedTo(5).rightConnectedTo(2).bottomConnectedTo(3).leftConnectedTo(0).build());
        faces.add(Face.newBuilder().withNumber(2).topConnectedTo(5).rightConnectedTo(4).bottomConnectedTo(3).leftConnectedTo(1).build());
        faces.add(Face.newBuilder().withNumber(3).topConnectedTo(1).rightConnectedTo(2).bottomConnectedTo(4).leftConnectedTo(0).build());
        faces.add(Face.newBuilder().withNumber(4).topConnectedTo(3).rightConnectedTo(2).bottomConnectedTo(0).leftConnectedTo(0).build());
        faces.add(Face.newBuilder().withNumber(5).topConnectedTo(4).rightConnectedTo(2).bottomConnectedTo(1).leftConnectedTo(0).build());
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }

    public Face getFace(int number) {
        return faces.get(number);
    }
}
