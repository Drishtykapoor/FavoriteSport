package com.reachmobi.sports.repository

import com.reachmobi.sports.repository.pojo.AllPlayers
import com.reachmobi.sports.repository.pojo.Player
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response


@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class PlayersRepositoryImplTest {

    @Mock
    lateinit var sportsService: SportsService

    private lateinit var underTest: PlayersRepositoryImpl

    private val players = listOf(
        Player(
            idPlayer = "some-idPlayer",
            idTeam = "some-idTeam",
            strPlayer = "some-strPlayer",
            strThumb = "some-strThumb"
        )
    )

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        underTest = PlayersRepositoryImpl(sportsService)
    }


    private suspend fun setMockResponse() {
        Mockito.`when`(sportsService.getAllTeamPlayers(players[0].idTeam)).thenReturn(
            Response.success(AllPlayers(players))
        )
    }

    @Test
    fun whenGetDataThenCallViewWithData() {
        runTest {
            setMockResponse()
            val response = underTest.getAllTeamPlayers(players[0].idTeam, 1)
            assertEquals(players, response.body()!!.player)
        }
    }

}