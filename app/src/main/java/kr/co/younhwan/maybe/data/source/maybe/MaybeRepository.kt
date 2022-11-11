package kr.co.younhwan.maybe.data.source.maybe

object MaybeRepository : MaybeSource {
    private val maybeRemoteDataSource = MaybeRemoteDataSource

    override fun read(readCallback: MaybeSource.ReadCallback?) {
        maybeRemoteDataSource.read(
            object : MaybeSource.ReadCallback {
                override fun onRead() {
                    readCallback?.onRead()
                }
            }
        )
    }
}