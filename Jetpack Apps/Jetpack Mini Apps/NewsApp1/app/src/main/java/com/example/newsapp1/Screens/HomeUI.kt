import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp1.MyViewModel.MyViewModel
import com.example.newsapp1.models.Article
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUI(viewModel: MyViewModel) {
    val response = viewModel.response.value?.articles ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Latest News",
                    fontWeight = FontWeight.SemiBold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(response) { article ->
                ArticleCard(
                    article = article,
                    onArticleClick = { /* Handle article click */ },
                    onShareClick = { /* Handle share */ },
                    onBookmarkClick = { /* Handle bookmark */ }
                )
            }

            // Add bottom padding for last item
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(
    article: Article,
    onArticleClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onBookmarkClick: () -> Unit = {}
) {
    var isBookmarked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onArticleClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Image Section
            article.urlToImage?.let { imageUrl ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    val painter = rememberAsyncImagePainter(model = imageUrl)
                    Image(
                        painter = painter,
                        contentDescription = "Image for ${article.title}",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)),
                        contentScale = ContentScale.Crop
                    )

                    // Gradient overlay for better text readability
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.3f)
                                    )
                                )
                            )
                    )
                }
            }

            // Content Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Title
                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Description
                article.description?.let { description ->
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                // Author and Date Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Author and Date Column
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        article.author?.let { author ->
                            Text(
                                text = "By $author",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        article.publishedAt?.let { publishedAt ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AccessTime,
                                    contentDescription = "Published time",
                                    modifier = Modifier.size(14.dp),
                                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = formatDate(publishedAt),
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    // Action Buttons
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Bookmark Button
                        IconButton(
                            onClick = {
                                isBookmarked = !isBookmarked
                                onBookmarkClick()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = "Bookmark",
                                tint = if (isBookmarked)
                                    MaterialTheme.colorScheme.primary
                                else
                                    MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        // Share Button
                        IconButton(onClick = onShareClick) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Share",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                // Source Badge (if available)
                article.source?.name?.let { sourceName ->
                    Spacer(modifier = Modifier.height(8.dp))
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.primaryContainer
                    ) {
                        Text(
                            text = sourceName,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }
    }
}

// Helper function to format date
private fun formatDate(dateString: String): String {
    return try {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val outputFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val date = inputFormatter.parse(dateString)
        date?.let { outputFormatter.format(it) } ?: "Unknown date"
    } catch (e: Exception) {
        "Unknown date"
    }
}
