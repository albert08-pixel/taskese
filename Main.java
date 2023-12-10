package main.java;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    public static void main(String args[]) throws ParseException {

        boolean run = true;
        Date date = new Date();
        while (run) {
            System.out.println("Welcome to my tasks!\n---");
            System.out.println("Current Time: " + UNIXtoHuman(date.getTime() * 1000L));

            if (tasks.size() != 0) {
                System.out.println("**TASKS**");
                System.out.printf("%-25s%-25s%-25s%n", "Task", "Due Date", "Time Untill Due?");
                System.out.println("------------------------------------------------------------------------------------");
                for (Task task : tasks) {
                    System.out.printf("%-25s%-25s%-25s%n", task.getTaskName(), UNIXtoHuman(task.getDueTime()), getTimeUntillDue(task.getDueTime()));
                }
            } else {
                System.out.println("NO TASKS AVALIABLE");
            }
            System.out.println("---");

            System.out.println("1. Add Task");
            System.out.println("2. View a Task");
            System.out.println("3. Modify a Task");
            System.out.println("4. Refresh Time");
            System.out.println("5. Quit");
            System.out.println("Select an option: ");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    addTask();
                    break;
                case "2":
                    System.out.println("Enter the task name to view: ");
                    viewTask(scanner.nextLine());
                    break;
                case "3":
                    System.out.println("Enter the task name to modify: ");
                    modifyTask(scanner.nextLine());
                    break;
                case "4":
                    System.out.println("Current time has been successfully refreshed!");
                    break;
                case "5":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
    }

    public static String UNIXtoHuman(long UNIXTime) {
        SimpleDateFormat sdf =  new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        return sdf.format(UNIXTime / 1000L);
    }

    public static long HumantoUNIX(String HumanTime) throws ParseException {
        SimpleDateFormat sdf =  new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        long epoch = sdf.parse(HumanTime).getTime();
        return epoch / 1000L;
    }

    public static String getTimeUntillDue(long dueTime) {
        Date date = new Date();
        long currentTime = date.getTime() / 1000L;
        if (dueTime < currentTime) {
            return "Overdue";
        } else {
            int hours = (int) (dueTime - currentTime) / 3600;
            int minutes = (int) ((dueTime - currentTime) % 3600) / 60;
            int seconds = (int) (dueTime - currentTime) % 60;
            return (hours + ":" + minutes + ":" + seconds);
        }
    }

    public static void addTask() throws ParseException {
        System.out.println("Task Name: ");
        String taskName = scanner.nextLine();
        System.out.println("Due Time(MM-DD-YYYY HH:MM:SS)");
        long taskTime = HumantoUNIX((scanner.nextLine()));
        System.out.println("Description of Task: ");
        tasks.add(new Task(taskName, taskTime, scanner.nextLine()));
    }

    public static void viewTask(String taskName) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getTaskName().equals(taskName)) {
                found = true;
                System.out.printf("%-25s%-25s%-25s%-25s%n", "Task", "Due Date", "Time Untill Due", "Description");
                System.out.println("--------------------------------------------------------------------------------------------------------");
                System.out.printf("%-25s%-25s%-25s%-25s%n", task.getTaskName(), UNIXtoHuman(task.getDueTime()), getTimeUntillDue(task.getDueTime()), task.getDescription());
            }
        }
        if (!found) {
            System.out.println("Task Not Found!");
        }
    }

    public static void modifyTask(String taskName) throws ParseException {
        Iterator<Task> iterator = tasks.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getTaskName().equals(taskName)) {
                found = true;
                System.out.printf("%-25s%-25s%-25s%-25s%n", "Task", "Due Date", "Time Untill Due", "Description");
                System.out.println("--------------------------------------------------------------------------------------------------------");
                System.out.printf("%-25s%-25s%-25s%-25s%n", task.getTaskName(), UNIXtoHuman(task.getDueTime()), getTimeUntillDue(task.getDueTime()), task.getDescription());
                boolean run = true;
                while (run) {
                    System.out.println("What do you want to edit?");
                    System.out.println("1. Task Name");
                    System.out.println("2. Change Task Due Time");
                    System.out.println("3. Change Task Description");
                    System.out.println("4. Remove this Task");
                    System.out.println("5. Quit");
                    String option = scanner.nextLine();

                    switch (option) {
                        case "1":
                            System.out.println("Enter New Task Name: ");
                            task.updateTaskName(scanner.nextLine());
                            break;
                        case "2":
                            System.out.println("Enter New Due Time(MM-DD-YYYY HH:MM:SS):");
                            task.updateDueTime(HumantoUNIX(scanner.nextLine()));
                            break;
                        case "3":
                            System.out.println("Enter New Task Description:");
                            task.updateDescription(scanner.nextLine());
                            break;
                        case "4":
                            iterator.remove();
                            System.out.println("Task Successfully Removed!");
                        case "5":
                            run = false;
                            break;
                        default:
                            System.out.println("Invalid option, please try again.");
                    }
                }
            }
        }
        if (!found) {
            System.out.println("Task Not Found!");
        }
    }

}