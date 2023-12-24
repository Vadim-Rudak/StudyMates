package com.vr.app.sh.domain.UseCase

import com.vr.app.sh.domain.repository.CookieRepo

class CleanCookie(private val cookieRepo: CookieRepo) {
    fun execute(){
        cookieRepo.clean()
    }
}