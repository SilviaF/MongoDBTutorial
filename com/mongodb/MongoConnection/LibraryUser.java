import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class LibraryUser {

    private String id;
    private String name;
    private int age;
    private long phone;
    private List<Integer> books;
    private Address address;

    public LibraryUser(String id, String name, int age, Address address, List<Integer> books, long phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.books = books;
        this.phone = phone;
    }


    /************************
     getters
     ************************/

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getPhone() {
        return phone;
    }

    public List<Integer> getBooks() {
        return books;
    }

    public String getStreet() {
        return address.getStreet();
    }

    public String getCity() {
        return address.getCity();
    }

    public long getZip() {
        return address.getZip();
    }

    public List<String> getAllInfo() {
        List<String> allInfo = new ArrayList<String>();
        String age = Integer.toString(getAge());
        allInfo.add(name);
        allInfo.add(age);
        allInfo.add(address.getStreet());
        allInfo.add(address.getCity());
        allInfo.add(Long.toString(address.getZip()));
        allInfo.add(books.toString());
        allInfo.add(Long.toString(phone));
        return allInfo;
    }

    /************************
     setters
     ************************/

    public void setAddress(String street, String city, long zip) {
        this.address.setStreet(street);
        this.address.setCity(city);
        this.address.setZip(zip);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStreet(String street) {
        this.address.setStreet(street);
    }

    public void setCity(String city) {
        this.address.setCity(city);
    }

    public void setZip(long zip) {
        this.address.setZip(zip);
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setBooks(Integer bookId) {
        this.books.add(bookId);
    }

}
