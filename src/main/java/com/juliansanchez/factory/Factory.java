package com.juliansanchez.factory;

import com.juliansanchez.enums.ClawCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.juliansanchez.enums.ClawCommand.*;
import static com.juliansanchez.enums.ClawCommand.LEFT;

@Getter
@Setter
/**
 * Factory class
 */
public class Factory {
    private int[] boxes;
    private int[] expectedBoxes;
    private Claw claw;

    public Factory(int[] boxes, int clawPos, int boxInClaw) {
        this.boxes = boxes;
        this.expectedBoxes = new int[boxes.length];
        this.claw = new Claw(clawPos, boxInClaw);
    }

    /**
     * Solves the next step for box stacking problem
     *
     * @return {@link ClawCommand}
     */
    public ClawCommand solve() {
        calculateExpectedBoxes();

        if (Arrays.equals(boxes, expectedBoxes)) {
            return NOTHING;
        }

        if (claw.getHasBox() == 1) {
            if (boxes[claw.getPosition()] < expectedBoxes[claw.getPosition()]) {
                return PLACE;
            } else {
                return goToClosestStackWithLessThanExpected();
            }
        } else {
            if (boxes[claw.getPosition()] > expectedBoxes[claw.getPosition()]) {
                return PICK;
            } else {
                return goToClosestStackWithMoreThanExpected();
            }
        }
    }

    /**
     * Calculates expected boxes for current boxes in each stack
     */
    private void calculateExpectedBoxes() {
        int totalBoxQty = IntStream.of(boxes).sum() + claw.getHasBox();
        int initialBoxPerStack = totalBoxQty / boxes.length;
        int boxRemainder = totalBoxQty - (initialBoxPerStack * boxes.length);

        if (initialBoxPerStack == 0 && boxRemainder == 0) {
            boxRemainder = 1;
        }

        for (int i = 0; i < expectedBoxes.length; i++) {
            expectedBoxes[i] = initialBoxPerStack;

            if (boxRemainder > 0) {
                expectedBoxes[i]++;
                boxRemainder--;
            }
        }
    }

    /**
     * Calculates next move by going to closest stack with less boxes than expected
     *
     * @return {@link ClawCommand}
     */
    private ClawCommand goToClosestStackWithLessThanExpected() {
        int rightPointer = claw.getPosition();
        int leftPointer = claw.getPosition();

        while ((boxes[rightPointer] >= expectedBoxes[rightPointer] && boxes[leftPointer] >= expectedBoxes[leftPointer])) {
            if (rightPointer != boxes.length - 1) {
                rightPointer++;
            }
            if (leftPointer != 0) {
                leftPointer--;
            }
        }

        if (boxes[rightPointer] < expectedBoxes[rightPointer])
            return RIGHT;
        else
            return LEFT;

    }

    /**
     * Calculates next move by going to closest stack with more boxes than expected
     *
     * @return {@link ClawCommand}
     */
    private ClawCommand goToClosestStackWithMoreThanExpected() {
        int rightPointer = claw.getPosition();
        int leftPointer = claw.getPosition();

        while ((boxes[rightPointer] <= expectedBoxes[rightPointer] && boxes[leftPointer] <= expectedBoxes[leftPointer])) {
            if (rightPointer != boxes.length - 1) {
                rightPointer++;
            }
            if (leftPointer != 0) {
                leftPointer--;
            }
        }

        if (boxes[rightPointer] > expectedBoxes[rightPointer])
            return RIGHT;
        else
            return LEFT;
    }
}
