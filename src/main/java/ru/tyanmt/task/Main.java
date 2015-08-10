package ru.tyanmt.task;

import ru.tyanmt.task.cubes.BlueCube;
import ru.tyanmt.task.solution.CubeAssembler;
import ru.tyanmt.task.solution.UnfoldedCube;
import ru.tyanmt.task.util.Printer;

/**
 * Created by mityan on 31.07.2015.
 */
public class Main {
    public static void main(String[] args) {
        CubeAssembler assembler = new CubeAssembler();
        UnfoldedCube unfoldedCube = assembler.assembleCube(new BlueCube());
        Printer.print(unfoldedCube);
    }

}
