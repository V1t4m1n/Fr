package ua.dp.friends

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView: TextView = findViewById(R.id.tv)
        AsyncTaskHandler(this).execute("https://randomuser.me/api?results=3")
    }

    class AsyncTaskHandler(conn: Context) : AsyncTask<String, String, String>() {

        lateinit var listModelsUser: ArrayList<User>
        val context = conn

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
            } finally {
                connection.disconnect()
            }
            return jsonString
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            CreateJsonArray(result)
        }

        private fun CreateJsonArray(jsonString: String?) {
            val jsonObject = JSONObject(jsonString)
            var jsonArray: JSONArray = jsonObject.getJSONArray("results")


            val jsonObjectName = JSONObject(jsonArray.toString())
            var jsonArrayName = jsonObjectName.getJSONArray("name")

            CreatingModelsFromJsonArray(jsonArray, jsonArrayName)
        }

        private fun CreatingModelsFromJsonArray(jsonArray: JSONArray, jsonArrayName: JSONArray) : List<User>{

            listModelsUser = ArrayList()


            for ( counter in 0 until jsonArray.length()) {

                var jsonObjectDetail = jsonArray.getJSONObject(counter)
                var jsonObjectDetailName = jsonArrayName.getJSONObject(counter)
                var userModel = User()

                userModel.titleName = jsonObjectDetailName.getString("title")
                userModel.firstName = jsonObjectDetailName.getString("first")
                userModel.lastName = jsonObjectDetailName.getString("last")

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