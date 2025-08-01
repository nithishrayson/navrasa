package com.navrasaapp.navrasa.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.navrasaapp.navrasa.R
import com.navrasaapp.navrasa.models.Track

@Composable
fun PlaybackScreen(track: Track) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(60.dp))

            CircularSeekBarWithAlbumArt(track.albumArtRes, progress = 0.65f)

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = track.title,
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black)
            )

            Text(
                text = track.artist,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )

            Spacer(modifier = Modifier.height(40.dp))

            PlaybackControls()
        }
    }
}

@Composable
fun CircularSeekBarWithAlbumArt(imageRes: Int, progress: Float) {
    Box(contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(220.dp)) {
            drawArc(
                color = Color.Gray.copy(alpha = 0.4f),
                startAngle = -90f,
                sweepAngle = 360f,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = 8.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
            drawArc(
                color = Color.Black,
                startAngle = -90f,
                sweepAngle = 360f * progress,
                useCenter = false,
                style = androidx.compose.ui.graphics.drawscope.Stroke(
                    width = 8.dp.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Album Art",
            modifier = Modifier
                .size(160.dp)
                .clip(CircleShape)
        )
    }
}

@Composable
fun PlaybackControls() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {/* Shuffle Songs */}){
            Image(
                painter = painterResource(id = R.drawable.shuffle),
                contentDescription = "Shuffle",
                modifier = Modifier.size(30.dp)
            )
        }
        IconButton(onClick = { /* skip back */ }) {
                Image(
                    painter = painterResource(id = R.drawable.previous),
                    contentDescription = "Previous",
                    modifier = Modifier.size(30.dp)
                )
        }

        IconButton(onClick = { /* play/pause */ }) {

                Image(
                    painter = painterResource(id = R.drawable.play),
                    contentDescription = "Play",
                    modifier = Modifier.size(50.dp)
                )
        }

        IconButton(onClick = { /* skip next */ }) {
                Image(
                    painter = painterResource(id = R.drawable.next),
                    contentDescription = "Next",
                    modifier = Modifier.size(30.dp)
                )
        }
        IconButton(onClick = {/* repeat songgs */}) {
            Image(
                painter = painterResource(id = R.drawable.repeat),
                contentDescription = "Repeat",
                modifier = Modifier.size(30.dp)
            )
        }
    }
}