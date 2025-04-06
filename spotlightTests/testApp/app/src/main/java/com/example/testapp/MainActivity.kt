package com.example.testapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.modules.ArchiveFileFactory
import org.osmdroid.tileprovider.modules.MapTileFileArchiveProvider
import org.osmdroid.tileprovider.tilesource.ITileSource
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.tileprovider.util.SimpleRegisterReceiver
import org.osmdroid.tileprovider.MapTileProviderArray
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import org.osmdroid.util.GeoPoint
import java.io.File
import java.io.FileOutputStream

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация osmdroid
        Configuration.getInstance().userAgentValue = packageName

        setContent {
            MapScreen()
        }
    }
}

@Composable
fun MapScreen() {
    var mapView by remember { mutableStateOf<MapView?>(null) }

    AndroidView(
        factory = { context ->
            MapView(context).apply {
                // Установка источника тайлов
                setTileSource(TileSourceFactory.MAPNIK)

                // Включение мультитач-управления
                setMultiTouchControls(true)

                // Копирование MBTiles из assets в файловую систему
                val mbtilesFile = File(context.filesDir, "campus_result.mbtiles")

                Log.d("Existence", mbtilesFile.exists().toString())
                Log.d("MBTilesPath", mbtilesFile.absolutePath)
                if (!mbtilesFile.exists()) {
                    context.assets.open("campus_result.mbtiles").use { inputStream ->
                        FileOutputStream(mbtilesFile).use { outputStream ->
                            inputStream.copyTo(outputStream)
                        }
                    }
                }

                // Загрузка MBTiles
                if (mbtilesFile.exists()) {
                    val archiveFile = ArchiveFileFactory.getArchiveFile(mbtilesFile)
                    val registerReceiver = SimpleRegisterReceiver(context)
                    val tileSource: ITileSource = TileSourceFactory.MAPNIK

                    Log.d("MBTiles", tileSource.name())

                    // Создание провайдера для MBTiles
                    val archiveProvider = MapTileFileArchiveProvider(
                        registerReceiver,
                        tileSource,
                        arrayOf(archiveFile)
                    )

                    // Создание MapTileProviderArray и добавление провайдера
                    val tileProvider = MapTileProviderArray(tileSource, registerReceiver, arrayOf(archiveProvider))
                    this.tileProvider = tileProvider
                }

                // Настройка карты
                controller.setZoom(20.0)


                // Добавление метки
                val marker = Marker(this)
                marker.position = GeoPoint(37.6836684, 55.7629521 ) // Координаты метки
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
                marker.title = "Комната 101"
                overlays.add(marker)
            }.also { mapView = it }
        },
        modifier = Modifier.fillMaxSize(),
        update = { view ->
            // Обновление MapView (если нужно)
        }
    )

    // Управление жизненным циклом MapView
    DisposableEffect(Unit) {
        onDispose {
            mapView?.onPause()
        }
    }
}