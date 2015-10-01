package ru.tyanmt.task.common;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;

/**
 * Created by mityan on 10.08.2015.
 */
public class CubeASCII {
    private static final int FACES_IN_ROW = 3;

    private final String TEXT =
                    "  o  o o o  o  " +
                    " ooo ooooo oooo" +
                    "ooooo ooo oooo " +
                    " ooo ooooo oooo" +
                    "  o  o o o  o  " +
                    " o o  o o  o o " +
                    "oooo ooooo oooo" +
                    " oooo ooo oooo " +
                    "oooo ooooo oooo" +
                    "oo o o o  oo oo";

    public List<Face> getFaces() {
        return IntStream.rangeClosed(1,6).mapToObj(i -> getFace(i)).collect(Collectors.toList());
    }

    public Face getFace(int faceNumber) {
        if (faceNumber < 1 || faceNumber > 6) {
            throw new IllegalArgumentException("Number of face should be in range 1 to 6");
        }
        int startPosition = faceNumber <= FACES_IN_ROW ? getPositionInFirstRow(faceNumber) : getPositionInSecondRow(faceNumber);
        return readFaceAt(startPosition, faceNumber);
    }

    private Face readFaceAt(int position, int faceNumber) {
        Face face = new Face();
        int rowNumber = 0;
        while (position < TEXT.length() && rowNumber < FACE_LENGTH) {
            String row = TEXT.substring(position, position + FACE_LENGTH);
            for (int i = 0; i < row.length(); i++) {
                face.setPoint(rowNumber, i, row.charAt(i) == 'o' ? faceNumber : 0);
            }
            rowNumber++;
            position += FACES_IN_ROW * FACE_LENGTH;
        }
        return face;
    }

    private int getPositionInFirstRow(int faceNumber) {
        return (faceNumber - 1) * FACE_LENGTH;
    }

    private int getPositionInSecondRow(int faceNumber) {
        return FACE_LENGTH * FACE_LENGTH * FACES_IN_ROW + (faceNumber - 1 - FACES_IN_ROW) * FACE_LENGTH;
    }

}
