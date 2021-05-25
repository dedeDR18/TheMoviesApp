//package com.example.core.data.source
//
//import android.util.Log
//import androidx.paging.ExperimentalPagingApi
//import androidx.paging.LoadType
//import androidx.paging.PagingState
//import androidx.paging.RemoteMediator
//import androidx.room.withTransaction
//import com.example.core.data.source.local.entity.MovieEntity
//import com.example.core.data.source.local.entity.MoviePagesKey
//import com.example.core.data.source.local.room.TmdbDao
//import com.example.core.data.source.local.room.TmdbDatabase
//import com.example.core.data.source.remote.network.TmdbService
//import com.example.core.domain.model.Movie
//import com.example.core.utils.DataMapper
//import retrofit2.HttpException
//import retrofit2.awaitResponse
//import java.io.IOException
//import java.io.InvalidObjectException
//import java.lang.Exception
//
///**
// * Created on : 24/05/21 | 11.33
// * Author     : dededarirahmadi
// * Name       : dededarirahmadi
// * Email      : dededarirahmadi@gmail.com
// */
//
//@OptIn(ExperimentalPagingApi::class)
//class MovieRemoteMediator(
//    private val apiService: TmdbService,
//    private val tmdbDatabase: TmdbDatabase
//) : RemoteMediator<Int, MovieEntity>() {
//
//    override suspend fun initialize(): InitializeAction {
//        // Require that remote REFRESH is launched on initial load and succeeds before launching
//        // remote PREPEND / APPEND.
//        return InitializeAction.LAUNCH_INITIAL_REFRESH
//    }
//
//    override suspend fun load(
//        loadType: LoadType,
//        state: PagingState<Int, MovieEntity>
//    ): MediatorResult {
//        Log.d("TAG", "TEREKSEKUSI")
//
//
//        val pageKeyData = getKeyPageData(loadType, state)
//        val page = when (pageKeyData) {
//            is MediatorResult.Success -> {
//                return pageKeyData
//            }
//            else -> {
//                pageKeyData as Int
//            }
//        }
//
//        try {
//
//            val response = apiService.fetchMovieListByGenreWithPaging(12, page)
//
//            val isEndOfList = response.results.isEmpty()
//            tmdbDatabase.withTransaction {
//                // clear all tables in the database
//                if (loadType == LoadType.REFRESH) {
//                    tmdbDatabase.tmdbPagesKeyDao().clearMoviePagesKey()
//                    tmdbDatabase.tmdbDao().clearMovies()
//                }
//                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1
//                Log.d("TAG", "isEndOfList value = $isEndOfList")
//                val nextKey = if (isEndOfList) null else page + 1
////                val keys = response.results.map {
////                    MoviePagesKey(it.id, nextKey, prevKey)
////                }
//                tmdbDatabase.tmdbPagesKeyDao().saveMoviePageKeys(keys)
//                tmdbDatabase.tmdbDao()
//                    .insertMovies(DataMapper.mapMovieResponseToMovieEntity(response.results))
//
//
//            }
//
//
//            return MediatorResult.Success(isEndOfList)
//        } catch (exception: IOException) {
//            return MediatorResult.Error(exception)
//        } catch (exception: HttpException) {
//            return MediatorResult.Error(exception)
//        } catch (e:Exception){
//            return MediatorResult.Error(e)
//            Log.d("TAG", "gagal execute ${e.message}")
//        }
//
//    }
//
//
//    //private suspend fun getMoviePages() = tmdbDatabase.tmdbPagesKeyDao().getMoviePagesKey().firstOrNull()
//
//    /**
//     * this returns the page key or the final end of list success result
//     */
//    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, MovieEntity>): Any? {
//        return when (loadType) {
//            LoadType.REFRESH -> {
//                val movieKeys = getClosestMovieKey(state)
//                movieKeys?.after?.minus(1) ?: DEFAULT_PAGE_INDEX
//            }
//            LoadType.APPEND -> {
//                val movieKeys = getLastMovieKey(state)
//                    ?: throw InvalidObjectException("Movie key should not be null for $loadType")
//                movieKeys.after
//            }
//            LoadType.PREPEND -> {
//                val remoteKeys = getFirstMovieKey(state)
//                    ?: throw InvalidObjectException("Invalid state, key should not be null")
//                //end of list condition reached
//                remoteKeys.before ?: return MediatorResult.Success(endOfPaginationReached = true)
//                remoteKeys.before
//            }
//        }
//    }
//
//
//    private suspend fun getLastMovieKey(state: PagingState<Int, MovieEntity>): MoviePagesKey? {
//        return state.pages
//            .lastOrNull { it.data.isNotEmpty() }
//            ?.data?.lastOrNull()
//            ?.let { movie -> tmdbDatabase.tmdbPagesKeyDao().getMoviePagesKey(movie.id) }
//    }
//
//    private suspend fun getFirstMovieKey(state: PagingState<Int, MovieEntity>): MoviePagesKey? {
//        return state.pages
//            .firstOrNull()
//            {
//                it.data.isNotEmpty()
//            }
//            ?.data?.firstOrNull()
//            ?.let { movieEntity ->
//                tmdbDatabase.tmdbPagesKeyDao().getMoviePagesKey(movieEntity.id)
//            }
////            .firstOrNull() { it.data.isNotEmpty() }
////            ?.data?.firstOrNull()
////            ?.let { movie -> tmdbDatabase.tmdbPagesKeyDao().getMoviePagesKey(movie.id) }
//    }
//
//    private suspend fun getClosestMovieKey(state: PagingState<Int, MovieEntity>): MoviePagesKey? {
//        return state.anchorPosition?.let { position ->
//            state.closestItemToPosition(position)?.id?.let { id ->
//                tmdbDatabase.tmdbPagesKeyDao().getMoviePagesKey(id)
//            }
//        }
//    }
//
//
//    companion object {
//        const val DEFAULT_PAGE_INDEX = 1
//        const val DEFAULT_PAGE_SIZE = 20
//
//    }
//
//
//}