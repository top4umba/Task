package ru.tyanmt.task.common;

/**
 * Created by mityan on 07.08.2015.
 */
public class Cube3D {
    int[][][] cube = new int[5][5][5];

    public Cube3D(){
        this.cube = new int[5][5][5];
    }

    public Cube3D(int[][][] cube){
        this.cube=cube;
    }

    int[][] cubeInt = new int[5][5];

    public Face3D getTop() {
        Face3D face = new Face3D();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; i++) {
                face.getFace()[i][j] = cube[i][4][j];
            }
        }
        return face;
    }

    public void placeOnTop(Face3D faceCandidate) {
        Face3D cubeFace = this.getTop();
        if (facesConnected(faceCandidate, cubeFace)){
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; i++) {
                    cube[i][4][j] = faceCandidate.getFace()[i][j];
                }
            }
        } else {

        };

    }

    private boolean facesConnected(Face3D faceCandidate, Face3D cubeFace) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; i++) {
                if (cubeFace.getFace()[i][j]!=0){
                    if (faceCandidate.getFace()[i][j]!=0){
                        return false;
                    }
                }else{
                    if (faceCandidate.getFace()[i][j]==0) {
                        if (isVertex(i, j)){
                            if (!isAdjacentSidesEmpty(cubeFace, i, j)) return false;
                            //TODO if vertex is not accesible
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean isAdjacentSidesEmpty(Face3D cubeFace, int i, int j) {
        boolean sideIsEmpty = true;
        for (int k = 0; k < 5; k++) {
            if (cubeFace.getFace()[i][k]!=0) {
                if (!sideIsEmpty) {return false;} else { sideIsEmpty = false;}
            }
            if (cubeFace.getFace()[k][j]!=0) {
                if (!sideIsEmpty) {return false;} else { sideIsEmpty = false;}
            }
        }
        return true;
    }

    private boolean isVertex(int i, int j) {
        return (i == 0 && j == 0)||(i == 0 && j == 5) || (i == 5 && j == 0) || (i == 5 && j == 0);
    }
}
