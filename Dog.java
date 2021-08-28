import java.util.ArrayList;
import java.util.Scanner;

// Md Reaz Morshed mdmo9317
public class Dog {
    private Scanner userInput = new Scanner(System.in); // moved from skeleton
    private static final double MAX_TAIL_LENGTH = 3.7;
    private static final double TAIL_DENOMINATOR = 10.0;
    private String name;
    private int userAge;
    private String breed;
    private double taailLength;
    private int weight;
    private String owner="";
    private int auction;
    private ArrayList<Bid> bidder= new ArrayList<Bid>();

    Dog(String naMe, int userGivenOld, String breed, int userWeight) {
        name = naMe;
        userAge = userGivenOld;
        this.breed = breed;
        weight = userWeight;
        taailLength=0.0;
        if (breed.equals("Tax") || breed.equals("Dachshund") || breed.equals("tax") || breed.equals("dachshund")) {
            taailLength = MAX_TAIL_LENGTH;
        } else {
            taailLength =  userAge * weight / TAIL_DENOMINATOR;

        }
    }

    //getter and setter------------------------------------
    String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return userAge;
    }
    public double getTailLength() {
        return taailLength;
    }
    public String getBreed() {
        return breed;
    }
    public int getWeight() {
        return weight;
    }
    public String getOwnerName(){return owner;}
    public void setOwnername(String name){ owner=name;}

    public void increaseAge(int x) {   // have to fix increase age in the skeleton
        userAge = userAge + x;
        if (breed.equals("Tax") || breed.equals("Dachshund") || breed.equals("tax") || breed.equals("dachshund")) {
            taailLength = MAX_TAIL_LENGTH;
        }else
            taailLength = userAge * weight / TAIL_DENOMINATOR;

    }
    public void setAuction(int x){
        auction=x;}
    public int getAuction(){return auction;}

    public void addBid(String name,int bidAmount){
        Bid temp=new Bid(name,bidAmount);
        bidder.add(temp);
    }

    @Override
    public  String toString() {
        return " (Name : " + name + " , Age :" + userAge  + ", Breed : " + breed + ", " +
                " Length of tail : " + taailLength + ")";
    }
    /*private void removeDog() {
        System.out.println("enter the name of the dog ?>");
        String dogNameThatToBeRemoved = userInput.nextLine();
        dogNameThatToBeRemoved = dogNameThatToBeRemoved.trim().toLowerCase();
        int indx = checkregiterdog(dogNameThatToBeRemoved);
        if (indx == NOT_IN_REGISTER) {
            System.out.println("Error: no such dog");
        } else {
            removedogprocess(dogNameThatToBeRemoved);
            System.out.println(dogNameThatToBeRemoved + " is removed from the register");
        }
    } // moved from skeleton*/
}
