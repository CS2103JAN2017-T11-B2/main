package guitests.guihandles;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import guitests.GuiRobot;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.CompleteCommand;
import seedu.address.model.tag.UniqueTagList;
import seedu.address.model.todo.ReadOnlyTodo;

/**
 * Provides a handle to a todo card in the todo list panel.
 */
public class TodoCardHandle extends GuiHandle {
    private static final String NAME_FIELD_ID = "#name";
    private static final String TAGS_FIELD_ID = "#tags";
    private static final String STARTTIME_FIELD_ID = "#start";
    private static final String ENDTIME_FIELD_ID = "#end";
    private static final String COMPLETETIME_FIELD_ID = "#complete";

    private Node node;

    public TodoCardHandle(GuiRobot guiRobot, Stage primaryStage, Node node) {
        super(guiRobot, primaryStage, null);
        this.node = node;
    }

    protected String getTextFromLabel(String fieldId) {
        return getTextFromLabel(fieldId, node);
    }

    public String getFullName() {
        return getTextFromLabel(NAME_FIELD_ID);
    }

    public List<String> getTags() {
        return getTags(getTagsContainer());
    }

    private List<String> getTags(Region tagsContainer) {
        return tagsContainer
                .getChildrenUnmodifiable()
                .stream()
                .map(node -> ((Labeled) node).getText())
                .collect(Collectors.toList());
    }

    private List<String> getTags(UniqueTagList tags) {
        return tags
                .asObservableList()
                .stream()
                .map(tag -> tag.tagName)
                .collect(Collectors.toList());
    }

    private Region getTagsContainer() {
        return guiRobot.from(node).lookup(TAGS_FIELD_ID).query();
    }

    private String getStartTime() {
        return getTextFromLabel(STARTTIME_FIELD_ID);
    }

    private String getEndTime() {
        return getTextFromLabel(ENDTIME_FIELD_ID);
    }
    //@@author A0163786N
    private String getCompleteTime() {
        return getTextFromLabel(COMPLETETIME_FIELD_ID);
    }
    //@@author
    //@@author A0163786N
    public boolean isSameTodo(ReadOnlyTodo todo, boolean checkCompleteTime) {
        if (checkCompleteTime) {
            DateFormat completeCommandDateFormat = new SimpleDateFormat(CompleteCommand.COMPLETE_TIME_FORMAT);
            if (todo.getCompleteTime() != null && !getCompleteTime().equals("Completed at "
                + completeCommandDateFormat.format(todo.getCompleteTime()))) {
                return false;
            }
        }
        return isSameTodo(todo);
    }
    //@@author
    //@@author A0163786N
    public boolean isSameTodo(ReadOnlyTodo todo) {
        DateFormat addCommandDateFormat = new SimpleDateFormat(AddCommand.DATE_FORMAT);
        if (todo.getStartTime() != null
                && !getStartTime().equals("Start: " + addCommandDateFormat.format(todo.getStartTime()))) {
            return false;
        }
        if (todo.getEndTime() != null
            && !getEndTime().equals("End: " + addCommandDateFormat.format(todo.getEndTime()))) {
            return false;
        }
        if (todo.getCompleteTime() != null && getCompleteTime().equals("Not Complete")) {
            return false;
        }
        return getFullName().equals(todo.getName().fullName) && getTags().equals(getTags(todo.getTags()));
    }
    //@@author
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TodoCardHandle) {
            TodoCardHandle handle = (TodoCardHandle) obj;
            return getFullName().equals(handle.getFullName())
                    && getTags().equals(handle.getTags());
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return getFullName();
    }
}
