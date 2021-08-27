package com.russellworld.sofegram.utilits

/* Класс перечисление состояний приложения*/

enum class AppStates(var state: String) {
    ONLINE("в сети"),
    OFFLINE("был недавно"),
    TYPING("печатает");

    companion object {
        fun updateState(appStates: AppStates) {
            /*Функция принимает состояние и записывает в базу данных*/
            REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
                .setValue(appStates.state)
                .addOnSuccessListener { USER.state = appStates.state }
                .addOnFailureListener { showToast(it.message.toString()) }
        }
    }
}