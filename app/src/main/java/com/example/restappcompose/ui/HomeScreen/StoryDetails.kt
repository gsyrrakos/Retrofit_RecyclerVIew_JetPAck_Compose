package com.example.restappcompose.ui.HomeScreen

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import coil.compose.rememberImagePainter
import com.example.mvp_graphql.Data.Restaurants
import com.example.restappcompose.R



@Composable
fun StoryDetailsScreen(story: Restaurants, ){
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.padding(16.dp)
    ) {
        // Remember our own LazyListState
        val listState = rememberLazyListState()

        // Provide it to LazyColumn
        LazyColumn(state = listState) {
            item {
                StoryDetailsHeader(
                    title = story.name!!,
                    text = story.name,
                    url = story.logo,
                    score = story.type!!,
                    descendants = story.address!!,
                    relativeTime = story.name,
                    author = story.name!!
                )
            }

            }
        }

    }


@Composable
fun StoryDetailsHeader(
    title: String,
    text: String?,
    url: String?,
    score: String,
    descendants: String,
    relativeTime: String,
    author: String
){
    Column {
        // Title of the post
        Text(
            text = title,
            style = MaterialTheme.typography.h4
        )

       /* text?.let {
            val text = HtmlCompat.fromHtml(it, Html.FROM_HTML_MODE_LEGACY).toString()
            Text(
                text = text,
                style = MaterialTheme.typography.body1
            )
        }*/

        // Display favicon with base url and a clickable icon
        // that takes you to the original link.
        url?.let {
            FaviconAndUrl(
                url = it,
                modifier = Modifier.padding(top = 16.dp)
            )
        }

        StoryMetadata(
            score,
            descendants,
            relativeTime,
            author
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStoryDetailsHeader(){
    StoryDetailsHeader(
        title = "Test Title",
        text = "This story is awesome!",
        url = "https://www.google.com/",
        score = "50",
        descendants = "5",
        relativeTime = "an hour ago",
        author = "Avin Sharma"
    )
}

@Composable
fun FaviconImage(url: String, modifier: Modifier){
    // Fetch base url to further use it to fetch favicon
    val faviconUrl = url
    Image(
        painter = rememberImagePainter(url),
        contentDescription = null,
        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
    )

}

@Composable
fun FaviconAndUrl(url: String, modifier: Modifier){
    val baseUrl = url
    val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    val context = LocalContext.current


    Row(modifier = modifier) {
        FaviconImage(url = url, modifier = Modifier.size(24.dp))
        //Text(text = baseUrl, modifier = Modifier.padding(start = 8.dp, end = 8.dp))


       /* Icon(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Button that opens the story url",
            tint = MaterialTheme.colors.onSurface,
            modifier = Modifier.clickable {
                startActivity(context, urlIntent, Bundle.EMPTY)
            }
        )*/



    }
}

@Composable
fun StoryMetadata(
    score: String,
    commentsCount: String,
    relativeTime: String,
    author: String
){
    Column() {
        Row() {
            Text(text = score, color = MaterialTheme.colors.primary, style = MaterialTheme.typography.h6)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "p")
            }
            Spacer(modifier = Modifier.width(16.dp))

            Text(text = commentsCount)
        }

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "by $author | $relativeTime",
                style = MaterialTheme.typography.body2
            )
        }
    }
}

