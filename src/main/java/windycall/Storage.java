package windycall;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and handle tasks change
 * by overwriting contents in tasks back to file
 */
public class Storage {

    public Storage() {

    }

    /**
     * Loads data inside data file to current tasks.
     * Handles non-existence of files or related directory
     *
     * @param tasks an ArrayList that will be loaded into
     */
    public void handleLoad(List<Task> tasks) {
        //check if "./data" directory exist
        File file = new File("./data");
        File taskFile = new File("./data/WindyCall.txt");;
        // if data file exists
        if (!file.isDirectory()) {
            file.mkdir();
        }
        try {
            if (taskFile.createNewFile()) {
                Ui.space();
                System.out.println("File created: " + taskFile.getName());
            }
        } catch (IOException e) {
            System.out.println("An error occurred. Can not load your tasks");
            e.printStackTrace();
        }

        // load data in task_file into this.tasks
        try {
            Scanner myReader = new Scanner(taskFile);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.charAt(0) == 'T') {
                    String description = data.substring(8);
                    Task task = new Todo(description, data.charAt(4) == 'X');
                    tasks.add(task);
                } else if (data.charAt(0) == 'D') {
                    int idx = data.indexOf("|", 8);
                    String description = data.substring(8, idx - 1);
                    String deadline = data.substring(idx + 2);
                    Task task = new Deadline(description, data.charAt(4) == 'X', deadline);
                    tasks.add(task);
                } else {
                    int idx1 = data.indexOf("|", 8);
                    int idx2 = data.indexOf("|", idx1 + 1);
                    String description = data.substring(8, idx1 - 1);
                    String from = data.substring(idx1 + 2, idx2 - 1);
                    String to = data.substring(idx2 + 2);
                    Task task = new Event(description, data.charAt(4) == 'X', from, to);
                    tasks.add(task);
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    /**
     * Handles tasks change by overwriting contents in tasks
     * back to data file so that change is saved each time tasks get modified
     *
     * @param tasks tasks that has been changed to do users' operation on tasks
     */
    public void handleTaskChange(List<Task> tasks) {
        // overwrite tasks back to data file to record the change
        try {
            FileWriter myWriter = new FileWriter("./data/WindyCall.txt");
            for (Task task : tasks) {
                myWriter.write(task.getFileFormat());
            }
            myWriter.close();
            Ui.space();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
