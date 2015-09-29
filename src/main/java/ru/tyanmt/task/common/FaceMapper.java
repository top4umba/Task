package ru.tyanmt.task.common;

/**
 * Created by mityan on 17.08.2015.
 */
public class FaceMapper {


    public static int getPointFromFace(int number, int i, int j, int[][][] cube) {
        switch (number) {
            case 1:
                return cube[0][i][j];
            case 2:
                return cube[i][0][j];
            case 3:
                return cube[4][i][j];
            case 4:
                return cube[i][j][4];
            case 5:
                return cube[i][4][j];
            case 6:
                return cube[i][j][0];
            default:
                throw new IllegalArgumentException("Side number should be between 1 and 6");
        }
    }

    public static int getPointFromSection(int number, int i, int j, int[][][] cube) {
        switch (number) {
            case 1:
            case 3:
                return cube[2][i][j];
            case 2:
            case 5:
                return cube[i][2][j];
            case 4:
            case 6:
                return cube[i][j][2];
            default:
                throw new IllegalArgumentException("Side number should be between 1 and 6");
        }
    }

    public static void setPointToFace(int number, int i, int j, int value, int[][][] cube) {
        switch (number) {
            case 1:
                cube[0][i][j] = value;
                break;
            case 2:
                cube[i][0][j] = value;
                break;
            case 3:
                cube[4][i][j] = value;
                break;
            case 4:
                cube[i][j][4] = value;
                break;
            case 5:
                cube[i][4][j] = value;
                break;
            case 6:
                cube[i][j][0] = value;
                break;
            default:
                throw new IllegalArgumentException("Side number should be between 1 and 6");
        }
    }
}
