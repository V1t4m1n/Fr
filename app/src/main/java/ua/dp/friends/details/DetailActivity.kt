package ua.dp.friends.details

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import ua.dp.friends.R


class DetailActivity: AppCompatActivity() {

    private lateinit var avatarImageView: ImageView
    private lateinit var addedSwitch: Switch
    private lateinit var nameTextView: TextView
    //private lateinit var cityTextView: TextView
    //var countryTextView: TextView
    //var ageTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var genderTextView: TextView

    var added: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

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

        addedSwitch.setOnCheckedChangeListener { _, _ ->
            added = !added
            if (added) {
                Toast.makeText(this, "Added to friend list!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Remove when friend list!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
