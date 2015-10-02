package ru.tyanmt.task.common;

import java.util.function.BiPredicate;
import java.util.stream.IntStream;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;

/**
 * Created by top4umba on 10.08.2015.
 */
public class FaceMergeValidator {

    private final Face candidateFace;
    private final Face cubeFace;

    public FaceMergeValidator(Face candidateFace, Face cubeFace) {
        this.candidateFace = candidateFace;
        this.cubeFace = cubeFace;
    }

    public boolean validate() {
        boolean foundConflict = IntStream.range(0, FACE_LENGTH).flatMap(x ->
                        IntStream.range(0, FACE_LENGTH)
                                .filter(y -> pointsIntersect()
                                        .or(pointStillEmpty().and(isVertexAndNeighborFacesFilled().or(isNotVertexAndNeighborFaceFilled()))).test(x, y))
        ).findAny().isPresent();
        return !foundConflict;
    }

    private BiPredicate<Integer, Integer> pointStillEmpty() {
        return (x, y) -> isEmpty(candidateFace.getPoint(x, y)) && isEmpty(cubeFace.getPoint(x, y));
    }

    private BiPredicate<Integer, Integer> pointsIntersect() {
        return (x, y) -> !isEmpty(candidateFace.getPoint(x, y)) && !isEmpty(cubeFace.getPoint(x, y));
    }

    private BiPredicate<Integer, Integer> isVertexAndNeighborFacesFilled() {
        return (x, y) -> isVertex(x, y) && !isAnyNeighborFaceEmpty(x, y);
    }

    private BiPredicate<Integer, Integer> isNotVertexAndNeighborFaceFilled() {
        return (x, y) -> !isVertex(x, y) && !isNeighborFaceEmpty(x, y);
    }

    private int getNeighborCoordinate(int x) {
        return x == 0 ? 1 : penultimateIndex();
    }

    private boolean isEmpty(int value) {
        return value == 0;
    }

    private boolean isNeighborFaceEmpty(int x, int y) {
        return cubeFace.getNeighborAt(x, y) == 0;
    }

    private boolean isAnyNeighborFaceEmpty(int x, int y) {
        return cubeFace.getNeighborAt(getNeighborCoordinate(x), y) == 0 || cubeFace.getNeighborAt(x, getNeighborCoordinate(y)) == 0;
    }

    public static boolean isVertex(int x, int y) {
        return (x == 0 || x == lastIndex()) && (y == 0 || y == lastIndex());
    }

    public static boolean isEdge(int x, int y) {
        return x == 0 || x == lastIndex() || y == 0 || y == lastIndex();
    }

    private static int lastIndex() {
        return FACE_LENGTH - 1;
    }

    private int penultimateIndex() {
        return lastIndex() - 1;
    }
}
