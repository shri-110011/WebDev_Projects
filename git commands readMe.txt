
Useful git commands:

#The git init command creates a new Git repository. It can be used to convert an existing, unversioned project to a Git repository or initialize a new, #empty repository.
git init

#To check the remote repo
git remote -v

#To add the remote repo
git remote add origin https://github.com/shri-110011/Java-Projects.git

#To update the remote origin url:
git remote set-url origin <NEW_REMOTE_URL>

#To check the files in the git staging area
git status

#To add the files to the staging area
git add .

#To commit the changes to the local repo
git commit -m "Some message about the code changes"

#To commit the changes to the local repo with multiline comments
git commit
This will open a vim editor and there add the multiline comment.

#To edit the commit message for the most recent un-pushed commit
1. git commit --amend
2. This will open a vim editor and there you can edit the multiline comment and save it.

# To change the message of the most recently pushed commit
1. git commit --amend
2. This will open a vim editor and there you can edit the multiline comment and save it.
3. Use the push --force-with-lease command to force push over the old commit.
git push --force-with-lease origin EXAMPLE-BRANCH

--force-with-lease is a safer option that will not overwrite any work on the remote branch if more commits were added to the remote branch (by another team-member or coworker or what have you). It ensures you do not overwrite someone else's work by force pushing.

References:
https://docs.github.com/en/pull-requests/committing-changes-to-your-project/creating-and-editing-commits/changing-a-commit-message
https://stackoverflow.com/questions/52823692/git-push-force-with-lease-vs-force


#To edit the commit messages of multiple commits
1. git rebase -i HEAD~n, this would open the vim editor containing the last n commit from HEAD.
2. Change the word 'pick' to 'reword' corresponding to the commits whose messages you want to edit and save and close the vim editor.
3. In each resulting commit file, type the new commit message, save the file, and close it.
4. When you're ready to push your changes to GitHub, use the push --force command to force push over the old commit.
git push --force origin EXAMPLE-BRANCH


#To push the code to the remote branch
git push origin <branch_name>
While using the above command the <branch_name> would be considered the local branch name from where the changes are to be pushed to the remote branch and
<branch_name> would also be considered the name of the remote branch where we want our code to be pushed to.

#To push code from a specific local branch to a specific remote branch
git push origin <local_branch_name>:<remote_branch_name>

#Note by default "git init" will name the branch in our local repo as "master"
#To change that branch name in our local repo use:
git branch -m <new_branch_name>
or
git branch -m <old_branch_name> <new_branch_name>

#To checkout to an existing branch in the local
git checkout <existing_branch_name>

#To create a new branch and checkout to it
git checkout -b <new_branch_name>
#The -b option is a convenience flag that tells Git to run git branch before running git checkout ＜new-branch>

#To create a new branch
git branch <new_branch_name>

#To delete an existing branch in the local. This command deletes the specified branch but only if it has been fully merged into its upstream branch or the #current branch. If the branch has unmerged changes, Git will refuse to delete it and will prompt you with an error message. This is a safer option as it #helps prevent accidental deletion of work.
git branch --delete <existing_branch_name>

#Before deleting the branch first checkout to a different branch.

git branch -D <existing_branch_name>: #This command forcefully deletes the specified branch branch1, regardless of whether it has been merged or not. It's #useful when you want to delete a branch regardless of its merge status, including branches with unmerged changes. However, it's riskier because it can lead #to loss of commits if you accidentally delete a branch with unmerged changes.

#To get information about past commits
git log [-number_of_past_commits]
number_of_past_commits is optional and it represents a number.


-------------------------------------------------------------------

git branch -vv: Use this command to know which remote branch a local branch is tracking.


-------------------------------------------------------------------

Difference b/w git checkout -b branch1 branch2 and git checkout -b branch1:
git checkout -b branch1 branch2: This command creates a new branch named branch1 starting from the commit that branch2 is currently pointing to. In other words, it sets branch1 to be at the same commit as branch2.

git checkout -b branch1: This command creates a new branch named branch1 starting from the commit that your current branch (the one you are currently on) is pointing to. It's essentially shorthand for creating a new branch at your current location.


-------------------------------------------------------------------

If I have 2 branches, branch1 and branch2 and I merge brance2 on branch1. I made the last commit on branch1 at 10:00am and on brach2 at 9:00am on the same day. Will the branch2  commit get added first then the branch1 commit during merging?

When you merge branch2 into branch1, the order of the commits is determined by their parent-child relationships in the Git history, not by the timestamp of the commits.

If the last commit on branch1 was made at 10:00am and the last commit on branch2 was made at 9:00am on the same day, and you merge branch2 into branch1, the commit from branch2 will be merged into branch1 as a child of the commit from branch1.

However, the order of the commits in the log doesn't necessarily represent their parent-child relationship in the Git history graph.

By default, git log displays commits in reverse chronological order based on their commit timestamp. If the commit from branch2 has an earlier timestamp compared to the commit from branch1, it will appear last in the log output.

-------------------------------------------------------------------

git merge new-branch --allow-unrelated-histories: Use this command to merge two branches that do not have a common ancestor.

-------------------------------------------------------------------

Git workflow example:

git init

git remote -v
git remote add origin https://github.com/siddharth-110011/React-Todo-App.git
git remote -v

git status
git add .
git commit -m "Initial Code commit"

git branch
git branch -m main
git branch

git log

git fetch origin
git branch -r

git merge origin/main --allow-unrelated-histories

-------------------------------------------------------------------

Steps to create a single folder having multiple java projects in Eclipse:

1. Create a folder in your workspace say "Test".
2. Create a java project say "TestProject" in eclipse by choosing the default location which will be the workspace.
3. Move the "TestProject" into the folder "Test".
4. Refresh the Project Explorer.
5. Now go to File >  Import... and select "Projects from Folder or Archive" option under the "General" section.
under the General section.
6. Click on Directory and choose the folder "Test" which is the one that we want to hold multiple java projects.
7. Check the Search for nested projects checkbox.
8. Click on Finish.
And you should now see the folder "Test" containing java projects in Project Explorer.

----------------------------------------------------------------------

Steps to add a specific remote branch to Eclipse:
1. git clone -b Learn-Advanced-Java-Features --single-branch https://github.com/shri-110011/Java-Projects.git

2. Go to File > Open Projects from File System...

3. Click on the Directory... and select the parent directory of the cloned project.

4. Again go to File > Open Projects from File System... > click on the Directory... and select the nested Project say Project1 one at a time and click Finish.

5. Right click on Project1 in Project Explorer > Properties > Project Natures > Click on Add... > Select  Java > Apply and Close.

6. Right click on Project1 in Project Explorer > Build Path > Configure Build Path... > Source tab > Apply and Close.

7. Now the nested Java project, Project1 has been configured as a Java Project and the Standard Java libraries have been added to classpath.

8. Repeat step 4 - step 7 for each nested Java project in the parent directory of the cloned project.
