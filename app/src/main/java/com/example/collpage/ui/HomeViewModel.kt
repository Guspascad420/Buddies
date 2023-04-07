package com.example.collpage.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.util.UUID

private const val TAG = "MyActivity"

class HomeViewModel : ViewModel() {
    private val db = Firebase.firestore
    private val currentUserId = Firebase.auth.currentUser?.uid
    var selectedItem by mutableStateOf("")
    var user by mutableStateOf(User())

    var sheetContent: SheetContent by mutableStateOf(SheetContent.NOTE)

    val months = listOf("Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus",
    "September", "Oktober", "November", "Desember")

    private val _userProjects = mutableStateListOf<Project>()
    val userProjects: List<Project> = _userProjects

    private val _userEducations = mutableStateListOf<Education>()
    val userEducations: List<Education> = _userEducations

    private val _userExperiences = mutableStateListOf<Experience>()
    val userExperiences: List<Experience> = _userExperiences

    private val _userAchievements = mutableStateListOf<Achievement>()
    val userAchievements: List<Achievement> = _userAchievements

    fun getUserData() {
        db.collection("users").document(currentUserId.toString()).get()
            .addOnCompleteListener {
                user = it.result.toObject(User::class.java)!!
                Log.d(TAG, user.toString())
            }
    }

    fun addUserProject(newProject: HashMap<String, Any>) {
        val id = UUID.randomUUID().toString()
        val projectsUsers = hashMapOf(
            "project_id" to id,
            "user_id" to currentUserId
        )
        viewModelScope.launch {
            db.collection("projects").document(id).set(newProject)
            db.collection("projects_users").add(projectsUsers)
        }
    }

    fun getUserExperiences() {
        viewModelScope.launch {
            db.collection("experiences")
                .whereEqualTo("user_id", currentUserId.toString()).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        _userExperiences.add(document.toObject(Experience::class.java))
                    }
                }
        }
    }

    fun getUserEducations() {
        viewModelScope.launch {
            db.collection("educations")
                .whereEqualTo("user_id", currentUserId.toString()).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        _userEducations.add(document.toObject(Education::class.java))
                    }
                }
        }
    }

    fun getUserAchievements() {
        viewModelScope.launch {
            db.collection("achievements")
                .whereEqualTo("user_id", currentUserId.toString()).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        _userAchievements.add(document.toObject(Achievement::class.java))
                    }
                }
        }
    }

    fun getUserProjects() {
        viewModelScope.launch {
            db.collection("projects_users")
                .whereEqualTo("user_id", currentUserId.toString()).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        getProject(document["project_id"].toString())
                    }
                }
        }
    }

    private fun getProject(projectId: String) {
        db.collection("projects").document(projectId).get()
            .addOnCompleteListener {
                val project = it.result.toObject(Project::class.java)!!
                _userProjects.add(project)
            }
    }
}

enum class SheetContent {
    NOTE, COMMENT, START_MONTHS,
    END_MONTHS, START_YEARS, END_YEARS
}

data class User(
    val email: String = "",
    var name: String = "",
    val occupation: String? = "",
    val profile_desc: String = "",
    val username: String? = "",
)

data class Project(
    val name: String = "",
    val description: String = "",
    val start_date: String = "",
    val end_date: String = "",
    val type: String = ""
)

data class Education(
    val name: String = "",
    val major: String = "",
    val year_in: Int = 0,
    val year_out: Int = 0,
    val activities: List<String> = listOf(),
    val user_id: String = ""
)

data class Experience(
    val name: String = "",
    val department: String = "",
    val year_in: Int = 0,
    val year_out: Int = 0,
    val activities: List<String> = listOf(),
    val user_id: String = ""
)

data class Achievement(
    val name: String = "",
    val organizer: String = "",
    val published: String = "",
    val user_id: String = ""
)

class ActivityNote(
    val title: String,
    val desc: String,
    val iconId: Int,
    var activeIconTint: Color,
    var activeBgColor: Color,
    var activeIconBgColor: Color,
    var activeBackIconColor: Color,
    val backIconId: Int
)