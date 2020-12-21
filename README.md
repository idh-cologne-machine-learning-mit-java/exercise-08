# Exercise 8: Optimizations and Extensions


This exercise has the goal of giving you something to do over the break :)
Based on the training implementation we have done in class, we add various improvements.

## Optional: Continue your own repository

I created a new repository called exercise 8, but in a real world, we would 
continue the implementation with the old one. You can also do that if you want 
to. The exercise-08 repository is a copy of the exercise-07 repository, based
on the reference solution.

If you want to incorporate the reference solution, and the changes for 
exercise 8 into your branch based on ex 6, you can use a merge command across
repostories: If you are in your ex 6 repository, running 
`git merge https://github.com/idh-cologne-machine-learning-mit-java/exercise-08.git` 
should do the trick. It is, however, quite likely that you need to deal with
merge conflicts. 


## Step 1
Please `clone` the repository `https://github.com/idh-cologne-machine-learning-mit-java/exercise-08`.

Create a new branch, using your UzK username.

## Step 2
The `Classifier` class has three new (static) functions: `subsets(Instances, int)`, 
`getMajority(int[])`, and `countClasses(Instances)`. Implement unit tests for the 
three, and fix all bugs that you uncover :)

## Step 3 (optional)

One of the additions in the C4.5 algorithm (compared to its predecessor ID3) 
is the ability to handle numeric attributes. Add this extension to our current +
implementation of the decision tree.

## Step 4 (optional)
Our current implementation actually creates sub set data sets when it builds 
the tree (by creating new `Instances` objects). This is a lot of overhead, 
because most of the copied data is not needed later on. An alternative 
implementation would be to store index numbers of the instances in the subset.
To implement this, you will need to change several methods, including the 
recursive `train()` method, because you will need to pass down an int array 
through the recursion. Do it!

## Step 5
As always, commit and push your code.