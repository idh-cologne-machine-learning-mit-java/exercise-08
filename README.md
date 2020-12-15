# Exercise 7: CI and Library Functions


This exercise has two goals: Getting the GitHub action to work and implementing 
some core functions we will need for the real algorithm implementation.

## Optional: Continue your own repository

I created a new repository called exercise 7, but in a real world, we would 
continue the implementation with the old one. You can also do that if you want 
to. The exercise-07 repository is a copy of the exercise-06 repository, based
on the reference solution.

If you want to incorporate the reference solution, and the changes for 
exercise 7 into your branch based on ex 6, you can use a merge command across
repostories: If you are in your ex 6 repository, running 
`git merge https://github.com/idh-cologne-machine-learning-mit-java/exercise-07.git` 
should do the trick. It is, however, quite likely that you need to deal with
merge conflicts. 


## Step 1
Please `clone` the repository `https://github.com/idh-cologne-machine-learning-mit-java/exercise-07`.

Create a new branch, using your UzK username.

## Step 2
Setup the GitHub action. You will need three steps in your action:
checkout (as we have already done during the exercise), set up Java and run maven. 

For the java setup, you can use the action `actions/setup-java@v1`, which takes 
the java version as a parameter `java-version`. 

The maven step doesn't use a predefined action, but executes a command on a 
command line. This can be done with the parameter `run`, the value being the 
command. Use `mvn test` here.

You may find a copy-pastable solution if you start googling, but you learn more
if you do it by yourself. The description above, together with what we have 
discussed in class, contains everything you need to know.

## Step 3
Implement the function `Tree.isLeaf()`. Verify that it covers all relevant cases 
by running unit tests. Commit and push to the server, verify that unit tests are
fulfilled there as well.

## Step 4
Implement the function `Classifier.entropy()`. Verify that it covers all relevant cases 
by running unit tests. Commit and push to the server, verify that unit tests are
fulfilled there as well.

## Step 5
Implement the function `Classifier.informationGain()`. Verify that it covers all relevant cases 
by running unit tests. Commit and push to the server, verify that unit tests are
fulfilled there as well.

