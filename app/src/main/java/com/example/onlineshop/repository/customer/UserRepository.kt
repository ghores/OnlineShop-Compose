package com.example.onlineshop.repository.customer

import com.example.onlineshop.api.customer.UserApi
import com.example.onlineshop.model.ApiResponse
import com.example.onlineshop.model.customer.User
import com.example.onlineshop.model.customer.UserDto
import com.example.onlineshop.repository.base.BaseRepository
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
) : BaseRepository() {

    suspend fun getUserInfo(token: String): ApiResponse<User> = safeApiCall {
        api.getUserInfo(prepareToken(token))
    }

    suspend fun changePassword(data: UserDto, token: String): ApiResponse<User> =
        safeApiCall {
            api.changePassword(data, prepareToken(token))
        }

    suspend fun login(data: UserDto): ApiResponse<UserDto> =
        safeApiCall {
            api.login(data)
        }

    suspend fun register(data: UserDto): ApiResponse<User> =
        safeApiCall {
            api.register(data)
        }

    suspend fun update(data: UserDto, token: String): ApiResponse<User> =
        safeApiCall {
            api.update(data, prepareToken(token))
        }
}