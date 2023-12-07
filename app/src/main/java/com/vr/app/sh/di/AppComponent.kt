package com.vr.app.sh.di

import com.vr.app.sh.ui.books.view.AddBook
import com.vr.app.sh.ui.books.view.FragmentSubjectsClass
import com.vr.app.sh.ui.door.view.Authoriz
import com.vr.app.sh.ui.door.view.Reg
import com.vr.app.sh.ui.door.view.Verification
import com.vr.app.sh.ui.menu.view.Settings
import com.vr.app.sh.ui.menu.view.TopMenu
import com.vr.app.sh.ui.profile.view.MyProfile
import com.vr.app.sh.ui.tests.view.addTest.AddQuestion
import com.vr.app.sh.ui.tests.view.listTests.FragmentListTests
import com.vr.app.sh.ui.tests.view.result.TestResultAct
import com.vr.app.sh.ui.tests.view.subjects.FragmentAllSubjects
import com.vr.app.sh.ui.tests.view.test.WindowTest
import com.vr.app.sh.ui.timeTable.view.DayFragment
import com.vr.app.sh.ui.timeTable.view.TimeTable
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
    fun injectResultTest(testResultAct: TestResultAct)
    fun injectFragmentListTests(fragmentListTests: FragmentListTests)
    fun injectWindowTest(windowTest: WindowTest)
    fun injectFragmentDay(dayFragment: DayFragment)
    fun injectTimeTable(timeTable: TimeTable)
    fun injectMyProfile(myProfile: MyProfile)
    fun injectSettings(settings: Settings)

    fun injectVerification(verification: Verification)
}