package com.nesterov.veld.common

import coil3.ImageLoader
import coil3.PlatformContext
import coil3.request.crossfade
import coil3.util.DebugLogger

fun PlatformContext.getAsyncImageLoader() = imageLoader {
    crossfade(true)
    logger(DebugLogger())
}

private fun PlatformContext.imageLoader(block: ImageLoader.Builder.() -> Unit): ImageLoader {
    val builder = ImageLoader.Builder(this)
    block.invoke(builder)
    return builder.build()
}
