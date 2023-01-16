public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public abstract String getTaskTypeIcon();

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }


    public String getCurrentDescription() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}