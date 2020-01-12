package ua.dp.friends.people

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import ua.dp.friends.R
import ua.dp.friends.db.DBHelper
import ua.dp.friends.utils.User
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var listPeopleRecyclerView: RecyclerView
    lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DBHelper(this)

        listPeopleRecyclerView = findViewById(R.id.listPeopleRecyclerView)
        listPeopleRecyclerView.layoutManager = LinearLayoutManager(MainActivity(), LinearLayoutManager.VERTICAL, false)

        AsyncTaskHandler(listPeopleRecyclerView, this)
            .execute("https://randomuser.me/api?results=10")
    }

    class AsyncTaskHandler(recyclerView: RecyclerView, context: Context) : AsyncTask<String, String, String>() {

        lateinit var listModelsUser: ArrayList<User>
        var mListPeopleRecyclerView: RecyclerView = recyclerView
        var mContext = context

        override fun doInBackground(vararg url: String?): String {
            var jsonString: String
            val connection = URL(url[0]).openConnection() as HttpURLConnection

            try {
                connection.connect()
                jsonString = connection.inputStream.use {
                    it.reader().use {
                            reader -> reader.readText()
                    }
                }
            }
            finally {
                connection.disconnect()
            }
            return jsonString
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            var listUser = this.CreatingModelsFromJsonArray(CreateJsonArray(result))
            mListPeopleRecyclerView.adapter = PeopleAdapter(listUser, mContext)
        }

        private fun CreateJsonArray(jsonString: String?): JSONArray {
            val jsonObject = JSONObject(jsonString)
            var jsonArray: JSONArray = jsonObject.getJSONArray("results")
            CreatingModelsFromJsonArray(jsonArray)
            return jsonArray
        }

        private fun CreatingModelsFromJsonArray(jsonArray: JSONArray) : List<User>{

            listModelsUser = ArrayList()

            for ( index in 0 until jsonArray.length()) {

                var jsonObjectDetail = jsonArray.getJSONObject(index)
                var jsonObjectName = jsonArray.getJSONObject(index).getJSONObject("name")
                var jsonObjectAvatar = jsonArray.getJSONObject(index).getJSONObject("picture")

                var userModel = User()

                //userModel.city =
                //userModel.country = jsonObjectDetail.getString("country")

                userModel.titleName = jsonObjectName.getString("title")
                userModel.firstName = jsonObjectName.getString("first")
                userModel.lastName = jsonObjectName.getString("last")

                userModel.medium = jsonObjectAvatar.getString("medium")
                userModel.thumbnail = jsonObjectAvatar.getString("thumbnail")
                userModel.large = jsonObjectAvatar.getString("large")

                userModel.gender = jsonObjectDetail.getString("gender")
                userModel.email = jsonObjectDetail.getString("email")
                userModel.phone = jsonObjectDetail.getString("phone")

                listModelsUser.add(userModel)
            }

            return listModelsUser
        }
    }
}
