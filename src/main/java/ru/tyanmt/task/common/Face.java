package ru.tyanmt.task.common;

import java.util.Arrays;

/**
 * Created by mityan on 10.08.2015.
 */
public class Face {
    private int[][] face;
    private int[][] adjacentFaces = new int[3][3];

    public Face() {
        this.face = new int[5][5];
    }

    public Face(int[][] face) {
        this.face = face;
    }

    public int[][] getFace() {
        return face;
    }

    public void setFace(int[][] face) {
        this.face = face;
    }

    public int[][] getAdjacentFaces() {
        return adjacentFaces;
    }

    public void setAdjacentFaces(int[][] adjacentFaces) {
        this.adjacentFaces = adjacentFaces;
    }
}
