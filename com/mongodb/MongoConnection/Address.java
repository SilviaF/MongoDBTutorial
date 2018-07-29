public class Address {

    private String street;
    private String city;
    private String zip;

    public Address(String street, String city, String zip){
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

    public String getZip(){
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

    public void setZip(String zip){
        this.zip = zip;
    }


}
