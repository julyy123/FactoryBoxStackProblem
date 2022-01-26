package com.juliansanchez.exercise;

import com.juliansanchez.enums.ClawCommand;
import com.juliansanchez.factory.Factory;
import lombok.NoArgsConstructor;

import java.util.stream.IntStream;

@NoArgsConstructor
/**
 * Factory box stack problem exercise class
 */
public class FactoryBoxStackProblemExercise {
    private static final int MINIMUM_STACKS_AMOUNT = 2;
    private static final int MAXIMUM_STACKS_AMOUNT = 8;
    private static final int MINIMUM_BOXES_AMOUNT = 1;
    private static final int MAXIMUM_BOXES_AMOUNT = 16;

    /**
     * Solves box stacking problem
     *
     * @param boxes     {@link int[]}
     * @param clawPos   {@link int}
     * @param boxInClaw {@link int}
     * @return {@link ClawCommand}
     */
    public ClawCommand solve(int[] boxes, int clawPos, int boxInClaw) {
        // NOTE: I think is better to use a boolean instead of int for boxInClaw but i choose int because the exercise required
        if (boxes.length >= MINIMUM_STACKS_AMOUNT && boxes.length <= MAXIMUM_STACKS_AMOUNT
                && amountOfBoxesAreRight(boxes, boxInClaw) && (boxInClaw == 1 || boxInClaw == 0)) {
            Factory factory = new Factory(boxes, clawPos, boxInClaw);

            return factory.solve();
        }

        return ClawCommand.INVALID_PARAMETERS;
    }

    /**
     * Check if current parameters are right
     *
     * @param boxes     {@link int[]}
     * @param boxInClaw {@link int}
     * @return {@link boolean}
     */
    private boolean amountOfBoxesAreRight(int[] boxes, int boxInClaw) {
        int totalBoxQty = IntStream.of(boxes).sum() + boxInClaw;

        return totalBoxQty >= MINIMUM_BOXES_AMOUNT && totalBoxQty <= MAXIMUM_BOXES_AMOUNT;
    }
}
