package com.vr.app.sh.di

import com.vr.app.sh.ui.books.view.*
import com.vr.app.sh.ui.door.view.*
import com.vr.app.sh.ui.menu.view.TopMenu
import com.vr.app.sh.ui.profile.view.MyProfile
import com.vr.app.sh.ui.tests.view.addTest.AddQuestion
import com.vr.app.sh.ui.tests.view.listTests.FragmentListTests
import com.vr.app.sh.ui.tests.view.result.TestResultAct
import com.vr.app.sh.ui.tests.view.subjects.FragmentAllSubjects
import com.vr.app.sh.ui.tests.view.test.WindowTest
import com.vr.app.sh.ui.timeTable.view.*
import dagger.Component

@Component(modules = [AppModule::class,DataModule::class,DomainModule::class])
interface AppComponent {
    fun injectAddBook(addBook: AddBook)
    fun injectAddQuestion(addQuestion: AddQuestion)
    fun injectFragmentAllSubjects(fragmentAllSubjects: FragmentAllSubjects)
    fun injectAuthoriz(authoriz: Authoriz)
    fun injectFragmentSubjectsClass(fragmentSelectBook: FragmentSelectBook)
    fun injectTopMenu(topMenu: TopMenu)
    fun injectReg(reg: Reg)
    fun injectResultTest(testResultAct: TestResultAct)
    fun injectFragmentListTests(fragmentListTests: FragmentListTests)
    fun injectWindowTest(windowTest: WindowTest)
    fun injectFragmentDay(dayFragment: DayFragment)
    fun injectTimeTable(timeTable: TimeTable)
    fun injectMyProfile(myProfile: MyProfile)

    fun injectVerification(verification: Verification)
}