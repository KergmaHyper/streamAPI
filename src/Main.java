import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
    Stream<Car> store1 = Stream.of(new Car("WV Polo",27000),
                                   new Car("AUDI A7",60000),
                                   new Car("Hnd SantaFe",55000),
                                   new Car("Chvrlt Tacuma",23000));

    store1.filter(s->s.getPrice()<30000).forEach(s->System.out.printf("30000 > %s ; ",s.getName()));
    store1.close();
    store1 = Stream.of(new Car("WV Polo",27000),
                new Car("AUDI A7",60000),
                new Car("Hnd SantaFe",55000),
                new Car("Chvrlt Tacuma",23000));
    store1.map(x->x.getName()+" "+x.getPrice()).forEach(s->System.out.println(s));
    store1.close();
    store1 = Stream.of(new Car("WV Polo",27000),
                new Car("AUDI A7",60000),
                new Car("Hnd SantaFe",55000),
                new Car("Chvrlt Tacuma",23000));
    store1.flatMap(c->Stream.of(
            String.format(c.getName()+": "+c.getPrice()),
            String.format(c.getName()+"+2000: "+(int)(c.getPrice()+2000))
    )).forEach(s->System.out.println(s));

    //store1.filter(s->s.getPrice()<30000).forEach(s->System.out.printf("30000 > %s ",s.getName()));

    }
}

class Car {
    private String name;
    private int price;

    Car(String name, int price){this.name=name; this.price=price;}

    public String getName(){return name;}
    public int getPrice(){return price;}

}