import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;

import org.junit.Test;
import java.net.UnknownHostException;

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

}
