import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MyMongo {

    public DB myDB = null;
    public MongoClient mongoClient = null;
    public DBCollection dbCollection = null;

    public static void main(String[] args) {

        MyMongo myMongo = new MyMongo();

        try{
            myMongo.getConnection();
            myMongo.mongoClient = new MongoClient();
            myMongo.myDB = myMongo.getDatabase("TestDB");
            myMongo.dbCollection = myMongo.getDBCollection("TestCollection");

        } catch (Exception e){
            e.getMessage();
        } finally {
            myMongo.closeConnection();
        }

        System.out.println("Database name: " + myMongo.myDB.getName());
        System.out.println("Collection name: " + myMongo.dbCollection.getName());

    }

    public void getConnection() throws Exception{
        this.mongoClient = new MongoClient();
    }

    /**
     * Method to close a given Mongo client connection
     */
    public void closeConnection(){
        System.out.println("Closing connection...");
        this.mongoClient.close();
        System.out.println("Connection closed.");
    }

    public DB getDatabase(String dbName){
        return this.mongoClient.getDB(dbName);
    }

    public DBCollection getDBCollection(String collectionName){
        return this.myDB.getCollection(collectionName);
    }


}
