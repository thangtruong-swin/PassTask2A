package au.edu.swin.sdmd.passtask2a_wish_you_were_here

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import au.edu.swin.sdmd.passtask2a.Location

class DetailActivity : AppCompatActivity() {
    private final var location: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

//        get data from MainActivity via Parcelable
        location = intent.getParcelableExtra<Location>("location")
        location?.let {
//            Find TextView components and display values on them
            val vTitle = findViewById<TextView>(R.id.textViewTitle)
            vTitle.text = it.title
            val vCity = findViewById<TextView>(R.id.textViewCity)
            vCity.text = it.city
            val image = findViewById<ImageView>(R.id.imageView)

//            This will return current image according to clicked
            val imageResult = when(it.title){
                "Tarneit Shopping Centre" -> R.drawable.tarneit_shoppingcenter
                "Tarneit Bunnings Warehouse" -> R.drawable.tarneit_bunnings_warehouse
                "Tarneit Medical Centre" -> R.drawable.tarneit_medical_center
                else -> R.drawable.tarneit_village_cinemas
            }
            image.setImageDrawable(getDrawable(imageResult))
//          Find TextView components and display values on them
            val vDate = findViewById<TextView>(R.id.textViewDate)
            vDate.text = it.date.toString()
            val vRating = findViewById<RatingBar>(R.id.ratingBar)
            vRating.rating = it.rating.toFloat()
        }
    }

    //    When button back has pressed
    override fun onBackPressed() {
//        Assigned equal true when visited
        location?.visited = true
        val i = intent.apply {
            putExtra("visited", location)
        }
        setResult(Activity.RESULT_OK, i)
        super.onBackPressed()
    }
}