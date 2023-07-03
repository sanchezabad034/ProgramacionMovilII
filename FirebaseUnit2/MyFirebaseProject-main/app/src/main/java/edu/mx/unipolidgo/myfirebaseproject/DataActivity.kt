package edu.mx.unipolidgo.myfirebaseproject

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.model.Document
import edu.mx.unipolidgo.myfirebaseproject.databinding.ActivityDataBinding

const val TAG = "FIRESTORE"

class DataActivity : AppCompatActivity() {

    private var binding: ActivityDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_data)

        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        uploadData()
        readData()

    }
    class FirebaseUtils {
        val firestoreDatabase = FirebaseFirestore.getInstance()
    }

    private fun uploadData(){
        binding!!.btnUploadData.setOnClickListener {
            val hashMap = hashMapOf<String, Any>(
                "Name" to "Hamburguesa",
                "Description" to "Hamburguesa con doble carne",
                "Size" to "Combo",
                "Price" to "$45",
                "Image" to "https://firebasestorage.googleapis.com/v0/b/projectfirebase-87c88.appspot.com/o/burgues.jpg?alt=media&token=e4804f67-e5ed-4135-98de-a3d31dd0e8e8"
            )
            FirebaseUtils().firestoreDatabase.collection("platillos")
                .add(hashMap)
                .addOnSuccessListener {
                    Log.d(TAG, "Se añadió el documento con ID {$it.id}")
                    Toast.makeText(this, "Se ha creado un nuevo documento con ID {$it.id}", Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener{
                    Log.d(TAG, "El documento no se pudo agregar {$it}")
                }

        }
    }

    private fun readData(){
        binding!!.btnReadData.setOnClickListener {
            FirebaseUtils().firestoreDatabase.collection("platillos")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    val productList = mutableListOf<Product>()
                    for (document in querySnapshot) {
                        val product = document.toObject(Product::class.java)
                        productList.add(product)
                    }

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("PRODUCT_LIST", ArrayList(productList))
                    startActivity(intent)
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Failed to read documents: ${exception.message}")
                    Toast.makeText(this, "Failed to read documents", Toast.LENGTH_LONG).show()
                }


        }
    }




}
