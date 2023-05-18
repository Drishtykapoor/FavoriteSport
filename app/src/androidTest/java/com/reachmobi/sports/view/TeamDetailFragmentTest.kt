package com.reachmobi.sports.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.reachmobi.sports.R
import com.reachmobi.sports.adapter.TeamEventsAdapter
import com.reachmobi.sports.repository.TeamsRepositoryImpl
import com.reachmobi.sports.viewmodel.TeamDetailViewModelImpl
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TeamDetailFragmentTest {

    @Test
    fun test_isTeamsDataVisible() {

        val teamsService = FakeTeamService()

        val teamsRepository = TeamsRepositoryImpl(teamsService)

        val bundle = Bundle()
        bundle.putString("idTeam", "135795")

        val fragmentFactory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return TestMyFragment().apply {
                    viewModel = TeamDetailViewModelImpl(teamsRepository)
                    teamEventsAdapter = TeamEventsAdapter()
                }
            }
        }

        val scenario = launchFragmentInContainer<TestMyFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        //VERIFY
        Espresso.onView((withId(R.id.title)))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView((withId(R.id.description)))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView((withId(R.id.image)))
            .check(ViewAssertions.matches(isDisplayed()))


    }

    class TestMyFragment : TeamDetailFragment() {
        override fun doInjection() {}
    }
}
