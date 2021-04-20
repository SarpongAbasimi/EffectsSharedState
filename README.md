# `Shared States in FP`

### Stack
- Cats
- Cats-effect

### This was inspired by the TypeLevel
`Shared State in Functional Programming`

### How to run the application

- `Clone` the repo 
- Open it in `intelliJ`
- `Run` or `sbt run`

```
We have a program that runs three computations 
at the same time and updates the internal state to keep 
track of the tasks that have been completed. 
When all the tasks are completed 
we request the final state and print it out.

You should get an output similar to the following one:

Extracting person 2
Extracting person 3
Extracting person 1

Going to update the applicationRef
ref update completed for 1
Going to update the applicationRef
ref update completed for 2
Going to update the applicationRef
ref update completed for 3

List(Ben, Sam, chris)
```