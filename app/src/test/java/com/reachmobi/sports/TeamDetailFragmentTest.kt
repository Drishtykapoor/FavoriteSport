package com.reachmobi.sports

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.reachmobi.sports.adapter.TeamEventsAdapter
import com.reachmobi.sports.repository.TeamsRepositoryImpl
import com.reachmobi.sports.repository.TeamsService
import com.reachmobi.sports.view.TeamDetailFragment
import com.reachmobi.sports.viewmodel.TeamDetailViewModelImpl
import io.mockk.mockk
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TeamDetailFragmentTest {

    @Test
    fun test_isTeamsDataVisible() {

        val teamsService = mockk<TeamsService>()

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
