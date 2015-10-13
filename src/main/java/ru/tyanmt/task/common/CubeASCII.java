package ru.tyanmt.task.common;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;

public class CubeASCII {
    private static final int FACES_IN_ROW = 3;
    private final String text;
    private final List<Face> faces;

    public CubeASCII(String resourceName) {
        try {
            Path file = getPath(resourceName);
            text = Files.lines(file).map(this::pad).collect(Collectors.joining());
            faces = readFaces();
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Cannot read " + resourceName, e);
        }
    }

    public List<Face> getFaces() {
        return new ArrayList<>(faces);
    }

    private List<Face> readFaces() {
        return IntStream.rangeClosed(1, 6).mapToObj(this::readFace).collect(Collectors.toList());
    }

    private Face readFace(int faceNumber) {
        if (faceNumber < 1 || faceNumber > 6) {
            throw new IllegalArgumentException("Number of face should be in range 1 to 6");
        }
        int startPosition = faceNumber <= FACES_IN_ROW ? getPositionInFirstRow(faceNumber) : getPositionInSecondRow(faceNumber);
        return readFaceAt(startPosition, faceNumber);
    }

    private Face readFaceAt(int position, int faceNumber) {
        int[][] matrix = new int[FACE_LENGTH][FACE_LENGTH];
        int rowNumber = 0;
        while (readingIsNotFinished(position, rowNumber)) {
            readLine(position, faceNumber, matrix, rowNumber);
            rowNumber++;
            position += FACES_IN_ROW * FACE_LENGTH;
        }
        return new Face(matrix);
    }

    private void readLine(int position, int faceNumber, int[][] matrix, int rowNumber) {
        String row = text.substring(position, position + FACE_LENGTH);
        IntStream.range(0, row.length()).forEach(i ->
                        matrix[rowNumber][i] = row.charAt(i) == 'o' ? faceNumber : 0
        );
    }

    private boolean readingIsNotFinished(int position, int rowNumber) {
        return position < text.length() && rowNumber < FACE_LENGTH;
    }

    private int getPositionInFirstRow(int faceNumber) {
        return (faceNumber - 1) * FACE_LENGTH;
    }

    private int getPositionInSecondRow(int faceNumber) {
        return FACE_LENGTH * FACE_LENGTH * FACES_IN_ROW + (faceNumber - 1 - FACES_IN_ROW) * FACE_LENGTH;
    }

    private String pad(String line) {
        return String.format("%1$-" + FACE_LENGTH * FACES_IN_ROW + "s", line);
    }

    private Path getPath(String resourceName) throws URISyntaxException, IOException {
        URI uri = ClassLoader.getSystemResource(resourceName).toURI();
        if (isResourceInJar(uri)) {
            return getPathFromFileSystem(uri);
        } else {
            return Paths.get(uri);
        }
    }

    private boolean isResourceInJar(URI uri) {
        return uri.toString().contains("!");
    }

    private Path getPathFromFileSystem(URI uri) throws IOException {
        String[] uriSplit = uri.toString().split("!");
        return FileSystems.newFileSystem(URI.create(uriSplit[0]), Collections.<String, Object>emptyMap()).getPath(uriSplit[1]);
    }

}
