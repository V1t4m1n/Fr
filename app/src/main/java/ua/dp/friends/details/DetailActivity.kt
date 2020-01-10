package ua.dp.friends.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recycler_view.*
import ua.dp.friends.R
import ua.dp.friends.utils.User

class DetailActivity(private val value: List<User>) : AppCompatActivity() {

    private var pos = intent.getIntExtra("POSITION", 0)

    private lateinit var avatarImageView: ImageView
    private lateinit var nameTextView: TextView
    //private lateinit var cityTextView: TextView
    //var countryTextView: TextView
    //var ageTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var genderTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_recycler_view)

        avatarImageView = findViewById(R.id.avatarImageView)
        nameTextView = findViewById(R.id.nameTextView)
        //cityTextView = findViewById(R.id.cityTextView)
        //countryTextView = itemView.findViewById(R.id.countryTextView)
        //ageTextView = itemView.findViewById(R.id.ageTextView)
        phoneTextView = findViewById(R.id.numberTextView)
        genderTextView = findViewById(R.id.genderTextView)

        InitAllView(value, pos)
    }

    fun InitAllView(user: List<User>, pos: Int) {

        Picasso.get().load(user[pos].medium).into(avatarImageView)

        nameTextView.text = user[pos].titleName +
                ". " + user[pos].firstName + " " + user[pos].lastName

        phoneTextView.text = user[pos].phone
        genderTextView.text = user[pos].gender
    }
}
