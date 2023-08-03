package com.vr.app.sh.di

import com.vr.app.sh.ui.books.view.AddBook
import com.vr.app.sh.ui.books.view.FragmentSubjectsClass
import com.vr.app.sh.ui.door.view.Authoriz
import com.vr.app.sh.ui.door.view.Reg
import com.vr.app.sh.ui.menu.view.TopMenu
import com.vr.app.sh.ui.tests.view.addTest.AddQuestion
import com.vr.app.sh.ui.tests.view.listTests.FragmentListTests
import com.vr.app.sh.ui.tests.view.result.ResultTest
import com.vr.app.sh.ui.tests.view.subjects.FragmentAllSubjects
import com.vr.app.sh.ui.tests.view.test.WindowTest
import dagger.Component

@Component(modules = [AppModule::class,DataModule::class,DomainModule::class])
interface AppComponent {
    fun injectAddBook(addBook: AddBook)
    fun injectAddQuestion(addQuestion: AddQuestion)
    fun injectFragmentAllSubjects(fragmentAllSubjects: FragmentAllSubjects)
    fun injectAuthoriz(authoriz: Authoriz)
    fun injectFragmentSubjectsClass(fragmentSubjectsClass: FragmentSubjectsClass)
    fun injectTopMenu(topMenu: TopMenu)
    fun injectReg(reg: Reg)
    fun injectResultTest(resultTest: ResultTest)
    fun injectFragmentListTests(fragmentListTests: FragmentListTests)
    fun injectWindowTest(windowTest: WindowTest)
}