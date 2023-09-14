package com.example.news.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
data class NewsArticle(
    // val source: ArticleSource,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    // val content: String
) : Parcelable {

    val maxDescriptionCharacters = 300

    val shortenedDescription: String?
        get() {
            return if (description?.length ?: 0 > maxDescriptionCharacters) {
                description?.substring(0, maxDescriptionCharacters) + "..."
            } else {
                description
            }
        }

    val relativePublishedAt: String
        get() {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            sdf.timeZone = TimeZone.getTimeZone("UTC")

            try {
                val publishDate = sdf.parse(publishedAt)
                val currentDate = Date()

                val diffInMillis = currentDate.time - publishDate.time
                val seconds = diffInMillis / 1000
                val minutes = seconds / 60
                val hours = minutes / 60
                val days = hours / 24

                return when {
                    days > 1 -> "${days.toInt()} days ago"
                    days == 1L -> "1 day ago"
                    hours > 1 -> "${hours.toInt()} hours ago"
                    hours == 1L -> "1 hour ago"
                    minutes > 1 -> "${minutes.toInt()} minutes ago"
                    minutes == 1L -> "1 minute ago"
                    else -> "just now"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }
}
