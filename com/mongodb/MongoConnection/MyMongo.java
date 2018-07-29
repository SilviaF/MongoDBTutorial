import com.mongodb.*;

import java.util.List;
import static java.util.Arrays.asList;

public class MyMongo {

    public DB myDB = null;
    public MongoClient mongoClient = null;
    public DBCollection dbCollection = null;

    public static void main(String[] args) {

        MyMongo myMongo = new MyMongo();

        try {
            myMongo.getConnection();
            myMongo.mongoClient = new MongoClient();
            myMongo.myDB = myMongo.getDatabase("TestDB");
            myMongo.dbCollection = myMongo.getDBCollection("TestCollection");

            System.out.println("Database name: " + myMongo.myDB.getName());
            System.out.println("Collection name: " + myMongo.dbCollection.getName());

            //Create User
            LibraryUser kyloRen = new LibraryUser("kylo","Kylo Ren", 31, new Address("Star Destroyer", "Tatooine","09SD14"), asList(2435184, 2435548));
            kyloRen.setPhone(31415926535L);
            System.out.println(kyloRen.getAllInfo());

            //Insert adapted user to mongoDB
            myMongo.dbCollection.insert(UserAdaptor.toDBObject(kyloRen));



            //Create new document
//            DBObject person = createDocument(
//                    Arrays.asList(27464, 747854, 314159),
//                    "jo",
//                    "Jo Bloggs",
//                    "123 Fake St",
//                    "Faketon",
//                    "MA",
//                    "12345");
//            myMongo.dbCollection.insert(person);
//
//            DBObject penguin = createDocument(
//                    Arrays.asList(27464, 747854, 314159),
//                    "mo",
//                    "Mo the Penguin",
//                    "123 Fake St",
//                    "New Zealand",
//                    "NA",
//                    "12345");
//
//            myMongo.dbCollection.insert(penguin);

        } catch (Exception e) {
            e.getMessage();
        } finally {
            myMongo.closeConnection();
        }

    }

    public void getConnection() throws Exception {
        this.mongoClient = new MongoClient();
    }

    /**
     * Method to close a given Mongo client connection
     */
    public void closeConnection() {
        System.out.println("Closing connection...");
        this.mongoClient.close();
        System.out.println("Connection closed.");
    }

    public DB getDatabase(String dbName) {
        return this.mongoClient.getDB(dbName);
    }

    public DBCollection getDBCollection(String collectionName) {
        return this.myDB.getCollection(collectionName);
    }

    public static DBObject createDocument(List<Integer> books, String id, String name, String street, String city, String state, String zip) {
        DBObject document = new BasicDBObject("_id", id)
                .append("name", name)
                .append("address", new BasicDBObject("street", street)
                        .append("city", city)
                        .append("state", state)
                        .append("zip", zip))
                .append("books", books);

        return document;
    }

}
