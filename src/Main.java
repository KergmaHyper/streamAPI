import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

    ArrayList<Integer> ar1 = new ArrayList<>();
    Collections.addAll(ar1,  1,2,3,4,5,6,7,8,9,10);
    Stream<Integer> str1 = ar1.stream();

    Stream<Integer> str2 = Stream.of(1,2,3,4,5,6,7,8,9,10);



    Optional<Integer> res = str1.parallel().reduce((x,y)->(x+y));
    System.out.println(res.get());



    int res2 = str2.parallel().reduce(0,
            (x,y)->{
                    if ((y%2)==0) {
                      //  System.out.println(y%2+" "+(int)(y));
                        return x+y;}
                    else{
                       // System.out.println(y%2+" "+(int)(-y));
                        return x-y;
                    }
                    }
    //                ,(x,y)->x+y
    );

    System.out.println(res2);

    }
}



