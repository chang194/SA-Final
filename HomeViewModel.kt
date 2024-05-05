package com.example.database.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.data.entity.MemberEntity
import com.example.database.data.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.sql.Date
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val memberRepository: MemberRepository,
) : ViewModel() {

    private val _memberDetailsList = MutableStateFlow(emptyList<MemberEntity>())
    val memberDetailsList = _memberDetailsList.asStateFlow()

    //回傳給repository
    fun updateMember(memberEntity: MemberEntity) {
        viewModelScope.launch(IO) {
            memberRepository.update(memberEntity)
        }
    }
    //註冊新會員
    fun insertMember(memberEntity: MemberEntity) {
        viewModelScope.launch(IO) {
            memberRepository.insert(memberEntity)
        }
    }

//從UI取得資訊
    private val _memberName = MutableStateFlow("")
    val memberName = _memberName.asStateFlow()
    fun setMemberName(membername: String) {
        _memberName.tryEmit(membername)
    }

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    fun setEmail(email: String) {
        _email.tryEmit(email)
    }


    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    fun setPassword(password: String) {
        _password.tryEmit(password)
    }

    private val _coin = MutableStateFlow(0)
    val coin = _coin.asStateFlow()
    fun setCoin(coin: Int) {
        _coin.tryEmit(coin)
    }

    private val _outcome = MutableStateFlow("")
    val outcome= _outcome.asStateFlow()
    fun setOutcome(outcome: String) {
        _outcome.tryEmit(outcome)
    }

    private val _skin = MutableStateFlow("")
    val skin = _skin.asStateFlow()
    fun setSkin(skin: String) {
        _skin.tryEmit(skin)
    }

    private val _gender = MutableStateFlow(0)
    val gender = _gender.asStateFlow()
    fun setGender(gender: Int) {
        _gender.tryEmit(gender)
    }

    private val _birthday = MutableStateFlow(java.util.Date())
    val birthday = _birthday.asStateFlow()
    fun setBirthday(birthday: Date) {
        _birthday.tryEmit(birthday)
    }

}