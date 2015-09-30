package ru.tyanmt.task;

import ru.tyanmt.task.common.CubeASCII;
import ru.tyanmt.task.solution.CubeAssembler;

/**
 * Created by mityan on 31.07.2015.
 */
public class Main {
    public static void main(String[] args) {
        CubeAssembler assembler = new CubeAssembler();
        assembler.assembleCube(new CubeASCII());
    }

}
