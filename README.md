# Employee ID Search — Algorithm Battle

**Course assignment:** Compare Binary Search Tree (BST), AVL Tree and Hashing using Linear Probing for an Employee ID Search application.

## Problem
An organization needs to store employee records and find an employee quickly using a unique Employee ID. The same dataset is loaded into all three data structures so their search and insertion behaviour can be compared fairly.

## Project structure
- `src/Employee.java` — employee record model
- `src/SearchResult.java` — search result and comparison count
- `src/BST.java` — BST insertion, search, height and inorder traversal
- `src/AVL.java` — AVL insertion with LL, RR, LR and RL rotations, search, height and inorder traversal
- `src/LinearProbingHash.java` — hashing with Linear Probing and collision count
- `src/Main.java` — loads the CSV and runs the experiment
- `data/employees.csv` — 40 unique employee records
- `docs/` — experiment and viva guidance

## Requirements
Install Java JDK 8 or later.

## Compile and run
Run these commands from the repository root:

```bash
javac src/*.java
java -cp src Main
```

If running from another folder, pass the CSV path:

```bash
java -cp src Main path/to/employees.csv
```

Enter:
1. An ID that exists in the CSV for successful search.
2. An ID that does not exist for unsuccessful search (for example, `9999`).
3. A new unique ID for the insertion test (for example, `2001`).

## Measurements
The program reports:
- Search comparisons for successful and unsuccessful searches
- BST height and AVL height
- Hash-table collisions during insertion using Linear Probing
- Updated heights/collisions after inserting a new employee
- Inorder traversal of BST and AVL (ascending Employee ID order)

**Important:** Run the program and record the values it prints. Do not submit made-up experimental observations. If you change the dataset, use the same CSV for all three structures.

## Technical comparison
- BST: average search is O(log n) when reasonably balanced; worst case O(n) if it becomes skewed.
- AVL: search and insertion are O(log n), because rotations maintain balance.
- Hashing: average search is O(1) with a suitable table size and low load factor; worst case O(n) when collisions/clustering occur.

Your final winner should be selected from your own measured results and application needs, not from theoretical complexity alone.
