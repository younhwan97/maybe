package kr.co.younhwan.maybe.data.source.maybe

interface MaybeSource {
    fun read(
        readCallback: ReadCallback?
    )

    interface ReadCallback {
        fun onRead()
    }
}