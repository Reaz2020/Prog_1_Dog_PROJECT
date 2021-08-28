import java.util.ArrayList;
// Md Reaz Morshed mdmo9317

public class Auction {
    private static final int LIST_SIZE=3;
    private String dogname;
    private ArrayList<Bid>bidder=new ArrayList<Bid>();
    private int auctionNumber;
    Auction(String name,int number)
    {
        dogname=name;
        auctionNumber=number;
    }
    private void bubblesort()
    {
        for(int i=0;i<bidder.size()-1;i++){
            for(int j=i+1;j<bidder.size();j++){
                if(bidder.get(i).getBidAmount()<bidder.get(j).getBidAmount()){
                    Bid temp=new Bid(bidder.get(i).getName(),bidder.get(i).getBidAmount());
                    bidder.get(i).setName(bidder.get(j).getName());
                    bidder.get(i).setBidAmount(bidder.get(j).getBidAmount());

                    bidder.get(j).setName(temp.getName());
                    bidder.get(j).setBidAmount(temp.getBidAmount());
                }
            }
        }
    }



    public void addBidder(Bid bid)
    {
        bidder.add(bid);
    }

    public int checkbidder(String name)
    {
        for(int i=0;i<bidder.size();i++){
            if(name.equals(bidder.get(i).getName())){
                return i;
            }
        }
        return -1;
    }

    public int getbidamount(int indx)
    {
        return bidder.get(indx).getBidAmount();
    }

    public void replace(int i,int newbid)
    {
        bidder.get(i).setBidAmount(newbid);
    }

    public int getAuctionNumber(){return auctionNumber;}
    public String getDogname(){return dogname;}

    public  void printAuction() {
        System.out.print("Auction #"+auctionNumber+": "+dogname+". Top bids: [" );
        bubblesort();
        for(int i=0;i<bidder.size() && i<LIST_SIZE;i++){
            bidder.get(i).printBid();
            if(i!=bidder.size()-1 && i!=LIST_SIZE-1){
                System.out.print(",");
            }
        }
        System.out.println("]");
    }
    public void printbid(){
        bubblesort();
        for(int i=0;i<bidder.size();i++) {
            bidder.get(i).printBid();
            System.out.print("\n");
        }
    }

    public boolean bidderSize(){
        return bidder.size()>0;
    }
    public Bid findWinner()
    {
        Bid winner=new Bid("",0);
        for(int i=0;i<bidder.size();i++){
            if(winner.getBidAmount()<bidder.get(i).getBidAmount()){
                winner=bidder.get(i);
            }
        }
        return winner;
    }

    public void removefrombidder(String username)
    {
        for(int i=0;i<bidder.size();i++){
            if(bidder.get(i).getName().equals(username)){
                bidder.remove(i);
                return;
            }
        }
    }

    public int minimumbid(){
        if(bidder.size()==0) return 1;
        int minimum=bidder.get(0).getBidAmount();
        for(int i=1;i<bidder.size();i++){
            if(minimum<bidder.get(i).getBidAmount()){
                minimum=bidder.get(i).getBidAmount();
            }
        }
        return minimum+1;
    }
}
