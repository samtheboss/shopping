# Git Control System

A JavaFX-based Git system that simulates basic Git operations like **commit**, **merge**, **view commit history**, and **ignore files**. This project includes an intuitive GUI that allows users to interact with Git-like features, including the ability to execute commands such as `git commit`.

---

## Features

### Core Git Functionalities
- **Commit Changes**  
  Save the current state of tracked files with a descriptive message, automatically handling file hashes and metadata.

- **View Commit History**  
  Retrieve a list of all commits for the current branch, displaying commit messages, hashes, and parent relationships.

- **Merge Commits**  
  Merge changes between branches or commits, with conflict handling if necessary. Applies merged changes to the working directory.

- **Git Ignore**  
  Exclude specified files or directories from being tracked or committed.

- **Access Commit Files**  
  Retrieve files associated with any commit for easy exploration.

### GUI Features
- **Text Command Execution**  
  Type commands like `git commit` , `git add .` `git init` in a text field and execute them by pressing Enter.

- **Real-Time Feedback**  
  Displays success or error messages in dialog boxes or console output.

- **Interactive Commit Input**  
  Input commit messages via a text field for seamless interaction.

---

## Prerequisites

Before running the application, ensure you have the following set up:

1. **Java Development Kit (JDK)**  
   Version 11 or later.
   
2. **JavaFX SDK**  
   Download and configure JavaFX to enable GUI support.

3. **File Structure**  
   Ensure the `.dotgit` directory is created in your project root, with the following subdirectories:
 
 

.dotgit/
  ├── objects/          # Stores file contents hashed by SHA-256
  ├── refs/heads/       # Tracks branches and their latest commit hashes
  ├── commits/          # Metadata for each commit
.gitignore              # File to specify ignored paths


## How to Run

1. **Set Up the Project**
- Clone this repository.
- Open the project in your favorite IDE (e.g., IntelliJ IDEA, Eclipse, or VSCode).
- Ensure all dependencies are configured, including JavaFX.

2. **Run the Application**
- Execute the `GitCommitUI` class.

3. **Using Commands**
- **Commit Changes**: Type `git commit` in the text field.  
- **View History**: Type `git history` in the text field.  
- **Merge Branches**: Type `git merge <branch-name>` in the text field.

---

## File Structure


---

## Example Usage

### Commit Changes
1. Modify files in your working directory.
2. Enter `git commit -m 'message'` in the GUI text field.
3. Enter `git add .` in the GUI text field.

### View Commit History
1. click on the commit button then  click on view history
2. A list of commit messages with their hashes will be displayed.

### Merge Branches
1. Type `git merge '<branch-name>'` in the GUI text field.
2. If conflicts arise, they will be logged for resolution.

---

## Code Highlights

### Commit Logic
Handles creating a commit:
```java
 public static void commit(String message) {
        String author = "test"; // Author of the commit
        try {
            String branchName = GitServices.getCurrentBranch(); // Get the current branch name
            if (branchName.isEmpty()) { // Check if the branch name is empty
                System.out.println("No active branch found. Please create or switch to a branch.");
                return; // Exit if no active branch
            }}
