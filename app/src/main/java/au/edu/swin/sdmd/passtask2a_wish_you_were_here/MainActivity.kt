package au.edu.swin.sdmd.passtask2a_wish_you_were_here

import android.content.Intent
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModel
import au.edu.swin.sdmd.passtask2a.Location
import java.util.*

class MainActivity : AppCompatActivity() {
    private val imageViewModel: ImageModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Re-Render TextColor View
        reRenderView()

        //Find CardView and assign Event onClick for cardViewTarneitShoppingCentre
        val vTarneitShoppingCentre = findViewById<CardView>(R.id.cardViewTarneitShoppingCentre)
        vTarneitShoppingCentre.setOnClickListener{
            imageViewModel.imageLocations[0].visited = true
            Log.i("locationTarneitShoppingCenter", imageViewModel.imageLocations[0].toString())
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("location",imageViewModel.imageLocations[0])
            }
            startForResult.launch(intent)
        }

//        Find CardView and assign Event onClick for cardViewBunnings_warehouse
        val vBunningsWarehouse = findViewById<CardView>(R.id.cardViewBunnings_warehouse)
        vBunningsWarehouse.setOnClickListener{
            imageViewModel.imageLocations[1].visited = true
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("location",imageViewModel.imageLocations[1])
            }
            startForResult.launch(intent)
        }

        //Find CardView and assign Event onClick for cardView_Tarneit_medical_center
        val vTarneitMedicalCentre = findViewById<CardView>(R.id.cardView_Tarneit_medical_center)
        vTarneitMedicalCentre.setOnClickListener{
            imageViewModel.imageLocations[2].visited = true
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("location",imageViewModel.imageLocations[2])
            }
            startForResult.launch(intent)
        }

        //Find CardView and assign Event onClick for cardView_village_cinemas
        val vVillageCinemas = findViewById<CardView>(R.id.cardView_village_cinemas)
        vVillageCinemas.setOnClickListener{
            imageViewModel.imageLocations[3].visited = true
            val intent = Intent(this, DetailActivity::class.java).apply {
                putExtra("location",imageViewModel.imageLocations[3])
            }
            startForResult.launch(intent)
        }
    }

    //    Event from onBackPressed
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when(result.resultCode){
                RESULT_OK -> {
                    val data = result.data
                    val visited = data?.getParcelableExtra<Location>("visited")
                    visited?.let {
                        reRenderView()
                        when (it.title) {
                            "Tarneit Shopping Centre" -> {
                                Log.i("locationTarneitShoppingCenterVISITED", imageViewModel.imageLocations[0].toString())
                                val ratingBarCardView1 = findViewById<RatingBar>(R.id.ratingBarCard_View_1)
                                ratingBarCardView1.rating=(it.rating.toFloat())
                            }
                            "Tarneit Bunnings Warehouse" ->  {
//                                val titleCardView2 = findViewById<TextView>(R.id.title_Card_View_2)
//                                imageViewModel.textColor(titleCardView2)
                                val ratingBarCardView2 = findViewById<RatingBar>(R.id.ratingBarCard_View_2)
                                ratingBarCardView2.rating=(it.rating.toFloat())
                            }
                            "Tarneit Medical Centre" -> {
                                val ratingBarCardView3 = findViewById<RatingBar>(R.id.ratingBarCard_View_3)
                                ratingBarCardView3.rating=(it.rating.toFloat())
                            }
                            else -> {
                                val ratingBarCardView4 = findViewById<RatingBar>(R.id.ratingBarCard_View_4)
                                ratingBarCardView4.rating=(it.rating.toFloat())
                            }
                        }
                    }
                }
            }
        }

    //Render the View
    private fun reRenderView() {
        val titleCardView1 = findViewById<TextView>(R.id.title_Card_View_1)
        val titleCardView2 = findViewById<TextView>(R.id.title_Card_View_2)
        val titleCardView3 = findViewById<TextView>(R.id.title_Card_View_3)
        val titleCardView4 = findViewById<TextView>(R.id.title_Card_View_4)
        imageViewModel.textColor("Tarneit Shopping Centre",titleCardView1)
        imageViewModel.textColor("Tarneit Bunnings Warehouse",titleCardView2)
        imageViewModel.textColor("Tarneit Medical Centre",titleCardView3)
        imageViewModel.textColor("Tarneit Village Cinemas",titleCardView4)
    }
}
/**
 * The view model itself: now contains the images
 */
class ImageModel: ViewModel() {
    private val simpleDate = SimpleDateFormat("dd/MM/yyyy - hh:mm:ss a", Locale.getDefault())
    private var currentDate = simpleDate.format(Date())

    var imageLocations: List<Location> = listOf(
        Location("Tarneit Shopping Centre", "Melbourne",currentDate,4.5),
        Location("Tarneit Bunnings Warehouse", "Melbourne",currentDate,4.0),
        Location("Tarneit Medical Centre", "Melbourne",currentDate,4.5),
        Location("Tarneit Village Cinemas", "Melbourne",currentDate,4.0)
    )

    fun textColor(title: String, textView: TextView){
        for (item in imageLocations){
            Log.i("item", imageLocations.toString())
            if(item.title == title && item.visited){
                textView.setTextColor(Color.RED)
            }
        }
    }

}