Git Control System - Full Implementation
This project is a JavaFX-based Git system designed to simulate basic Git operations such as commit, merge, view commit history, and ignore files. The system integrates a GUI to make it easy for users to interact with Git-like features, including the ability to execute commands like git commit.

Features
Core Git Functionalities
Commit Changes:

Save the current state of tracked files with a descriptive message.
Automatically handles file hashes and metadata.
View Commit History:

Retrieve a list of all commits for the current branch.
Displays the commit messages, hashes, and parent relationships.
Merge Commits:

Merge changes between branches or commits, handling conflicts when necessary.
Applies merged changes to the working directory.
Git Ignore:

Exclude specified files or directories from being tracked or committed.
Access Commit Files:

Retrieve files associated with any commit.
GUI Features
Text Command Execution:
Type commands like git commit or git merge in a text field and execute them by pressing Enter.
Real-Time Feedback:
Displays success or error messages in a dialog box or console output.
Interactive Commit Input:
Enter commit messages through a text field for seamless interaction.
Prerequisites
Ensure the following tools and libraries are set up:

Java Development Kit (JDK):
Version 11 or later.
JavaFX SDK:
Download and configure JavaFX for GUI support.
File Structure:
.dotgit directory to simulate Git's internal storage, with subdirectories:
objects/ for file blobs.
refs/heads/ for branch references.
commits/ for commit metadata.
How to Run
Set Up Project:

Clone the repository and navigate to the project directory.
Ensure all Java and JavaFX dependencies are resolved.
Run the Application:

Use your IDE or terminal to execute the GitCommitUI class.
Command Examples:

git commit: Opens a dialog for entering commit messages.
git history: Displays the commit history of the current branch.
git merge <branch>: Merges the specified branch into the current branch.
File Structure
plaintext
Copy code
.dotgit/
  ├── objects/          # Stores file contents hashed by SHA-256
  ├── refs/heads/       # Tracks branches and their latest commit hashes
  ├── commits/          # Metadata for each commit
.gitignore              # File to specify ignored paths
Example Usage
Commiting Changes
Modify files in your working directory.
Enter git commit in the GUI text field.
Enter the commit message in the dialog box and confirm.
Viewing History
Type git history in the GUI text field.
A window will display all commit messages and their hashes.
Merging Branches
Type git merge <branch-name> in the GUI text field.
Conflicts (if any) will be resolved interactively.
Code Highlights
Commit Logic
Handles creating a commit:

java
Copy code
public static void commit(String message) {
    String commitHash = generateCommitHash(message);
    saveCommit(commitHash, message);
}
View History
Displays commit history for the current branch:

java
Copy code
public static List<String> getCommitHistory() {
    List<String> history = new ArrayList<>();
    String currentCommit = getCurrentBranchCommit();
    while (currentCommit != null) {
        history.add(getCommitMessage(currentCommit));
        currentCommit = getParentCommit(currentCommit);
    }
    return history;
}
Merge Logic
Merges commits and handles conflicts:

java
Copy code
public static void merge(String sourceBranch) {
    Map<String, String> mergedChanges = resolveChanges(sourceBranch, getCurrentBranch());
    applyMergedChanges(mergedChanges);
}
Future Enhancements
Full Command Line Interface:
Support advanced commands like git log, git checkout, and git reset.
Conflict Resolution GUI:
Provide a visual interface for resolving merge conflicts.
Branching System:
Enable branch creation, switching, and deletion.
Contributing
Contributions are welcome! Feel free to fork the repository and submit pull requests. Issues and feature requests can be submitted via the GitHub issues page.

License
This project is licensed under the MIT License.
