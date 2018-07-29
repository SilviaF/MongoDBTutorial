import com.mongodb.*;
import org.junit.Test;

import java.net.UnknownHostException;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;


public class MongoTests {

    MyMongo mongoTest = null;
    MongoClient mongoClient = null;

    @Test
    public void shouldCreateANewMongoClientConnectedToLocalhost() throws Exception {
        // When
        mongoTest = new MyMongo();
        mongoTest.getConnection();

        // Then
        assertThat(mongoTest, is(notNullValue()));
    }

    @Test
    public void shouldGetADatabaseFromTheMongoClient() throws Exception {
        // Given
        mongoTest = new MyMongo();
        mongoTest.getConnection();

        // When
        mongoTest.myDB = mongoTest.getDatabase("TestDB");

        // Then
        assertThat(mongoTest.myDB, is(notNullValue()));
    }

    @Test
    public void shouldGetACollectionFromTheDatabase() throws Exception {
        // Given
        mongoTest = new MyMongo();
        mongoTest.getConnection();

        mongoTest.myDB = mongoTest.getDatabase("TestDB");;

        // When
        mongoTest.dbCollection = mongoTest.getDBCollection("TestCollection");

        // Then
        assertThat(mongoTest.dbCollection, is(notNullValue()));
    }

    @Test(expected = Exception.class)
    public void shouldNotBeAbleToUseMongoClientAfterItHasBeenClosed() throws UnknownHostException {
        // Given
        MongoClient mongoClient = new MongoClient();

        // When
        mongoClient.close();

        // Then
        mongoClient.getDB("SomeDatabase").getCollection("coll").insert(new BasicDBObject("field", "value"));
    }

    @Test
    public void shouldTurnAPersonIntoADBObject() {
        // Given
        LibraryUser bob = new LibraryUser("bob", "Bob The Amazing", 29, new Address("123 Fake St", "LondonTown", "1234567890"), asList(27464, 747854));
        bob.setPhone(1234567890L);

        // When
        DBObject bobAsDBObject = UserAdaptor.toDBObject(bob);

        // Then
        String expectedDBObject = "{" +
                " \"_id\" : \"bob\" ," +
                " \"name\" : \"Bob The Amazing\" ," +
                " \"address\" : {" +
                " \"street\" : \"123 Fake St\" ," +
                " \"city\" : \"LondonTown\" ," +
                " \"phone\" : 1234567890" +
                "} ," +
                " \"books\" : [ 27464 , 747854]" +
                "}";
        assertThat(bobAsDBObject.toString(), is(expectedDBObject));
    }

    @Test
    public void shouldBeAbleToSaveAPerson() throws UnknownHostException {
        // Given
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB database = mongoClient.getDB("Examples");
        DBCollection collection = database.getCollection("people");

        LibraryUser charlie = new LibraryUser("charlie", "Charles", 58, new Address("74 That Place", "LondonTown", "1234567890"), asList(1, 74));

        // When
        collection.insert(UserAdaptor.toDBObject(charlie));

        // Then
        assertThat(collection.find().count(), is(1));

        // Clean up
        database.dropDatabase();
    }

}