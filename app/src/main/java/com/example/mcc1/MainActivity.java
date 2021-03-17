package com.example.mcc1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class MainActivity extends AppCompatActivity {
    EditText editName;
    EditText editAddress;
    EditText editPhone;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    contactsAdapter adapter;
    ArrayList<contacts> contacts;
    ListView listViewContact;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editName = findViewById(R.id.TV1);
        editAddress = findViewById(R.id.TV2);
        editPhone = findViewById(R.id.TV3);
        listViewContact = findViewById(R.id.contactLv);
        contacts = new ArrayList<>();
    }

    public void saveToFirebase(View v) {
        String Name = editName.getText().toString();
        String PhoneNumber = editPhone.getText().toString();
        String Address = editAddress.getText().toString();


        Map<String, Object> contact = new HashMap<>();
        contact.put("contantName", Name);
        contact.put("contactNumber", PhoneNumber);
        contact.put("contactAddress", Address);
        db.collection("contacts")
                .add(contact)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "The addition succeeded .... " + documentReference.getId());
                    }
                })                .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("TAG", "Added Error ......", e);
            }
        });

        db.collection("contacts")

                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for(DocumentSnapshot userSnapshot : task.getResult()){
                                String name = (String) userSnapshot.get("name");
                                String phone = (String) userSnapshot.get("phone");
                                String address = (String) userSnapshot.get("address");

                                contacts user = new contacts(name,phone,address);
                                contacts.add(user);
                            }
                            adapter=new contactsAdapter(contacts,MainActivity.this);
                            listViewContact.setAdapter(adapter);

                        } else {
                            Log.w("TAG", "Faild.", task.getException());
                        }
                    }
                });




    }
}