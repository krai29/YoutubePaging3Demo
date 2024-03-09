package com.krai29.youtubepaging3demo.data

data class YoutubeData(
    var kind:String?,
    var etag:String?,
    var nextPageToken:String?,
    var prevPageToken:String?,
    var pageInfo:PageInfo,
    var regionCode:String?,
    var items:List<Items>,
)

data class PageInfo(
    var totalResults:Int,
    var resultsPerPage:Int
)
data class Items(
    var kind:String?,
    var etag:String?,
    var id:ID,
    var snippet:Snippet
)
data class ID(
    var kind:String?,
    var videoId:String?
)
data class Snippet(
    var publishedAt:String?,
    var channelId:String?,
    var title:String?,
    var description:String?,
    var channelTitle:String?,
    var liveBroadcastContent:String?,
    var publishTime:String?,
    var thumbnails:ThumbIcon
)
data class ThumbIcon(
    var default:Default,
    var medium:Medium,
    var high:High
)
data class Default(
    var url:String?,
    var width:Int,
    var height:Int
)
data class Medium(
    var url:String?,
    var width:Int,
    var height:Int
)
data class High(
    var url:String?,
    var width:Int,
    var height:Int
)