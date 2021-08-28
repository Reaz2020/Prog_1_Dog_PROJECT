import java.util.ArrayList;
// Md Reaz Morshed mdmo9317
public class DogSorter {
    public void sort(ArrayList<Dog> dogs) {

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
}