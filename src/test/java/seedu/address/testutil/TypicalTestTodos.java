package seedu.address.testutil;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TodoList;
import seedu.address.model.todo.Todo;
import seedu.address.model.todo.UniqueTodoList;

/**
 *
 */
public class TypicalTestTodos {

    public TestTodo dog, cat, math, english, dishes, lawn, dinner, laundry, car;

    public TypicalTestTodos() {
        try {
            dog = new TodoBuilder().withName("Walk the dog")
                    .withTags("petcare").build();
            cat = new TodoBuilder().withName("Walk the cat")
                    .withTags("petcare", "cat").build();
            math = new TodoBuilder().withName("Do math homework").build();
            english = new TodoBuilder().withName("Do english homework").build();
            dishes = new TodoBuilder().withName("Wash dishes").build();
            lawn = new TodoBuilder().withName("Mow the lawn").build();
            dinner = new TodoBuilder().withName("Cook dinner").build();

            // Manually added
            laundry = new TodoBuilder().withName("Do laundry").build();
            car = new TodoBuilder().withName("Wash car").build();
            eventTest = new TodoBuilder().withName("eventTest").withStartTime("11-11-17T5:00")
                    .withEndTime("11-11-17T6:00").build();
            deadLineTest = new TodoBuilder().withName("deadLineTest").withEndTime("11-11-17T6:00").build();
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadTodoListWithSampleData(TodoList ab) {
        for (TestTodo todo : new TypicalTestTodos().getTypicalTodos()) {
            try {
                ab.addTodo(new Todo(todo));
            } catch (UniqueTodoList.DuplicateTodoException e) {
                assert false : "not possible";
            }
        }
    }

    public TestTodo[] getTypicalTodos() {
        return new TestTodo[] { dog, cat, math, english, dishes, lawn, dinner};
    }

    public TodoList getTypicalTodoList() {
        TodoList ab = new TodoList();
        loadTodoListWithSampleData(ab);
        return ab;
    }
}
