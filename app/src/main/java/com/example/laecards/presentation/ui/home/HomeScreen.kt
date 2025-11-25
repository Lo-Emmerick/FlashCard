package com.example.laecards.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laecards.data.model.Card
import com.laecards.app.R
import androidx.compose.material3.Card

@Composable
fun HomeScreen(
    state: HomeState,
    onAddClick: () -> Unit,
    onCardClick: (Card) -> Unit,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 80.dp)
    ) {
        HomeHeader(onAddClick)

        Spacer(modifier = Modifier.height(20.dp))

        when (state) {
            HomeState.Loading -> LoadingContent()
            HomeState.Empty -> EmptyContent()
            HomeState.Error -> ErrorContent(onRetry)
            is HomeState.Success -> SuccessContent(list = state.result, onCardClick = onCardClick)
        }
    }
}

@Composable
fun HomeHeader(onAddClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Text(
                text = "Bem vindo(a),",
                fontSize = 35.sp
            )

            Text(
                text = "LaeCards !",
                fontSize = 35.sp,
            )

            Spacer(modifier = Modifier.height(35.dp))

            Text(
                text = "Que tal estudar hoje ?!",
                fontSize = 20.sp
            )
        }

        IconButton(
            onClick = onAddClick,
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.BottomEnd)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFDDA0DD),
                        shape = androidx.compose.foundation.shape.CircleShape
                    )
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_add_24),
                    contentDescription = "Adicionar",
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.vector),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Você ainda não tem cards criados",
            color = Color(0xFF6B6572),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Crie cards e potencialize seus estudos",
            color = Color(0xFF6B6572),
            fontSize = 14.sp
        )
    }
}

@Composable
fun ErrorContent(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Erro ao trazer os cards",
            color = Color(0xFF6B6572),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Tente novamente mais tarde",
            color = Color(0xFF6B6572),
            fontSize = 14.sp
        )
    }
}

@Composable
fun SuccessContent(list: List<Card>, onCardClick: (Card) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(0.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(list) { card ->
            CardItem(card = card, onClick = { onCardClick(card) })
        }
    }
}

@Composable
fun LoadingContent() {
    Column {
        repeat(3) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Box(
                    modifier = Modifier
                        .size(170.dp, 150.dp)
                        .background(Color.LightGray.copy(alpha = 0.3f))
                )

                Box(
                    modifier = Modifier
                        .size(170.dp, 150.dp)
                        .background(Color.LightGray.copy(alpha = 0.3f))
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CardItem(card: Card, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(card.first_text, fontWeight = FontWeight.Bold)
            Text(card.second_text)
        }
    }
}
