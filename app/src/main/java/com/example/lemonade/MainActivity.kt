package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeApplicationContainer()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApplicationContainer(){
    LemonadeTheme {
        var currentStep by remember { mutableIntStateOf(1) }
        var sqeezeCount by remember { mutableIntStateOf(0) }
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Lemonade",
                            fontWeight = FontWeight.ExtraBold
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan)
                )
            }
        ){
                innerpadding ->
            Surface(modifier = Modifier.fillMaxSize().padding(innerpadding).background(MaterialTheme.colorScheme.tertiaryContainer),
                color = MaterialTheme.colorScheme.background) {

                when(currentStep){
                    1 -> LemonadeWithTextAndImage(modifier = Modifier.fillMaxSize().wrapContentSize(),
                        R.drawable.lemon_tree,R.string.lemon_tree,
                        R.string.LemonTree,
                        {
                            currentStep = 2
                            sqeezeCount =(2..4).random()
                        })
                    2-> LemonadeWithTextAndImage(modifier = Modifier.fillMaxSize().wrapContentSize(),
                        R.drawable.lemon_squeeze,R.string.squeeze_it,
                        R.string.Lemon,
                        {
                            sqeezeCount--
                            if(sqeezeCount==0){
                                currentStep =3
                            }
                        })
                    3->LemonadeWithTextAndImage(modifier = Modifier.fillMaxSize().wrapContentSize(),
                        R.drawable.lemon_drink,R.string.drink_it,
                        R.string.Glass_of_lemonade,
                        {
                            currentStep = 4
                        })
                    4 ->LemonadeWithTextAndImage(modifier = Modifier.fillMaxSize().wrapContentSize(),
                        R.drawable.lemon_restart,R.string.start_again,
                        R.string.Empty_glass,
                        {
                            currentStep = 1
                        })
                }
            }

        }
    }
}
@SuppressLint("ResourceType")
@Composable
fun LemonadeWithTextAndImage(modifier: Modifier = Modifier,
             drawableResourceId : Int,
             textResourceId :Int,
             contentDesc : Int,
             onImageClick : () -> Unit) {
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Button(onClick = onImageClick,
            modifier=Modifier.border(
                width = 2.dp,
                color = Color.Cyan,
                shape = RoundedCornerShape(40.dp)
                ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = stringResource(contentDesc),
                modifier = Modifier
                    .width(dimensionResource(R.dimen.button_image_width))
                    .height(dimensionResource(R.dimen.button_image_height)),

            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textResourceId),
            fontSize = 10.sp,
            style = MaterialTheme.typography.bodyLarge,
            fontStyle = FontStyle.Italic

        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
    LemonadeApplicationContainer()
}