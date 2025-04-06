package com.example.maplibrenativetest


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import org.maplibre.android.BuildConfig
import org.maplibre.android.MapLibre
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.MapLibreMap
import org.maplibre.android.maps.Style
import org.maplibre.android.style.layers.LineLayer
import org.maplibre.android.style.layers.PropertyFactory.lineColor
import org.maplibre.android.style.layers.PropertyFactory.lineWidth
import org.maplibre.android.style.sources.TileSet
import org.maplibre.android.style.sources.VectorSource


class MainActivity : AppCompatActivity() {

   // private lateinit var mapView: MapView

   // private lateinit var maplibreMap: MapLibreMap
   private lateinit var myWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main) // Directly set layout

        myWebView = findViewById(R.id.webview)
        myWebView.webViewClient = WebViewClient()
        myWebView.settings.javaScriptEnabled = true

        myWebView.loadUrl("https://api.maptiler.com/maps/0195e431-1711-7754-95d8-125ec2429b38/?key=gpVOgqbHwdAs6xGA7G9m#15/55.76758/37.68705")

    }
}
 //       mapView = rootView.findViewById(R.id.mapView)

//        mapView.onCreate(savedInstanceState)
//        mapView.getMapAsync { map ->
//            val styleUrl = "http://[::]:8080/styles/test-style/style.json"
//            //    "https://api.maptiler.com/maps/0195e431-1711-7754-95d8-125ec2429b38/style.json?key=gpVOgqbHwdAs6xGA7G9m"
//            map.setStyle(Style.Builder().fromUri(styleUrl), { error ->
//                Log.e("MapError", "Failed to load style: ${error}")
//            })
//        }


//    val tileset = TileSet(
//        "openmaptiles",
//        "https://demotiles.maplibre.org/tiles-omt/{z}/{x}/{y}.pbf"
//    )
//    val openmaptiles = VectorSource("openmaptiles", tileset)
//    style.addSource(openmaptiles)
//    val roadLayer = LineLayer("road", "openmaptiles").apply {
//        setSourceLayer("transportation")
//        setProperties(
//            lineColor("red"),
//            lineWidth(2.0f)
//        )
//    }
//
//override fun onStart() {
//        super.onStart()
//        mapView.onStart()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        mapView.onResume()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        mapView.onPause()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mapView.onStop()
//    }
//
//    override fun onLowMemory() {
//        super.onLowMemory()
//        mapView.onLowMemory()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mapView.onDestroy()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        mapView.onSaveInstanceState(outState)
//    }




