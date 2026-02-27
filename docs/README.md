# Atlas Task Navigator - User Guide

Welcome to **Atlas**, your mission-critical task management companion. Atlas helps you organize your daily objectives with precision and ease.

---

## Quick Start
1. Ensure you have **Java 17** or above installed.
2. Download the latest `atlas.jar` from our releases.
3. Open your terminal, navigate to the folder, and run:  
   `java -jar atlas.jar`
4. Use the command box to start navigating your tasks!

---

## Features

### 1. Manage To-dos: `todo`
Adds a basic task without any specific date/time.
* **Format:** `todo [description]`
* **Example:** `todo Read Harry Potter`

### 2. Manage Deadlines: `deadline`
Adds a task that needs to be completed by a specific date.
* **Format:** `deadline [description] /by [YYYY-MM-DD]`
* **Example:** `deadline Return book /by 2026-03-01`

### 3. Manage Events: `event`
Adds a task that spans a specific time period.
* **Format:** `event [description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]`
* **Example:** `event Career Fair /from 2026-03-10 /to 2026-03-12`

### 4. Organize the List: `sort`
Alphabetizes your entire task list based on the description.
* **Format:** `sort`

### 5. Locate Tasks: `find`
Filters your list to show tasks containing a specific keyword.
* **Format:** `find [keyword]`
* **Example:** `find book`

### 6. Mark as Complete: `mark`
Marks a task as finished.
* **Format:** `mark [index]`
* **Example:** `mark 1`

### 7. Remove Tasks: `delete`
Deletes a task permanently from the records.
* **Format:** `delete [index]`
* **Example:** `delete 2`

### 8. Exit System: `bye` / `exit`
Safely saves your data and closes the application.

---

## Data Persistence
Atlas automatically saves your data in `data/atlas.txt`. This file is updated after every command, so your progress is never lost, even if the system crashes.

## FAQ
**Q: What happens if I enter a wrong date format?** A: Atlas will catch the error and ask you to use the `YYYY-MM-DD` format.

**Q: Can I add duplicate tasks?** A: No, Atlas prevents duplicate entries to keep your navigation records clean.