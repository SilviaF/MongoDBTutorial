import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class UserAdaptor {

    public static final DBObject toDBObject (LibraryUser user){
        return new BasicDBObject("_id", user.getId())
                .append("name", user.getName())
                .append("address", new BasicDBObject("street", user.getStreet())
                    .append("city", user.getCity())
                    .append("phone", user.getPhone()))
                .append("books", user.getBooks());
    }

}
