package java11.todolist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodoListTest {

  @DisplayName("TEST BUSINESS LOGIC: menampilkan data todolist")
  @Disabled
  @Test
  public void testShowTodoList() {
    TodoList.modelDataTodoList[0] = "Belajar Pemograman";
    TodoList.modelDataTodoList[1] = "Belajar Database";
    TodoList.modelDataTodoList[2] = "Belajar RDBMS";
    TodoList.modelDataTodoList[3] = "Belajar Struktur Data";

    TodoList.showTodoList();
  }


  @DisplayName("TEST BUSINESS LOGIC: menambahkan data todolist")
  @Disabled
  @Test
  public void testAddTodoList() {
    for (var i = 1; i <= 15; i++) {
      TodoList.addTodoList("Test Data ->"+i);
    }

    TodoList.showTodoList();
  }


  @DisplayName("TEST BUSINESS LOGIC: menghapus todolist")
  @Disabled
  @Test
  public void testDeleteTodoList() {
    for (var i = 1; i <= 10; i++) {
      TodoList.addTodoList("Test Data Ke "+i);
    }

    var actual = TodoList.deleteTodoList(13);
//    Assertions.assertTrue(actual);
    Assertions.assertFalse(actual);

    TodoList.showTodoList();
  }


  @DisplayName("TEST BUSINESS LOGIC: merubah data todolist")
  @Disabled
  @Test
  public void testUpdateTodoList() {
    for (var i = 1; i <= 10; i++) {
      TodoList.addTodoList("Test Data Ke "+i);
    }

    var actual = TodoList.updateTodoList(2, "Data Baru Nih");
    Assertions.assertTrue(actual);

    TodoList.showTodoList();
  }
}
