package main.java;

class Task {
    private String taskName;
    private long dueTime;
    private String description;

    public Task(String taskName, long dueTime,  String description) {
        this.taskName = taskName;
        this.dueTime = dueTime;
        this.description = description;
    }

    public String getTaskName() {
        return taskName;
    }

    public long getDueTime() {
        return dueTime;
    }

    public String getDescription() {
        return description;
    }

    public void updateTaskName(String taskName) {
        this.taskName = taskName;
        System.out.println("Successfully Changed Task Name!");
    }

    public void updateDueTime(long dueTime) {
        this.dueTime = dueTime;
        System.out.println("Successfully Changed Task Due Time!");
    }

    public void updateDescription(String description) {
        this.description = description;
        System.out.println("Successfully Changed Task Description!");
    }
}
