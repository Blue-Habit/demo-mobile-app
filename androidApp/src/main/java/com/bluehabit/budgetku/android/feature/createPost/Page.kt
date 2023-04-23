/*
 * Copyright © 2023 Blue Habit.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.bluehabit.budgetku.android.feature.createPost

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.bluehabit.budgetku.android.ApplicationState
import com.bluehabit.budgetku.android.R
import com.bluehabit.budgetku.android.base.UIWrapper
import com.bluehabit.budgetku.android.components.bottomSheet.BottomSheetSelectPostVisibility
import com.bluehabit.budgetku.android.components.button.ButtonOutlinedPrimary
import com.bluehabit.budgetku.android.components.button.ButtonPrimary
import com.bluehabit.budgetku.android.components.input.MultilineHintTextField
import com.bluehabit.budgetku.android.ui.Grey300
import com.bluehabit.budgetku.android.ui.Grey900

object CreatePost {
    const val routeName = "CreatePost"
}

enum class PostVisibility {
    PUBLIC, ONLY_ME, ONLY_FOLLOWING, ONLY_MENTIONED
}

fun NavGraphBuilder.routeCreatePost(
    state: ApplicationState,
) {
    composable(CreatePost.routeName) {
        ScreenCreatePost(appState = state)
    }
}

@Composable
fun ScreenCreatePost(
    appState : ApplicationState,
) = UIWrapper<CreatePostViewModel>(appState = appState) {
    val state by uiState.collectAsState()
    with(appState) {
        setupBottomSheet {
            BottomSheetSelectPostVisibility(
                postVisibility = state.postVisibility,
                onSubmit = {
                    dispatch(CreatePostEvent.ChangePostVisibility(it))
                    hideBottomSheet()
                },
                onDismiss = { hideBottomSheet() },
                onVisibilitySelected = {
                    dispatch(CreatePostEvent.ChangePostVisibility(it))
                }
            )
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            TopAppBarCreatePost(
                isSubmitEnabled = state.isSubmitEnabled
            )
            ProfileCreatePost(
                postVisibility = state.postVisibility,
                profileName = state.profileName,
                onChangeVisibility = { showBottomSheet() }
            )
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp, vertical = 24.dp),
            ) {
                item {
                    ContentCreatePost(
                        hintText = stringResource(id = R.string.text_content_placeholder_create_post),
                        contextText = state.contentText,
                        onValueChanged = {
                            dispatch(CreatePostEvent.ChangeContentText(it))
                        }
                    )
                }
            }
        }
        Column(
            modifier = Modifier
                .background(Color.White)
                .align(Alignment.BottomCenter),
        ) {
            Divider(thickness = 1.dp, color = Grey300)
            BottomAppBarCreatePost()
        }

    }




}

@Composable
fun TopAppBarCreatePost(
    isSubmitEnabled: Boolean = false,
    onDismiss: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.clickable { onDismiss() },
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = ""
        )
        ButtonPrimary(
            modifier = Modifier,
            text = stringResource(id = R.string.text_button_submit_create_post),
            textStyle = MaterialTheme.typography.subtitle2,
            enabled = isSubmitEnabled,
            fullWidth = false,
            height = 33.dp,
            onClick = {  }
        )
    }
}

@Composable
fun BottomAppBarCreatePost(
    onAction: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.clickable {  },
                painter = painterResource(id = R.drawable.ic_image),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.clickable {  },
                painter = painterResource(id = R.drawable.ic_attachment),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.clickable {  },
                painter = painterResource(id = R.drawable.ic_calculator),
                contentDescription = ""
            )
            Image(
                modifier = Modifier.clickable {  },
                painter = painterResource(id = R.drawable.ic_graph),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun ProfileCreatePost(
    postVisibility: PostVisibility = PostVisibility.PUBLIC,
    profileName: String = "",
    onChangeVisibility: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .height(48.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = R.drawable.ic_profile_placeholder),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = profileName,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            val textButton = when(postVisibility) {
                PostVisibility.PUBLIC -> stringResource(id = R.string.text_bottom_sheet_public_create_post)
                PostVisibility.ONLY_ME -> stringResource(id = R.string.text_bottom_sheet_only_me_create_post)
                PostVisibility.ONLY_FOLLOWING -> stringResource(id = R.string.text_bottom_sheet_only_follow_create_post)
                PostVisibility.ONLY_MENTIONED -> stringResource(id = R.string.text_bottom_sheet_only_mention_create_post)
            }
            ButtonOutlinedPrimary(
                text = textButton,
                textColor = Grey900,
                textStyle = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Normal,
                fullWidth = false,
                height = 25.dp,
                borderColor = Grey300,
                contentPaddingValues = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                trailingIcon = R.drawable.ic_arrow_short_down,
                onClick = onChangeVisibility
            )
        }
    }
}

@Composable
fun ContentCreatePost(
    hintText: String = "",
    attachments: List<Uri> = listOf(),
    contextText: String = "",
    onValueChanged: (String) -> Unit = {},
) {
    Column(
        modifier = Modifier,
    ) {
        MultilineHintTextField(
            value = contextText,
            onValueChanged = {
                onValueChanged(it)
            },
            hintText = hintText
        )
        Spacer(modifier = Modifier.height(24.dp))
        /*
        * FOR COMPOSE PREVIEW ONLY
        * ACTUAL IMPLEMENTATION:
        * - preview multiple attachments
        * - add and remove images functionality
        * */

        // if the attachment is an image
        Image(
            modifier = Modifier
                .size(335.dp)
                .clip(RoundedCornerShape(16.dp)),
            painter = painterResource(id = R.drawable.dummy_post_pic),
            contentDescription = ""
        )

        // if the attachment is a video or a file

    }
}