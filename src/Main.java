import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
      //  ArrayList<String> cities = new ArrayList<String>();
      //  Collections.addAll(cities, "Париж", "Лондон", "Мадрид");
        Car car1 = new Car("ZAZ","Slavuta",5000);

        ArrayList<Car> catalog = new ArrayList<Car>();
        catalog.add( new Car("WV","Polo",27000));
        catalog.add( new Car("ZAZ","Lanos",5000));
        catalog.add( new Car("AUDI","A7",60000));
        catalog.add( new Car("Huinday","SantaFe",47000));
        catalog.add( new Car("Chvrlt","Tacuma",23000));
        catalog.add( new Car("Chvrlt","Aveo",15000));
        catalog.add( new Car("Chvrlt","Lacceti",25000));
        catalog.add( new Car("Chvrlt","Camaro",45000));
        catalog.add(car1);
        catalog.add( new Car("WV","Passat",37000));
        catalog.add( new Car("WV","Tiguan",62000));


       /* catalog.stream().
                sorted(new CarPriceUpComparator()).
                dropWhile(s->s.getPrice()<20000).
                takeWhile(s->s.getPrice()<40000).
                forEach(s->System.out.println(s.getVendor()+" "+s.getModel()+" "+s.getPrice()));

*/

        Stream<Car> car2=Stream.of( new Car("ZAZ","Lanos",9000)
                                    ,new Car("ZAZ","Tavria Nova",5000)
                                    ,new Car("ZAZ","Sens",7500)
                                    ,new Car("ZAZ","Slavuta",6500)
                                    ,new Car("AUDI","TT",75000)
        );

        ArrayList<Car> arCar3 = new ArrayList<>();
        arCar3.addAll(Arrays.asList( new Car("VAZ","Niva",12000)
                                    ,new Car("VAZ","Priora",14000)
                                    ,new Car("VAZ","Kalina",11000)
        ));

       /* Stream.concat(
         arCar3.stream().sorted(new CarPriceUpComparator()).takeWhile(s->s.getPrice()<40000),
         car2.sorted(new CarPriceUpComparator()).dropWhile(s->s.getPrice()<6000)
        ).
                sorted(new CarVendorComparator()).
        forEach(s->System.out.println(s.getVendor()+" "+s.getModel()+" "+s.getPrice()));
*/
     /* Optional<Car> maxPrice = arCar3.stream().max(new CarVendorComparator());
      System.out.println(maxPrice.get().getVendor() +" " +maxPrice.get().getModel()+
              " "+maxPrice.get().getPrice());
*/

        //Map<String, List<Car>> carVendors =
        Map<Boolean, List<Car>> carVendorsBool =
                Stream.concat(Stream.concat(catalog.stream(),arCar3.stream()),car2)
                .collect(Collectors
                //.groupingBy(s->s.getVendor())
                .partitioningBy( x->x.getVendor().equals("ZAZ"))
                );


        //for( Map.Entry<String,List<Car>> ptr : carVendors.entrySet() ){
        for( Map.Entry<Boolean,List<Car>> ptr : carVendorsBool.entrySet() ){
            if (ptr.getKey()){
                System.out.println("VENDOR: ZAZ");}
                else{System.out.println("VENDOR: NO ZAZ");};

            for ( Car c : ptr.getValue() ){
                 System.out.println(c.getModel()+" "+c.getPrice());
            }

            int i = ptr.getValue().stream()
                    .reduce(0, (x,y)->{ return x+y.getPrice();} ,(x,y)->x+y );
            System.out.println("Total : "+i);
            System.out.println("------------------------------------------------");

        }



    }
}




class Car {
    private String model;
    private String vendor;
    private int price;

    Car(String vendor, String model, int price){
        this.model=model;
        this.vendor=vendor;
        this.price=price;}

    public String getModel(){return model;}
    public String getVendor(){return vendor;}
    public int getPrice(){return price;}

}
class CarVendorComparator implements Comparator<Car>{
    @Override
    public int compare(Car car1, Car car2) {
        return car1.getVendor().compareToIgnoreCase(car2.getVendor());
    }
}
class CarModelComparator implements Comparator<Car>{
    @Override
    public int compare(Car car1, Car car2) {
        return car1.getModel().compareToIgnoreCase(car2.getModel());
    }
}
class CarPriceUpComparator implements Comparator<Car> {
    @Override
    public int compare(Car car1, Car car2) {
        int calc = car1.getPrice() - car2.getPrice();
        int ret = 0;
        if (calc > 0) ret = 1;
        if (calc < 0) ret = -1;
        return ret;
    }
}
    class CarPriceDownComparator implements Comparator<Car>{
        @Override
        public int compare(Car car1, Car car2) {
            int calc = car2.getPrice() - car1.getPrice();
            int ret=0;
            if (calc > 0) ret = 1;
            if (calc < 0) ret = -1;
            return ret;
        }

    }