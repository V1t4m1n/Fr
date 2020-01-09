package ua.dp.friends.people

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.dp.friends.R
import ua.dp.friends.User
import java.net.URI

class PeopleAdapter (private val value: List<User>) : RecyclerView.Adapter<PeopleAdapter.PeopleViewHolder>() {


    override fun getItemCount() = value.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return PeopleViewHolder(itemView)
    }
    
    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        holder.avatarImageView.setImageURI(Uri.parse(value[position].avatar))
        holder.nameTextView.text = value[position].titleName +
                ". " + value[position].firstName + " " + value[position].lastName
        holder.ageTextView.text = value[position].age
        holder.cityTextView.text = value[position].city
        holder.countryTextView.text = value[position].country
        holder.phoneTextView.text = value[position].phone
    }

    class PeopleViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarImageView: ImageView
        var nameTextView: TextView
        var cityTextView: TextView
        var countryTextView: TextView
        var ageTextView: TextView
        var phoneTextView: TextView

        init {
            avatarImageView = itemView.findViewById(R.id.avatarImageView)
            nameTextView = itemView.findViewById(R.id.nameTextView)
            cityTextView = itemView.findViewById(R.id.cityTextView)
            countryTextView = itemView.findViewById(R.id.countryTextView)
            ageTextView = itemView.findViewById(R.id.ageTextView)
            phoneTextView = itemView.findViewById(R.id.numberTextView)
        }
    }
}