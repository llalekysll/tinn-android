package com.example.tinn.ui.features.authorization

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.tinn.data.modelForJSON.Gender
import com.example.tinn.ui.components.*
import com.example.tinn.ui.components.calendarView.CalendarAlertDialog
import com.example.tinn.ui.components.spinner.GenderSpinner
import com.example.tinn.ui.components.textFields.TextFieldDate
import com.example.tinn.ui.components.textFields.TextFieldPhoneNumber
import com.example.tinn.ui.components.textFields.TextFieldsWithLabelError
import com.example.tinn.ui.navigation.Screens
import com.example.tinn.ui.theme.Blue
import com.example.tinn.ui.theme.Gray
import com.example.tinn.utils.*
import com.example.tinn.viewModel.UserViewModel
import java.util.*

typealias status = StatusRequestFactory.StatusType

@Composable
fun UserInputInfoScreen(navController: NavController) {
    val viewModel: UserViewModel = viewModel()
    val state by viewModel.requestStatus.observeAsState()

    LaunchedEffect(key1 = Unit, block = {
        viewModel.getGender()
    })

    if (state?.status == status.SUCCESS && state?.key == "putUser") {
        navController.navigate(Screens.Main.route) {
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state?.status == status.LOADING) CircularProgressIndicator()
        if (state?.status == status.SUCCESS && state?.key == "gender") {

            var login by remember { mutableStateOf("") }
            var firstName by remember { mutableStateOf("") }
            var secondName by remember { mutableStateOf("") }
            var sex by remember { mutableStateOf<Gender?>(null) }
            var phone by remember { mutableStateOf("") }

            var dateOfBirth by remember { mutableStateOf<Calendar?>(null) }
            var calendarAlertIsVisible by remember { mutableStateOf(false) }

            CalendarAlertDialog(
                isVisible = calendarAlertIsVisible,
                hideAlertDialog = { calendarAlertIsVisible = false },
                changeSelectedDate = { dateOfBirth = it }
            )

            Text(
                text = "???????????????????? ??????????????????????",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            TextFieldsWithLabelError(
                value = login,
                onValueChange = { login = it },
                modifier = Modifier.fillMaxWidth(),
                labelText = "??????????"
            )

            TextFieldsWithLabelError(
                value = firstName,
                onValueChange = { firstName = it },
                modifier = Modifier.fillMaxWidth(),
                labelText = "??????",
            )

            TextFieldsWithLabelError(
                value = secondName,
                onValueChange = { secondName = it },
                modifier = Modifier.fillMaxWidth(),
                labelText = "??????????????",
            )

            GenderSpinner(
                items = state!!.body as List<Gender>,
                hint = sex?.title ?: "???????????????? ??????",
                borderColor = Gray,
                onClick = { sex = it }
            )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                TextFieldPhoneNumber(
                    value = phone,
                    modifier = Modifier.width(200.dp),
                    onValueChange = { phone = it },
                )

                TextFieldDate(
                    label = "???????? ????????????????",
                    modifier = Modifier.width(150.dp).padding(top = 6.dp),
                    currentDate = dateOfBirth
                ) {
                    calendarAlertIsVisible = true
                }
            }
            val annotatedString = buildAnnotatedString {
                append("?????????????? ???? ???????????? '??????????????????????' ???? ?????????? ???????????????? ???? ")
                pushStringAnnotation("?????????????????? ???????????????????????? ????????????", "https://tinn.io/main")
                withStyle(SpanStyle(color = Blue)) {
                    append("?????????????????? ???????????????????????? ????????????")
                }
            }

            ClickableText(
                text = annotatedString,
                modifier = Modifier.fillMaxWidth(),
                onClick = { offset ->
                    annotatedString.getStringAnnotations(
                        "?????????????????? ???????????????????????? ????????????",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {

                    }
                }
            )

            AppButton(
                onClick = {
                    viewModel.putUserInfo(
                        login,
                        firstName,
                        secondName,
                        sex!!.id,
                        phone,
                        dateOfBirth!!,
                    )
                },
                modifier = Modifier.padding(top = 16.dp),
                enabled = login.isNotEmpty()
                        && firstName.isNotEmpty()
                        && secondName.isNotEmpty()
                        && sex != null
                        && phone.length == 10
                        && dateOfBirth != null,
                text = "??????????????????????"
            )
        }
    }
}