package com.juliansanchez.exercise;

import com.juliansanchez.enums.ClawCommand;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.juliansanchez.enums.ClawCommand.NOTHING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FactoryBoxStackProblemExerciseTest {
    private static final int MAX_MOVEMENTS = 200;
    private FactoryBoxStackProblemExercise factoryBoxStackProblemExercise = new FactoryBoxStackProblemExercise();
    private int[] boxes;
    private int clawPos;
    private int boxInClaw;
    private int commandCounter;

    /**
     * Scenario:
     * Executes {@link #operateFactory(int[])} with valid parameters
     *
     * Expectation:
     * Boxes are stacked in the right way and commandCounter is less than MAX_MOVEMENTS.
     */
    @Test
    void whenParametersAreValidShouldSolve() {
        clawPos = 1;
        boxInClaw = 1;
        boxes = new int[]{3, 2, 1, 4};
        int[] expectedBoxes = {3, 3, 3, 2};

        operateFactory(expectedBoxes);

        assertTrue(Arrays.equals(boxes, expectedBoxes));
        assertTrue(commandCounter <= MAX_MOVEMENTS);
    }

    /**
     * Scenario:
     * Executes {@link #operateFactory(int[])} with minimum parameters
     *
     * Expectation:
     * Boxes are stacked in the right way and commandCounter is less than MAX_MOVEMENTS.
     */
    @Test
    void whenParametersAreTheMinimumShouldSolve() {
        clawPos = 0;
        boxInClaw = 0;
        boxes = new int[]{0, 1};
        int[] expectedBoxes = {1, 0};

        operateFactory(expectedBoxes);

        assertTrue(Arrays.equals(boxes, expectedBoxes));
        assertTrue(commandCounter <= MAX_MOVEMENTS);
    }

    /**
     * Scenario:
     * Executes {@link #operateFactory(int[])} with maximum parameters
     *
     * Expectation:
     * Boxes are stacked in the right way and commandCounter is less than MAX_MOVEMENTS.
     */
    @Test
    void whenParametersAreTheMaximumShouldSolve() {
        clawPos = 0;
        boxInClaw = 0;
        boxes = new int[]{0, 0, 0, 0, 0, 0, 0, 16};
        int[] expectedBoxes = {2, 2, 2, 2, 2, 2, 2, 2};

        operateFactory(expectedBoxes);

        assertTrue(Arrays.equals(boxes, expectedBoxes));
        assertTrue(commandCounter <= MAX_MOVEMENTS);
    }

    /**
     * Scenario:
     * Executes {@link #operateFactory(int[])} with invalid clawPos
     *
     * Expectation:
     * Returned {@link ClawCommand} should be INVALID_PARAMETERS and commandCounter should be 1
     */
    @Test
    void whenBoxInClawIsNotZeroOrOneShouldReturnInvalidParameters() {
        clawPos = 0;
        boxInClaw = 6;
        boxes = new int[]{3, 2, 1, 4};
        int[] expectedBoxes = {4, 4, 4, 4};

        assertEquals(ClawCommand.INVALID_PARAMETERS, operateFactory(expectedBoxes));
        assertEquals(1, commandCounter);
    }

    /**
     * Scenario:
     * Executes {@link #operateFactory(int[])} with boxes greater than allowed
     *
     * Expectation:
     * Returned {@link ClawCommand} should be INVALID_PARAMETERS and commandCounter should be 1
     */
    @Test
    void whenBoxesAreGreaterThanAllowedShouldReturnInvalidParameters() {
        clawPos = 0;
        boxInClaw = 0;
        boxes = new int[]{8, 0, 8, 16};
        int[] expectedBoxes = {8, 8, 8, 8};

        assertEquals(ClawCommand.INVALID_PARAMETERS, operateFactory(expectedBoxes));
        assertEquals(1, commandCounter);
    }

    /**
     * Scenario:
     * Executes {@link #operateFactory(int[])} with boxes greater than allowed
     *
     * Expectation:
     * Returned {@link ClawCommand} should be INVALID_PARAMETERS and commandCounter should be 1
     */
    @Test
    void whenBoxesAreLesserThanAllowedShouldReturnInvalidParameters() {
        clawPos = 0;
        boxInClaw = 0;
        boxes = new int[]{0, 0, 0, 0};
        int[] expectedBoxes = {0, 0, 0, 0};

        assertEquals(ClawCommand.INVALID_PARAMETERS, operateFactory(expectedBoxes));
        assertEquals(1, commandCounter);
    }

    /**
     * Scenario:
     * Executes {@link #operateFactory(int[])} with stacks greater than allowed
     *
     * Expectation:
     * Returned {@link ClawCommand} should be INVALID_PARAMETERS and commandCounter should be 1
     */
    @Test
    void whenStacksAreGreaterThanAllowedShouldReturnInvalidParameters() {
        clawPos = 0;
        boxInClaw = 0;
        boxes = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1};
        int[] expectedBoxes = {1, 0, 0, 0, 0, 0, 0, 0, 0};

        assertEquals(ClawCommand.INVALID_PARAMETERS, operateFactory(expectedBoxes));
        assertEquals(1, commandCounter);
    }

    /**
     * Scenario:
     * Executes {@link #operateFactory(int[])} with stacks lesser than allowed
     *
     * Expectation:
     * Returned {@link ClawCommand} should be INVALID_PARAMETERS and commandCounter should be 1
     */
    @Test
    void whenStacksAreLesserThanAllowedShouldReturnInvalidParameters() {
        clawPos = 0;
        boxInClaw = 1;
        boxes = new int[]{0};
        int[] expectedBoxes = {1};

        assertEquals(ClawCommand.INVALID_PARAMETERS, operateFactory(expectedBoxes));
        assertEquals(1, commandCounter);
    }

    /**
     * Scenario:
     * Executes {@link #operateFactory(int[])} with boxes already arranged
     *
     * Expectation:
     * Returned {@link ClawCommand} should be NOTHING and commandCounter should be 1
     */
    @Test
    void whenBoxesAreAlreadyArrangedShouldReturnInvalidParameters() {
        clawPos = 1;
        boxInClaw = 0;
        boxes = new int[]{3, 3, 3, 2};
        int[] expectedBoxes = {3, 3, 3, 2};

        assertEquals(ClawCommand.NOTHING, operateFactory(expectedBoxes));
        assertEquals(1, commandCounter);
    }

    /**
     * Executes {@link FactoryBoxStackProblemExercise#solve(int[], int, int)} in a loop until the boxes are stacked
     * in the right way or the {@link ClawCommand} is NOTHING or INVALID_PARAMETERS.
     *
     * @param expectedBoxes {@link int[]}
     * @return last {@link ClawCommand} executed
     */
    private ClawCommand operateFactory(int[] expectedBoxes) {
        ClawCommand clawCommand = factoryBoxStackProblemExercise.solve(boxes, clawPos, boxInClaw);
        executeCommand(clawCommand);

        while (!Arrays.equals(boxes, expectedBoxes) && !clawCommand.equals(NOTHING)
                && !clawCommand.equals(ClawCommand.INVALID_PARAMETERS)) {
            clawCommand = factoryBoxStackProblemExercise.solve(boxes, clawPos, boxInClaw);
            executeCommand(clawCommand);
        }

        return clawCommand;
    }

    /**
     * Executes claw command for given {@link ClawCommand}
     *
     * @param clawCommand {@link ClawCommand}
     */
    private void executeCommand(ClawCommand clawCommand) {
        switch (clawCommand) {
            case LEFT:
                if (clawPos > 0)
                    clawPos--;
                break;
            case RIGHT:
                if (clawPos < boxes.length - 1)
                    clawPos++;
                break;
            case PICK:
                if (boxInClaw == 0) {
                    boxInClaw = 1;
                    boxes[clawPos]--;
                }
                break;
            case PLACE:
                if (boxInClaw == 1) {
                    boxInClaw = 0;
                    boxes[clawPos]++;
                }
        }

        commandCounter++;
    }
}
