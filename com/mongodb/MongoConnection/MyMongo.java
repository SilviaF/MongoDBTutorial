import com.mongodb.*;

import java.util.List;
import java.util.Map;

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
            LibraryUser newUser = createUser(
                    "Id4",
                    "Kylo Ren",
                    20,
                    new Address(
                            "Street Address",
                            "City",
                            121345),
                    asList(27464, 747854),
                    48951386);

            //Insert adapted user to mongoDB
//            insertUserIntoDB(myMongo.dbCollection, newUser);

            System.out.println(newUser.getAllInfo());

            //Retrieve document by Id from mongoDB
            DBObject userFound = getUserByID(myMongo.dbCollection, "anId");
            System.out.println("Name retrieved by ID: " + userFound.get("name"));

            //Retrieve document(s) by key from mongoDB
            DBCursor results = getUserByKey(myMongo.dbCollection, "name", "Kylo Ren");
            System.out.println("Records retrieved by key: " + results.size());

            for (DBObject result : results){
                System.out.println("Documents retrieved: " + result.toString());
            }

            System.out.println("------------------------------------------");

            results = getSpecificValueByKey(myMongo.dbCollection, "name", "Kylo Ren", "phone");
            for (DBObject result : results){
                System.out.println("Documents retrieved: " + result.toString());
            }




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
            System.err.println("Error encountered: " + e.getMessage());
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

    public static LibraryUser createUser(String id, String name, int age, Address address, List<Integer> books, long phone) {
        LibraryUser libraryUser = new LibraryUser(id, name, age, address, books, phone);
        return libraryUser;
    }

    public static void insertUserIntoDB(DBCollection collection, LibraryUser libraryUser) {
        collection.insert(UserAdaptor.toDBObject(libraryUser));
    }

    public static DBObject getUserByID(DBCollection collection, String userID) {
        DBObject query = new BasicDBObject("_id", userID);
        DBCursor cursor = collection.find(query);
        DBObject userFound = cursor.one();
        return userFound;
    }

    public static DBCursor getUserByKey(DBCollection collection, String key, String value){
        DBCursor results = collection.find(new BasicDBObject(key, value));
        return results;
    }

    public static DBCursor getSpecificValueByKey(DBCollection collection, String key, String value, String output){
        DBCursor results = collection.find(new BasicDBObject(key, value),
                new BasicDBObject(output, 1));
        return results;
    }

}
