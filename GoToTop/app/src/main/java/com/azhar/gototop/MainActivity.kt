package com.azhar.gototop

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

/**
 * Created by Azhar Rivaldi on 10-12-2020.
 */

class MainActivity : AppCompatActivity() {

    private val modelMainList: ArrayList<ModelMain> = ArrayList()
    var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setAdapter
        mainAdapter = MainAdapter(modelMainList)

        //serRecyclerview
        rvMain.setLayoutManager(LinearLayoutManager(this))
        rvMain.setHasFixedSize(true);
        rvMain.setAdapter(mainAdapter)

        //FAB Back To Top
        fabBackTop.setOnClickListener {
            rvMain.smoothScrollToPosition(0);
        }

        getData();
    }

    /*private fun getData() {
        AndroidNetworking.get("https://api.learn2crack.com/android/jsonandroid/")
            .setPriority(Priority.HIGH)
            .build()
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    for (i in 0 until response.length()) {
                        try {
                            val dataApi = ModelMain()
                            val jsonObject = response.getJSONObject(i)
                            dataApi.ver = jsonObject.getString("ver")
                            dataApi.name = jsonObject.getString("name")
                            dataApi.api = jsonObject.getString("api")
                            modelMainList.add(dataApi)
                        } catch (e: JSONException) {
                            e.printStackTrace()
                            Toast.makeText(
                                this@MainActivity, "Gagal menampilkan data!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    showList()
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(
                        this@MainActivity, "Tidak ada jaringan internet!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
    }*/

    private fun getData() {
        AndroidNetworking.get("https://dev.farizdotid.com/api/purwakarta/kuliner")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        val playerArray = response.getJSONArray("kuliner")
                        for (i in 0 until playerArray.length()) {
                            val jsonObject = playerArray.getJSONObject(i)
                            val dataApi = ModelMain()
                            dataApi.name = jsonObject.getString("nama")
                            dataApi.jam = jsonObject.getString("jam_buka_tutup")
                            dataApi.alamat = jsonObject.getString("alamat")
                            modelMainList.add(dataApi)
                            /*mainAdapter?.setOnItemClickCallback(object : OnItemClickCallback {
                                    override fun onItemClicked(modelMain: ModelMain) {
                                        val moveIntent1 =
                                            Intent(this@MainActivity, DetailActivity::class.java)
                                        moveIntent1.putExtra(DetailActivity.ITEM_EXTRA, modelMain)
                                        startActivity(moveIntent1)
                                    }
                                })*/
                        }
                        mainAdapter?.notifyDataSetChanged()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                        Toast.makeText(this@MainActivity, "Gagal menampilkan data!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(this@MainActivity, "Tidak ada jaringan internet!", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
