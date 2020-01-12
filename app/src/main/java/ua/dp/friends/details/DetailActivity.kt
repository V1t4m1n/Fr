package ua.dp.friends.details

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import ua.dp.friends.R
import ua.dp.friends.db.DBHelper
import java.io.FileNotFoundException
import java.io.InputStream


class DetailActivity: AppCompatActivity() {

    lateinit var db: DBHelper

    val PICK_IMAGE = 1

    private lateinit var avatarImageView: ImageView
    private lateinit var addedSwitch: Switch
    private lateinit var nameTextView: TextView
    //private lateinit var cityTextView: TextView
    //var countryTextView: TextView
    //var ageTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var genderTextView: TextView

    var added: Boolean = false

    //For FRIENDS ACTIVITY
    /*fun refreshData() {
            listFriends = db.AllFriendsList as ArrayList<User>
            recyclerView.adapter = Adapter(listFriends)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        db = DBHelper(this)

        avatarImageView = findViewById(R.id.avatarImageView)
        nameTextView = findViewById(R.id.nameTextView)
        //cityTextView = findViewById(R.id.cityTextView)
        //countryTextView = itemView.findViewById(R.id.countryTextView)
        //ageTextView = itemView.findViewById(R.id.ageTextView)
        phoneTextView = findViewById(R.id.numberTextView)
        genderTextView = findViewById(R.id.genderTextView)
        addedSwitch = findViewById(R.id.addedSwitch)


        Picasso.get().load(intent.getStringExtra("AVATAR")).into(avatarImageView)
        nameTextView.text = intent.getStringExtra("NAME")
        phoneTextView.text = intent.getStringExtra("PHONE")
        genderTextView.text = intent.getStringExtra("GENDER")

        var title = intent.getStringExtra("TITLE")
        var first = intent.getStringExtra("FIRST")
        var last = intent.getStringExtra("LAST")

        var avatar = intent.getStringExtra("AVATAR")

        addedSwitch.setOnCheckedChangeListener { _, _ ->
            added = !added
            if (added) {

                /* val friend = User(
                    Random(100).nextInt(),
                     "example@email.com",
                     genderTextView.text.toString(),
                     phoneTextView.text.toString(),
                     "",
                     avatar,
                     "",
                     first,
                     last,
                     title
                 )

                 db.AddFriend(friend)*/
                Toast.makeText(this, "Added", Toast.LENGTH_LONG).show()

            } else {

                /*val friend = User(
                    Random(100).nextInt(),
                    "example@email.com",
                    genderTextView.text.toString(),
                    phoneTextView.text.toString(),
                    "",
                    avatar,
                    "",
                    first,
                    last,
                    title
                )

                db.DeleteFriend(friend)*/
                Toast.makeText(this, "Removed", Toast.LENGTH_LONG).show()
            }
        }

        avatarImageView.setOnClickListener {
            val intentPhoto = Intent(Intent.ACTION_PICK)
            intentPhoto.type = "image/*"
            startActivityForResult(intentPhoto, PICK_IMAGE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)

        when (requestCode) {
            PICK_IMAGE -> if (resultCode === Activity.RESULT_OK) {
                try { //Получаем URI изображения, преобразуем его в Bitmap
//объект и отображаем в элементе ImageView нашего интерфейса:
                    val imageUri: Uri = imageReturnedIntent!!.data!!
                    val imageStream: InputStream? = contentResolver.openInputStream(imageUri)
                    val selectedImage = BitmapFactory.decodeStream(imageStream)
                    avatarImageView.setImageBitmap(selectedImage)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }
            }
        }
    }
}
