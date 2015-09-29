package ru.tyanmt.task.common;

/**
 * Created by mityan on 10.08.2015.
 */
public class Face {
    private int[][] matrix;
    private int[][] adjacentFacesSection = new int[5][5];

    public Face() {
        this.matrix = new int[5][5];
    }

    public Face(int[][] face) {
        this.matrix = face;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public int[][] getAdjacentFacesSection() {
        return adjacentFacesSection;
    }

    public void setAdjacentFacesSection(int[][] adjacentFacesSection) {
        this.adjacentFacesSection = adjacentFacesSection;
    }
}
