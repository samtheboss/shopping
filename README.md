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
