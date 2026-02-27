# Miffy User Guide
Miffy is a schedule manager optimized for the Command Line Interface (CLI). It enables proficient typists to handle their daily planning effectively.
<img width="1046" height="452" alt="image" src="https://github.com/user-attachments/assets/24af8ff8-805c-49b4-b9e3-8bd69dd83867" />


# How To Set Up
1. **Install Java 17+:** Verify that your computer has Java `17` or a newer version installed. <br> 
   *Mac users:* Please follow the specific JDK installation guide [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
2. **Download the App:** Grab the latest `.jar` release file from [here](https://github.com/abigailtong/ip/releases).
3. **Set Up Your Directory:** Move the downloaded file into a dedicated new folder. (Note: Running the app for the first time will automatically generate a `data/WordsOfWisdom.txt` file in this directory to save your tasks).
4. **Launch Miffy:** Open your terminal, navigate to your new folder, and run the app with the following command: `java -jar <insert release-name>.jar`

# Features

## Adding a ToDo 
Adds a ToDo to the list.

Format: `todo TASK_NAME`

Examples:
* `todo buy groceries`
* `todo watch lecture recording`


```text
Let's add that to the words of wisdom:
 [T][ ] buy groceries
Now you have 1 tasks in the list.
____________________________________________________________
```

## Adding a Deadline
Adds a Deadline task to the list.

Format: `deadline TASK_NAME /by DEADLINE`

Examples:
* `deadline submit assignment /by Friday 2359`
* `deadline pay bills /by 2026-10-31 1200`


```text
Let's add that to the words of wisdom:
 [D][ ] submit assignment (by: Friday 2359)
Now you have 1 tasks in the list.
____________________________________________________________
```

## Adding an Event
Adds an Event task to the list.

Format: `event TASK_NAME /from START_TIME /to END_TIME`

Examples:
* `event team hackathon /from Saturday 9am /to Sunday 6pm`
* `event concert /from 2026-12-01 1900 /to 2026-12-01 2200`


```text
Let's add that to the words of wisdom:
 [E][ ] team hackathon (from: Saturday 9am to: Sunday 6pm)
Now you have 1 tasks in the list.
____________________________________________________________
```

## Listing all tasks
Displays all tasks currently in the list.

Format: `list`

Examples:
* `list`


```text
We are checking:
 1.[T][ ] buy groceries
 2.[D][ ] submit assignment (by: Friday 2359)
 3.[E][ ] team hackathon (from: Saturday 9am to: Sunday 6pm)
____________________________________________________________
```

## Marking a task as done or not done
Updates the completion status of a specified task in the list.

Format: 
* `mark INDEX`
* `unmark INDEX`

Examples:
* `mark 1`
* `unmark 1`

**Expected output for `mark`:**
```text
Congratulations on the miffy.task, it was such a success:
 [T][X] buy groceries
____________________________________________________________
```
**Expected output for `unmark`:**
```text
Retired the miffy.task:
 [T][ ] buy groceries
____________________________________________________________
```

## Locating tasks by name
Searches for tasks that match a specific keyword.

Format: `find KEYWORD`

Examples:
* `find groceries`
* `find assignment`


```text
Miffy found these tasks containing "groceries":
 1.[T][ ] buy groceries
____________________________________________________________
```

## Deleting a task
Removes a specified task from the list.

Format: `delete INDEX`

Examples:
* `delete 1`


```text
Let's remove this from the words of wisdom:
 [D][ ] submit assignment (by: Friday 2359)
Now you have 2 tasks in the list.
____________________________________________________________
```

## Exiting the program
Saves your data and closes the application.

Format: `bye`

Examples:
* `bye`

```text
As always sir, a great pleasure watching you work!
____________________________________________________________
```
