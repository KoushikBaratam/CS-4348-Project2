# 2026-04-11 01:02

## Thoughts So Far
I reviewed the bank simulation project requirements. The program needs multiple threads, shared resources controlled by semaphores, and logging of all teller and customer actions. I wanted to begin by setting up the repository and the required Java files.

## Plan for This Session
- Create the repository
- Create the starter Java files
- Organize the project structure

## Session Reflection
I created the initial project files and set up the base repository structure for the assignment.


# 2026-04-11 01:21

## Thoughts So Far
After creating the initial files, I wanted to keep the repository clean by ignoring generated files and using a development log to track progress.

## Plan for This Session
- Create `.gitignore`
- Ignore compiled `.class` files
- Add `DEVLOG.md`

## Session Reflection
I added a `.gitignore` file and started the development log to document progress during implementation.


# 2026-04-12 22:49

## Thoughts So Far
The next step was to define the core structure of the simulation. Before writing full logic, I wanted to declare all shared variables, semaphores, and thread arrays in each file.

## Plan for This Session
- Declare variables in all classes
- Add comments for each code section
- Prepare the structure for future implementation

## Session Reflection
I created the project structure and declared the shared variables needed for the bank simulation.


# 2026-04-13 01:50

## Thoughts So Far
With the structure ready, I moved to the main driver program. The simulation should create the bank object, launch teller threads, launch customer threads, and wait for all threads to complete.

## Plan for This Session
- Implement `main()`
- Start teller threads
- Start customer threads
- Join threads after completion

## Session Reflection
I implemented the thread creation and startup logic in the main program.


# 2026-04-13 02:44

## Thoughts So Far
Now that the threads could start, I focused on teller behavior. Tellers need to announce readiness, wait for customers, and become available through a shared queue.

## Plan for This Session
- Implement teller thread loop
- Add ready state logging
- Add customer assignment waiting logic

## Session Reflection
I implemented teller readiness and customer assignment behavior using semaphores and the shared queue.


# 2026-04-13 19:50

## Thoughts So Far
The next part of the project was customer behavior. Customers must randomly choose a transaction, arrive after a delay, enter the bank, wait in line, and choose a teller when one becomes available.

## Plan for This Session
- Implement customer thread behavior
- Add random transaction selection
- Add teller selection logic
- Add customer exit flow

## Session Reflection
I implemented customer arrival behavior and teller selection behavior.


# 2026-04-13 23:15

## Thoughts So Far
With customer and teller communication working, I needed to implement the actual transactions. Withdrawals require manager approval, while both transaction types require access to the safe.

## Plan for This Session
- Add manager synchronization
- Add safe synchronization
- Add transaction delays
- Complete deposit and withdrawal flow

## Session Reflection
I implemented deposit and withdrawal transaction processing with semaphore protection for shared resources.


# 2026-04-14 20:12

## Thoughts So Far
After testing the simulation, I wanted cleaner output and a permanent record of execution. Logging to the console alone was not enough, so I added file logging support.

## Plan for This Session
- Create output log file
- Write logs to console and file
- Keep logging synchronized

## Session Reflection
I implemented an automatic logging system that writes simulation output to both the console and `bank_log.txt`.


# 2026-04-14 20:24

## Thoughts So Far
During testing I found that a key synchronization method in the teller class was missing. Without it, customer assignment was incomplete and teller threads could not receive work correctly.

## Plan for This Session
- Fix customer assignment logic
- Store assigned customer and transaction
- Signal teller thread correctly

## Session Reflection
I added the missing `assignCustomer()` method to pass customer data and wake the teller thread.


# 2026-04-14 20:29

## Thoughts So Far
After final testing, I wanted to include the generated output file in the repository as evidence of a successful run.

## Plan for This Session
- Run full simulation
- Generate sample output file
- Add final output to repository

## Session Reflection
I added `bank_log.txt` containing simulation output from a completed program run.