import java.util.ArrayList;

import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> otd = new ArrayList<>();

        otd.add("feo");
        otd.add("ek");
        otd.add("it");
        otd.add("inkass");
        otd.add("buh");
        otd.add("zahist");
        Collections.addAll(otd,"bud","mmsb","okr","corp","bezpeka","HR","back","valut","finmon");

        otd.stream().filter(s->s.length()<3).sorted().forEach(s->System.out.println(s));


    }
}
