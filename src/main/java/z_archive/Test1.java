package z_archive;

import java.util.ArrayList;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("Abba");
        names.add("Aaron");
        names.add("Genna");
        names.add("Rim");
        names.add("Bam");
        int count = 0;

        for (int i = 0; i < names.size(); i++) {
            String currentName  = names.get(i);
            if (currentName.startsWith("A")) {
                count++;

            }
        }
        System.out.println(count);
    }
}
