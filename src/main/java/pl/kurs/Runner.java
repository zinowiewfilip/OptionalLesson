package pl.kurs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Runner {
    public static void main(String[] args) {

        System.out.println(getEmployeeByID(2l));

        getEmployeeByID(1l).ifPresent(x -> System.out.println(x.hashCode()));

        List<Employee> employeeList = null; //new ArrayList<>();

        printListSize(employeeList);


        Employee emp1 = new Employee("Karol", "Nowak");
        Employee emp2 = null;

        //Tworzenie Optionali
        //Metoda of() - przy próbie stworzenia optionala z null'a rzuci wyjątek
        Optional<Employee> opt1 = Optional.of(emp1);
        System.out.println(opt1);
        //Optional<Employee> opt2 = Optional.of(emp2); //przy próbie utworzenia wywala NullPointerException


        //Metoda ofNullable() - utworzenie optionala z czegoś co może być null'em
        Optional<Employee> opt3 = Optional.ofNullable(emp1);
        Optional<Employee> opt4 = Optional.ofNullable(emp2); //tworzy pustego optional'a
        System.out.println(opt3);
        System.out.println(opt4);

        //Metoda empty()

        Optional<Employee> opt5 = Optional.empty();
        System.out.println(opt5);

        System.out.println("//////////////////");

        //Metoda isPresent() / isEmpty() - zwraca boola, czy Optional jest pełny/pusty

        System.out.println(opt3.isEmpty());
        System.out.println(opt4.isPresent());

        //Metoda get() - zwraca zawartosc Optionala (nieformalny zakaz używania tej metody)

        //Employee e1 = opt4.get();
        //System.out.println(e1);

        //Metoda ifPresent() - metoda void'owa, która podejmuje działanie typu Consumer, jeżeli Optional jest pełny

        opt3.ifPresent(x -> System.out.println("Zawartość Optionala obecna: " + x ));

        //Metoda orElse() - zwraca zawartość Optionala jeżeli jest obecny, a jezeli jest pusty to zwraca obiekt przyjęty wprost

        Employee e2 = opt4.orElse(new Employee("Kubus", "Puchatek"));
        System.out.println(e2);

        //Metoda orElseGet() - robi to samo co orElse() ale w argumencie przyjmuje Supplier'a

        Employee e3 = opt3.orElseGet(() -> new Employee("Gargamel", "Podły"));

        //Metoda orElseThrow() - zwraca zawartość Optionala kiedy będzie pełny, a kiedy będzie pusty rzuca wyjątek

        //Employee e4 = opt4.orElseThrow(() -> new RuntimeException());
        //System.out.println(e4);

        //Metoda ifPresentOrElse() - metoda void'owa, podejmuje 2 scenariusze, odpowiednik ifologi.

        opt4.ifPresentOrElse(x -> System.out.println("Optional jest pełny"), () -> System.out.println("Optional pusty"));

        //Metoda or() - w przypadku pustego Optionala, zwraca nowego Optionala

        opt4 = opt4.or(() -> Optional.of(new Employee("Mateusz", "Morawiecki")));
        System.out.println(opt4);


        String pesel = "11111111111";
        pesel = Optional.ofNullable(pesel)
                .filter(x -> x.matches("\\d{11}"))
                .orElseThrow(() -> new RuntimeException("Błędny pesel"));
        System.out.println(pesel);
    }
    public static Optional<Employee> getEmployeeByID(long id){
        if(id == 1)
            return Optional.of(new Employee("Adam", "Nowak"));
        else
            return Optional.empty();
    }
    public static <T> void printListSize(List<T> list){
        list = Optional.ofNullable(list).orElseGet(Collections::emptyList);
        System.out.println("list.size() = " + list.size());
    }
}
