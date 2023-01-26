package windycall;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task{

    private LocalDate deadline;


    public Deadline(String description, boolean status, String deadline) {
        super(description, status);
        this.deadline = processDateTime(deadline);
    }

    @Override
    public String getTaskTypeIcon() {
        return "D";
    }


    @Override
    public String fileFormat() {
        return "D | " + getStatusIcon() + " | " + description + " | " + deadline + "\n";
    }
    public LocalDate processDateTime(String deadline) {
        // now assume date is in the form
        // dd/mm/yy or yy-mm-dd
        // in later versions, more form of date and time will be resolved
        LocalDate dateTime;
        try {
            dateTime = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            String[] parts = deadline.split("/");
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            dateTime = LocalDate.of(year, month, day);
        }
        return dateTime;
    }

    private String deadlineFormat() {
        return deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    private String getDeadline() {
        return " (by: " + deadlineFormat() + ")";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getCurrentDescription() + this.getDeadline();
    }
}
