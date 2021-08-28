import java.util.ArrayList;
import java.util.Scanner;

// Md Reaz Morshed mdmo9317
public class ProgrammSkeleton {
    private static final int NOT_IN_REGISTER = -2;
    private Scanner userInput = new Scanner(System.in);
    private ArrayList<Dog> dogs = new ArrayList<>();
    private User[] users;
    private ArrayList<Auction> auctions = new ArrayList<>();
    private int sz;
    private int mxAuction;



    //  main commando method  is called from main
    void start() {
        sz=0;
        mxAuction=0;
        initialize();
        runCommandoLoop();
        closeDown();

    }

    private void closeDown() {
        System.out.println("hej då");
    }

    // methods for dog class------------------

    private void removeDog() {
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
    }

    private void removedogprocess(String dogNameThatToBeRemoved){
        int indx = getdogindex(dogNameThatToBeRemoved);
        if (dogs.get(indx).getOwnerName().length() > 0)
            removefromuser(indx);
        dogs.remove(indx);
        removefromauction(dogNameThatToBeRemoved);
    }

    private void removefromauction(String dogname)
    {
        for (int i = 0; i < auctions.size(); i++) {
            if (auctions.get(i).getDogname().equals(dogname)) {
                auctions.remove(i);
                return;
            }
        }
    }

    private void removefromuser(int indx) {
        int ownerindex = 0;
        for (int i = 0; i < sz; i++) {
            if (dogs.get(indx).getOwnerName().equals(users[i].getUserName())) {
                ownerindex = i;
                break;
            }
        }
        Dog[] temp = new Dog[users[ownerindex].getSz() - 1];
        int j = 0;
        for (int i = 0; i < users[ownerindex].getSz(); i++) {
            if (users[ownerindex].getDog(i).getName().equals(dogs.get(indx).getName())) {
                j = 1;
            } else {
                temp[i-j] = users[ownerindex].getDog(i);
            }
        }
        users[ownerindex].setDogs(temp);
        users[ownerindex].setSz(users[ownerindex].getSz()-1);
    }

    private void removeUser() {
        System.out.println("enter the name of the user?>");
        String name;
        name = userInput.nextLine().trim().toLowerCase();
        int indx = checkregiteruser(name);
        if (indx != -1) {
            System.out.println(name + " is removed from the register");
            for (int i = 0; i < dogs.size(); i++) {
                if (dogs.get(i).getOwnerName().equals(users[indx].getUserName())) {
                    removedogprocess(dogs.get(i).getName());
                }
            }
            copyFromUser(indx);
            removeUserFromAuction(name);
        } else {
            System.out.println("Error: No such user");
        }
    }

    private void copyFromUser(int indx){
        User[] temp = new User[sz - 1];
        for (int i = 0; i < indx; i++) {
            temp[i] = users[i];
        }
        for (int i = indx + 1; i < sz; i++) {
            temp[i-1] = users[i];
        }
        users = temp;
        sz--;
    }

    private void removeUserFromAuction(String username)
    {
        for (int i=0;i<auctions.size();i++){
            auctions.get(i).removefrombidder(username);
        }
    }

    private void listDogs() {
        if (dogs.size() > 0) {
            System.out.println("Smallest tail length to display?>");
            double tailSz = userInput.nextDouble();   // shall i write Double or double ??
            userInput.nextLine();
            // I have to print out only the dog with equal or large tail on the list
            sort(dogs);
            System.out.println("The following dogs has such a large tail:");
            for (int i = 0; i < dogs.size(); i++) {
                if (tailSz <= dogs.get(i).getTailLength()) {
                    System.out.print("* "+dogs.get(i).getName() + " (" + dogs.get(i).getBreed() + ", " + dogs.get(i).getAge() + " years, " + dogs.get(i).getWeight() + " kilo, " + dogs
                            .get(i).getTailLength() + " cm tail");
                    if (dogs.get(i).getOwnerName().length() > 0) {
                        System.out.print(", owned by " + dogs.get(i).getOwnerName());
                    }
                    System.out.println(")");
                }
            }
        } else {
            System.out.println("Error: no dogs in register");
        }
    }

    private void sort(ArrayList<Dog> dogs) {

        for (int i = 0; i < dogs.size(); i++) {
            int indexMin = i;

            for (int j = i + 1; j < dogs.size(); j++) {

                if (dogs.get(j).getTailLength() < dogs.get(indexMin).getTailLength()) {
                    indexMin = j;
                } else if (dogs.get(j).getTailLength() == dogs.get(indexMin).getTailLength()) {
                    if (dogs.get(indexMin).getName().compareTo(dogs.get(j).getName()) > 0) {
                        indexMin = j;
                    }
                }


            }

            Dog temp = dogs.get(i);
            dogs.set(i, dogs.get(indexMin));
            dogs.set(indexMin, temp);

        }
    }

    private void increaseAge() {
        System.out.println("Enter the dog name?>");
        String name = userInput.nextLine().trim().toLowerCase();
        int indx = checkregiterdog(name);
        if (indx == NOT_IN_REGISTER) {
            System.out.println("Error: no such dog");
        } else {
            dogs.get(indx).increaseAge(1);
            System.out.println(name + " is now one year older");
        }
    }

    private void registerDog() {                    // have to use trim method to remove user input spaces.
        System.out.println("Add new Dog Name?> ");
        String userGivenDogName = userInput.nextLine().trim().toLowerCase();
        while (userGivenDogName.length() == 0) {
            System.out.println("Error: Name cant be empty");
            System.out.println("Name?>");
            userGivenDogName = userInput.nextLine().trim().toLowerCase();
        }

        System.out.println("Add breed ?> ");
        String userBreed = userInput.nextLine().trim().toLowerCase();
        while (userBreed.length() == 0) {
            System.out.println("Error: Breed cant be empty");
            System.out.println("Breed?>");
            userBreed = userInput.nextLine().trim().toLowerCase();
        }

        System.out.println("Add Its age?>");
        int userGivenAgeYear = userInput.nextInt();


        System.out.println("Add Its Weight?> ");
        int userWeight = userInput.nextInt();
        userInput.nextLine();


        Dog dog = new Dog(userGivenDogName.trim().toLowerCase(), userGivenAgeYear, userBreed.trim().toLowerCase(), userWeight);

        dogs.add(dog);
        System.out.println(userGivenDogName + "  added to the registered");


    }

    //methods for user---------------------------
    private void registerUser() {
        System.out.print("Name?> ");
        String userGivenOwnersName;
        boolean flag = false;
        do {
            if (flag) System.out.println("Error: name can not be empty");
            flag = true;
            userGivenOwnersName = userInput.nextLine().trim().toLowerCase();

        } while (userGivenOwnersName.length() == 0);

        User owner = new User(userGivenOwnersName);
        System.out.println(userGivenOwnersName.trim().toLowerCase() + "  added to register");
        addInArray(owner);
    }

    private void addInArray(User owner) {
        User[] temp = new User[sz + 1];
        for (int i = 0; i < sz; i++) {
            temp[i] = users[i];
        }
        temp[sz] = owner;
        users = temp;
        sz++;
    }

    // program charshes .*****
    private void listUsers() {
        if (sz == 0) {
            System.out.println("Error: no user in register");
        }
        for (int i = 0; i < sz; i++) {
            System.out.print(users[i].getUserName() + " [");
            for (int j = 0; j < users[i].getSz(); j++) {
                System.out.print(users[i].getDog(j).getName());
                if (j != users[i].getSz() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }

    }

    private void givedogcommad() {
        String dogname;
        String username;     //checking if the dog is available
        int dogindex = -1;
        int userindex = -1;
        System.out.print("Enter the name of the dog?> ");
        dogname = userInput.nextLine().trim().toLowerCase();

        dogindex = checkregiterdog(dogname);

        if (dogindex == NOT_IN_REGISTER) {
            System.out.println("Error: no dog with that name");
        } else if (dogindex == -1) {
            System.out.println("Error: the dog has already owner");
        } else {
            System.out.print("Enter the name of the new owner?> ");
            username = userInput.nextLine().trim().toLowerCase();
            userindex = checkregiteruser(username);
            if (userindex == -1) {
                System.out.println("Error: No user with that name");
            } else {
                givedog(dogname, username);
            }
        }
    }

    private void givedog(String dogname, String username) {

        int dogindex = -1;
        int userindex = -1;

        dogindex = checkregiterdog(dogname);

        userindex = checkregiteruser(username);

        Dog[] temp = new Dog[users[userindex].getSz() + 1];
        for (int i = 0; i < users[userindex].getSz(); i++) {
            temp[i] = users[userindex].getDog(i);
        }
        temp[users[userindex].getSz()] = dogs.get(dogindex);
        users[userindex].setDogs(temp);
        users[userindex].setSz(users[userindex].getSz()+1);
        System.out.println(username + " now owns " + dogname);
        dogs.get(dogindex).setOwnername(users[userindex].getUserName());


    }
    //auction

    private int checkregiterdog(String dogname) {
        int indx = NOT_IN_REGISTER;
        for (int i = 0; i < dogs.size(); i++) {
            if (dogname.equals(dogs.get(i).getName())) {
                if (dogs.get(i).getOwnerName().length() > 0) {
                    indx = -1;
                    break;
                }
                indx = i;
                break;
            }
        }
        return indx;
    }

    private int getdogindex(String dogname) {
        for (int i = 0; i < dogs.size(); i++) {
            if (dogname.equals(dogs.get(i).getName())) {
                return i;
            }
        }
        return -1;
    }

    private int checkregiteruser(String username) {
        int userindex = -1;
        for (int i = 0; i < sz; i++) {
            if (username.equals(users[i].getUserName())) {
                userindex = i;
                break;
            }
        }
        return userindex;
    }

    private int getauctionnumber() {
        mxAuction++;
        return mxAuction ;
    }

    private int checkAuction(String dogname) {
        for (int i = 0; i < auctions.size(); i++) {
            if (dogname.equals(auctions.get(i).getDogname())) {
                return i;
            }
        }
        return -1;
    }

    private void startAuction() {
        System.out.print("Enter the name of the dog?> ");
        String dogname;
        dogname = userInput.nextLine().toLowerCase().trim();
        int indx = checkregiterdog(dogname);

        if (checkAuction(dogname) != -1) {
            System.out.println("Error: this dog is already up for auction");
        } else if (indx >= 0) {
            int number = getauctionnumber();
            Auction temp = new Auction(dogname, number);
            auctions.add(temp);
            System.out.println(dogname + " has been put up for auction in auction #" + number);
        } else if (indx == -1) {
            System.out.println("Error: this dog already has an owner");
        } else {
            System.out.println("Error: no such dog");
        }
    }

    private void makeBid() {
        String username;
        String dogname;
        int bidAmount;
        System.out.print("Enter the name of the user?> ");
        username = userInput.nextLine().toLowerCase().trim();
        if (checkregiteruser(username) == -1) {
            System.out.println("Error: no such user");
            return;
        }
        System.out.print("Enter the name of the dog?> ");
        dogname = userInput.nextLine().trim().toLowerCase();
        if (checkregiterdog(dogname) == NOT_IN_REGISTER) {
            System.out.println("Error: no such dog");
            return;
        } else if (checkAuction(dogname) == -1) {
            System.out.println("Error:This dog is not up for auction");
            return;
        }else if (checkregiterdog(dogname) == -1) {
            System.out.println("Error: the dog already has an owner");
            return;
        }

        makebidprocess(username,dogname);
    }

    private void makebidprocess(String username,String dogname)
    {
        int bidAmount;
        int indx = checkAuction(dogname);
        boolean flag = false;
        do {
            if (flag) {
                System.out.println("Error: too low bid!");
            }
            flag = true;
            System.out.print("Amount to bid (min "+ auctions.get(indx).minimumbid() +")?> ");
            bidAmount = userInput.nextInt();
        } while (bidAmount < auctions.get(indx).minimumbid());
        userInput.nextLine();
        if (auctions.get(indx).checkbidder(username) >= 0) {
            int bidderindx = auctions.get(indx).checkbidder(username);
            auctions.get(indx).replace(bidderindx, bidAmount);
        } else {
            Bid temp = new Bid(username, bidAmount);
            auctions.get(indx).addBidder(temp);
        }
        System.out.println("Bid Made");
    }

    private void listAuction() {
        if (auctions.size() == 0) {
            System.out.println("Error: no auctions in progress");
        } else {
            for (int i = 0; i < auctions.size(); i++) {
                auctions.get(i).printAuction();
            }
        }
    }

    private void listbid()
    {
        System.out.print("Enter the name of the dog?>");
        String dogname = userInput.nextLine().toLowerCase().trim();
        int indx = checkAuction(dogname);
        if (indx == -1) {
            System.out.println("Error: this dog is not up for auction");
        } else {
            if (!auctions.get(indx).bidderSize()) {
                System.out.println("No bids registred yet for this auction");
            } else {
                System.out.println("Here are the bids for this auction");
                auctions.get(indx).printbid();
            }
        }
    }

    private void closeAuction() //checking the name for the dog to close auction for.
    {
        System.out.print("Enter the name of the dog?>");
        String dogname;
        dogname = userInput.nextLine().trim().toLowerCase();
        if (checkregiterdog(dogname) == NOT_IN_REGISTER) {
            System.out.println("Error: this dog is not up for auction");
            return;
        }
        closeAuctionProcess(dogname);
    }

    private void closeAuctionProcess(String dogname)
    {
        Bid winner = new Bid();
        boolean flag = true;
        for (int i = 0; i < auctions.size(); i++) {
            if (auctions.get(i).getDogname().equals(dogname)) {
                winner = auctions.get(i).findWinner();
                flag = false;
                auctions.remove(i);
                break;
            }
        }
        if (flag) {
            System.out.println("Error: this dog is not up for auction");
        } else if (winner.getBidAmount() == 0) {
            System.out.println("The auction is closed. No bids where made for " + dogname);
        } else {
            System.out.println("The auction is closed. The winning bid was " + winner.getBidAmount() + "kr and was made by " + winner.getName());
            givedog(dogname, winner.getName());
        }
    }

// methods for commando-------------------------

    private void runCommandoLoop() {
        String command;
        do {
            command = readCommando();
            handleCommando(command);
        } while (!command.equals("exit"));
    }

    private void handleCommando(String command) {

        switch (command) {

            case "register new dog":
                registerDog();
                break;
            case "register new user":
                registerUser();
                break;
            case "increase age":
                increaseAge();
                break;
            case "list dogs":
                listDogs();
                break;
            case "list users":
                listUsers();
                break;
            case "remove dog":
                removeDog();
                break;
            case "remove user":
                removeUser();
                break;
            case "give dog":
                givedogcommad();
                break;
            case "start auction":
                startAuction();
                break;
            case "make bid":
                makeBid();
                break;
            case "list auctions":
                listAuction();
                break;
            case "list bids":
                listbid();
                break;
            case "close auction":
                closeAuction();
                break;
            case "exit":
                break;
            default:
                defaultmessege();
        }
    }

    private String readCommando() {
        System.out.println("Kommanndo?> ");
        String command = userInput.nextLine();
        return command.trim().toLowerCase();
    }

    private void initialize() {
        System.out.println("Hej och välkommen till hundprogrammet! ");
        System.out.println("register new dog");
        System.out.println("increase age: ");
        System.out.println("list dogs ");
        System.out.println("register new user");
        System.out.println("list users ");
        System.out.println("remove user : ");
        System.out.println("remove dog");
        System.out.println("give dog");
        System.out.println("start auction");
        System.out.println("make bid");
        System.out.println("list bids");
        System.out.println("list auctions");
        System.out.println("exit");
    }

    private void defaultmessege()
    {
        System.out.println("Fel, inte ett kommando!");

    }

}

