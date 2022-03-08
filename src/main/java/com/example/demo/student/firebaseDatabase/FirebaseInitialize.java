package com.example.demo.student.firebaseDatabase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

    @Service
    public class FirebaseInitialize {

        // connect to firebase firestore
        @PostConstruct
        public void initialize() {
            try {
                FileInputStream serviceAccount =
                        new FileInputStream("./connectDataBase/connectFirebase.json");

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://fir-902a9-default-rtdb.firebaseio.com")
                        .build();

                FirebaseApp.initializeApp(options);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


