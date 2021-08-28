// Md Reaz Morshed mdmo9317
public class Bid {
    private String name;
    private int bidAmount;

    Bid(){

    }

    Bid(String user,int bid){
        name=user;
        bidAmount=bid;
    }
    public void set(Bid temp){
        name=temp.getName();
        bidAmount=temp.getBidAmount();
    }
    public void setName(String user){name=user;}
    public void setBidAmount(int bid){bidAmount=bid;}
    public String getName(){return name;}
    public int getBidAmount(){return bidAmount;}
    public void printBid(){
        System.out.print(name+" "+bidAmount+" kr");
    }

}
