package com.reachmobi.sports.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import com.reachmobi.sports.repository.viewstate.PlayerDetailViewState
import javax.inject.Inject

class PlayerDetailComposeHelper @Inject constructor(private val descriptionComposeHelper: DescriptionComposeHelper) {


    @Composable
    fun playerDetailCard(data: LiveData<PlayerDetailViewState>) {

        val fetchedData by data.observeAsState(PlayerDetailViewState.Loading)
        val title = ""
        val description = ""
        when (fetchedData) {
            PlayerDetailViewState.Empty -> {

            }
            is PlayerDetailViewState.Error -> {
                (fetchedData as PlayerDetailViewState.Error).error?.let {
                    descriptionComposeHelper.errorCase(
                        error = it
                    )
                }
            }
            PlayerDetailViewState.Loading -> {

            }
            is PlayerDetailViewState.Success -> {
                val title =
                    (fetchedData as PlayerDetailViewState.Success).allTeamsResponse.player[0].strPlayer

                val description =
                    (fetchedData as PlayerDetailViewState.Success).allTeamsResponse.player[0].strDescriptionEN
                        ?: ""

                descriptionComposeHelper.successCase(title = title!!, description = description)
            }
        }
    }

}
//true -> PlayerDetailViewState.Success(AllPlayers(Player(strPlayer = "")))

//val response = ""
//
//        if (fetchedData.isSuccessful) {
//            if (response.body() != null) {
//                val data = response.body()!!.teams
//                if (data.isEmpty()) {
//                    fetchedData.value = PlayerDetailViewState.Empty
//                } else {
//                    fetchedData.value = PlayerDetailViewState.Success(response.body()!!)
//                }
//            } else fetchedData.value = PlayerDetailViewState.Empty
//        } else {
//            fetchedData.value = PlayerDetailViewState.Error(response.message())
//        }


//
//        Scaffold {
//            topBar = {
//                TopAppBar(title = {
//                    Text("My App")
//                })
//            })
//            {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    if (fetchedData.isEmpty()) {
//                        Text(text = "Loading..")
//                    } else {
//                        if (fetchedData.isLoading()) {
//                            Text(text = item.name)
//                        }
//                    }
//                }
//            }
//        }

//        Row(modifier = Modifier.padding(all = 8.dp)) {
//
//            // Add a horizontal space between the image and the column
//            Spacer(modifier = Modifier.width(8.dp))
//
//            Column {
//                Text(text = item.strPlayer)
//                // Add a vertical space between the author and message texts
//                Spacer(modifier = Modifier.height(4.dp))
//                Text(text = item.strDescriptionEN)
//            }
//        }
