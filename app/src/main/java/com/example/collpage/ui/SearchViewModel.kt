package com.example.collpage.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

sealed interface SearchUiState {
    object Success : SearchUiState
    object Loading : SearchUiState
    object Default : SearchUiState
}

class SearchViewModel : ViewModel() {
    private val db = Firebase.firestore
    var query by mutableStateOf("")

    private val _jobslist = mutableStateListOf<Job>()
    val jobsList: List<Job> = _jobslist

    var activeResultType by mutableStateOf("Semua")
    var searchUiState: SearchUiState by mutableStateOf(SearchUiState.Default)

    fun getJobResults() {
        searchUiState = SearchUiState.Loading
        viewModelScope.launch {
            db.collection("job_vacancies").whereEqualTo("name", query).get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        _jobslist.add(document.toObject(Job::class.java))
                    }
                }
            searchUiState = SearchUiState.Success
        }
    }
}

data class Job(
    val name: String,
    val company_id: String,
    val placement: String,
    val salary_range: String,
    val type: String
)