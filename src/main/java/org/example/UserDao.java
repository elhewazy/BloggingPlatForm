package org.example;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class UserDao {

    MongoCollection<Document> userCollection;
    UserDao(MongoDatabase blogDB){
        userCollection = blogDB.getCollection("users");
    }

    public boolean addUser(String name ,String email,String password){
        Document user = new Document();
        user.append("email",email).append("password",password).append("name",name);
        try{
        userCollection.insertOne(user);
        return true;
        }catch (MongoWriteException e) {
            if (e.getError().getCategory().equals(ErrorCategory.DUPLICATE_KEY)) {
                System.out.println("user Already in use");
            }
            throw e;
        }
    }

    public Document getuser(String name ,String password){
        Document user;
        user = userCollection.find(eq("name",name)).first();
        if(user == null ){
            return null;
        }


        return user;
    }
}
