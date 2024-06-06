package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

import static com.mongodb.client.model.Filters.eq;

public class BloggingDao {
    MongoCollection<Document> blogCollections ;
    //
    String connString = "mongodb://localhost:27017";
    String dataBase = "blogging";
    String collection = "blog";
    public BloggingDao(){
MongoClient mongoClient = MongoClients.create(connString);
MongoDatabase mongoDatabase = mongoClient.getDatabase(dataBase);

blogCollections= mongoDatabase.getCollection("blog");

    }
public void addPost(String title , String body,String userName){
        Document post = new Document("title", title).append("name",userName).append("body",body).append("comments",new ArrayList<>()).append("date",new Date());
        blogCollections.insertOne(post);

}
    public void addPostComment(final String name, final String email, final String body, final String permalink) {
        Document comment = new Document("author", name)
                .append("body", body);

        if (email != null && !email.isEmpty()) {
            comment.append("email", email);
        }

        blogCollections.updateOne(eq("permalink", permalink),
                new Document("$push", new Document("comments", comment)));
    }

}
