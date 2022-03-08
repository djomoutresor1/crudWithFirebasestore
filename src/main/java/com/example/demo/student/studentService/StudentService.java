package com.example.demo.student.studentService;

import com.example.demo.student.Student;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Tresor Djomou",
                        "djomouetresor@gmail.com",
                        "2000/08/05",
                        37

                )
        );
    }
    // studentTable : nome della tabella su firebase
    public static final String COL_NAME="studentTable";

    public String saveStudentDetails(Student student) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(student.getName()).set(student);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public Student getStudentDetails(String name) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection(COL_NAME).document(name);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        Student student = null;

        if(document.exists()) {
            student = document.toObject(Student.class);
            return student;
        }else {
            return null;
        }
    }

    public String updateStudentDetails(Student student) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COL_NAME).document(student.getName()).set(student);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public String deleteStudent(String name) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection(COL_NAME).document(name).delete();
        return "Document with Student name "+name+" has been deleted";
    }
}
