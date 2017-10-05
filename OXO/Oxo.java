/*Class which drives the program and calls other classes. 
  As such it is reliant on the other classes for its 
  functionality and is the only class aware of the others
  No testing on class due to compexity of interactions 
  with other classes - test is implicit by functionality
  of program and testing of methods within display and b
  board class.*/


class Oxo  {
  Symbol space = Symbol.space; Symbol X = Symbol.X;
  Symbol O = Symbol.O;
  private Board b = new Board();
  private Display d = new Display();
  private Comp c = new Comp();

  //carries out loop while still moves left or no win
  private void run(String s)  {
  
    int diff = 0;
    Symbol e = space;
 
    b.setPlayer(e.setEnum(s));
    if(d.compPlayer())  {
      c.setAI(b);
    }
    if(c.getAI() == true)  {
      diff = d.getDiff();
      c.setDiff(diff);
    }
    while(!b.winBoard() && !b.checkDraw())  {
        d.print(b.get());
        d.stateTurn(b.getPlayer()); 
        moves();
    }
    d.print(b.get());

  }

  //takes user input from display class and sets position
  //on board
  private void moves()  {
  
    String str;
    
    if(!c.getAI() || (b.getPlayer() != c.getAIPlayer())) 
      str = d.get();
    else str = c.AIMove(b);  
    
    Position p = new Position(str);
    
    if(b.set(p.getI(), p.getJ()) == false)
      d.print("Choose an empty cell");

  }

  public static void main (String[] args)  {
  
    Oxo game = new Oxo();
    
    if((args.length == 1) && (game.checkInput(args[0])))  {
      game.run(args[0]);
    }
    else  {
      game.d.print("Use: java Oxo X or java Oxo O");
      System.exit(1);
    }

  }

  //checks initial input correct
  private boolean checkInput(String input)  {
    return((input.equals("X")) || (input.equals("O")))
          ? true : false;

  }

}
