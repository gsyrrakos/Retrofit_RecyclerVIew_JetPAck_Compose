package com.example.restappcompose.ui.HomeScreen

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.androiddevchallenge.ui.theme.graySurface

import com.example.mvp_graphql.Data.Restaurants

@Composable
fun TopStoriesScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    Surface(color = MaterialTheme.colors.background) {

        Progressbar(isDisplayed = viewModel.loading.value)

        // https://stackoverflow.com/questions/16120697/kotlin-how-to-pass-a-function-as-parameter-to-another
        StoryList(
            storyItems = viewModel.topStories,
            viewModel::checkEndOfList,
            viewModel::onStoryClicked,
            navController
        )
    }
}


@Composable
fun Progressbar(isDisplayed: Boolean) {
    if (isDisplayed) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(100.dp),
            horizontalArrangement = Arrangement.Center


        ) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)


        }


    }


}


@Composable
fun StoryList(
    storyItems: List<Restaurants>,
    checkEndOfList: (index: Int) -> Unit,
    onItemClicked: (item: Restaurants) -> Unit,
    navController: NavController
) {
    // Remember our own LazyListState
    val listState = rememberLazyListState()

    // Provide it to LazyColumn
    @OptIn(ExperimentalFoundationApi::class)
    LazyColumn(state = listState) {
        // Header
        stickyHeader {
            StoryListHeader()
        }

        // List of items
        itemsIndexed(storyItems) { index, item ->
            StoryListItem(item = item, onItemClicked, navController)
            // Load more items
            checkEndOfList(index)

            // TODO: Add a boolean for loading
            // TODO: Show a loading composable while the boolean is true

        }
    }
}

@Composable
private fun StoryListHeader() {
    Surface(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
    ) {
        Text(
            text = "Restaurants",
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun StoryListItem(
    item: Restaurants,
    onItemClicked: (item: Restaurants) -> Unit,
    navController: NavController
) {
    StoryListItem(
        score = "10",
        title = item.name!!,
        author = item.name!!,
        relativeTime = item.type,
        commentsCount = item.name,
        url = item.logo,
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                onItemClicked(item)
                navController.navigate("storyDetails")
            }
    )
}


@Composable
fun StoryScore(score: String) {
    Text(
        text = score,
        style = MaterialTheme.typography.h5,
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(8.dp)
            .width(50.dp)
    )
}


@Composable
fun StoryTitleAndMetadata(title: String, author: String, relativeTime: String, modifier: Modifier) {
    Column(modifier = modifier) {
        // Title of the story
        Text(text = title, style = MaterialTheme.typography.subtitle1)

        // Author and Time metadata
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Text(
                text = "by $author | $relativeTime",
                style = MaterialTheme.typography.body2
            )
        }
    }
}


@Composable
fun StoryCommentIconAndCount(count: String, url: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {

        Image(
            painter = rememberImagePainter(url),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(84.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )

        Text(text = count, modifier = Modifier.align(Alignment.End))
    }
}


//san to recycler view

@Composable
fun StoryListItem(
    score: String,
    title: String,
    author: String,
    relativeTime: String,
    url: String,
    commentsCount: String?,
    modifier: Modifier
) {
    Card(
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = graySurface,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    )

    {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Score / Points

            // StoryScore(score)

            //  Title + metadata
            StoryTitleAndMetadata(
                title = title,
                author = author,
                relativeTime = relativeTime,
                modifier = Modifier.weight(1f)
            )

            // Comment icon + comment count
            var count = "0"
            commentsCount?.let {
                count = it
            }
            StoryCommentIconAndCount(count = count, url)
        }
    }
}


@Preview
@Composable
fun PreviewStoryListItem() {
    StoryListItem(
        score = "10",
        title = "Test Title",
        author = "Avin",
        relativeTime = "10 hours ago",
        commentsCount = "20",
        url = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.m.wikipedia.org%2Fwiki%2FFile%3ACat03.jpg&psig=AOvVaw34BT5qKRFDVGAfo-FBEkax&ust=1628337303981000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCPCrmKarnPICFQAAAAAdAAAAABAD",
        modifier = Modifier
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewStoryCommentIconAndCount() {
    StoryCommentIconAndCount(
        count = "20",
        "https://www.google.com/url?sa=i&url=https%3A%2F%2Fen.m.wikipedia.org%2Fwiki%2FFile%3ACat03.jpg&psig=AOvVaw34BT5qKRFDVGAfo-FBEkax&ust=1628337303981000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCPCrmKarnPICFQAAAAAdAAAAABAD"
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewStoryTitleAndMetadata() {
    StoryTitleAndMetadata(
        title = "Test Title",
        author = "Avin",
        relativeTime = "10 hours ago",
        modifier = Modifier
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewStoryScore() {
    StoryScore(score = "100")
}