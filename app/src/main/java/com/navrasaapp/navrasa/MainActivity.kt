package com.navrasaapp.navrasa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.navrasaapp.navrasa.presentation.PlaybackScreen
import com.navrasaapp.navrasa.models.Track
import com.navrasaapp.navrasa.ui.theme.NavrasaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavrasaTheme {
                val sampleTrack = Track(
                    title = "Lovely",
                    artist = "Billie Eilish & Khalid",
                    imageRes = R.drawable.track3,     // used if you display a small icon
                    albumArtRes = R.drawable.track3,   // used for central artwork
                    listeners = "155M listeners"
                )
                PlaybackScreen(track = sampleTrack)
            }
        }
    }
}