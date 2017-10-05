/*deals with all input and printing of board (is not aware
  of board - printing funtion will print string given to it*/

import java.util.Scanner;

class Display  {

  public String get()  { 

    String str = playerChoice();
    return str;

  }

  public boolean compPlayer()  {

    boolean b = false;    
    print("Do you want to play the computer or other person\n" +
          "Enter C for computer, P for person");
    String s = scanChoice();
    
    switch(s.charAt(0))  {
      case 'C' :  {  b = true; break;  }
      case 'P' :  break;
      default : {  print("Invalid choice"); compPlayer();  }

    }
    
    return b;
  }

  public int getDiff()  {
    
    int diff = 2;
    print("Set difficulty: E for easy, M for medium, H for Hard"+
          " - Hard is default");
    String s = scanChoice();
    
    if(s.equals("E")) diff = 0;
    else if(s.equals("M")) diff = 1;

    return diff;

  }

    
  //scans input. Calls itself if input invalid.
  public String playerChoice()  {

    print("type in a letter and a number such as b1.");
    String s = scanChoice();
    
    if(!validInput(s))  {
      s = playerChoice();
    }
    
    return s;
    
  }

  private String scanChoice()  {
  
    Scanner scanner = new Scanner(System.in);
    String s = scanner.nextLine();
    
    return s;
    
  }
  
  public void stateTurn(Symbol e)  {
    print(e + "'s turn:");
  }

  //checks both correct range for input and correct chars
  private boolean validInput(String s)  {

    if(s.length() != 2)  {
      print("Invalid - Need both letter and number, try again");
      return false;
    }
    
    if(s.charAt(0) < 'a' || s.charAt(0) > 'c' || 
       s.charAt(1) < '1' || s.charAt(1) > '3') {
      print("Invalid - Must be a-c, 1-3, try again");
      return false;
    }

    return true;

  }

  public void print(String s)  { 
    System.out.println(s);
  }

  /*this test will still trigger the system.out error messages
    it tests the validInput method - other methods not tested
    as unnecessary - they are tested by their correct use in 
    Oxo class*/
  void test()  {
  
    assert(validInput("a1") == true);
    assert(validInput("c3") == true);
    assert(validInput("c14") == false);
    assert(validInput("x0") == false);
    assert(validInput("098765432") == false);
    assert(validInput("nirthgba") == false);
    assert(validInput("aa") == false);
    
  }

  public static void main(String[] args)  {
    boolean testing = false;
    
    assert(testing = true);
    if(testing)  {
      Display program = new Display();
      program.test();
    }

  }
  
}

