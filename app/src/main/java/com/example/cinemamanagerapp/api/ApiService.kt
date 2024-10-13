package com.example.cinemamanagerapp.api

import com.example.cinemamanagerapp.models.FoodDrink
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

// Định nghĩa lớp Request cho API đăng nhập
data class LoginRequest(
    val email: String,
    val password: String
)
// Data class cho phản hồi đăng nhập
data class LoginResponse(
    val userId: Int
)


data class RegisterInfo(
    val username: String,
    val email: String,
    val password: String // Thêm trường password
)

data class UserProfile(
    var email: String,        // Tham số email
    var password: String,     // Tham số password
    var username: String,
    var address: String? = null,
    var phone_number: String? = null,
    var gender: String? = null
)
data class PasswordUpdate(
    val email: String,
    val oldPassword: String,
    val newPassword: String
)


interface ApiService {
    @POST("auth/login")
    fun loginUser(@Body user: LoginRequest): Call<LoginResponse>

    @POST("auth/register")
    fun registerUser(@Body user: RegisterInfo): Call<Void>

    @POST("auth/get-profile")
    fun getProfile(@Body params: Map<String, Int>): Call<UserProfile> // key string la userId; value là id
    // server dựa vào idUser trong database lấy thông tin gửi về

    @PUT("auth/update-profile")
    fun updateProfile(@Body user: ApiUser): Call<Void> // muốn update thanh phần gì thì
    // ghi vào trong map rồi up lên server . ví dụ userName và phoneNumber
    // val val params = mapOf( "userName" to newName, "phoneNumber" to 012345679) rồi gui len server

    @PUT("auth/update-password")
    fun updatePassword(@Body passwordRequest: PasswordUpdate): Call<Void>

    @GET("api/food-drinks/")
    fun getFoodDrinks(): Call<List<FoodDrink>>
}