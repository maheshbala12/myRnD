package com.mydemo.codechallenges;

class Laptop{
  int price;
  String details;
  public Laptop(String details, int price ){
      this.price=price;
      this.details = details;
  }
  public int getPrice(){
    return this.price;
  }
  public String getDetails(){
    return this.details;
  }
}

class Afford{
  //code here
  public String checkConfiguration(Laptop laptop) throws Exception{
    if(laptop.getPrice()>70000){
      throw new LaptopException("Price too high");
    }
    
    int ram = 0;
    int HD = 0;
    String type = "";
    String spec = laptop.getDetails();
    
    if(spec.indexOf("/")!=-1){
      String []strs = spec.split("/");
      if(strs.length==3){
        ram = Integer.parseInt(strs[0]);
        HD =  Integer.parseInt(strs[1]);
        type = strs[2];
      }
    }
    
    if(ram<8){
      throw new LaptopException("Minimum 8 RAM required");
    }
    else if(HD<256){
      throw new LaptopException("Minimum 256 space required");
    }
    else if("HDD".equals(type)){
      throw new LaptopException("SSD required");
    }
    return "Can be purchased";
  }

  public String purchaseLaptop(Laptop laptop) throws Exception{
    try{
      String msg = checkConfiguration(laptop);
    }
    catch(Exception ex){
      if(ex.getClass().getName().equals("LaptopException")){
        return "Change configuration";
      }
      return "other exception";
    }
    return "Perfect configuration";
  }
}
class LaptopException extends Exception
{
  //code
  public LaptopException(String msg){
    super(msg);
  }
}

// Class name should be "Source",
// otherwise solution won't be accepted
public class CutShortCodingChallenge {
	public static void main(String args[] ) throws Exception {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT */
    Laptop laptop=new Laptop("11/512/SSD", 58000);
    Afford af=new Afford();
    String s=af.checkConfiguration(laptop);
    String t=af.purchaseLaptop(laptop);
    s.toLowerCase();
    t.toLowerCase();
    System.out.println(s+":"+t);
	}
}