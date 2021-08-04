package java11.todolist;


import java.util.Scanner;

public class TodoList {
  private static final Scanner scan = new Scanner(System.in);
  private static boolean stop = true;
  public static String[] modelDataTodoList = new String[10];


/*=======================================START: Flow Business Logic===================================*/
  /**
   * @desc menampilkan data todolist
   */
  public static void showTodoList() {
    for (var i = 0; i < modelDataTodoList.length; i++) {
      var todo = modelDataTodoList[i];
      var number = i+1;

      /*jika setiap elemen todolist tidak null maka tampilkan*/
      if (modelDataTodoList[i] != null) System.out.println(number+". "+todo);
    }
  }


  /**
   * @desc menambahkan data todolist
   * @param todo menerima argument data yang akan ditambahkan
   */
  public static void addTodoList(String todo) {
    for (var i = 0; i < modelDataTodoList.length; i++) {
      if (modelDataTodoList[i] == null) {
        modelDataTodoList[i] = todo;

        /*cek apakah index elemen yang ditelusuri sama dengan panjang todolist*/
        if (i == modelDataTodoList.length -1) {
          String[] temp = modelDataTodoList;

          /*risize length todolist*/
          modelDataTodoList = new String[modelDataTodoList.length * 2]; //10
          for (var index = 0; index < temp.length; index++) {
            modelDataTodoList[index] = temp[index];
          }
        }
        break;
      }
    }
  }


  /**
   * @desc menghapus data todolist
   * @param number menerima argument nomor todolist yang akan dihapus
   */
  public static boolean deleteTodoList(Integer number) {
    /*
      cek jika number index yang akan dihapus melebihi panjang dari todolist atau juga
      number index yang akan dihapus sama dengan null
    */
    if ((number - 1) >= modelDataTodoList.length || modelDataTodoList[number-1] == null) return false;

    /*cek apakah data dari number yang akan dihapus null atau tidak*/
    if (modelDataTodoList[number-1] != null) {
      modelDataTodoList[number-1] = null;

      /*jika data yang dihapus sudah null*/
      if (modelDataTodoList[number-1] == null) {
        /*lakukan pergeseran elemen dengan menelusuri index elemen array dari index yang dihapus*/
        for (var i = number -1; i < modelDataTodoList.length; i++) {

          /*jika pergeseran elemen array sudah dititik akhir dari index jangan lakukan pergeseran*/
          if (i == modelDataTodoList.length - 1) break;
          modelDataTodoList[i] = modelDataTodoList[i+1];
        }
      }
    }

    return true;
  }


  /**
   * @desc merubah data todolist
   */
  public static boolean updateTodoList(Integer number, String newTodolist) {
    if ((number-1) >= modelDataTodoList.length || modelDataTodoList[number-1] == null) {
      return false;
    }

    if (modelDataTodoList[number-1] != null) {
      modelDataTodoList[number-1] = newTodolist;
    }
    return true;
  }
/*=========================================END: Flow Business Logic===================================*/


/*==========================================START: Flow View==========================================*/
  /**
   * @desc view utama
   */
  public static void viewMainTodoList() {
    while (stop) {
      System.out.println("-----------+ Todo List +-----------");
      showTodoList();
      System.out.println("-----------------------------------");
      System.out.println("-------------+ Menu +--------------");
      System.out.print("[1]. Add TodoList\n" +
              "[2]. Delete TodoList\n" +
              "[3]. Update TodoList\n" +
              "[x]. Exit\n");
      System.out.println("-----------------------------------");
      System.out.print("Choose Menu > ");
      String choose = scan.nextLine();

      switch (choose) {
        case "1": viewAddTodoLits(); break;
        case "2": viewDeleteTodoList(); break;
        case "3": viewUpdateTodoList(); break;
        case "x": System.exit(0); break;
        default: System.err.println("Choose menu '"+choose+"' Not Found!"); break;
      }
    }
  }


  public static void viewAddTodoLits() {
    System.out.print("input name todolist : ");
    String todo = scan.nextLine();
    addTodoList(todo);
    System.out.println("\n\n");
  }


  public static void viewDeleteTodoList() {
    System.out.print("delete number todolist (x if cancel) : ");
    String deleteTodo = scan.nextLine();

    if (deleteTodo.equalsIgnoreCase("x")) {
      stop = false;
    }
    else {
      var success = deleteTodoList(Integer.valueOf(deleteTodo));
      if (!success) System.out.println("Fail Delete Number! "+deleteTodo+" Not Found\n\n");
      if (success) System.out.println("Success Delete Todo\n\n");
    }

  }


  public static void viewUpdateTodoList() {
    System.out.print("input new name todolist (x if cancel) : ");
    String newNameTodo = scan.nextLine();

    if (newNameTodo.equalsIgnoreCase("x")) {
      stop = false;
    } else {
      System.out.print("input update number todolist : ");
      String updateNumberTodo = scan.nextLine();

      boolean success = updateTodoList(Integer.valueOf(updateNumberTodo), newNameTodo);
      if (!success) System.out.println("Update Todo Fail! Number "+updateNumberTodo+" Not Found!\n\n");
      if (success) System.out.println("Update Todo Success\n\n");
    }
  }
/*===========================================END: Flow View===========================================*/


  /**
   * @desc Main Program
   */
  public static void main(String[] xxx) {
    viewMainTodoList();
  }
}
