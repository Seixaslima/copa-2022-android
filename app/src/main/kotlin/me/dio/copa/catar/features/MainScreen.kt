package me.dio.copa.catar.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.dio.copa.catar.R
import me.dio.copa.catar.domain.extensions.getDate
import me.dio.copa.catar.domain.model.MatchDomain
import me.dio.copa.catar.domain.model.TeamDomain
import me.dio.copa.catar.ui.theme.Shapes

@Composable
fun MainScreen(matchs: List<MatchDomain>) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(matchs) { match ->
                Column {
                    MatchInfo(match)
                }
            }
        }
    }
    
}

@Composable
fun MatchInfo(match: MatchDomain) {
    Card(
        shape = Shapes.large,
        modifier = Modifier.fillMaxWidth()
        ) {
        Box {
            AsyncImage(
                model = match.stadium.image,
                contentDescription = "Imagem do estadio",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(160.dp)
            )
            Column (modifier = Modifier.padding(16.dp)) {
                Notifications(match)
                Title(match)
                Teans(match)
            }
        }

    }
}


@Composable
fun Notifications (match: MatchDomain) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
        )  {
        val icone = if(match.notificationEnabled) R.drawable.ic_notifications_active else R.drawable.ic_notifications
        Image(
            painter = painterResource(id = icone),
            contentDescription = null
        )

    }
}
@Composable
fun Title(match: MatchDomain) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${match.date.getDate()} ${match.name}",
            style = MaterialTheme.typography.h6.copy(color = Color.White)
        )
    }
}

@Composable
fun Teans(match: MatchDomain) {
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TeamItem(team = match.team1)
        Text(
            text = "X",
            style = MaterialTheme.typography.h6.copy(color = Color.White),
            modifier = Modifier.padding(horizontal = 16.dp)
            )
        TeamItem(team = match.team2,second = true)
    }
}

@Composable
fun TeamItem(team: TeamDomain, second: Boolean = false) {

    Row (
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (second) {
            Text(
                text = team.displayName,
                style = MaterialTheme.typography.h6.copy(color = Color.White),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = team.flag,
                style = MaterialTheme.typography.h3.copy(color = Color.White),
                modifier = Modifier.align(Alignment.CenterVertically)
            )

        } else {

            Text(
                text = team.flag,
                style = MaterialTheme.typography.h3.copy(color = Color.White),
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = team.displayName,
                style = MaterialTheme.typography.h6.copy(color = Color.White),
                textAlign = TextAlign.Center
            )
        }
    }
}
