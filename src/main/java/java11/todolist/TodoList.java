package java11.todolist;


import java.util.Scanner;

public class TodoList {
  private static final String RESET,RED,GREEN,PURPLE,BLUE,YELLOW,RED_BOLD,GREEN_BOLD,YELLOW_BOLD;
  static {
    RESET = "\033[0m";
    RED = "\033[0;31m";
    GREEN = "\033[0;32m";
    PURPLE = "\033[0;35m";
    BLUE = "\033[0;34m";
    YELLOW = "\033[0;33m";
    RED_BOLD = "\033[1;31m";
    GREEN_BOLD = "\033[1;32m";
    YELLOW_BOLD = "\033[1;33m";
  }


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
      if (modelDataTodoList[i] != null) System.out.println(PURPLE+number+". "+todo+RESET);
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
      System.out.println(YELLOW_BOLD+"-----------+ Todo List +-----------"+RESET);
      showTodoList();
      System.out.println(YELLOW_BOLD+"-----------------------------------"+RESET);
      System.out.println(YELLOW_BOLD+"-------------+ Menu +--------------"+RESET);
      System.out.print(GREEN+"[1]. Add TodoList\n"+RESET +
              GREEN+"[2]. Delete TodoList\n"+RESET +
              GREEN+"[3]. Update TodoList\n"+RESET +
              GREEN+"[x]. Exit\n"+RESET);
      System.out.println(YELLOW_BOLD+"-----------------------------------"+RESET);
      System.out.print(YELLOW_BOLD+"Choose Menu > "+RESET);
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
    System.out.print(GREEN_BOLD+"input name todolist : "+RESET);
    String todo = scan.nextLine();
    addTodoList(todo);
    System.out.println("\n\n");
  }


  public static void viewDeleteTodoList() {
    System.out.print(RED_BOLD+"delete number todolist (x if cancel) : "+RESET);
    String deleteTodo = scan.nextLine();

    if (deleteTodo.equalsIgnoreCase("x")) {
      stop = false;
    }
    else {
      var success = deleteTodoList(Integer.valueOf(deleteTodo));
      if (!success) System.out.println(RED+"Fail Delete Number! "+deleteTodo+" Not Found\n\n"+RESET);
      if (success) System.out.println(BLUE+"Success Delete Todo\n\n"+RESET);
    }

  }


  public static void viewUpdateTodoList() {
    System.out.print(GREEN_BOLD+"input new name todolist (x if cancel) : "+RESET);
    String newNameTodo = scan.nextLine();

    if (newNameTodo.equalsIgnoreCase("x")) {
      stop = false;
    } else {
      System.out.print(GREEN_BOLD+"input update number todolist : "+RESET);
      String updateNumberTodo = scan.nextLine();

      boolean success = updateTodoList(Integer.valueOf(updateNumberTodo), newNameTodo);
      if (!success) System.out.println(RED+"Update Todo Fail! Number "+updateNumberTodo+" Not Found!\n\n"+RESET);
      if (success) System.out.println(BLUE+"Update Todo Success\n\n"+RESET);
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
