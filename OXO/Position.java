//this class takes a string and converts this to intergers

class Position  {
  private int i, j;

  //assertion in place as defensive check
  public Position(String s)  {
    i = s.charAt(0) - 'a';
    j = s.charAt(1) - ('0' + 1);
    assert(i >= 0 && i < 3 && j >= 0 && j < 3);
  }

  public int getI()  {  
    return i;  
  }

  public int getJ()  {  
    return j;  
  }
  
  private void test()  {
  
    assert(getI() == 1);
    assert(getJ() == 1);
    
  }
  
  public static void main(String[] args)  {
    boolean testing = false;
    
    if(testing)  {
      Position p = new Position("a1");
      p.test();
    }
    
  }
    
}
