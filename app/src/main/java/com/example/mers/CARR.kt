package com.example.mers

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import java.util.*



import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.util.*


class CARR {

    class MainActivity : AppCompatActivity() {
        lateinit var EdtCarname: EditText
        lateinit var EdtCarmodel: EditText
        lateinit var EdtCarprice: EditText
        lateinit var Btn_uploadcardata: Button
        lateinit var Btn_uploadcarphoto: Button
        lateinit var Btn_view: Button
        lateinit var Btn_choose: Button
        lateinit var imageView: ImageView
        var fileUri: Uri? = null;

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_car2)

            EdtCarname=findViewById(R.id.carname)
            EdtCarmodel=findViewById(R.id.carmodel)
            EdtCarprice=findViewById(R.id.carprice)
            Btn_uploadcarphoto=findViewById(R.id.uploadcarphoto)
            Btn_uploadcardata=findViewById(R.id.uploadcardata)
            Btn_choose=findViewById(R.id.viewuploadedcars)
            imageView = findViewById(R.id.idIVImage)

            var dataBase = FirebaseDatabase.getInstance()
            var dataseRef =dataBase.getReference("cars")

            Btn_choose.setOnClickListener{
                // on below line calling intent to get our image from phone storage.
                val intent = Intent()
                // on below line setting type of files which we want to pick in our case we are picking images.
                intent.type = "image/*"
                // on below line we are setting action to get content
                intent.action = Intent.ACTION_GET_CONTENT
                // on below line calling start activity for result to choose image.
                startActivityForResult(
                    // on below line creating chooser to choose image.
                    Intent.createChooser(
                        intent,
                        "Pick your image to upload"
                    ),
                    22
                )




            }


            Btn_uploadcardata.setOnClickListener{
                val Make = EdtCarname.toString().trim()
                val Model = EdtCarmodel.toString().trim()
                val Price = EdtCarprice.toString().trim()

                // on below line calling upload image button to upload our image.
                uploadImage()
            }
        }

        // on below line adding on activity result method this method is called when user picks the image.
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            // on below line we are checking if the result is ok
            if (requestCode == 22 && resultCode == RESULT_OK && data != null && data.data != null) {
                // on below line initializing file uri with the data which we get from intent
                fileUri = data.data
                try {
                    // on below line getting bitmap for image from file uri.
                    val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri);
                    // on below line setting bitmap for our image view.
                    imageView.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    // handling exception on below line
                    e.printStackTrace()
                }
            }
        }

        // on below line creating a function to upload our image.
        fun uploadImage() {
            // on below line checking weather our file uri is null or not.
            if (fileUri != null) {
                // on below line displaying a progress dialog when uploading an image.
                val progressDialog = ProgressDialog(this)
                // on below line setting title and message for our progress dialog and displaying our progress dialog.
                progressDialog.setTitle("Uploading...")
                progressDialog.setMessage("Uploading your image..")
                progressDialog.show()

                // on below line creating a storage refrence for firebase storage and creating a child in it with
                // random uuid.
                val ref: StorageReference = FirebaseStorage.getInstance().getReference()
                    .child(UUID.randomUUID().toString())
                // on below line adding a file to our storage.
                ref.putFile(fileUri!!).addOnSuccessListener {
                    // this method is called when file is uploaded.
                    // in this case we are dismissing our progress dialog and displaying a toast message
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Image Uploaded..", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    // this method is called when there is failure in file upload.
                    // in this case we are dismissing the dialog and displaying toast message
                    progressDialog.dismiss()
                    Toast.makeText(applicationContext, "Fail to Upload Image..", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }


}