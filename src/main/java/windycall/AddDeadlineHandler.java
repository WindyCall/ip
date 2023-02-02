package windycall;

public class AddDeadlineHandler extends AddTaskHandler {

    @Override
    public void handle(String userCommand) {

    }

    public static Task handleAddDeadline(String message) throws WindyCallException {
        int idx = message.indexOf("/by");
        if (message.length() == 8 || message.substring(8).trim().isEmpty()
                || (idx != -1 && message.substring(8, idx).trim().isEmpty())) {
            throw new WindyCallException("☹ OOPS!!! The description of a deadline cannot be empty!");
        }
        if (idx == -1) {
            throw new WindyCallException("☹ OOPS!!! You haven't specify the deadline for the Task");
        }
        if (message.length() == idx + 3 || message.substring(idx + 3).trim().isEmpty()) {
            throw new WindyCallException("☹ OOPS!!! You haven't specify the deadline for the Task");
        }
        String description = message.substring(9, idx - 1);
//        System.out.println("Got it. I've added this Deadline task:");
        String deadline = message.substring(idx + 4);
        return new Deadline(description, false, deadline);
    }
}
