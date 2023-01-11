// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json          = Json { allowStructuredMapKeys = true }
// val iTuneResponse = json.parse(ITuneResponse.serializer(), jsonString)

package com.godwin.testmusic.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*

@Parcelize
data class ITuneResponse(
    val feed: Feed
) : Parcelable

@Parcelize
data class Feed(
    val author: Author,
    val entry: List<Entry>,
    val updated: Label,
    val rights: Label,
    val title: Label,
    val label: Label,
    val link: List<Link>,
    val id: Label
) : Parcelable

@Parcelize
data class Author(
    val name: Label,
    val uri: Label
) : Parcelable

@Parcelize
data class Label(
    val label: String
) : Parcelable

@Parcelize
data class Entry(
    @SerializedName("im:name")
    val imName: Label,

    @SerializedName("im:image")
    val imImage: List<IMImage>,

    @SerializedName("im:itemCount")
    val imLabel: Label,

    @SerializedName("im:price")
    val imPrice: IMPrice,

    @SerializedName("im:contentType")
    val imContentType: EntryIMContentType,

    val rights: Label,
    val title: Label,
    val link: Link,
    val id: ID,

    @SerializedName("im:artist")
    val imArtist: IMArtist,

    val category: Category,

    @SerializedName("im:releaseDate")
    val imReleaseDate: IMReleaseDate
) : Parcelable

@Parcelize
data class Category(
    val attributes: CategoryAttributes
) : Parcelable

@Parcelize
data class CategoryAttributes(
    @SerializedName("im:id")
    val imID: String,

    val term: String,
    val scheme: String,
    val label: String
) : Parcelable

@Parcelize
data class ID(
    val label: String,
    val attributes: IDAttributes
) : Parcelable

@Parcelize
data class IDAttributes(
    @SerializedName("im:id")
    val imID: String
) : Parcelable

@Parcelize
data class IMArtist(
    val label: String,
    val attributes: IMArtistAttributes
) : Parcelable

@Parcelize
data class IMArtistAttributes(
    val href: String
) : Parcelable


@Parcelize
data class EntryIMContentType(
    @SerializedName("im:contentType")
    val imContentType: IMContentTypeIMContentType,

    val attributes: IMContentTypeAttributes
) : Parcelable


@Parcelize
data class IMContentTypeAttributes(
    val term: String,
    val label: String
) : Parcelable


@Parcelize
data class IMContentTypeIMContentType(
    val attributes: IMContentTypeAttributes
) : Parcelable


@Parcelize
data class IMImage(
    val label: String,
    val attributes: IMImageAttributes
) : Parcelable


@Parcelize
data class IMImageAttributes(
    val height: String
) : Parcelable

@Parcelize
data class IMPrice(
    val label: String,
    val attributes: IMPriceAttributes
) : Parcelable

@Parcelize
data class IMPriceAttributes(
    val amount: String,
    val currency: String
) : Parcelable

@Parcelize
data class IMReleaseDate(
    val label: String,
    val attributes: Label
) : Parcelable

@Parcelize
data class Link(
    val attributes: LinkAttributes
) : Parcelable

@Parcelize
data class LinkAttributes(
    val rel: String,
    val type: String? = null,
    val href: String
) : Parcelable
