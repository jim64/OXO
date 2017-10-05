/*class for AI. It is aware of the Symbol calls for enums
  and aware of the board class to judge its moves. It uses 
  (where possible) the same methods for trying to get its
  own wins and to block the user wins. Difficulty level 
  can be set to easy, medium or hard. This is done by 
  switching of certain function calls dependent on level*/

class Comp  {
  private Symbol space = Symbol.space, X = Symbol.X,
          O = Symbol.O;
  private boolean AI = false;
  private Symbol AIPlayer;
  private int diff = 2, SIZE = 3, i0, j0;
 
  //sets boolean AI to test whether comp player selected
  public void setAI(Board b)  {

    AI = true;
    setAIPlay(b);
    assert(AI == true);

  }

  public boolean getAI()  {
    return(AI == true) ? true : false;
  }

  public void setAIPlay(Board b)  {
    AIPlayer = (b.getPlayer() == X) ? O : X;
  }

  //sets difficulty variable to test against in later methods
  public void setDiff(int d)  {
    diff = d;
  }

  /*returns copy of AI player to stop actual Symbol
    being accessed from outside class */
  public Symbol getAIPlayer()  {

    Symbol a = AIPlayer;
    assert(a == AIPlayer);
    
    return a;

  }

  /*functions determining moves all use -1 to indicate
    a board position not been set. 
    Depending on level  AI first attempts to win, then 
    block a player win, otherwise moves to central position
    as this gives the most likelihood of a win next turn,
    then diagonal positions. It will just find any space 
    available if above options fail*/
  public String AIMove(Board b)  {

    int a = scanForWin(AIPlayer, b);
   
    if(a == -1)  {
      if(diff > 0) a = blockWin(b);
      if(b.getSymbolPos(1, 1) == space && a == -1 && diff == 2)
        a = 5;
      if(a == -1 && diff == 2)  a = diagonal(b);
      if(a == -1)  a = findMove(b);
    }
    assert(a != -1);

    String s = moveStr(a); 

    return s;

  }

  /*scans each row / column for two symbols the same. Used 
    both for AI winning moves and to block player dependent
    on the symbol passed to func. i and j positions inverted
    to scan columns*/
  private int scanForWin(Symbol a, Board b)  {

    int pos = -1, rowcnt = 0, colcnt = 0, j; 
    boolean rowflag = false, colflag = false;

    for(int i = 0; i < SIZE; i++)  {
      for(j = 0; j < SIZE; j++)  {
      
        if(b.getSymbolPos(i, j) == a) rowcnt++;
        if((rowcnt == 2) && (rowflag!= true)) { 
          scanRowSpace(i, j, b);
          rowflag = true; rowcnt = 0;
        }
        
        if(b.getSymbolPos(j, i) == a) {colcnt++;}
        if(colcnt == 2 && colflag != true)  {
          scanColSpace(i, j, b);
          colflag = true; colcnt = 0;
        }
      }
      rowcnt = 0; colcnt = 0;
    }
  
    if((rowflag == true || colflag == true) 
      && b.getSymbolPos(i0, j0) == space) 
      pos = findPos();

    if(pos == -1) pos = diagWin(a, b);

    return pos;
    
  }

  /* 2 funcs below called when two of same symbol found
     in row or column. These funcs scan for available space*/
  private void scanColSpace(int i, int j, Board b)  {

    for(j = 0; j <SIZE; j++)  {
      if(b.getSymbolPos(j, i) == space)  {
        i0 = j; j0 = i;
      }
    }

  } 

  private void scanRowSpace(int i, int j, Board b)  {

    for(j = 0; j < SIZE; j++)  {
      if (b.getSymbolPos(i, j) == space)  {
        i0 = i; j0 = j;
      }
    }

  }

  /*simple if statements to check if a diagonal win 
    possible*/
  private int diagWin(Symbol a, Board b)  {
    
    int pos = -1;

    if(b.getSymbolPos(1,1) == a)  {

      if(b.getSymbolPos(0,0) == a 
        && b.getSymbolPos(2, 2) == space)
        pos = 9;

      if(b.getSymbolPos(0,2) == a 
        && b.getSymbolPos(2, 0) == space)
        pos = 7;
  
      if(b.getSymbolPos(2, 0) == a 
        && b.getSymbolPos(0, 2) == space)
        pos = 3;

      if(b.getSymbolPos(2, 2) == a 
        && b.getSymbolPos(0, 0) == space)
        pos = 1;
    }
    else if(b.getSymbolPos(1,1) == space)  {
      pos = centreDiag(pos, a, b);
    }

    return pos;
  
  }

  //called to block player win. Uses same scanwin func as AI
  private int blockWin(Board b)  {

    Symbol a = (AIPlayer == X) ? O : X;
    int pos = -1;
    pos = scanForWin(a, b);
    
    if(b.getSymbolPos(1, 1) == space)  {
      pos = diagWin(a, b);
    }
   
    return pos;

  }

  //used to block central position
  private int centreDiag(int pos, Symbol a, Board b)  {

    if(((b.getSymbolPos(0, 0) == a && b.getSymbolPos(2, 2) == a) 
      || (b.getSymbolPos(2, 0) == a && b.getSymbolPos(0, 2) == a))
      && b.getSymbolPos(1, 1) == space);
      pos = 5;

    return pos;

  }

  //used to set AI to a diagonal pos if no better move free
  private int diagonal(Board b)  {

    int pos = -1;

    if(b.getSymbolPos(0, 0) == space) pos = 1;
    else if(b.getSymbolPos(2, 2) == space) pos = 9;
    else if(b.getSymbolPos(0, 2) == space) pos = 3;
    else if(b.getSymbolPos(2, 0) == space) pos = 7;
    
    return pos;

  }
      
  //used for AI move if no better move poss. Finds 1st space
  private int findMove(Board b)  {

    int pos = -1;

    for(int i = 0; i < SIZE; i++)  {
      for(int j = 0; j < SIZE; j++)  {
        if(b.getSymbolPos(i, j) == space)  {
          i0 = i; j0 = j;
        }
      }
    }

   pos = findPos();

   return pos;

  }

  //called to set position from funcs that set i0 and j0;
  private int findPos()  {

    int pos = -1;

    if(i0 == 0)  {
      if(j0 == 0)  pos = 1; 
      if(j0 == 1)  pos = 2;
      if(j0 == 2)  pos = 3;
    }
    else if(i0 == 1)  {
      if(j0 == 0)  pos = 4;
      if(j0 == 1)  pos = 5;
      if(j0 == 2)  pos = 6;
    }
    else if(i0 == 2)  {
      if(j0 == 0)  pos = 7;
      if(j0 == 1)  pos = 8;
      if(j0 == 2)  pos = 9;
    }

    assert(pos > 0 && pos < 10);

    return pos;

  }

  /*converts pos to string to pass back to AI move so
    string can be passed out of class*/
  private String moveStr(int pos)  {

    String s = "a1";

    switch(pos)  {
      case 1: s = "a1"; break;
      case 2: s = "a2"; break;
      case 3: s = "a3"; break;
      case 4: s = "b1"; break;
      case 5: s = "b2"; break;
      case 6: s = "b3"; break;
      case 7: s = "c1"; break;
      case 8: s = "c2"; break;
      case 9: s = "c3"; break;
    }

    return s;

  }

  /*tests cover all functions. This is achieved by setting
    the board to different configurations*/
  void testAI(Board b)  {
    //tests setting to AI play
    setAI(b);
    assert(AI == true);
    assert(AI != false);
    //tests AI is set correctly
    b.setPlayer(X);
    setAIPlay(b);
    assert(AIPlayer == O);
    assert(AIPlayer != X);
    assert(getAIPlayer() == O);
    b.setPlayer(O);
    setAIPlay(b);
    assert(AIPlayer == X);
    assert(AIPlayer != O);
    assert(getAIPlayer() == X);
    assert(getAI() == true); 
    //tests AI moves are done correctly
    b.setBoard(space);
    assert(AIMove(b) == "b2");
    b.setPlayer(O);
    b.set(0, 0); b.set(0, 1);
    assert(diagonal(b) == 9);
    b.set(2, 2);
    assert(diagonal(b) == 3);
    b.set(0, 2);
    assert(diagonal(b) == 7);
    b.set(2, 0);
    assert(diagonal(b) == -1);
    b.setBoard(space);
    b.insert(1, 1, X); b.insert(1, 2, X);
    //test ScanforWin func as a whole
    assert(scanForWin(X, b) == 4);
    b.insert(0, 0, X); b.insert(1, 0, X);
    assert(scanForWin(X, b) == 7);
    //test find col space and scan col
    assert(j0 == 0);
    assert(i0 == 2);
    assert(findPos() == 7);
    b.setBoard(space);
    b.insert(1, 0, X); b.insert(1, 1, X);
    assert(scanForWin(X, b) == 6);
    b.set(2, 1);
    assert(scanForWin(X, b) == 2);
    b.setBoard(O); 
    b.insert(1, 1, space); 
    assert(centreDiag(-1, O, b) == 5);
    findMove(b);
    assert(i0 == 1);
    assert(j0 == 1);
    assert(findPos() == 5);
    //tests moveStr()
    assert(moveStr(1) == "a1");
    assert(moveStr(2) == "a2");
    assert(moveStr(3) == "a3");
    assert(moveStr(4) == "b1");
    assert(moveStr(5) == "b2");
    assert(moveStr(6) == "b3");
    assert(moveStr(7) == "c1");
    assert(moveStr(8) == "c2");
    assert(moveStr(9) == "c3");
    
  }

  public static void main (String[] args)  {
    boolean testing = false;
    assert(testing = true);
    if(testing)  {
      Comp program = new Comp();
      Board b= new Board();
      program.testAI(b);
    }
  }
}
 

