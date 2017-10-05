/*Completes all logic for board - insert and testing wins
  main includes testing option for class.
  Magic num 2 is left in place for clarity when used to 
  specify board position*/

class Board  {
  private Symbol space = Symbol.space, X = Symbol.X,
          O = Symbol.O;
  private Symbol[][] board;
  private Symbol player;
  private int i0, j0, move, SIZE = 3, END_ROW = 2;

  //Constructor that also sets all to blank board
  Board() {

    board = new Symbol[SIZE][SIZE]; 
    setBoard(space);

  }

  public void setBoard(Symbol a)  {

    for(int i = 0; i < SIZE; i++)  {
      for(int j = 0; j < SIZE; j++)  {
        board[i][j] = a;
      }
    }
    assert(board[1][1] == a);

  }
  
  /*not tested here as tested via call in OXO class
    creates full board layout with current values
    string used to ensure this class does not ouput
    board or other internal values*/
  public String get()  { 

    String str = "   1   2   3 \na  ";
    for(int i = 0; i < SIZE; i++)  {
      for(int j = 0; j < SIZE; j++)  {
        str += board[i][j].getEnum();
        if(j < END_ROW)  str += " | "; 
        if(j == END_ROW)  str += "\n";  
      }
      if(i < (SIZE-1))  str += "  ---+---+---\n";  
      if(i == 0)  str += "b  ";  
      if(i == 1)  str += "c  ";  
    }

    return str;

  }

  public void changePlayer()  {  
    player = (player == X) ? O : X;
  }    

  //main driving function for game logic
  public boolean set(int i, int j)  {

    if(!validMove(i, j)) return false; 
    board[i][j] = (player == X) ? X : O;
    assert(board[i][j] == player);
    
    if(winBoard()) return true;
    changePlayer();
    
    return true;

  }

  public Symbol getSymbolPos(int i, int j)  {
    
    Symbol s = space;

    if(board[i][j] == X) s = X;
    if(board[i][j] == O) s = O;

    return s;
  }

  public void setPlayer(Symbol p)  {
    player = p;
  }

  //returns copy of player so internal values not accessed
  public Symbol getPlayer()  {

    Symbol a = player;
    return a;

  }
   
  private boolean validMove(int a, int j)  {
    return(board[a][j] == space) ? true:false;
  } 

  /*scans each column, row and diagonal for win by current
    player*/ 
  public boolean winBoard()  {

    for(int i = 0; i < SIZE; i++)  {
      if((checkValue(0, i, player)) 
        && (checkValue(1, i, player))
        && (checkValue(2, i, player)))  {  
        return true;
      }
      if((checkValue(i, 0, player)) 
        && (checkValue(i, 1, player))
        && (checkValue(i, 2, player)))  {  
        return true;  
      }
    }

    if(diagWin()) return true;
    
    return false;
  }

  private boolean checkValue(int i, int j, Symbol a)  {
    return(board[i][j] == a) ? true : false;
  }  

  /*scans top left to bottom right and bottom left to top
    right for win*/
  private boolean diagWin()  {
  
    if((board[0][0] == player) && (board[1][1] == player) 
      && (board[2][2] == player))  {  
      return true;
    }

    if((board[0][2] == player) && (board[1][1] == player)
      && (board[2][0] == player))  {  
      return true; 
    }

    return false;
  }

  //checks to see if spaces left
  public boolean checkDraw()  {

    for(int i = 0; i < SIZE; i++)  {
      for(int j = 0; j < SIZE; j++)  {
        if(board[i][j] == space) return false;
      }
    }

    return true;
  }

  //used for testing
  public void insert(int i, int j, Symbol a)  {
    board[i][j] = a;
  }


  void test()  {
  
    assert(checkDraw() == false);
    assert(diagWin() == false);
    assert(winBoard() == false);
    assert(validMove(1, 1) == true);
    setBoard(X);
    setPlayer(X);
    assert(player == X);
    assert(player != O);
    assert(getPlayer() == X);
    assert(checkDraw() == true);
    assert(diagWin() == true);
    assert(winBoard() == true);
    assert(validMove(1, 1) == false);
    changePlayer();
    assert(player == O);
    changePlayer();
    assert(player == X);
    setBoard(O);
    setPlayer(O);
    assert(player == O);
    assert(player != X);
    assert(getPlayer() == O);
    assert(checkDraw() == true);
    assert(diagWin() == true);
    assert(winBoard() == true);
    assert(validMove(2, 2) == false);
    changePlayer();
    board[1][1] = X;
    assert(board[1][1] == X);
    assert(diagWin() == false);
    assert(checkValue(1, 1, X) == true);
    assert(checkValue(0, 1, X) == false);
    setBoard(space);
    set(0, 0);
    assert(board[0][0] == X);
    assert(board[0][1] == space);
    assert(player == O);

  }


  public static void main (String[] args)  {
    boolean testing = false;
    
    assert(testing = true);
    if (testing)  {
      Board program = new Board();
      program.test();
    }
    
  }

}

