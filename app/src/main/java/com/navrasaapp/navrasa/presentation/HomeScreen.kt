package com.navrasaapp.navrasa.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.navrasaapp.navrasa.R
import com.navrasaapp.navrasa.models.BottomNavBar
import com.navrasaapp.navrasa.models.Playlist
import com.navrasaapp.navrasa.models.Track

@Composable
fun HomeScreen() {
    val userName = "Nithish Rayson"
    val profilePic = R.drawable.profile

    val featuredPlaylists = listOf(
        Playlist("Morning Vibes", R.drawable.playlist1),
        Playlist("Workout Mix", R.drawable.playlist2),
        Playlist("Lo-Fi Chill", R.drawable.playlist3)
    )

    val recommendedTracks = listOf(
        Track("Shape of you", "Ed Sheeran", R.drawable.track1, R.drawable.track1, "112M listeners"),
        Track("Love me like you do - Fifty Shades of Grey", "Ellie Goulding", R.drawable.track2, R.drawable.track2, "141M listeners"),
        Track("Lovely", "Billie Eilish & Khalid", R.drawable.track3, R.drawable.track3, "155M listeners")
    )

    val bottomNavItems = listOf(
        BottomNavBar("",R.drawable.home),
        BottomNavBar("",R.drawable.search),
        BottomNavBar("",R.drawable.library),
        BottomNavBar("",R.drawable.settings)
    )

    Scaffold(
        containerColor = Color.Black,
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, start = 15.dp, end = 15.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text("Welcome back", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                        Text(userName, style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold, color = Color.White))
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Image(
                        painter = painterResource(profilePic),
                        contentDescription = "Profile",
                        modifier = Modifier.size(40.dp).clip(CircleShape)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                SearchBar()
            }
        },
        bottomBar = {
            BottomNavigationBar(bottomNavItems)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            SectionTitle("Featured Playlists")
            HorizontalPlaylistList(featuredPlaylists)
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("Recommended Tracks")
            VerticalTrackList(recommendedTracks)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        placeholder = {
            Text("Search songs, albums, artists...", color = Color.LightGray)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(25.dp)),
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.DarkGray,
            focusedTextColor = Color.White,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true
    )
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold,
            color = Color.White
        ),
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun HorizontalPlaylistList(playlists: List<Playlist>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(playlists) { playlist ->
            PlaylistCard(playlist)
        }
    }
}

@Composable
fun PlaylistCard(playlist: Playlist) {
    Column(modifier = Modifier.width(140.dp)) {
        Image(
            painter = painterResource(playlist.imageRes),
            contentDescription = "Playlist cover",
            modifier = Modifier
                .size(140.dp)
                .clip(RoundedCornerShape(12.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            playlist.name,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun VerticalTrackList(tracks: List<Track>) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        tracks.forEach { track ->
            TrackItem(track)
        }
    }
}

@Composable
fun TrackItem(track: Track) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(track.albumArtRes),
            contentDescription = "Album Art",
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column {
            Text(track.title, style = MaterialTheme.typography.bodyLarge.copy(color = Color.White))
            Text(track.artist, style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
            Text(track.listeners, style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray))
        }
    }
}

@Composable
fun BottomNavigationBar(items: List<BottomNavBar>) {
    NavigationBar(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
            .height(100.dp)
            .background(Color.DarkGray),
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = false,
                onClick = { /* handle navigation logic */ },
                modifier = Modifier.padding(10.dp),
                icon = {
                    Image(
                        painter = rememberAsyncImagePainter(model = item.iconRes),
                        contentDescription = item.label,
                        modifier = Modifier.size(28.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}