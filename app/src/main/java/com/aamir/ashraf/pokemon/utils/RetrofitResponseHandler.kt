package com.aamir.ashraf.pokemon.utils

import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


//for more https://stackoverflow.com/questions/43322186/how-to-handle-error-response-with-retrofit-2

//here we integrate the resource class to handle all the retrofit responses

fun <T> retrofitErrorHandler(res: Response<T>): Resource<T> {
    return if (res.isSuccessful) {
        res.body()?.let {
            Resource.Success(it)
        } ?: Resource.Error("Response body is null")
    } else {
        val errorMsg = res.errorBody()?.string()?.let {
            JSONObject(it).getString("error") // or whatever your message is
        } ?: res.message()
        Resource.Error(errorMsg)
    }
}

// Wrapper function to handle exceptions and use retrofitErrorHandler
//but this may not required ,we may remove this in future
suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
    return try {
        val response = apiCall()
        retrofitErrorHandler(response)
    } catch (e: IOException) {
        Resource.Error("Network error: ${e.localizedMessage}")
    } catch (e: HttpException) {
        Resource.Error("HTTP error: ${e.message()}")
    } catch (e: Exception) {
        Resource.Error("Unexpected error: ${e.localizedMessage}")
    }
}