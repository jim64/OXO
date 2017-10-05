/* Enumerated num class to return enum as the correct string
   also sets enum from string. Testing is unnecessary as this
   is implicit in its correct use by other classes*/

import java.util.*;

public enum Symbol  {
  X, O, space;

  public String getEnum()  {
  
    String s = "Unrecognised Enum type";
    switch(this)  {
      case X: s = "X"; break;
      case O: s = "O"; break;
      case space: s = " "; break;
      default: System.err.println(s);
               System.exit(1);
    }
    return s;
    
  }

  public Symbol setEnum(String s)  {
    return(s.equals("X")) ? X : O;
  }

}
