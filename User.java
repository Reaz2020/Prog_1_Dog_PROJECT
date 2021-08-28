public class User {
    // Md Reaz Morshed mdmo9317
    private String ownerName;
    private Dog[] dogs;
    private int sz;

    //Attributes------------------------
    User(String naMe) {
        this.ownerName = naMe;
        sz=0;
    }

    //getter and setter-----------------
    String getUserName() {
        return ownerName;
    }
    public void setName(String name) {
        this.ownerName = name;
    }
    public int getSz(){return sz;}
    public void setSz(int x){sz=x;}

    public void setDogs(Dog[] dogs) {
        this.dogs = dogs;
    }

    public Dog getDog(int i){
        return dogs[i];
    }

    public void setDogAtIndex(int i,Dog temp)
    {
        dogs[i]=temp;
    }

    @Override
    public  String toString() {
        return " (Name : " + ownerName + ")";
    }



}
