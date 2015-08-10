package ru.tyanmt.task.common;

/**
 * Created by mityan on 10.08.2015.
 */
public class Face3D {
    private int[][] face;

    public Face3D(){
        this.face = new int[5][5];
    }

    public Face3D(int[][] face){
        this.face=face;
    }

    public int[][] getFace() {
        return face;
    }

    public void setFace(int[][] face) {
        this.face = face;
    }
}
