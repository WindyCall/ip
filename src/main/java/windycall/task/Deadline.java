package windycall.task;

import windycall.parser.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    private LocalDate deadline;


    public Deadline(String description, boolean status, String deadline) {
        super(description, status);
        this.deadline = Parser.processDate(deadline);
    }

    public Deadline(String description, boolean status, String deadline, String tag) {
        super(description, status, tag);
        this.deadline = Parser.processDate(deadline);
    }

    @Override
    public String getTaskTypeIcon() {
        return "D";
    }


    @Override
    public String getFileFormat() {
        return "D | " + getStatusIcon() + " | " + tag + " | " + description + " | " + deadline + "\n";
    }

    private String changeDateTimeFormat() {
        return deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    private String getDeadline() {
        return " (by: " + changeDateTimeFormat() + ")";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getCurrentDescription() + this.getDeadline();
    }
}
