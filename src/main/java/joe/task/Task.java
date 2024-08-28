package joe.task;

public class Task {

    private String task;
    private boolean isDone = false;

    public Task(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return String.format("%s %s", status, this.task);
    }

    /**
     * Prints the task with its status.
     */
    public void printTask() {
        String status = isDone ? "[X]" : "[ ]";
        System.out.printf("%s %s", status, this.task);
    }

    /**
     * Toggles the done status of the task.
     */
    public void toggleDone() {
        this.isDone = !this.isDone;
    }

    /**
     * @return The done status of the task.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * @return The task.
     */
    public String getTask() {
        return this.task;
    }

    /**
     * @return A string representation of the task for saving.
     */
    public String toSaveString() {
        return String.format("T|%d|%s", isDone() ? 1 : 0, getTask());
    }
}
