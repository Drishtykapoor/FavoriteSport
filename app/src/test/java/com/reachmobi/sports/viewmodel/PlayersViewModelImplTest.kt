package com.reachmobi.sports.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.reachmobi.sports.repository.PlayersRepository
import com.reachmobi.sports.repository.pojo.AllPlayers
import com.reachmobi.sports.repository.pojo.Player
import com.reachmobi.sports.repository.viewstate.PlayersViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class PlayersViewModelImplTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var playersRepository: PlayersRepository

    private lateinit var underTest: PlayersViewModelImpl

    private val players = listOf(
        Player(
            idPlayer = "some-idPlayer",
            idTeam = "some-idTeam",
            strPlayer = "some-strPlayer",
            strThumb = "some-strThumb"
        )
    )

    private val expectedSuccessResponse = PlayersViewState.Success(AllPlayers(players))

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        underTest = PlayersViewModelImpl(playersRepository)
    }

    private suspend fun setMockSuccessResponse() {
        Mockito.`when`(playersRepository.getAllTeamPlayers(players[0].idTeam)).thenReturn(
            Response.success(AllPlayers(players))
        )
    }

    @Test
    fun whenGetDataThenGetDataIsCalledOnRepo() {
        runTest {
            val testDispatcher = UnconfinedTestDispatcher(testScheduler)
            Dispatchers.setMain(testDispatcher)
            setMockSuccessResponse()
            val observer = MyObserver()
            val data = underTest.getAllTeamPlayers()
            data.observeForever(observer)
            underTest.getData(players[0].idTeam)
            assertEquals(expectedSuccessResponse, observer.actualResponse)
            data.removeObserver(observer)
        }
    }

    class MyObserver : Observer<PlayersViewState> {
        var actualResponse: PlayersViewState? = null

        override fun onChanged(value: PlayersViewState) {
            actualResponse = value
        }
    }

}