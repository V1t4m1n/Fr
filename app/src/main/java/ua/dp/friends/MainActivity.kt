package ua.dp.friends

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import ua.dp.friends.people.PeopleAdapter
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var listPeopleRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listPeopleRecyclerView = findViewById(R.id.listPeopleRecyclerView)
        listPeopleRecyclerView.layoutManager = LinearLayoutManager( MainActivity(), LinearLayoutManager.VERTICAL, true)

        AsyncTaskHandler(/*listPeopleRecyclerView*/).execute("https://randomuser.me/api?results=3")

    }

    class AsyncTaskHandler(/*recyclerView: RecyclerView*/) : AsyncTask<String, String, String>() {

        lateinit var listModelsUser: ArrayList<User>
        //var mListPeopleRecyclerView: RecyclerView = recyclerView

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

            CreateJsonArray(result)
            //mListPeopleRecyclerView.adapter = PeopleAdapter(CreatingModelsFromJsonArray(CreateJsonArray(result)))
        }

        private fun CreateJsonArray(jsonString: String?): JSONArray {
            val jsonObject = JSONObject(jsonString)
            var jsonArray: JSONArray = jsonObject.getJSONArray("results")
            return jsonArray
        }

        private fun CreatingModelsFromJsonArray(jsonArray: JSONArray) : List<User>{

            listModelsUser = ArrayList()

            for ( index in 0 until jsonArray.length()) {

                var jsonObjectDetail = jsonArray.getJSONObject(index)
                var jsonObjectName = jsonArray.getJSONObject(index).getJSONObject("name")
                var userModel = User()

                userModel.titleName = jsonObjectName.getString("title")
                userModel.firstName = jsonObjectName.getString("first")
                userModel.lastName = jsonObjectName.getString("last")

                userModel.gender = jsonObjectDetail.getString("gender")
                userModel.email = jsonObjectDetail.getString("email")
                userModel.phone = jsonObjectDetail.getString("phone")
                //userModel.country = jsonObjectDetail.getString("country")
                //userModel.city = jsonObjectDetail.getString("city")

                listModelsUser.add(userModel)
            }
            Log.d("SIZE LIST", "${listModelsUser.size}")
            //Give list to adapter...
            return listModelsUser
        }
    }
}
