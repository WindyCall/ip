package windycall;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {

    private LocalDate from;
    private LocalDate to;


    public Event(String description, boolean status, String from, String to) {
        super(description, status);
        this.from = processDate(from);
        this.to = processDate(to);
    }

    /**
     * Return formatted LocalDate by translating user input String deadline
     *
     * @param deadline String representation of date input by user
     * @return formatted LocalDate
     */
    private LocalDate processDate(String deadline) {
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

    @Override
    public String getTaskTypeIcon() {
        return "E";
    }


    @Override
    public String getFileFormat() {
        return "E | " + getStatusIcon() + " | " + description + " | " + from + " | " + to + "\n";
    }
    private String getDeadlineFormat(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    private String getEventInterval() {
        return " (from: " + getDeadlineFormat(from) + " to: " + getDeadlineFormat(to) + ")";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getCurrentDescription() + this.getEventInterval();
    }
}
