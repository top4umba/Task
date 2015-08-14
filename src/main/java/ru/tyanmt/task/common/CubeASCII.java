package ru.tyanmt.task.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mityan on 10.08.2015.
 */
public class CubeASCII {

    private String text =
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

    public Face getFace(int faceNumber) {
        Face face = new Face();
        if (faceNumber < 1 || faceNumber > 6) {
            throw new IllegalArgumentException("Number of face should be in range 1 and 6");
        }
        int rowLength = 5;
        int position = faceNumber <= 3 ? (faceNumber-1) * rowLength : rowLength * rowLength * 3 + (faceNumber-4) * rowLength;
        int rowNumber = 0;
        while (position < text.length() && rowNumber < 5) {
            String row = text.substring(position, position + 5);
            for (int i = 0; i < row.length(); i++) {
                face.getFace()[rowNumber][i] = row.charAt(i) == 'o' ? faceNumber : 0;
            }
            rowNumber++;
            position += 15;
        }
        return face;
    }


    public List<Face> getFaces() {
        List<Face> faces = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            faces.add(getFace(i));
        }
        return faces;
    }

}
