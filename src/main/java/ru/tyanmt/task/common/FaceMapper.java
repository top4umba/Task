package ru.tyanmt.task.common;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;

/**
 * Created by mityan on 17.08.2015.
 */
public class FaceMapper {


    public static int getPointFromFace(FacePosition position, int i, int j, int[][][] cube) {
        switch (position) {
            case BOTTOM:
                return cube[0][i][j];
            case FRONT:
                return cube[i][0][j];
            case TOP:
                return cube[FACE_LENGTH - 1][i][j];
            case RIGHT:
                return cube[i][j][FACE_LENGTH - 1];
            case REAR:
                return cube[i][FACE_LENGTH - 1][j];
            case LEFT:
                return cube[i][j][0];
            default:
                throw new IllegalArgumentException("Side number should be between 1 and 6");
        }
    }

    public static int getPointFromSection(FacePosition position, int i, int j, int[][][] cube) {
        switch (position) {
            case BOTTOM:
            case TOP:
                return cube[FACE_LENGTH / 2][i][j];
            case FRONT:
            case REAR:
                return cube[i][FACE_LENGTH / 2][j];
            case LEFT:
            case RIGHT:
                return cube[i][j][FACE_LENGTH / 2];
            default:
                throw new IllegalArgumentException("Side number should be between 1 and 6");
        }
    }

    public static void setPointToFace(FacePosition position, int i, int j, int[][][] cube) {
        int faceNumber = position.ordinal()+1;
        switch (position) {
            case BOTTOM:
                cube[0][i][j] = faceNumber;
                break;
            case FRONT:
                cube[i][0][j] = faceNumber;
                break;
            case TOP:
                cube[FACE_LENGTH - 1][i][j] = faceNumber;
                break;
            case RIGHT:
                cube[i][j][FACE_LENGTH - 1] = faceNumber;
                break;
            case REAR:
                cube[i][FACE_LENGTH - 1][j] = faceNumber;
                break;
            case LEFT:
                cube[i][j][0] = faceNumber;
                break;
            default:
                throw new IllegalArgumentException("Side number should be between 1 and 6");
        }
    }
}
