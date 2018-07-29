public class Address {

    private String street;
    private String city;
    private long zip;

    public Address(String street, String city, long zip){
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    /************************
     getters
     ************************/
    public String getStreet(){
        return this.street;
    }

    public String getCity(){
        return this.city;
    }

    public long getZip(){
        return this.zip;
    }

    /************************
     setters
     ************************/
    public void setStreet(String street){
        this.street = street;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setZip(long zip){
        this.zip = zip;
    }


}
