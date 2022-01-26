# FactoryBoxStackProblem

### Notes: I assume that boxes and clawPos cannot have negative numbers. <br />
### Also i added 2 extra commands:
### &emsp;• NOTHING: Return this command when the stacks are already arranged.
### &emsp;• INVALID_PARAMETERS: Return this command when the parameters are invalid. 

# Goal
Rearrange the boxes in the factory to form stacks of equal height.

![image](https://user-images.githubusercontent.com/31144812/151227784-735d772b-a8bf-4965-b394-a63da3984604.png)

# Rules <br />
You work in an automated factory. The factory uses a simple robotic arm to move boxes around. The arm is capable of picking a box from a stack, and placing it on another stack. All boxes are on one of a given number of stacks. Your objective is to rearrange the stacks in order to have an equal number of boxes on each stack. If this is not possible, any excess box must be stacked from left to right. Your code will periodically receive the current state of the arm and the number of boxes on each stack. In order to succeed, your function must return one command per turn up until the boxes are correctly positioned. <br /> <br />
The available commands are: <br /> <br />
RIGHT : the arm moves one stack to the right. <br />
LEFT : the arm moves one stack to the left. <br />
PICK : the arm grabs a box from the stack directly below it. <br />
PLACE : the arm places a box onto the stack directly below it. Warning, you may execute a maximum of 200 commands, but it is not necessary to minimize the amount of instructions. <br /> <br />
# Implementation <br />
Implement the function solve(clawPos, boxes, boxInClaw) that takes as arguments: an integer clawPos for the index of the stack the arm is currently above. boxes an array of integers for the size of each stack. The integer boxInClaw which will be equal to 1 if the arm is carrying a box, 0 otherwise. <br /> <br />
Victory Conditions <br />
All stacks have been smoothed from left to right. It should look like this for the example above. <br />

![image](https://user-images.githubusercontent.com/31144812/151227814-5c981890-f0c3-4f78-905e-6b0c072037ad.png)

Lose Conditions <br />
Your function returns an incorrect command. The stacks still aren't smoothed after 200 turns.
Constraints
2 ≤ number of stacks ≤ 8 <br />
1 ≤ number of boxes ≤ 16
