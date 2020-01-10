package ua.dp.friends.people

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.dp.friends.R
import ua.dp.friends.details.DetailActivity
import ua.dp.friends.utils.User

class PeopleAdapter (private val value: List<User>, context: Context) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>(){

    var conn = context

    override fun getItemCount() = value.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return PeopleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        //Set picture in ImageView
        Picasso.get().load(value[position].large).into(holder.avatarImageView)

        holder.nameTextView.text = value[position].titleName +
                ". " + value[position].firstName + " " + value[position].lastName
        //holder.ageTextView.text = value[position].age
        //holder.cityTextView.text = value[position].city
        //holder.countryTextView.text = value[position].country
        holder.phoneTextView.text = value[position].phone
        holder.genderTextView.text = value[position].gender

        //DetailActivity(value)
        //conn.startActivity(Intent(conn, DetailActivity::class.java).putExtra("POSITION", position))
    }

    class PeopleViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarImageView: ImageView
        var nameTextView: TextView
        var cityTextView: TextView
        //var countryTextView: TextView
        //var ageTextView: TextView
        var phoneTextView: TextView
        var genderTextView: TextView

        init {
            avatarImageView = itemView.findViewById(R.id.avatarImageView)
            nameTextView = itemView.findViewById(R.id.nameTextView)
            cityTextView = itemView.findViewById(R.id.cityTextView)
            //countryTextView = itemView.findViewById(R.id.countryTextView)
            //ageTextView = itemView.findViewById(R.id.ageTextView)
            phoneTextView = itemView.findViewById(R.id.numberTextView)
            genderTextView = itemView.findViewById(R.id.genderTextView)

        }
    }
}