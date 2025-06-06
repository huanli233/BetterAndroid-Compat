/*
 * Better Android - Create more useful tool extensions for Android.
 * Copyright (C) 2019 HighCapable
 * https://github.com/BetterAndroid/BetterAndroid
 *
 * Apache License Version 2.0
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This file is created by fankes on 2023/12/4.
 */
@file:Suppress("unused")

package com.highcapable.betterandroid.compose.extension.ui

import androidx.compose.foundation.Indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import androidx.compose.foundation.clickable as foundationClickable
import androidx.compose.foundation.combinedClickable as foundationCombinedClickable
import androidx.compose.foundation.selection.selectable as foundationSelectable
import androidx.compose.foundation.selection.toggleable as foundationToggleable

/**
 * Draw content with [disabledAlpha] if [enabled] is false.
 * @receiver [Modifier]
 * @param enabled whether the content is enabled.
 * @param disabledAlpha the alpha value when the content is disabled, default is 0.5f.
 * @return [Modifier]
 */
fun Modifier.componentState(
    enabled: Boolean,
    disabledAlpha: Float = 0.5f
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "componentState"
        properties["enabled"] = enabled
        properties["disabledAlpha"] = disabledAlpha
    }
) { if (enabled) this else alpha(disabledAlpha) }

/**
 * Extension for [foundationClickable].
 * @see foundationClickable
 * @receiver [Modifier]
 * @return [Modifier]
 */
fun Modifier.clickable(
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "clickable"
        properties["interactionSource"] = interactionSource
        properties["indication"] = indication
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    val rememberInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    foundationClickable(
        interactionSource = rememberInteractionSource,
        indication = indication,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onClick = onClick
    )
}

/**
 * Extension for [foundationCombinedClickable].
 * @see foundationCombinedClickable
 * @receiver [Modifier]
 * @return [Modifier]
 */
fun Modifier.combinedClickable(
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = null,
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onLongClickLabel: String? = null,
    onLongClick: (() -> Unit)? = null,
    onDoubleClick: (() -> Unit)? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "combinedClickable"
        properties["interactionSource"] = interactionSource
        properties["indication"] = indication
        properties["enabled"] = enabled
        properties["onClickLabel"] = onClickLabel
        properties["role"] = role
        properties["onLongClickLabel"] = onLongClickLabel
        properties["onLongClick"] = onLongClick
        properties["onDoubleClick"] = onDoubleClick
        properties["onClick"] = onClick
    }
) {
    val rememberInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    foundationCombinedClickable(
        interactionSource = rememberInteractionSource,
        indication = indication,
        enabled = enabled,
        onClickLabel = onClickLabel,
        role = role,
        onLongClickLabel = onLongClickLabel,
        onLongClick = onLongClick,
        onDoubleClick = onDoubleClick,
        onClick = onClick
    )
}

/**
 * Extension for [foundationToggleable].
 * @see foundationToggleable
 * @receiver [Modifier]
 * @return [Modifier]
 */
fun Modifier.toggleable(
    value: Boolean,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = null,
    enabled: Boolean = true,
    role: Role? = null,
    onValueChange: (Boolean) -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "toggleable"
        properties["value"] = value
        properties["interactionSource"] = interactionSource
        properties["indication"] = indication
        properties["enabled"] = enabled
        properties["role"] = role
        properties["onValueChange"] = onValueChange
    }
) {
    val rememberInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    foundationToggleable(
        value = value,
        interactionSource = rememberInteractionSource,
        indication = indication,
        enabled = enabled,
        role = role,
        onValueChange = onValueChange
    )
}

/**
 * Extension for [foundationSelectable].
 * @see foundationSelectable
 * @receiver [Modifier]
 * @return [Modifier]
 */
fun Modifier.selectable(
    selected: Boolean,
    interactionSource: MutableInteractionSource? = null,
    indication: Indication? = null,
    enabled: Boolean = true,
    role: Role? = null,
    onClick: () -> Unit
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "selectable"
        properties["selected"] = selected
        properties["interactionSource"] = interactionSource
        properties["indication"] = indication
        properties["enabled"] = enabled
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    val rememberInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    foundationSelectable(
        selected = selected,
        interactionSource = rememberInteractionSource,
        indication = indication,
        enabled = enabled,
        role = role,
        onClick = onClick
    )
}

/**
 * Returns a callback to add haptic feedback on touch or click event.
 *
 * Usage:
 *
 * ```kotlin
 * Box(
 *     modifier = Modifier.combinedClickable(
 *         onLongClick = HapticFeedback {
 *             // Do something.
 *         }
 *     )
 * ) {
 *     Text("Long Click Me")
 * }
 * ```
 * @param type the haptic feedback type, default is [HapticFeedbackType.LongPress].
 * @param handler handle the event.
 * @return () -> [Unit]
 */
@Composable
fun HapticFeedback(
    type: HapticFeedbackType = HapticFeedbackType.LongPress,
    handler: () -> Unit
): () -> Unit {
    val hapticFeedback = LocalHapticFeedback.current
    return {
        handler()
        hapticFeedback.performHapticFeedback(type)
    }
}