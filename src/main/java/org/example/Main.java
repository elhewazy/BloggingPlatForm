package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class Main {
    static String connString = "mongodb://localhost:27017";
    static String dataBase = "blogging";
    static MongoClient mongoClient = MongoClients.create(connString);
    static MongoDatabase mongoDatabase = mongoClient.getDatabase(dataBase);
    String collection = "blog";
    public static void userAddAndValidation(){
        System.out.println("please enter your username ");
        Scanner sc = new Scanner(System.in);

        String userName = sc.nextLine();
        System.out.println("please enter your email ");
        String email = sc.nextLine();
        System.out.println("please enter you password ");
        String password = sc.nextLine();

        UserDao addUser = new UserDao(mongoDatabase );
        addUser.addUser(userName,email,password);

        System.out.println("enter your name ");
        String myName= sc.nextLine();
        System.out.println("enter your password");
        String mypassword= sc.nextLine();
        Document check = addUser.getuser(myName,mypassword);
        System.out.println(check.get("password"));
        System.out.println(check.get("name"));
    }
    public static void main(String[] args) {

BloggingDao bd = new BloggingDao();
        MongoCollection<Document> collection = mongoDatabase.getCollection("blog");
        Scanner sc = new Scanner(System.in);
System.out.println("write the title");
        String title= sc.nextLine();
        System.out.println("write the title");
        String name= sc.nextLine();
        System.out.println("write the title");
        String body= sc.nextLine();
        bd.addPost(title,name,body);



    }
}